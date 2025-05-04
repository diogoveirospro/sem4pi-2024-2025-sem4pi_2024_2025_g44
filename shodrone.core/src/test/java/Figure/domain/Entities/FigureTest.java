package Figure.domain.Entities;

import core.Category.domain.Entities.Category;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.general.domain.model.EmailAddress;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FigureTest{

    private Figure buildFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-A123");
        Version version = new Version("1.0.0");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(Designation.valueOf("Category1"), eapli.framework.general.domain.model.Description.valueOf()));

        Category category1 = new Category("Category1", "Category1 description";
        Category category2 = new Category("Category2");
        Category category3 = new Category("Category3");
        List

        ShowDesigner showDesigner = new ShowDesigner(Designation.valueOf("Show Designer Test"),
                new PhoneNumber("+351","912345678"),
                EmailAddress.valueOf("showdesigner1@shodrone.com"));

        return new Figure(code, version, description, dslDescription, category1, showDesigner);
    }
    return new Figure("FIG-A123", "1.0.0",("A test figure"),
                new DSLDescription(List.of("line1", "line2"), "1.0"),
                new Category("Category1"),
                new ShowDesigner("ShowDesigner1", "

    @Test

}
