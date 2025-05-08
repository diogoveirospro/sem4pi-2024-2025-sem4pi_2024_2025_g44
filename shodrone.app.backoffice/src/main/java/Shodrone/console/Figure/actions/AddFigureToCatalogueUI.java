package Shodrone.console.Figure.actions;

import Shodrone.exceptions.UserCancelledException;
import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Figure.application.AddFigureToCatalogueController;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.ValueObjects.*;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import core.User.domain.ShodroneRoles;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.time.domain.model.DateInterval;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for adding a figure to the catalogue.
 * This class extends AbstractUI and implements the doShow method to display the UI.
 */
public class AddFigureToCatalogueUI extends AbstractFancyUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddFigureToCatalogueController controller = new AddFigureToCatalogueController();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();

    /**
     * Show the UI for adding a figure to the catalogue.
     * @return true if the figure was added successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        try {
            Code code = enterValidCode();
            Version version = enterValidVersion();
            Description description = enterValidDescription();
            DSLDescription dslDescription = enterValidDSLDescription();
            Set<Keyword> keywords = enterValidKeywords();
            Set<Category> categories = showCategoriesAndSelect();
            Exclusivity exclusivity = enterValidExclusivity();
            ShowDesigner showDesigner = null;

            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.SHOWDESIGNER)){

                if (authz.session().isPresent()){
                    Name name = new Name(authz.session().get().authenticatedUser().name().firstName() + " " +
                            authz.session().get().authenticatedUser().name().lastName());
                    Email email = new Email(authz.session().get().authenticatedUser().email().toString());
                    PhoneNumber phoneNumber = userRepository.findByUsername(authz.session().get().authenticatedUser().
                            identity()).phoneNumber();
                    showDesigner = new ShowDesigner(name, phoneNumber, email);
                }

                if (exclusivity != null) {
                    addExclusiveFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
                } else {
                    addPublicFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner);
                }
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Figure added to catalogue successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return true;

            }

            return false;

        } catch (UserCancelledException e){
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nOperation cancelled." + UtilsUI.RESET);
            return false;
        }
    }

    /**
     * Display the headline for the UI.
     * @return the headline string.
     */
    @Override
    public String headline() {
        return "Add Figure to Catalogue";
    }

    /**
     * Add a public figure to the catalogue.
     *
     */
    private void addPublicFigureToCatalogue(Code code, Version version, Description description,
                                            DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                            ShowDesigner showDesigner) {
        try{
            controller.addPublicFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            new AddFigureToCatalogueUI().show();
        }

        controller.addPublicFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner);
    }

    /**
     * Add an exclusive figure to the catalogue.
     *
     */
    private void addExclusiveFigureToCatalogue(Code code, Version version, Description description,
                                               DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                               ShowDesigner showDesigner, Exclusivity exclusivity) {
        try{
            controller.addExclusiveFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            new AddFigureToCatalogueUI().show();
        }
    }

    /**
     * Show the categories and allow the user to select them.
     * @return a set of selected categories.
     */
    public Set<Category> showCategoriesAndSelect() {
        Iterable<Category> categories = controller.listCategories();
        if (categories == null || !categories.iterator().hasNext()) {
            System.out.print(UtilsUI.RED + UtilsUI.BOLD + "\nNo categories available." + UtilsUI.RESET);
            return null;
        }

        List<Category> categoriesToShow = new ArrayList<>();
        for (Category category : categories) {
            categoriesToShow.add(category);
        }

        Set<Category> selectedCategories = new HashSet<>();

        ListWidget<Category> categoryListWidget = new ListWidget<>("Choose the Categories", categories,
                Category::toString);
        categoryListWidget.show();

        int option = 0;
        do {
            option = UtilsUI.selectsIndex(new ArrayList<>((List) categories));

            if (option == -2) {
                break;
            }
            if (option < 1 || option > categories.spliterator().estimateSize()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
                continue;
            }

            selectedCategories.add(categoriesToShow.get(option - 1));
        } while(option < 1 || option > categories.spliterator().estimateSize());

        if (selectedCategories.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No categories selected." + UtilsUI.RESET);
            showCategoriesAndSelect();
        } else {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Selected categories: " + selectedCategories);
        }

        return selectedCategories;
    }

    /**
     * Show the customers and allow the user to select one.
     * @return the selected customer.
     */
    public Customer showCustomerAndSelect() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Choose a Customer", customerList, Customer::toString);
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
                return null;
            }

            if (option < 1 || option > customerList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                Customer selected = customerList.get(option - 1);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Selected customer: " + selected + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }

    /**
     * Enter valid keywords for the figure.
     * @return a set of valid keywords.
     */
    private Set<Keyword> enterValidKeywords() {
        Set<Keyword> keywords = new HashSet<>();
        String keyword;
        do {
            keyword = readLineFromConsole(UtilsUI.BOLD + "Enter a keyword (type 'done' to finish or 'cancel' to go back):\n: " + UtilsUI.RESET);

            if ("cancel".equalsIgnoreCase(keyword)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
            }

            assert keyword != null;
            if (!keyword.equalsIgnoreCase("done")) {
                try {
                    Keyword keywordObj = new Keyword(keyword);
                    keywords.add(keywordObj);
                } catch (IllegalArgumentException e) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid keyword. Please try again." + UtilsUI.RESET);
                }
            }
        } while (!keyword.equalsIgnoreCase("done"));
        return keywords;
    }

    /**
     * Enter a valid DSL description.
     * @return a DSLDescription object.
     */
    private DSLDescription enterValidDSLDescription() {
        while (true) {
            try {
                final String filePath = readLineFromConsole(UtilsUI.BOLD + "Enter the path to the DSL file " +
                        "(.txt) (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(filePath)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                assert filePath != null;
                final List<String> dslLines = Files.readAllLines(Paths.get(filePath));

                // Search for the line that starts with "DSL version" (case insensitive)
                String dslVersion = dslLines.stream()
                        .filter(line -> line.trim().toLowerCase().startsWith("dsl version"))
                        .findFirst()
                        .map(line -> line.replaceAll("(?i)dsl version", "")
                                .replaceAll("[^\\d\\.]", "")
                                .trim())
                        .orElseThrow(() -> new IllegalArgumentException("DSL version not found in file."));

                return new DSLDescription(dslLines, dslVersion);

            } catch (IOException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError reading file: " + e.getMessage() + UtilsUI.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Invalid input: " + e.getMessage() + UtilsUI.RESET);
            }

            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Please try again." + UtilsUI.RESET);
        }
    }

    /**
     * Enter a valid description for the figure.
     * @return a Description object.
     */
    private Description enterValidDescription() {
        String description;
        do {
            try{
                description = readLineFromConsole(UtilsUI.BOLD + "Enter a description (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(description)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new Description(description);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    /**
     * Enter a valid version for the figure.
     * @return a Version object.
     */
    private Version enterValidVersion() {
        String version;
        do {
            try {
                version = readLineFromConsole(UtilsUI.BOLD + "Enter a version (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(version)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new Version(version);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    /**
     * Enter a valid code for the figure.
     * @return a Code object.
     */
    private Code enterValidCode() {
        String code;
        do {
            try {
                code = readLineFromConsole(UtilsUI.BOLD + "Enter a code (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(code)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }

                return new Code(code);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    /**
     * Enter a valid exclusivity for the figure.
     * @return an Exclusivity object.
     */
    private Exclusivity enterValidExclusivity() {
        do {
            try {
                if (UtilsUI.confirm(UtilsUI.BOLD + "Are you an Exclusive Figure? (Y/N):" + UtilsUI.RESET)) {

                    Customer customer = showCustomerAndSelect();
                    if (customer == null) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "No customer selected. Please try again." + UtilsUI.RESET);
                        continue;
                    }

                    Date startDate = UtilsUI.readDateFromConsole(UtilsUI.BOLD + "\nEnter the start date (dd-MM-yyyy): " + UtilsUI.RESET);
                    Date endDate = UtilsUI.readDateFromConsole(UtilsUI.BOLD + "Enter the end date (dd-MM-yyyy): " + UtilsUI.RESET);

                    Calendar start = Calendar.getInstance();
                    start.setTime(startDate);

                    Calendar end = Calendar.getInstance();
                    end.setTime(endDate);

                    DateInterval duration = new DateInterval(start, end);


                    return new Exclusivity(customer, duration);
                } else {
                    return null;
                }

            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }
}
