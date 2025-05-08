package shodrone.presentation;

/**
 * A colorful abstract UI base class that defines the structure and appearance
 * of a user interface screen. Subclasses should implement their custom logic
 * via the {@code doShow()} and {@code headline()} methods.
 *
 * @author Diogo Pereira
 */
@SuppressWarnings("squid:S106")
public abstract class AbstractFancyUI {

    protected static final String BORDER = UtilsUI.BLUE + UtilsUI.BOLD +
            "+==============================================================================+" + UtilsUI.RESET;
    protected static final String SEPARATOR = UtilsUI.BRIGHT_BLUE +
            "+------------------------------------------------------------------------------+" + UtilsUI.RESET;

    /**
     * Subclasses should override this method to implement their custom UI logic.
     *
     * @return {@code true} if the user wants to exit this UI.
     */
    protected abstract boolean doShow();

    /**
     * Provides the headline for this UI screen.
     *
     * @return the screen title.
     */
    public abstract String headline();

    /**
     * Keeps the UI running until the user chooses to exit.
     */
    public void mainLoop() {
        boolean wantsToExit;
        do {
            wantsToExit = show();
        } while (!wantsToExit);
    }

    /**
     * Template Method: draws the UI layout and executes the user interaction.
     *
     * @return {@code true} if the user wants to exit this UI.
     */
    public boolean show() {
        UtilsUI.clearConsole();
        drawFormTitle();
        final boolean wantsToExit = doShow();
        drawFormBorder();
        return wantsToExit;
    }

    /**
     * Draws the form title with borders and colors.
     */
    protected void drawFormTitle() {
        System.out.println();
        drawFormTitle(headline().toUpperCase());
        System.out.println();
    }

    /**
     * Draws the bottom border of the form.
     */
    protected void drawFormBorder() {
        System.out.println();
        System.out.println(BORDER);
        System.out.println();
    }

    /**
     * Draws a separator between sections.
     */
    protected void drawFormSeparator() {
        System.out.println(SEPARATOR);
    }

    /**
     * Draws a title line centered within a border.
     *
     * @param title the title string
     */
    protected void drawFormTitle(final String title) {
        String centered = centerTextWithPadding(title, 78); // Adjust for border width
        System.out.println(BORDER);
        System.out.println(UtilsUI.BLUE + UtilsUI.BOLD + "|" + centered + "|" + UtilsUI.RESET);
        System.out.println(BORDER);
    }

    /**
     * Centers the given text within a field of a specified width.
     *
     * @param text  the text to center
     * @param width the total width available
     * @return the padded string
     */
    private String centerTextWithPadding(String text, int width) {
        int padding = (width - text.length()) / 2;
        return " ".repeat(Math.max(0, padding)) + text + " ".repeat(Math.max(0, width - padding - text.length()));
    }
}
