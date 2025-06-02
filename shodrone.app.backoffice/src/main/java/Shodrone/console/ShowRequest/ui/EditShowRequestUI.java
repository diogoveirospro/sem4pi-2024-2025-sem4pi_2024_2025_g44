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
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EditShowRequestUI extends AbstractFancyUI {

    private final EditShowRequestController controller = new EditShowRequestController();
    private final CustomerPrinter printer = new CustomerPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        try {
            if (!authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: Unauthorized User" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
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
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nShow Request information updated successfully!" + UtilsUI.RESET);
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
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        int option;
        do {
            ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer\n", customerList, printer);
            customerListWidget.show();
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled.\n" + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
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

        int option;
        do {
            ListWidget<ShowRequest> showRequestListWidget = new ListWidget<>("\nChoose a Show Request\n", showRequestList, new ShowRequestPrinter());
            showRequestListWidget.show();
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

    private ShowDescription enterValidShowDescription() {
        String showDescription;
        do {
            try {
                showDescription = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's description (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(showDescription)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new ShowDescription(showDescription);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid description. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private Location enterValidLocation() {
        double latitude;
        double longitude;
        double altitude;
        do {
            try {
                do {
                    latitude = Double.parseDouble(Objects.requireNonNull(UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the latitude (-90 to 90) (or type 'cancel' to go back): " + UtilsUI.RESET)));
                    if (latitude < -90 || latitude > 90) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid latitude. Must be between -90 and 90.\n" + UtilsUI.RESET);
                        continue;
                    }
                    break;
                } while (true);

                do {
                    longitude = Double.parseDouble(Objects.requireNonNull(UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the longitude (-180 to 180) (or type 'cancel' to go back): " + UtilsUI.RESET)));
                    if (longitude < -180 || longitude > 180) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid longitude. Must be between -180 and 180.\n" + UtilsUI.RESET);
                        continue;
                    }
                    break;
                } while (true);

                do {
                    altitude = Double.parseDouble(Objects.requireNonNull(UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the altitude (>= 0) (or type 'cancel' to go back): " + UtilsUI.RESET)));
                    if (altitude < 0) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid altitude. Must be non-negative.\n" + UtilsUI.RESET);
                        continue;
                    }
                    break;
                } while (true);

                return new Location(latitude, longitude, altitude);
            } catch (NumberFormatException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please enter a valid number.\n" + UtilsUI.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }


    private LocalDate enterValidDate() {
        String date;
        LocalDate lDate;
        do {
            try {
                date = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's date (dd-mm-yyyy) (or type 'cancel' to go back): " + UtilsUI.RESET);

                assert date != null;
                if ("cancel".equalsIgnoreCase(date)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                lDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

                if (!lDate.isAfter(LocalDate.now())) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nThe date must be in the future. Please try again." + UtilsUI.RESET);
                    continue;
                }

                return lDate;
            } catch (DateTimeParseException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid date. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private LocalTime enterValidTime() {
        String time;
        LocalTime lTime;
        do {
            try {
                time = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's time(hh:mm) (or type 'cancel' to go back): " + UtilsUI.RESET);
                assert time != null;
                if ("cancel".equalsIgnoreCase(time)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                lTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));

                return lTime;
            } catch (DateTimeParseException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid time. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private QuantityOfDrones enterValidQuantityOfDrones() {
        String qDrones;
        do {
            try {
                qDrones = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the new show's quantity of drones (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(qDrones)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new QuantityOfDrones(qDrones);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid quantity of drones. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }
}
