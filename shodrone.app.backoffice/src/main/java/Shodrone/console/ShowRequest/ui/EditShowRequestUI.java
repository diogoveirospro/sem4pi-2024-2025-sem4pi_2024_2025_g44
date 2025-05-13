package Shodrone.console.ShowRequest.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.ShowRequest.printer.ShowRequestPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.domain.Entities.Customer;
import core.ShowRequest.application.EditShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class EditShowRequestUI extends AbstractFancyUI {

    private final EditShowRequestController controller = new EditShowRequestController();

    @Override
    protected boolean doShow() {
        try {
            Customer customer = getCustomer();
            if (customer == null) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer selected. Operation canceled." + UtilsUI.RESET);
                return false;
            }

            ShowRequest showRequest = getShowRequest(customer);
            if (showRequest == null) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No show request selected. Operation canceled." + UtilsUI.RESET);

                return false;
            }

            // info to change

           // controller.changeShowRequestInfo();
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Show Request information updated successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    @Override
    public String headline() {
        return null;
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

    private ShowRequest getShowRequest(Customer customer) {
        Iterable<ShowRequest> showRequests = controller.listShowRequestWithoutProposal(customer);
        if (showRequests == null || !showRequests.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No show requests available." + UtilsUI.RESET);
            return null;
        }

        List<ShowRequest> showRequestList = new ArrayList<>();
        showRequests.forEach(showRequestList::add);

        ListWidget<ShowRequest> showRequestListWidget = new ListWidget<>("Choose a Show Request", showRequestList, new ShowRequestPrinter());
        showRequestListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(showRequestList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return showRequestList.get(option);
            }
        } while (true);
    }
}
