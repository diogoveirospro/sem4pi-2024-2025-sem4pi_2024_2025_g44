package Shodrone.console.ShowRequest.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.ShowRequest.printer.ShowRequestPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.domain.Entities.Customer;
import core.ShowRequest.application.ListShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.AbstractFancyListUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ListShowRequestsUI extends AbstractFancyListUI<ShowRequest> {

    private final ListShowRequestController controller = new ListShowRequestController();

    @Override
    protected boolean doShow() {
       try {
        super.doShow();
           UtilsUI.goBackAndWait();
           return true;
       } catch (UserCancelledException e) {
           System.out.println(e.getMessage());
           UtilsUI.goBackAndWait();
           return false;
       }
    }

    @Override
    protected Iterable<ShowRequest> elements() {
        Customer customer = getCustomer();
        if (customer == null) {
            return new ArrayList<>();
        }
        return controller.listShowRequest(customer);
    }

    @Override
    protected Visitor<ShowRequest> elementPrinter() {
        return new ShowRequestPrinter();
    }

    @Override
    protected String elementName() {
        return "Show Request";
    }

    @Override
    protected String listHeader() {
        return "List of Show Requests";
    }

    @Override
    protected String emptyMessage() {
        return (UtilsUI.RED + UtilsUI.BOLD + "No show request available." + UtilsUI.RESET);
    }

    @Override
    public String headline() {
        return "List Show Requests";
    }

    private Customer getCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer", customerList, new CustomerPrinter());
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return customerList.get(option);
            }
        } while (true);
    }
}