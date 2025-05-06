package Shodrone.console.Figure.ui;

import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Figure.application.AddFigureToCatalogueController;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.time.domain.model.DateInterval;
import shodrone.UtilsUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * User Interface for adding a figure to the catalogue.
 * This class extends AbstractUI and implements the doShow method to display the UI.
 */
public class AddFigureToCatalogueUI extends AbstractUI {

    //private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddFigureToCatalogueController controller = new AddFigureToCatalogueController();

    /**
     * Show the UI for adding a figure to the catalogue.
     * @return true if the figure was added successfully, false otherwise.
     */
    @Override
    protected boolean doShow() {
        Code code = enterValidCode();
        Version version = enterValidVersion();
        Description description = enterValidDescription();
        DSLDescription dslDescription = enterValidDSLDescription();
        Set<Keyword> keywords = enterValidKeywords();
        Set<Category> categories = showCategoriesAndSelect();
        Exclusivity exclusivity = enterValidExclusivity();

        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
                new PhoneNumber("+351", "912345678"),
                new Email("showdesigner1@shodrone.com"));

        if (exclusivity != null) {
            addExclusiveFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
        } else {
            addPublicFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner);
        }
        return true;
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
        controller.addPublicFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner);
    }

    /**
     * Add an exclusive figure to the catalogue.
     *
     */
    private void addExclusiveFigureToCatalogue(Code code, Version version, Description description,
                                               DSLDescription dslDescription, Set<Keyword> keywords, Set<Category> categories,
                                               ShowDesigner showDesigner, Exclusivity exclusivity) {
        controller.addExclusiveFigureToCatalogue(code, version, description, dslDescription, keywords, categories, showDesigner, exclusivity);
    }

    /**
     * Show the categories and allow the user to select them.
     * @return a set of selected categories.
     */
    public Set<Category> showCategoriesAndSelect() {
        Iterable<Category> categories = controller.listCategories();
        if (categories == null || !categories.iterator().hasNext()) {
            System.out.println("No categories available.");
            return null;
        }

        List<Category> categoriesToShow = new ArrayList<>();
        for (Category category : categories) {
            categoriesToShow.add(category);
        }

        Set<Category> selectedCategories = new HashSet<>();

        ListWidget<Category> categoryListWidget = new ListWidget<>("Categories", categories,
                Category::toString);
        categoryListWidget.show();

        int option = 0;
        do {
            option = UtilsUI.selectsIndex(new ArrayList<>((List) categories));

            if (option == -2) {
                break;
            }
            if (option < 1 || option > categories.spliterator().estimateSize()) {
                System.out.println("Invalid option. Please try again.");
                continue;
            }

            selectedCategories.add(categoriesToShow.get(option - 1));
        } while(option < 1 || option > categories.spliterator().estimateSize());

        if (selectedCategories.isEmpty()) {
            System.out.println("No categories selected.");
            showCategoriesAndSelect();
        } else {
            System.out.println("Selected categories: " + selectedCategories);
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
            System.out.println("No customers available.");
            return null;
        }

        List<Customer> customerList = new ArrayList<>();
        customers.forEach(customerList::add);

        ListWidget<Customer> customerListWidget = new ListWidget<>("Customers", customerList, Customer::toString);
        customerListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(customerList);
            if (option == -2) {
                System.out.println("Selection cancelled.");
                return null;
            }

            if (option < 1 || option > customerList.size()) {
                System.out.println("Invalid option. Please try again.");
            } else {
                Customer selected = customerList.get(option - 1);
                System.out.println("Selected customer: " + selected);
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
            keyword = UtilsUI.readLineFromConsole("Enter a keyword (or 'done' to finish): ");
            assert keyword != null;
            if (!keyword.equalsIgnoreCase("done")) {
                try {
                    Keyword keywordObj = new Keyword(keyword);
                    keywords.add(keywordObj);
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid keyword. Please try again.");
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
                final String filePath = UtilsUI.readLineFromConsole("Enter the path to the DSL file (.txt): ");
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
                System.out.println("Error reading file: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }

            System.out.println("Please try again.\n");
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
                description = UtilsUI.readLineFromConsole("Enter a description: ");
                return new Description(description);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
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
                version = UtilsUI.readLineFromConsole("Enter a version: ");
                return new Version(version);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
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
                code = UtilsUI.readLineFromConsole("Enter a code: ");
                return new Code(code);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. Please try again.");
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
                if (UtilsUI.confirm("Are you an Exclusive Figure? (Y/N):")) {

                    Customer customer = showCustomerAndSelect();
                    if (customer == null) {
                        System.out.println("No customer selected. Please try again.");
                        continue;
                    }

                    Date startDate = UtilsUI.readDateFromConsole("Enter the start date (dd-MM-yyyy): ");
                    Date endDate = UtilsUI.readDateFromConsole("Enter the end date (dd-MM-yyyy): ");

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
                System.out.println("Invalid input. Please try again.");
                continue;
            }
        } while (true);
    }
}
