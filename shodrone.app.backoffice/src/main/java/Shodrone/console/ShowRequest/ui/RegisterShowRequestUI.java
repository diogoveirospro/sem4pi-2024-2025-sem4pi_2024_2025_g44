package Shodrone.console.ShowRequest.ui;

import Shodrone.console.Customer.printer.CustomerPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.RegisterShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.User.repositories.ShodroneUserRepository;
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

public class RegisterShowRequestUI extends AbstractFancyUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RegisterShowRequestController controller = new RegisterShowRequestController();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();
    private final CustomerPrinter printer = new CustomerPrinter();

    @Override
    protected boolean doShow() {
        try {

            //TODO: Understand how to get current user and check information
//            if (authenticatedUser is not a Collaborator)
//            {
//                print error msg
//                return false
//            }
           // if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.COLLABORATOR))
             //   ;


            ShowDescription showDescription = enterValidShowDescription();
            Location location = enterValidLocation();
            LocalDate date = enterValidDate();
            LocalTime time = enterValidTime();
            QuantityOfDrones quantityOfDrones = enterValidQuantityOfDrones();

            Customer customer = getCustomer();
            CRMCollaborator crmCollaborator = getCrmCollaborator();

            ShowRequest showRequest = new ShowRequest(showDescription, date, time, location, quantityOfDrones, customer, crmCollaborator);

            controller.registerShowRequest(showRequest);

            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Show Request added successfully!" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (
        UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }

    private ShowDescription enterValidShowDescription()
    {
        String showDescription;
        do {
            try {
                showDescription = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's description (or type 'cancel' to go back): " + UtilsUI.RESET);

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
                location = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's location (or type 'cancel' to go back): " + UtilsUI.RESET);

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
                date = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's date(dd-mm-yyyy) (or type 'cancel' to go back): " + UtilsUI.RESET);

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
                time = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's time(hh:mm) (or type 'cancel' to go back): " + UtilsUI.RESET);

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
                qDrones = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's quantity of drones (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(qDrones)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new QuantityOfDrones(qDrones);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid quantity of drones. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private Customer getCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer", customerList, printer);
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return customerList.get(option);
            }
        } while (true);
    }
    private CRMCollaborator getCrmCollaborator()
    {
        //TODO: get CRM Collaborator and return it
        return null;
    }

    @Override
    public String headline() {
        return "Register Show Request";
    }
}
