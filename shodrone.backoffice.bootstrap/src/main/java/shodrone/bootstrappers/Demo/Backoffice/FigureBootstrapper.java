package shodrone.bootstrappers.Demo.Backoffice;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Category.repositories.CategoryRepository;
import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Figure.application.AddFigureToCatalogueController;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.ValueObjects.Code;
import core.Figure.domain.ValueObjects.DSLDescription;
import core.Figure.domain.ValueObjects.Keyword;
import core.Figure.domain.ValueObjects.Version;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.actions.Action;
import eapli.framework.time.domain.model.DateInterval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FigureBootstrapper implements Action {

    private static final CategoryRepository categoryRepository = core.Persistence.PersistenceContext.repositories().categories();
    private static final CustomerRepository customerRepository = core.Persistence.PersistenceContext.repositories().customers();
    private static final ShowDesignerRepository showDesignerRepository = core.Persistence.PersistenceContext.repositories().showDesigners();
    private static final AddFigureToCatalogueController controller = new AddFigureToCatalogueController();
    private static final Logger LOGGER = LogManager.getLogger(FigureBootstrapper.class);


    //DSL Files
    private static final String DSL1 = "shodrone.backoffice.bootstrap/src/main/resources/sample_DSL_figures/sample_DSL_figure_1.txt";
    private static final String DSL2 = "shodrone.backoffice.bootstrap/src/main/resources/sample_DSL_figures/sample_DSL_figure_2.txt";
    private static final String DSL3 = "shodrone.backoffice.bootstrap/src/main/resources/sample_DSL_figures/sample_DSL_figure_3.txt";

    @Override
    public boolean execute() {
        registerPublic("FIG-1234", "1.0.1", "Public Figure", "A public figure", DSL1, Set.of("keyword1", "keyword2"),
                Set.of("category1", "category2"));

        registerPublic("FIG-1234", "1.0.2", "Another Version Public Figure 1", "Another version of public figure", DSL1, Set.of("keyword2"),
                Set.of("category1"));

        registerPublic("FIG-5678", "1.0.1", "Another Public Figure", "Another public figure", DSL2, Set.of("keyword3", "keyword4"),
                Set.of("category3", "category4"));

        registerPublic("FIG-5678", "1.0.2", "Another Version Public Figure 2", "Another version of public figure", DSL2, Set.of("keyword4"),
                Set.of("category3"));

        registerExclusive("FIG-9012", "1.0.1", "Exclusive Figure", "An exclusive figure", DSL3, Set.of("keyword5", "keyword6"),
                Set.of("category5", "category6"), "EA Sports", new Date(), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        registerExclusive("FIG-9012", "1.0.2", "Another Version Exclusive Figure", "Another version of exclusive figure", DSL3, Set.of("keyword6"),
                Set.of("category5"), "EA Sports", new Date(), new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24));

        return true;

    }

    private void registerPublic(String code, String version, String name, String description,
                          String DSLDescriptionFile, Set<String> keywords, Set<String> categories) {
        Code codeObj = new Code(code);
        Version versionObj = new Version(version);
        Name nameObj = new Name(name);
        Description descriptionObj = new Description(description);
        DSLDescription dslDescription = readDSLDescription(DSLDescriptionFile);
        if (dslDescription == null) {
            System.err.println("Error reading DSL description. Figure not registered.");
            return;
        }
        Set<Keyword> keywordsObj = new HashSet<>();
        for (String keyword : keywords) {
            keywordsObj.add(new Keyword(keyword));
        }
        Set<Category> categoriesObj = new HashSet<>();
        for (String category : categories) {
            Optional<Category> categoryOpt = categoryRepository.findByName(new CategoryName(category));
            if (categoryOpt.isEmpty()) {
                System.err.println("Category " + category + " not found. Figure not registered.");
                return;
            }
            categoriesObj.add(categoryOpt.get());
        }

        ShowDesigner showDesigner = showDesignerRepository.findByEmail(new Email("peter.parker@showdrone.com"));

        controller.addPublicFigureToCatalogue(codeObj, versionObj, nameObj, descriptionObj,
                dslDescription, keywordsObj, categoriesObj, showDesigner);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered public figure {}" + UtilsUI.RESET, code);

    }

    private void registerExclusive(String code, String version, String name, String description,
                                   String DSLDescriptionFile, Set<String> keywords, Set<String> categories,
                                   String customerName, Date startDate, Date endDate) {
        Code codeObj = new Code(code);
        Version versionObj = new Version(version);
        Name nameObj = new Name(name);
        Description descriptionObj = new Description(description);
        DSLDescription dslDescription = readDSLDescription(DSLDescriptionFile);
        if (dslDescription == null) {
            System.err.println("Error reading DSL description. Figure not registered.");
            return;
        }
        Set<Keyword> keywordsObj = new HashSet<>();
        for (String keyword : keywords) {
            keywordsObj.add(new Keyword(keyword));
        }
        Set<Category> categoriesObj = new HashSet<>();
        for (String category : categories) {
            Optional<Category> categoryOpt = categoryRepository.findByName(new CategoryName(category));
            if (categoryOpt.isEmpty()) {
                System.err.println("Category " + category + " not found. Figure not registered.");
                return;
            }
            categoriesObj.add(categoryOpt.get());
        }

        ShowDesigner showDesigner = showDesignerRepository.findByEmail(new Email("peter.parker@showdrone.com"));

        Customer customer = customerRepository.findCustomerByName(new Name(customerName));
        if (customer == null) {
            System.err.println("Customer " + customerName + " not found. Figure not registered.");
            return;
        }

        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        Exclusivity exclusivity = new Exclusivity(customer, new DateInterval(start, end));

        controller.addExclusiveFigureToCatalogue(codeObj, versionObj, nameObj, descriptionObj,
                dslDescription, keywordsObj, categoriesObj, showDesigner, exclusivity);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered exclusive figure {} for customer " +
                "{}" + UtilsUI.RESET, code, customerName);

    }

    private DSLDescription readDSLDescription(String filePath) {
        try {
            assert filePath != null;
            final List<String> dslLines = Files.readAllLines(Paths.get(filePath));

            String dslVersion = dslLines.stream()
                    .filter(line -> line.trim().toLowerCase().startsWith("dsl version"))
                    .findFirst()
                    .map(line -> line.replaceAll("(?i)dsl version", "")
                            .replaceAll("[^\\d\\.]", "")
                            .trim())
                    .orElseThrow(() -> new IllegalArgumentException("DSL version not found in file."));

            return new DSLDescription(dslLines, dslVersion);

        } catch (IOException e) {
            LOGGER.error(UtilsUI.BOLD + UtilsUI.RED + "Error reading DSL file '{}': {}" + UtilsUI.RESET,
                    filePath, e.getMessage());
            return null;
        } catch (IllegalArgumentException e) {
            LOGGER.warn(UtilsUI.BOLD + UtilsUI.RED + "Invalid DSL file '{}': {}" + UtilsUI.RESET,
                    filePath, e.getMessage());
            return null;
        }
    }


}
