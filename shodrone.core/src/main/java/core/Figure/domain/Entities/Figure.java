package core.Figure.domain.Entities;

import core.Category.domain.Entities.Category;
import core.Figure.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Description;
import core.ShowDesigner.domain.Entities.ShowDesigner;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;
import jakarta.persistence.Version;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Figure in the system.
 * This class is immutable and implements AggregateRoot and Serializable interfaces.
 */
@Entity
public class Figure implements AggregateRoot<FigureID>, Serializable {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The ID of the Figure in the system
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The version of the Figure in the system
     */
    @Version
    private Long version;

    /**
     * The ID of the Figure with the code and version
     */
    @Embedded
    @Column(unique = true)
    private FigureID figureID;

    /**
     * The description of the Figure
     */
    @Embedded
    private Description description;

    /**
     * The DSL description of the Figure
     */
    @Embedded
    private DSLDescription DSLDescription;

    /**
     * The status of the Figure
     */
    @Embedded
    @Enumerated(EnumType.STRING)
    FigureStatus figureStatus;

    /**
     * The keywords associated with the Figure
     */
    @ElementCollection
    private Set<Keyword> keywords = new HashSet<>();

    /**
     * The exclusivity of the Figure
     */
    @OneToOne(cascade = CascadeType.ALL)
    private Exclusivity exclusivity;

    /**
     * The categories associated with the Figure
     */
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    /**
     * The Show Designer associated with the Figure
     */
    @ManyToOne (optional = false)
    private ShowDesigner showDesigner;

    /**
     * Constructor for Figure without Exclusivity
     *
     * @param code the code of the Figure
     * @param version the version of the Figure
     * @param description the description of the Figure
     * @param DSLDescription the DSL description of the Figure
     * @param keywords the keywords associated with the Figure
     * @param categories the categories associated with the Figure
     */
    public Figure(Code code, core.Figure.domain.ValueObjects.Version version, Description description,
                  DSLDescription DSLDescription, Set<Keyword> keywords, Set<Category> categories) {

        this.figureID = new FigureID(code, version);
        this.description = description;
        this.DSLDescription = DSLDescription;
        this.figureStatus = FigureStatus.ACTIVE;
        this.keywords = keywords;
        this.categories = categories;
        this.showDesigner = showDesigner;

    }

    /**
     * Constructor for Figure with Exclusivity
     *
     * @param code the code of the Figure
     * @param version the version of the Figure
     * @param description the description of the Figure
     * @param DSLDescription the DSL description of the Figure
     * @param keywords the keywords associated with the Figure
     * @param categories the categories associated with the Figure
     * @param exclusivity the exclusivity of the Figure
     */
    public Figure(Code code, core.Figure.domain.ValueObjects.Version version, Description description,
                  DSLDescription DSLDescription, Set<Keyword> keywords, Set<Category> categories, Exclusivity exclusivity) {

        this.figureID = new FigureID(code, version);
        this.description = description;
        this.DSLDescription = DSLDescription;
        this.figureStatus = FigureStatus.ACTIVE;
        this.keywords = keywords;
        this.categories = categories;
        this.exclusivity = exclusivity;
        this.showDesigner = showDesigner;

    }

    /**
     * Default constructor for JPA
     */
    protected Figure() {
        this.figureID = null;
        this.description = null;
        this.DSLDescription = null;
        this.figureStatus = null;
        this.keywords = null;
        this.categories = null;
        this.exclusivity = null;
        this.showDesigner = null;
    }

    /**
     * Get Figure ID with code and version
     * @return the FigureID object
     */
    public FigureID figureID() {
        return figureID;
    }

    /**
     * Get Figure Description
     * @return the Description object
     */
    public Description description() {
        return description;
    }

    /**
     * Get Figure DSL Description
     * @return the DSLDescription object
     */
    public DSLDescription DSLDescription() {
        return DSLDescription;
    }

    /**
     * Get Figure Status
     * @return the FigureStatus object
     */
    public FigureStatus figureStatus() {
        return figureStatus;
    }

    /**
     * Get Figure Keywords
     * @return the set of Keywords
     */
    public Set<Keyword> keywords() {
        return keywords;
    }

    /**
     * Get Figure Exclusivity
     * @return the Exclusivity object
     */
    public Exclusivity exclusivity() {
        return exclusivity;
    }

    /**
     * Get Figure Categories
     * @return the set of Categories
     */
    public Set<Category> categories() {
        return categories;
    }

    /**
     * Get Figure Show Designer
     * @return the ShowDesigner object
     */
    public ShowDesigner showDesigner() {
        return showDesigner;
    }

    /**
     * equals method to compare two Figure objects.
     *
     * @param o the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(final Object o) {
        return DomainEntities.areEqual(this, o);
    }

    /**
     * sameAs method to verify if the Figure is the same as another object.
     * @param other the other object to compare with this object
     * @return true if the objects are the same, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        final Figure figure = (Figure) other;
        return this.equals(figure) && figureID.equals(figure.figureID());
    }

    /**
     * hashCode method to generate a hash code for the Figure object.
     * @return the hash code for the Figure object
     */
    @Override
    public FigureID identity() {
        return figureID;
    }
}
