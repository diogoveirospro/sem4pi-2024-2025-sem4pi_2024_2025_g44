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

import com.github.lalyos.jfiglet.FigletFont;
import core.Persistence.Application;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.infrastructure.pubsub.PubSubRegistry;
import eapli.framework.infrastructure.pubsub.impl.inprocess.service.InProcessPubSub;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * A base class for all console applications.
 */
public abstract class ShodroneBaseApplication {

    private static final Logger LOGGER = LogManager.getLogger(ShodroneBaseApplication.class);

    /**
     * The main method of the application. This method is the entry point of the application.
     * @param args the command line arguments
     */
    public void run(final String[] args) {
        configure();
        UtilsUI.clearConsole();
        printHeader();

        try {
            setupEventHandlers();
            doMain(args);

            printFooter();
        } catch (final Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Something unexpected has happened and the application will " +
                    "terminate. Please check the logs.\n" + UtilsUI.RESET);
            LOGGER.error(e);
        } finally {
            clearEventHandlers();
        }

        // exiting the application, closing all threads
        System.exit(0);
    }

    /**
     * Prints the footer of the application.
     */
    protected void printFooter() {
        String footer = "\n" + UtilsUI.BG_BLUE + randomFarewellMessage() + UtilsUI.RESET + "\n";
        animatedPrint(footer);
    }

    /**
     * Returns a random farewell message.
     * @return a random farewell message
     */
    private String randomFarewellMessage() {
        String[] messages = {
                "Thank you for flying with Shodrone!",
                "Lights out, drones grounded. See you soon!",
                "You've completed your mission. Shodrone signing off.",
                "Another flight logged. Until next time!",
                "Shodrone: where code meets the sky. Goodbye!"
        };
        return messages[new Random().nextInt(messages.length)];
    }

    /**
     * Prints a message with an animation effect.
     *
     * @param message the message to print
     */
    private void animatedPrint(String message) {
        for (char c : message.toCharArray()) {
            System.out.print(c);
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    /**
     * Prints the header of the application.
     */
    protected void printHeader() {
        try {
            String titulo = FigletFont.convertOneLine(appTitle());

            System.out.flush();

            System.out.println(UtilsUI.BLUE + UtilsUI.BOLD + titulo + UtilsUI.RESET);
            System.out.println(UtilsUI.BOLD + Application.VERSION);
            System.out.println(Application.COPYRIGHT + UtilsUI.RESET);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Loading animation
        loadingAnimation();
        try{
            // Small pause for dramatic effect
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Displays a loading animation while the application is initializing.
     */
    private void loadingAnimation() {
        Random random = new Random();
        String loadingMessage = " Initializing " + appTitle() + " ...";
        String[] loadingAnimations = {"|", "/", "-", "\\"};

        System.out.println();

        for (int i = 0; i < 20; i++) {

            System.out.print("\r" + UtilsUI.GREEN + loadingAnimations[i % loadingAnimations.length] +
                    loadingMessage + UtilsUI.RESET);
            try {
                TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(50));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println();
    }

    /**
     * Clears the event handlers and shuts down the dispatcher.
     */
    private void clearEventHandlers() {
        try {
            doClearEventHandlers(PubSubRegistry.dispatcher());

            PubSubRegistry.dispatcher().shutdown();
        } catch (final Exception e) {
            LOGGER.error("Unable to cleanup event handlers", e);
        }
    }

    /**
     * Sets up the event handlers for the application.
     */
    private void setupEventHandlers() {
        doSetupEventHandlers(PubSubRegistry.dispatcher());
    }

    /**
     * Configures the application. This method is called at the beginning of the application.
     */
    protected void configure() {
        configureAuthz();

        configurePubSub();
    }

    /**
     * Configures the pub/sub engine for the application.
     */
    protected void configurePubSub() {
        // TODO use a factory/registry to obtain the pub/sub engine
        /*
         * SimplePersistentPubSub.configure(PersistenceContext.repositories().
         * eventRecord(), PersistenceContext.repositories().eventConsumption(),
         * Application.settings().getProperty("eapli.framework.pubsub.instanceKey"),
         * Integer.valueOf(Application.settings().getProperty(
         * "eapli.framework.pubsub.poolInterval") ));
         * PubSubRegistry.configure(SimplePersistentPubSub.dispatcher(),
         * SimplePersistentPubSub.publisher());
         */
        // This is how they do it in the framework
        PubSubRegistry.configure(InProcessPubSub.dispatcher(), InProcessPubSub.publisher());
    }

    /**
     * Does the main work of the application. This method is called after the application has been configured.
     * @param args the command line arguments
     */
    protected abstract void doMain(String[] args);

    /**
     * Returns the title of the application.
     * @return the title of the application
     */
    protected abstract String appTitle();

    /**
     * Returns the goodbye message of the application.
     */
    protected abstract void configureAuthz();

    /**
     * Does the cleanup of the event handlers.
     * @param dispatcher the event dispatcher
     */
    protected void doClearEventHandlers(final EventDispatcher dispatcher) {
        // nothing to do
    }

    /**
     * Sets up the event handlers for the application.
     * @param dispatcher the event dispatcher
     */
    protected abstract void doSetupEventHandlers(EventDispatcher dispatcher);
}
