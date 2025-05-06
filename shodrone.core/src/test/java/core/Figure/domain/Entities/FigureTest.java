package core.Figure.domain.Entities;

import core.Category.domain.Entities.Category;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FigureTest {

    private Figure buildPublicFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-1234");
        Version version = new Version("1.0.0");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(new Name("Category1"), new Description("Description1")));
        categories.add(new Category(new Name("Category2"), new Description("Description2")));
        categories.add(new Category(new Name("Category3"), new Description("Description3")));

        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
                new PhoneNumber("+351", "912345678"),
                new Email("showdesigner1@shodrone.com"));

        return new Figure(code, version, figureDescription, dslDescription, keywords, categories, showDesigner);
    }

    private Figure buildExclusiveFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-5678");
        Version version = new Version("1.0.0");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(new Name("Category1"), new Description("Description1")));
        categories.add(new Category(new Name("Category2"), new Description("Description2")));
        categories.add(new Category(new Name("Category3"), new Description("Description3")));

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

        return new Figure(code, version, figureDescription, dslDescription, keywords, categories, showDesigner, exclusivity);
    }

    @Test
    public void testPublicFigure() {
        Figure figure = buildPublicFigure();
        assertNotNull(figure);

        // Check FigureID details
        assertEquals("FIG-1234", figure.figureID().code().toString());
        assertEquals("1.0.0", figure.figureID().version().toString());

        // Check description
        assertEquals("A test figure", figure.description().toString());

        // Check DSLDescription details
        assertEquals("1.0.0", figure.DSLDescription().version());
        assertEquals(2, figure.DSLDescription().codeLines().size());

        // Check keywords and categories
        assertEquals(3, figure.keywords().size());
        assertEquals(3, figure.categories().size());

        // Check FigureStatus
        assertEquals(FigureStatus.ACTIVE, figure.figureStatus());

        // Check ShowDesigner details
        assertEquals("ShowDesigner1", figure.showDesigner().name().toString());
        assertEquals("+351 912345678", figure.showDesigner().phoneNumber().toString());
        assertEquals("showdesigner1@shodrone.com", figure.showDesigner().email().toString());

        // Check exclusivity
        assertNull(figure.exclusivity());
    }

    @Test
    public void testExclusiveFigure() {
        Figure figure = buildExclusiveFigure();
        assertNotNull(figure);

        // Check FigureID details
        assertEquals("FIG-5678", figure.figureID().code().toString());
        assertEquals("1.0.0", figure.figureID().version().toString());

        // Check description
        assertEquals("A test figure", figure.description().toString());

        // Check DSLDescription details
        assertEquals("1.0.0", figure.DSLDescription().version());
        assertEquals(2, figure.DSLDescription().codeLines().size());

        // Check keywords and categories
        assertEquals(3, figure.keywords().size());
        assertEquals(3, figure.categories().size());

        // Check FigureStatus
        assertEquals(FigureStatus.ACTIVE, figure.figureStatus());

        // Check ShowDesigner details
        assertEquals("ShowDesigner1", figure.showDesigner().name().toString());
        assertEquals("+351 912345678", figure.showDesigner().phoneNumber().toString());
        assertEquals("showdesigner1@shodrone.com", figure.showDesigner().email().toString());

        // Check exclusivity
        assertNotNull(figure.exclusivity());

        // Check Customer details
        assertEquals("Customer1", figure.exclusivity().customer().name().toString());
        assertEquals("Rua 1", figure.exclusivity().customer().address().toString());
        assertEquals("PT123456789", figure.exclusivity().customer().vat().toString());
        assertEquals(CustomerType.REGULAR, figure.exclusivity().customer().type());

        // Check exclusivity dates
        assertEquals("05/05/2025", figure.exclusivity().startTime());
        assertEquals("10/05/2025", figure.exclusivity().endTime());
    }

    @Test
    public void testSameAs() {
        Figure figure1 = buildPublicFigure();
        Figure figure2 = buildPublicFigure();
        Figure figure3 = buildExclusiveFigure();

        assertTrue(figure1.sameAs(figure2));
        assertFalse(figure1.sameAs(figure3));
    }

    @Test
    public void testIdentity() {
        Figure figure = buildPublicFigure();
        assertEquals(figure.figureID(), figure.identity());
    }

    @Test
    public void testEquals() {
        Figure figure1 = buildPublicFigure();
        Figure figure2 = buildPublicFigure();
        Figure figure3 = buildExclusiveFigure();

        assertEquals(figure1, figure2);
        assertNotEquals(figure1, figure3);
    }

    @Test
    public void testHashCode() {
        Figure figure1 = buildPublicFigure();
        Figure figure2 = buildPublicFigure();
        Figure figure3 = buildExclusiveFigure();

        assertEquals(figure1.hashCode(), figure2.hashCode());
        assertNotEquals(figure1.hashCode(), figure3.hashCode());
    }

    @Test
    public void testCompareTo() {
        Figure figure1 = buildPublicFigure();
        Figure figure2 = buildPublicFigure();
        Figure figure3 = buildExclusiveFigure();

        assertEquals(0, figure1.compareTo(figure2.figureID()));
        assertNotEquals(0, figure1.compareTo(figure3.figureID()));
    }

    @Test
    public void testHasIdentity() {
        Figure figure = buildPublicFigure();
        assertTrue(figure.hasIdentity(figure.figureID()));
        assertFalse(figure.hasIdentity(new FigureID(new Code("FIG-9999"), new Version("1.0.0"))));
    }

    @Test
    public void testCreateFigureWithEmptyKeywordsAndCategories() {
        Set<Keyword> emptyKeywords = new HashSet<>();
        Set<Category> emptyCategories = new HashSet<>();

        Figure figure = new Figure(new Code("FIG-0000"), new Version("1.0.0"),
                new Description("Empty test"), new DSLDescription(List.of("line1", "line2"), "1.0.0"),
                emptyKeywords, emptyCategories,
                new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("sd@email.com")));

        assertNotNull(figure);
        assertEquals(0, figure.keywords().size());
        assertEquals(0, figure.categories().size());
    }

    @Test
    void ensureFigureIncludesAllRequiredParameters() {

        // null code
        assertThrows(AssertionError.class, () -> {
            new Figure(null, new Version("1.0.0"),
                    new Description("Description"), new DSLDescription(List.of("line1"), "1.0.0"),
                    Set.of(new Keyword("test")), Set.of(new Category(new Name("C"), new Description("D"))),
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // null version
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), null,
                    new Description("Description"), new DSLDescription(List.of("line1"), "1.0.0"),
                    Set.of(new Keyword("test")), Set.of(new Category(new Name("C"), new Description("D"))),
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // null description
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), new Version("1.0.0"),
                    null, new DSLDescription(List.of("line1"), "1.0.0"),
                    Set.of(new Keyword("test")), Set.of(new Category(new Name("C"), new Description("D"))),
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // null DSLDescription
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), new Version("1.0.0"),
                    new Description("Description"), null,
                    Set.of(new Keyword("test")), Set.of(new Category(new Name("C"), new Description("D"))),
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // null keywords
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), new Version("1.0.0"),
                    new Description("Description"), new DSLDescription(List.of("line1"), "1.0.0"),
                    null, Set.of(new Category(new Name("C"), new Description("D"))),
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // null categories
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), new Version("1.0.0"),
                    new Description("Description"), new DSLDescription(List.of("line1"), "1.0.0"),
                    Set.of(new Keyword("test")), null,
                    new ShowDesigner(new Name("SD"), new PhoneNumber("+351", "911111111"), new Email("email@test.com")));
        });

        // showDesigner is null
        assertThrows(AssertionError.class, () -> {
            new Figure(new Code("FIG-1234"), new Version("1.0.0"),
                    new Description("Description"), new DSLDescription(List.of("line1"), "1.0.0"),
                    Set.of(new Keyword("test")), Set.of(new Category(new Name("C"), new Description("D"))),
                    null);
        });
    }

    @Test
    public void testIsExclusive(){
        Figure figure = buildExclusiveFigure();
        assertTrue(figure.isExclusive());

        Figure publicFigure = buildPublicFigure();
        assertFalse(publicFigure.isExclusive());
    }

    @Test
    public void testIsActive(){
        Figure figure = buildPublicFigure();
        assertTrue(figure.isActive());
    }

}