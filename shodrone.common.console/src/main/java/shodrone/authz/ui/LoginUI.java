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
package shodrone.authz.ui;

import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import shodrone.infrastructure.authz.CredentialHandler;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

/**
 * UI for user login action.
 *
 * @author nuno 21/03/16.
 */
@SuppressWarnings("squid:S106")
public class LoginUI extends AbstractFancyUI {

	private Role onlyWithThis;
	private static final int DEFAULT_MAX_ATTEMPTS = 3;
	private final int maxAttempts;

	private final CredentialHandler credentialHandler;

	public LoginUI(CredentialHandler credentialHandler) {
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis) {
		this.onlyWithThis = onlyWithThis;
		maxAttempts = DEFAULT_MAX_ATTEMPTS;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final Role onlyWithThis, final int maxAttempts) {
		this.onlyWithThis = onlyWithThis;
		this.maxAttempts = maxAttempts;
		this.credentialHandler = credentialHandler;
	}

	public LoginUI(CredentialHandler credentialHandler, final int maxAttempts) {
		this.maxAttempts = maxAttempts;
		this.credentialHandler = credentialHandler;
	}

	@Override
	protected boolean doShow() {
		var attempt = 1;
		while (attempt <= maxAttempts) {
			final String userName = UtilsUI.readNonEmptyLine(UtilsUI.BOLD + "Username: " + UtilsUI.RESET,
					UtilsUI.RED + UtilsUI.BOLD + "Please provide a Username" + UtilsUI.RESET);
			final String password = UtilsUI.readPassword(UtilsUI.BOLD + "Password: " + UtilsUI.RESET);

			if (credentialHandler.authenticated(userName, password, onlyWithThis)) {
				return true;
			}
			System.out.printf(UtilsUI.RED + UtilsUI.BOLD +
							"%nWrong username or password. You have %d attempts left.%n" + UtilsUI.RESET,
					maxAttempts - attempt);
			attempt++;
		}
		System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Sorry, we are unable to authenticate you. Please contact your " +
				"System Administrator." + UtilsUI.RESET);
		return false;
	}

	@Override
	public String headline() {
		return "Login";
	}
}
