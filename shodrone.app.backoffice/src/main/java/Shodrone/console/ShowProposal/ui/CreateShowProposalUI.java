package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.CurrencyPrinter;
import Shodrone.console.ShowProposal.printer.CustomerPrinter;
import Shodrone.console.ShowProposal.printer.ShowRequestPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.application.CreateShowProposalController;
import core.ShowProposal.domain.ValueObjects.Insurance;
import core.ShowRequest.domain.Entities.ShowRequest;
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

public class CreateShowProposalUI extends AbstractFancyUI {

    private final CustomerPrinter customerPrinter = new CustomerPrinter();
    private final ShowRequestPrinter showRequestPrinter = new ShowRequestPrinter();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CreateShowProposalController controller = new CreateShowProposalController();
    private final CurrencyPrinter currencyPrinter = new CurrencyPrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.COLLABORATOR)) {

                ShowRequest showRequest = selectShowRequest();
                assert showRequest != null;
                LocalDate currDate = showRequest.getDateOfShow();
                LocalTime currTime = showRequest.getTimeOfShow();
                QuantityOfDrones currQuantityOfDrones = showRequest.getQuantityOfDrones();

                LocalDate date = enterValidDate(currDate);
                LocalTime time = enterValidTime(currTime);
                QuantityOfDrones quantityOfDrones = enterValidQuantityOfDrones(currQuantityOfDrones);
                Insurance insurance = enterValidInsurance();

                CRMCollaborator crmCollaborator = controller.getCrmCollaborator();
                controller.createShowProposal(showRequest, date, time, quantityOfDrones, insurance, crmCollaborator);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nShow Proposal created successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private Insurance enterValidInsurance() {
        do {
            try {
                String amountStr = enterValidAmount();
                String currencySymbol = selectCurrency();
                if (currencySymbol == null) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }
                return new Insurance(amountStr, currencySymbol);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
            }
        } while (true);

    }

    private String enterValidAmount() {
        do {
            try {
                String amountStr = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the insurance amount (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(amountStr)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                assert amountStr != null;
                if (!amountStr.matches("\\d+(\\.\\d{1,2})?")) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nThe amount must be a valid number with up to two decimal places." + UtilsUI.RESET);
                    continue;
                }

                return amountStr;
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String selectCurrency() {
        List<String> currencies = controller.listCurrencies();
        if (currencies == null || currencies.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo currencies available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> currencyListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                "\nChoose a Currency: \n" + UtilsUI.RESET, currencies, currencyPrinter );
        currencyListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(currencies);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else if (option < 0 || option >= currencies.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo currency selected! Please select a currency!" + UtilsUI.RESET);
            } else {
                String selectedCurrency = currencies.get(option);
                return controller.getCurrencySymbol(selectedCurrency);
            }
        } while (true);
    }

    private ShowRequest selectShowRequest() {
        Customer customer = selectCustomer();
        Iterable<ShowRequest> showRequests = controller.listShowRequests(customer);
        if (showRequests == null || !showRequests.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo show requests available for this customer." + UtilsUI.RESET);
            return null;
        }
        List<ShowRequest> showRequestList = new ArrayList<>();
        showRequests.forEach(showRequestList::add);
        int option;
        do {
            ListWidget<ShowRequest> showRequestListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "Choose a Show Request\n" + UtilsUI.RESET, showRequestList, showRequestPrinter);
            showRequestListWidget.show();
            option = UtilsUI.selectsIndex(showRequestList);
            if (option == -2) {
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return showRequestList.get(option);
            }
        } while (true);
    }

    /**
     * Provides the headline for this UI screen.
     *
     * @return the screen title.
     */
    @Override
    public String headline() {
        return "Create Show Proposal";
    }

    private LocalTime enterValidTime(LocalTime currTime) {
        String time;
        LocalTime lTime;

        LocalTime currTime1 = useCustomerProposedTime(currTime);
        if (currTime1 != null) return currTime1;
        do {
            try {
                time = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's time (hh:mm) (or type 'cancel' to go back): " + UtilsUI.RESET);
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

    private static LocalTime useCustomerProposedTime(LocalTime currTime) {
        boolean confirm = UtilsUI.confirm(UtilsUI.BOLD + "The current time is: " + currTime.format(DateTimeFormatter.ofPattern("HH:mm")) +
                ". Do you want to change the time of the show? (Y/N): " + UtilsUI.RESET);
        if (!confirm) {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nUsing the current time: " + currTime.format(DateTimeFormatter.ofPattern("HH:mm")) + UtilsUI.RESET);
            return currTime;
        }
        return null;
    }

    private LocalDate enterValidDate(LocalDate currDate) {
        LocalDate currDate1 = useCustomerProposedDate(currDate);
        if (currDate1 != null) return currDate1;

        String date;
        LocalDate lDate;
        do {
            try {
                date = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's date (dd-MM-yyyy) (or type 'cancel' to go back): " + UtilsUI.RESET);
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

    private static LocalDate useCustomerProposedDate(LocalDate currDate) {
        boolean confirm = UtilsUI.confirm(UtilsUI.BOLD + "The current date is: " + currDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) +
                ". Do you want to change the date of the show? (Y/N): " + UtilsUI.RESET);
        if (!confirm) {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nUsing the current date: " + currDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + UtilsUI.RESET);
            return currDate;
        }
        return null;
    }

    private QuantityOfDrones enterValidQuantityOfDrones(QuantityOfDrones currQuantityOfDrones) {
        QuantityOfDrones currQuantity = useCustomerProposedQuantity(currQuantityOfDrones);
        if (currQuantity != null) return currQuantity;

        String qDrones;
        do {
            try {
                qDrones = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the show's quantity of drones (or type 'cancel' to go back): " + UtilsUI.RESET);
                if ("cancel".equalsIgnoreCase(qDrones)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new QuantityOfDrones(qDrones);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid quantity of drones. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private static QuantityOfDrones useCustomerProposedQuantity(QuantityOfDrones currQuantityOfDrones) {
        boolean confirm = UtilsUI.confirm(UtilsUI.BOLD + "The current quantity of drones is: " + currQuantityOfDrones +
                ". Do you want to change it? (Y/N): " + UtilsUI.RESET);
        if (!confirm) {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nUsing the current quantity of drones: " + currQuantityOfDrones + UtilsUI.RESET);
            return currQuantityOfDrones;
        }
        return null;
    }

    private Customer selectCustomer() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        int option;
        do {
            ListWidget<Customer> customerListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "Choose a Customer\n" + UtilsUI.RESET, customerList, customerPrinter);
            customerListWidget.show();
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return customerList.get(option);
            }
        } while (true);
    }


}
