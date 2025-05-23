package Shodrone.console.authz.ui;

import Shodrone.console.Figure.printer.CategoriesPrinter;
import Shodrone.console.authz.printer.CountryPrinter;
import Shodrone.console.authz.printer.RolesPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Category.domain.Entities.Category;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.RegisterUsersController;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.*;
import java.util.regex.Pattern;

@SuppressWarnings("java:S106")
public class RegisterUserUI extends AbstractFancyUI {

    private final RegisterUsersController theController = new RegisterUsersController();
    private final ShodronePasswordPolicy passwordPolicy = new ShodronePasswordPolicy();

    @Override
    protected boolean doShow() {
        try {
            final String username = enterValidUsername();
            final String password = enterValidPassword();
            final String firstName = enterValidName(UtilsUI.BOLD + "First Name (or type 'cancel' to go back): " + UtilsUI.RESET);
            final String lastName = enterValidName(UtilsUI.BOLD + "\nLast Name (or type 'cancel' to go back): " + UtilsUI.RESET);
            final Email email = enterValidEmail();
            final PhoneNumber phoneNumber = enterValidPhoneNumber();

            final Set<Role> roleTypes = showRolesAndSelect();

            SystemUser user = this.theController.addUser(username, password, firstName, lastName, email.toString(), roleTypes, phoneNumber);

            if (user == null) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUser registration failed." + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            } else {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nUser registered successfully" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;
            }

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Error: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private String enterValidName(String prompt) {
        String name;
        do {
            name = UtilsUI.readLineFromConsole(prompt);
            if (name == null || name.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nName cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            } else if ("cancel".equalsIgnoreCase(name)) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            break;
        } while (true);
        return name;
    }

    private String enterValidUsername() {
        String username;

        do {
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Username (or type 'cancel' to go back): " + UtilsUI.RESET);

            if ("cancel".equalsIgnoreCase(username)) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            if (username == null || username.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUsername cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if (isUsernameTaken(username)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nUsername already taken. Please try again." + UtilsUI.RESET);
                continue;
            }

            break;
        } while (true);

        return username;
    }

    private boolean isUsernameTaken(String username) {
        return theController.isUsernameTaken(username);
    }

    private Email enterValidEmail() {
        String email;
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        do {
            try {
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the email (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(email)) {
                    throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                }

                if (!Pattern.matches(emailRegex, email)) {
                    throw new IllegalArgumentException("Invalid email format. Please try again.");
                }

                return new Email(email);
            } catch (IllegalArgumentException e) {
                System.out.println("\n\n" + UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET);
            }
        } while (true);
    }
    private String enterValidPassword() {
        String password;
        do {
            password = UtilsUI.readPassword(UtilsUI.BOLD + "\n\nPassword (at least 6 characters, including a number): " + UtilsUI.RESET);
            if (password == null || password.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            if (!passwordPolicy.isSatisfiedBy(password)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Password does not meet the required criteria. Please try again." + UtilsUI.RESET);
            } else {
                break;
            }
        } while (true);
        return password;
    }

    private PhoneNumber enterValidPhoneNumber() {
        String phoneNumber;
        String country;
        String countryCode;
        do {
            try {
                country = selectCountry();
                if (country == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No country selected. Operation canceled.\n" + UtilsUI.RESET);
                    return null;
                }
                countryCode = theController.countryCode(country);

                while (true) {
                    phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "\nEnter the phone number: " + UtilsUI.RESET);

                    if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPhone number cannot be empty. Please try again." + UtilsUI.RESET);
                        continue;
                    }

                    return new PhoneNumber(countryCode, phoneNumber);
                }

            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid phone number. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String selectCountry() {
        List<String> countries = theController.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo countries available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> countryListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                "\n\nChoose a Country: \n" + UtilsUI.RESET, countries, new CountryPrinter());
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.BOLD + UtilsUI.RED + "\nAction cancelled by user." + UtilsUI.RESET);
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid option. Please try again." + UtilsUI.RESET);
            } else {
                return countries.get(option);
            }
        } while (true);
    }

    public Set<Role> showRolesAndSelect() {
        Role[] roles = theController.getRoleTypes();
        if (roles == null) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No roles available." + UtilsUI.RESET);
            return null;
        }

        List<Role> roleList = new ArrayList<>(Arrays.asList(roles));
        roleList.add(Role.valueOf("None"));

        Set<Role> selectedRoles = new HashSet<>();
        RolesPrinter rolesPrinter = new RolesPrinter();

        ListWidget<Role> roleListWidget = new ListWidget<>(
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose the Roles:\n" + UtilsUI.RESET,
                roleList,
                rolesPrinter
        );
        roleListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(roleList);

            if (option == -2) {
                break;
            }

            if (option < 0 || option > roleList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid option. Please try again." + UtilsUI.RESET);
                continue;
            }

            Role selected = roleList.get(option);
            if (selected.toString().equals("None")){
                return selectedRoles;
            } else if (selectedRoles.contains(selected)) {
                System.out.println(UtilsUI.YELLOW + "Role already selected." + UtilsUI.RESET);
            } else {
                selectedRoles.add(selected);
            }

        } while (true);

        if (selectedRoles.isEmpty()) {
            throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
        }

        System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nSelected roles:\n" + selectedRoles + UtilsUI.RESET);
        return selectedRoles;
    }

    @Override
    public String headline() {
        return "Register User";
    }
}
