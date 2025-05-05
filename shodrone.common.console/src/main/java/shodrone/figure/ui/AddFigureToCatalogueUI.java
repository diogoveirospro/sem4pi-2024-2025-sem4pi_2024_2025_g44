package shodrone.figure.ui;

import core.Category.domain.Entities.Category;
import core.Figure.application.AddFigureToCatalogueController;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import shodrone.UtilsUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddFigureToCatalogueUI extends AbstractUI {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AddFigureToCatalogueController controller = new AddFigureToCatalogueController();

    @Override
    protected boolean doShow() {
        Code code = enterValidCode();
        Version version = enterValidVersion();
        Description description = enterValidDescription();
        DSLDescription dslDescription = enterValidDSLDescription();
        Set<Keyword> keywords = enterValidKeywords();
        Set<Category> categories = showCategoriesAndSelect();

        Figure figure = new Figure(code, version, description, dslDescription, keywords, categories);

        addFigureToCatalogue(figure);
        return true;
    }

    @Override
    public String headline() {
        return UtilsUI.generateHeader(UtilsUI.PURPLE, "Add Figure to Catalogue");
    }

    public void addFigureToCatalogue(Figure figure) {
        controller.addFigureToCatalogue(figure);
    }

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

    private DSLDescription enterValidDSLDescription() {
        while (true) {
            try {
                final String filePath = UtilsUI.readLineFromConsole("Enter the path to the DSL file (.txt): ");
                assert filePath != null;
                final List<String> dslLines = Files.readAllLines(Paths.get(filePath));

                final String dslVersion = UtilsUI.readLineFromConsole("Enter the DSL version (format X.Y.Z): ");

                return new DSLDescription(dslLines, dslVersion);

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input: " + e.getMessage());
            }

            System.out.println("Please try again.\n");
        }
    }


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

    public static void main(String[] args) {
        AddFigureToCatalogueUI ui = new AddFigureToCatalogueUI();
        ui.show();
    }
}
