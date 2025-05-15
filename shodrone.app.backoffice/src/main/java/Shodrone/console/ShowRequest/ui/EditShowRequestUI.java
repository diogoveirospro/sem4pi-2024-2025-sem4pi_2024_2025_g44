package Shodrone.console.ShowRequest.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.console.ShowRequest.printer.ShowRequestPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.EditShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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

            ShowDescription showDescription = enterValidShowDescription();
            Location location = enterValidLocation();
            LocalDate date = enterValidDate();
            LocalTime time = enterValidTime();
            QuantityOfDrones quantityOfDrones = enterValidQuantityOfDrones();

            controller.editShowRequest(showRequest, location, showDescription, date, time, quantityOfDrones);
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Show Request information updated successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            System.out.println(e.getMessage());
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    @Override
    public String headline() {
        return "Edit Show Request";
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

    private ShowDescription enterValidShowDescription()
    {
        String showDescription;
        do {
            try {
                showDescription = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's description (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(showDescription)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new ShowDescription(showDescription);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid description. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private Location enterValidLocation()
    {
        String location;
        do {
            try {
                location = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's location (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(location)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new Location(location);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid location. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }


    private LocalDate enterValidDate()
    {
        String date;
        LocalDate lDate;
        do {
            try {
                date = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's date(dd-mm-yyyy) (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(date)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                return lDate;
            } catch (DateTimeParseException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid date. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private LocalTime enterValidTime()
    {
        String time;
        LocalTime lTime;
        do {
            try {
                time = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's time(hh:mm) (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(time)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                lTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

                return lTime;
            } catch (DateTimeParseException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid time. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private QuantityOfDrones enterValidQuantityOfDrones()
    {
        String qDrones;
        do {
            try {
                qDrones = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's quantity of drones (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(qDrones)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new QuantityOfDrones(qDrones);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid quantity of drones. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
}
