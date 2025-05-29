package Shodrone.console.Figure.actions;

import Shodrone.console.Figure.printer.CategoriesPrinter;
import Shodrone.console.Figure.printer.CustomerPrinter;
import Shodrone.console.Figure.printer.FileNamePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Figure.application.AddFigureToCatalogueController;
import core.Figure.application.Service.DSLValidate;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.ValueObjects.*;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.time.domain.model.DateInterval;
import jakarta.persistence.PersistenceException;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static shodrone.presentation.UtilsUI.readLineFromConsole;

/**
 * User Interface for adding a figure to the catalogue.
 * This class extends AbstractUI and implements the doShow method to display the UI.
 */
public class AddFigureToCatalogueUI extends AbstractFancyUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    /**
     * Controller
     */
    private final AddFigureToCatalogueController controller = new AddFigureToCatalogueController();

    /**
     * User Repository
     */
    private final ShowDesignerRepository showDesignerRepository = PersistenceContext.repositories().showDesigners();

    /**
     * DSL Validator
     */
    private final DSLValidate dslValidate = new DSLValidate();

    /**
     * Show the UI for adding a figure to the catalogue.
     * @return true if the figure was added successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        boolean success;

        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.SHOWDESIGNER)){

                Code code = enterValidCode();
                Version version = enterValidVersion();
                Name name = enterValidName();
                Description description = enterValidDescription();
                DSLDescription dslDescription = enterValidDSLDescription();
                Set<Keyword> keywords = enterValidKeywords();
                Set<Category> categories = showCategoriesAndSelect();
                Exclusivity exclusivity = enterValidExclusivity();
                ShowDesigner showDesigner = null;

                if (authz.session().isPresent()) {
                    Email email = new Email(authz.session().get().authenticatedUser().email().toString());
                    showDesigner = showDesignerRepository.findByEmail(email);
                }

                if (exclusivity != null) {
                    success = addExclusiveFigureToCatalogue(code, version, name, description, dslDescription, keywords, categories, showDesigner, exclusivity);
                } else {
                    success = addPublicFigureToCatalogue(code, version, name, description, dslDescription, keywords, categories, showDesigner);
                }

                if (success){
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFigure added to catalogue successfully!\n" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to add figure to catalogue.\n" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }

            }

            return false;

        } catch (UserCancelledException e){
            return false;
        } catch (InterruptedException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nOperation interrupted.\n" + UtilsUI.RESET);
            Thread.currentThread().interrupt();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
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
    private boolean addPublicFigureToCatalogue(Code code, Version version, Name name, Description description,
                                            DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                            ShowDesigner showDesigner) throws InterruptedException {
        try{
            return controller.addPublicFigureToCatalogue(code, version, name, description, dslDescription, keywords, categories, showDesigner);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Error: " + e.getMessage() + UtilsUI.RESET);
            new AddFigureToCatalogueUI().show();
        } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause.getMessage() != null && cause.getMessage().contains("Unique index or primary key violation")) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                        "\nError: A picture with this code and version already exists!" + UtilsUI.RESET);
                Thread.sleep(2000);
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                        "\nError saving to the database!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                Thread.sleep(2000);
            }
            new AddFigureToCatalogueUI().show();
        }
        return false;
    }

    /**
     * Add an exclusive figure to the catalogue.
     *
     */
    private boolean addExclusiveFigureToCatalogue(Code code, Version version, Name name, Description description,
                                               DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                               ShowDesigner showDesigner, Exclusivity exclusivity) throws InterruptedException {
        try{
            return controller.addExclusiveFigureToCatalogue(code, version, name, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "Error: " + e.getMessage() + UtilsUI.RESET);
            new AddFigureToCatalogueUI().show();
        } catch (PersistenceException e) {
            Throwable cause = e.getCause();
            if (cause != null && cause.getMessage() != null && cause.getMessage().contains("Unique index or primary key violation")) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                        "\nError: A picture with this code and version already exists!" + UtilsUI.RESET);
                Thread.sleep(2000);
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                        "\nError saving to the database!" + UtilsUI.RESET);
                Thread.sleep(2000);
            }
            new AddFigureToCatalogueUI().show();
        }
        return false;
    }

    /**
     * Show the categories and allow the user to select them.
     * @return a set of selected categories.
     */
    public Set<Category> showCategoriesAndSelect() {
        Iterable<Category> categories = controller.listCategories();
        if (categories == null || !categories.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo categories available." + UtilsUI.RESET);
            return null;
        }

        List<Category> categoryList = new ArrayList<>();
        categories.forEach(categoryList::add);

        Set<Category> selectedCategories = new HashSet<>();
        CategoriesPrinter categoriesPrinter = new CategoriesPrinter();

        ListWidget<Category> categoryListWidget = new ListWidget<>(
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose the Categories:\n" + UtilsUI.RESET,
                categoryList,
                categoriesPrinter
        );
        categoryListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(categoryList);

            if (option == -2) {
                break;
            }

            if (option < 0 || option > categoryList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
                continue;
            }

            Category selected = categoryList.get(option);
            if (selectedCategories.contains(selected)) {
                System.out.println(UtilsUI.YELLOW + "\nCategory already selected." + UtilsUI.RESET);
            } else {
                selectedCategories.add(selected);
            }

        } while (true);

        if (selectedCategories.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo categories selected.\n" + UtilsUI.RESET);
            return showCategoriesAndSelect();
        }

        System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nSelected categories:\n" + selectedCategories + UtilsUI.RESET);
        return selectedCategories;
    }

    /**
     * Show the customers and allow the user to select one.
     * @return the selected customer.
     */
    public Customer showCustomerAndSelect() {
        Iterable<Customer> customers = controller.listCustomers();
        if (customers == null || !customers.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customers available." + UtilsUI.RESET);
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        CustomerPrinter customerPrinter = new CustomerPrinter();

        ListWidget<Customer> customerListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Customer:\n" +
                UtilsUI.RESET, customerList, customerPrinter);
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > customerList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                Customer selected = customerList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nSelected customer: " + selected.toString()  + "\n"
                        + UtilsUI.RESET);
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
            keyword = readLineFromConsole(UtilsUI.BOLD + "\nEnter a keyword (type 'done' to finish or 'cancel' to go back): " + UtilsUI.RESET);

            if ("cancel".equalsIgnoreCase(keyword)) {
                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
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

        if (keywords.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo keywords entered. Please try again." + UtilsUI.RESET);
            return enterValidKeywords();
        }

        return keywords;
    }

    /**
     * Enter a valid name for the figure.
     * @return a Name object.
     */
    private Name enterValidName() {
        String name;
        do {
            try{
                name = readLineFromConsole(UtilsUI.BOLD + "\nEnter a name (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(name)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                } else if (name.isEmpty()){
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nName cannot be empty." + UtilsUI.RESET);
                    continue;
                }

                return new Name(name);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                continue;
            }
        } while (true);
    }

    /**
     * Enter a valid DSL description.
     * @return a DSLDescription object.
     */
    private DSLDescription enterValidDSLDescription() {
        while (true) {
            try {
                final String filePath = chooseDSLFilePath();

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

                String dslCode = String.join("\n", dslLines);
                // Validate the DSL version format
                if (dslValidate.validate(dslCode).isValid()){
                    return new DSLDescription(dslLines, dslVersion);
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid DSL code in file. Please ensure the DSL is correct." + UtilsUI.RESET);
                }

            } catch (IOException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError reading file: " + e.getMessage() + UtilsUI.RESET);
            } catch (IllegalArgumentException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input: " + e.getMessage() + UtilsUI.RESET);
            }

            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nPlease try again." + UtilsUI.RESET);
        }
    }

    private String chooseDSLFilePath() {
        List<String> dslFiles = new ArrayList<>();
        Path basePath = findDSLFolder();

        if (basePath == null) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "DSL folder not found." + UtilsUI.RESET);
            return null;
        }

        try {
            Files.walk(basePath)
                    .filter(Files::isRegularFile)
                    .forEach(file -> dslFiles.add(file.toAbsolutePath().toString()));
        } catch (IOException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError reading DSL files: " + e.getMessage() + UtilsUI.RESET);
            return null;
        }

        if (dslFiles.isEmpty()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo DSL files found." + UtilsUI.RESET);
            return null;
        }

        dslFiles.add("Other");

        ListWidget<String> dslFileListWidget = new ListWidget<>(
                UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a DSL file:\n" + UtilsUI.RESET,
                dslFiles, new FileNamePrinter()
        );
        dslFileListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(dslFiles);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option >= dslFiles.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                String selected = dslFiles.get(option);

                if (selected.equals("Other")) {
                    selected = readLineFromConsole(UtilsUI.BOLD + "\nEnter the path to the DSL file " +
                            "(.txt) (or type 'cancel' to go back): " + UtilsUI.RESET);

                    if ("cancel".equalsIgnoreCase(selected)) {
                        throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                    }
                }
                return selected;
            }

        } while (true);
    }

    private Path findDSLFolder() {
        Path[] possiblePaths = {
                Paths.get("src/main/resources/sample_DSL_figures"),
                Paths.get("docs/LPROG_LOG_2DI_1230462_1230917_1230948_1220780_1230875/US251/files/sample_DSL_figures")
        };

        for (Path path : possiblePaths) {
            if (Files.exists(path)) {
                return path;
            }
        }

        return null;
    }

    /**
     * Enter a valid description for the figure.
     * @return a Description object.
     */
    private Description enterValidDescription() {
        String description;
        do {
            try{
                description = readLineFromConsole(UtilsUI.BOLD + "\nEnter a description (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(description)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
                } else if (description.isEmpty()){
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nDescription cannot be empty." + UtilsUI.RESET);
                    continue;
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
                version = readLineFromConsole(UtilsUI.BOLD + "\nEnter a version in the format X.Y.Z (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(version)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
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
                code = readLineFromConsole(UtilsUI.BOLD + "Enter a code in the format FIG-XXXX (or type 'cancel' to go back): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(code)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "Action cancelled by user." + UtilsUI.RESET);
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
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo customer selected. Please try again." + UtilsUI.RESET);
                        continue;
                    }

                    Date startDate;
                    Date endDate;

                    while (true) {
                        startDate = UtilsUI.readDateFromConsole(UtilsUI.BOLD + "Enter the start date (dd-MM-yyyy): " + UtilsUI.RESET);
                        endDate = UtilsUI.readDateFromConsole(UtilsUI.BOLD + "\nEnter the end date (dd-MM-yyyy): " + UtilsUI.RESET);

                        if (endDate.before(startDate)) {
                            System.out.println(UtilsUI.RED + UtilsUI.BOLD +
                                    "\nThe end date cannot be before the start date. Please try again." + UtilsUI.RESET);
                        } else if (startDate.before(new Date()) || endDate.before(new Date())) {
                            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nThe start and end dates cannot be in the " +
                                    "past. Please try again." + UtilsUI.RESET);
                        } else {
                            break; // valid dates
                        }
                    }


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
