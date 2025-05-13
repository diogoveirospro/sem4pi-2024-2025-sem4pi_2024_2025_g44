package Shodrone.console.authz.ui;

import Shodrone.exceptions.UserCancelledException;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.application.RegisterUsersController;
import core.User.domain.ShodronePasswordPolicy;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
            final String firstName = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "First Name: " + UtilsUI.RESET);
            final String lastName = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Last Name: " + UtilsUI.RESET);
            final Email email = enterValidEmail();
            final PhoneNumber phoneNumber = enterValidPhoneNumber();

            final Set<Role> roleTypes = new HashSet<>();
            boolean show;
            do {
                show = showRoles(roleTypes);
            } while (!show);

            this.theController.addUser(username, password, firstName, lastName, email.toString(), roleTypes, phoneNumber);

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

    private String enterValidUsername() {
        String username;
        do {
            username = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Username: " + UtilsUI.RESET);
            if (username == null || username.trim().isEmpty()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Username cannot be empty. Please try again." + UtilsUI.RESET);
                continue;
            }

            // Verificar se o nome de utilizador já está em uso
            if (isUsernameTaken(username)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "That username is already in use. Please choose another one." + UtilsUI.RESET);
            } else {
                break;
            }
        } while (true);
        return username;
    }

    private boolean isUsernameTaken(String username) {
        // Verifica se o nome de utilizador já existe no sistema
        return theController.isUsernameTaken(username);
    }

    private Email enterValidEmail() {
        String email;
        // Expressão regular simples para validar email (pode ser ajustada para requisitos específicos)
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";

        do {
            try {
                email = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the email (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(email)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                if (!Pattern.matches(emailRegex, email)) {
                    throw new IllegalArgumentException("Invalid email format. Please try again.");
                }

                return new Email(email); // Se o email for válido, cria e retorna o objeto Email.
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + e.getMessage() + UtilsUI.RESET); // Exibe a mensagem de erro.
            }
        } while (true);
    }
    private String enterValidPassword() {
        String password;
        do {
            password = UtilsUI.readPassword(UtilsUI.BOLD + "Password (at least 6 characters, including a number): " + UtilsUI.RESET);
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
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No country selected. Operation canceled." + UtilsUI.RESET);
                    return null;
                }
                countryCode = theController.countryCode(country);
                phoneNumber = UtilsUI.readLineFromConsole(UtilsUI.BOLD + "Enter the phone number: " + UtilsUI.RESET);
                return new PhoneNumber(countryCode, phoneNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid phone number. Please try again." + UtilsUI.RESET);
            }
        } while (true);
    }

    private String selectCountry() {
        List<String> countries = theController.availableCountries();
        if (countries == null || countries.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo countries available." + UtilsUI.RESET);
            return null;
        }

        ListWidget<String> countryListWidget = new ListWidget<>(UtilsUI.BOLD + "\nChoose a Country: \n" + UtilsUI.RESET, countries);
        countryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(countries);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled." + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                return countries.get(option);
            }
        } while (true);
    }

    private boolean showRoles(final Set<Role> roleTypes) {

        final Menu rolesMenu = buildRolesMenu(roleTypes);
        final MenuRenderer renderer = new VerticalMenuRenderer(rolesMenu, MenuItemRenderer.DEFAULT);
        return renderer.render();
    }

    private Menu buildRolesMenu(final Set<Role> roleTypes) {
        final Menu rolesMenu = new Menu();
        int counter = 0;
        rolesMenu.addItem(MenuItem.of(counter++, "No Role", Actions.SUCCESS));
        for (final Role roleType : theController.getRoleTypes()) {
            rolesMenu.addItem(
                    MenuItem.of(counter++, roleType.toString(), () -> roleTypes.add(roleType)));
        }
        return rolesMenu;
    }

    @Override
    public String headline() {
        return "Register User";
    }
}
