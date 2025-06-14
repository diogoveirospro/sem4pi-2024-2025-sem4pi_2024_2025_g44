package core.ShowProposal.domain.Entities;

import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Figure.domain.Entities.Exclusivity;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ShowConfigurationTest {

    private ShowConfiguration configuration;
    private Figure publicFigure;
    private Figure exclusiveFigure;


    private Figure buildPublicFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-1234");
        Version version = new Version("1.0.0");
        Name name = new Name("Test Figure");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(new CategoryName("Category1"), new Description("Description1")));
        categories.add(new Category(new CategoryName("Category2"), new Description("Description2")));
        categories.add(new Category(new CategoryName("Category3"), new Description("Description3")));

        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
                new PhoneNumber("+351", "912345678"),
                new Email("showdesigner1@shodrone.com"));

        return new Figure(code, version, name, figureDescription, dslDescription, keywords, categories, showDesigner);
    }

    private Figure buildExclusiveFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-5678");
        Version version = new Version("1.0.0");
        Name name = new Name("Exclusive Figure");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(new CategoryName("Category1"), new Description("Description1")));
        categories.add(new Category(new CategoryName("Category2"), new Description("Description2")));
        categories.add(new Category(new CategoryName("Category3"), new Description("Description3")));

        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
                new PhoneNumber("+351", "912345678"),
                new Email("showdesigner1@shodrone.com"));

        Customer customer = new Customer(new Name("Customer1"), new Address("Rua 1"), new VatNumber("PT123456789"),
                CustomerType.REGULAR);

        Calendar start = Calendar.getInstance();
        start.set(2025, Calendar.MAY, 5);
        Calendar end = Calendar.getInstance();
        end.set(2025, Calendar.MAY, 10);

        Exclusivity exclusivity = new Exclusivity(customer, new DateInterval(start, end));

        return new Figure(code, version, name, figureDescription, dslDescription, keywords, categories, showDesigner, exclusivity);
    }

    @BeforeEach
    void setUp() {
        configuration = new ShowConfiguration();
        publicFigure = buildPublicFigure();
        exclusiveFigure = buildExclusiveFigure();
    }

    @Test
    void ensureInactiveFigureCannotBeAdded() {
        publicFigure.decommission(); // Set figure to inactive

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            configuration.addFigures(List.of(publicFigure));
        });
        assertEquals("Cannot add an inactive figure.", exception.getMessage());
    }

    @Test
    void ensureActiveFigureCanBeAdded() {
        configuration.addFigures(List.of(publicFigure));

        assertEquals(1, configuration.figures().size());
        assertTrue(configuration.figures().contains(publicFigure));
    }

    @Test
    void ensureFigureCanBeAddedMultipleTimesIfNotConsecutive() {
        configuration.addFigures(List.of(publicFigure));
        configuration.addFigures(List.of(exclusiveFigure));
        configuration.addFigures(List.of(publicFigure));

        assertEquals(3, configuration.figures().size());
    }

    @Test
    void ensureFigureCannotBeAddedConsecutively() {
        configuration.addFigures(List.of(publicFigure));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            configuration.addFigures(List.of(publicFigure));
        });
        assertEquals("Cannot add the same figure consecutively.", exception.getMessage());
    }

}