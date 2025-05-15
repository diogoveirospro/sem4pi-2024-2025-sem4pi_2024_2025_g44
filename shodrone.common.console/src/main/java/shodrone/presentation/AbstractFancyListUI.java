package shodrone.presentation;

import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;

/**
 * A generic abstract List UI class to avoid code duplication.
 * Most list UIs share a similar structure, so this base class
 * provides a reusable implementation using the Template Method pattern.
 *
 * @param <T> The type of elements to be listed in the UI
 */
public abstract class AbstractFancyListUI<T> extends AbstractFancyUI {

    /**
     * Provides the elements to be listed.
     *
     * @return An iterable collection of elements
     */
    protected abstract Iterable<T> elements();

    /**
     * Provides the visitor responsible for printing each element.
     *
     * @return A visitor for formatting and displaying elements
     */
    protected abstract Visitor<T> elementPrinter();

    /**
     * Provides the name of the type of elements being listed.
     * Used in the empty message when there are no elements.
     *
     * @return The name of the element type
     */
    protected abstract String elementName();

    /**
     * Provides the header displayed above the list.
     *
     * @return The list header string
     */
    protected abstract String listHeader();

    /**
     * Provides the message shown when there are no elements to display.
     *
     * @return A message for empty lists
     */
    protected abstract String emptyMessage();

    /**
     * Displays the list of elements.
     * If there are elements, shows them with a header using ListWidget.
     * Otherwise, prints a message stating that the list is empty.
     *
     * @return true if the operation completes successfully
     */
    @Override
    @SuppressWarnings("squid:S106") // Console output is allowed in UI layer
    protected boolean doShow() {
        final Iterable<T> elems = elements();
        if (!elems.iterator().hasNext()) {
            System.out.println(emptyMessage());
        } else {
            new ListWidget<>(listHeader(), elems, elementPrinter()).show();
        }
        return true;
    }
}
