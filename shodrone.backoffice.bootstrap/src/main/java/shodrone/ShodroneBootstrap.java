/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */


package shodrone;

import core.Persistence.PersistenceContext;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.collections.util.ArrayPredicates;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.io.util.Console;
import plugins.PluginInitializer;
import shodrone.bootstrappers.Demo.ShodroneDemoBootstrapper;
import shodrone.bootstrappers.ShodroneBootstrapper;
import shodrone.bootstrappers.SmokeTests.ShodroneDemoSmokeTester;
import shodrone.presentation.UtilsUI;

import java.io.File;
import java.io.IOException;

/**
 * eCafeteria Bootstrapping data app
 */
@SuppressWarnings("squid:S106")
public final class ShodroneBootstrap extends ShodroneBaseApplication {

    private boolean isToBootstrapDemoData = true;
    private boolean isToRunSampleE2E;
    private boolean isToWaitInTheEnd;

    /**
     * avoid instantiation of this class.
     */
    private ShodroneBootstrap() {
    }

    public static void main(final String[] args) {
        PluginInitializer.initialize();
        new ShodroneBootstrap().run(args);
    }

    @Override
    protected void doMain(final String[] args) {
        handleArgs(args);

        ensureDatabaseFileExists();

        System.out.println("\n\n------- MASTER DATA -------");
        new ShodroneBootstrapper().execute();

        System.out.println("\n\n------- DEMO DATA -------");
        new ShodroneDemoBootstrapper().execute();

        if (isToRunSampleE2E) {
            System.out.println("\n\n------- BASIC SCENARIO -------");
            new ShodroneDemoSmokeTester().execute();
        }

        if (isToWaitInTheEnd) {
            Console.readLine("\n\n>>>>>> Enter to finish the program.");
        }
    }

    private void ensureDatabaseFileExists() {
        String dbFilePath = System.getProperty("user.home") + "/DB/shodrone.app.mv.db";
        File dbFile = new File(dbFilePath);

        if (!dbFile.exists()) {
            try {
                File dbDir = dbFile.getParentFile();
                if (!dbDir.exists()) {
                    dbDir.mkdirs();
                }
                dbFile.createNewFile();
                System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nDatabase file created in: " + dbFilePath + UtilsUI.RESET);
            } catch (IOException e) {
                System.err.println(UtilsUI.BOLD + UtilsUI.RED + "\nError creating the database file: " + e.getMessage() + UtilsUI.RESET);
            }
        } else {
            System.out.println(UtilsUI.BOLD + UtilsUI.GREEN + "\nDatabase file already exists in: " + dbFilePath + UtilsUI.RESET);
        }
    }

    private void handleArgs(final String[] args) {
        isToRunSampleE2E = ArrayPredicates.contains(args, "-smoke:e2e");
        if (isToRunSampleE2E) {
            isToBootstrapDemoData = true;
        } else {
            isToBootstrapDemoData = ArrayPredicates.contains(args, "-bootstrap:demo");
        }

        isToWaitInTheEnd = ArrayPredicates.contains(args, "-wait");
    }

    @Override
    protected String appTitle() {
        return "Shodrone Bootstrap";
    }


    @Override
    protected void configureAuthz() {
        AuthzRegistry.configure(PersistenceContext.repositories().users(), new ShodronePasswordPolicy(), new PlainTextEncoder());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doSetupEventHandlers(final EventDispatcher dispatcher) {
        //dispatcher.subscribe(new NewUserRegisteredFromSignupWatchDog(), NewUserRegisteredFromSignupEvent.class);
        //dispatcher.subscribe(new SignupAcceptedWatchDog(), SignupAcceptedEvent.class);
        //dispatcher.subscribe(new MealBookedWatchDog(), BookedEvent.class);
    }
}
