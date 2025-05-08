/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package shodrone.bootstrappers;

import core.Persistence.PersistenceContext;
import core.User.domain.ShodroneRoles;
import core.User.domain.UserBuilderHelper;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

/**
 * eCafeteria Bootstrapping data. This class bootstraps Master/reference data of
 * the application.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class ShodroneBootstrapper implements Action {
	private static final Logger LOGGER = LogManager.getLogger(ShodroneBootstrapper.class);

	private static final String POWERUSER_PWD = "Password123";
	private static final String POWERUSER = "poweruser";

	private final AuthorizationService authz = AuthzRegistry.authorizationService();
	private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
	private final UserRepository userRepository = PersistenceContext.repositories().users();

	@Override
	public boolean execute() {
		// declare bootstrap actions
		final Action[] actions = {
				new MasterUsersBootstrapper()		};

		registerPowerUser(userRepository);
		authenticateForBootstrapping();

		// execute all bootstrapping
		var ret = true;
		for (final Action boot : actions) {
			System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
			ret &= boot.execute();
		}
		return ret;
	}

	/**
	 * Register a power user directly in the persistence layer as we need to
	 * circumvent authorizations in the Application Layer.
	 */
	public static boolean registerPowerUser(final UserRepository userRepository) {

		final var userBuilder = UserBuilderHelper.builder();
		userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_PWD).withName("joe", "power")
				.withEmail("joe@email.org").withRoles(ShodroneRoles.POWER_USER);
		final var newUser = userBuilder.build();

		try {
			final var poweruser = userRepository.save(newUser);
			assert poweruser != null;
			return true;
		} catch (ConcurrencyException | IntegrityViolationException e) {
			// ignoring exception. assuming it is just a primary key violation
			// due to the tentative of inserting a duplicated user
			LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
			LOGGER.trace("Assuming existing record", e);
			return false;
		}
	}

	/**
	 * authenticate a super user to be able to register new users
	 *
	 */
	protected void authenticateForBootstrapping() {
		authenticationService.authenticate(POWERUSER, POWERUSER_PWD);
		Invariants.ensure(authz.hasSession());
	}

	private String nameOfEntity(final Action boot) {
		final var name = boot.getClass().getSimpleName();
		return Strings.left(name, name.length() - "Bootstrapper".length());
	}
}
