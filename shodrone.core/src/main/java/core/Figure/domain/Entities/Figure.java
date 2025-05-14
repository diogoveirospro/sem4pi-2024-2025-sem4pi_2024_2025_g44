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
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Represents a Figure in the system.
 * This class is immutable and implements AggregateRoot and Serializable interfaces.
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code", "figureVersion"})
})
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
    @Column(name = "id")
    private Long id;

    /**
     * The version of the Figure in the system
     */
    @Version
    private Long version;

    /**
     * The date and time when the Figure was created
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the Figure was last updated
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The ID of the Figure with the code and version
     */
    @Embedded
    private FigureID figureID;

    /**
     * The description of the Figure
     */
    @Embedded
    @Column(name = "description")
    private Description description;

    /**
     * The DSL description of the Figure
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "DSLCodeLines", column = @Column(name = "DSLCodeLines")),
            @AttributeOverride(name = "DSLVersion", column = @Column(name = "DSLVersion"))
    })
    private DSLDescription DSLDescription;

    /**
     * The status of the Figure
     */
    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "figureStatus")
    private FigureStatus figureStatus;

    /**
     * The keywords associated with the Figure
     */
    @ElementCollection
    @Column(name = "keywords")
    private Set<Keyword> keywords;

    /**
     * The exclusivity of the Figure
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "exclusivity")
    private Exclusivity exclusivity;

    /**
     * The categories associated with the Figure
     */
    @ManyToMany
    @JoinTable(name = "categories")
    private Set<Category> categories;

    /**
     * The Show Designer associated with the Figure
     */
    @ManyToOne (optional = false)
    @JoinColumn(name = "showDesigner")
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
                  DSLDescription DSLDescription, Set<Keyword> keywords, Set<Category> categories, ShowDesigner showDesigner) {

        assert code != null && version != null && description != null && DSLDescription != null &&
                keywords != null && categories != null && showDesigner != null;
        this.figureID = new FigureID(code, version);
        this.description = description;
        this.DSLDescription = DSLDescription;
        this.figureStatus = FigureStatus.ACTIVE;
        this.keywords = keywords;
        this.categories = categories;
        this.exclusivity = null;
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
                  DSLDescription DSLDescription, Set<Keyword> keywords, Set<Category> categories, ShowDesigner showDesigner,
                  Exclusivity exclusivity) {

        assert code != null && version != null && description != null && DSLDescription != null &&
                keywords != null && categories != null && showDesigner != null;
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
     * Method to update the updatedAt field before updating the entity
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Method to set the createdAt and updatedAt fields before persisting the entity
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
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
     * Allows to know if the Figure is exclusive or not
     * @return true if the Figure is exclusive, false otherwise
     */
    public boolean isExclusive() {
        return exclusivity != null;
    }

    /**
     * Allows to know if the Figure is active or not
     * @return true if the Figure is active, false otherwise
     */
    public boolean isActive() {
        return figureStatus == FigureStatus.ACTIVE;
    }

    /**
     * Checks if the Figure matches a given category.
     * @param category the category to check
     * @return true if the Figure matches the category, false otherwise
     */
    public boolean matchesCategory(String category) {
        if (category == null || category.isEmpty() || figureStatus == FigureStatus.DISABLE) {
            return false;
        }

        String normalizedInput = normalize(category);

        for (Category c : categories) {
            if (normalize(c.name().toString()).equals(normalizedInput)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the Figure matches a given keyword.
     * @param term the term to check
     * @return true if the Figure matches the keyword, false otherwise
     */
    public boolean matchesKeyword(String term) {
        if (term == null || term.isEmpty() || figureStatus == FigureStatus.DISABLE) {
            return false;
        }

        String normalizedInput = normalize(term);

        for (Keyword k : keywords) {
            if (normalize(k.toString()).equals(normalizedInput)) {
                return true;
            }
        }
        return false;
    }

    private String normalize(String input) {
        return Normalizer.normalize(input, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .toLowerCase();
    }

    /**
     * Decommissions the Figure by changing its status to DISABLE.
     * @return true if the Figure was successfully decommissioned, false otherwise
     */
    public boolean decommission() {
        if (figureStatus == FigureStatus.ACTIVE) {
            figureStatus = FigureStatus.DISABLE;
            return true;
        }
        throw new IllegalStateException("Figure is already decommissioned");
    }

    /**
     * Checks if the Figure is the same as another object.
     * @param other the object to compare
     * @return true if the Figure is the same as the other object, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Figure)) return false;
        Figure figure = (Figure) other;

        return figureID.equals(figure.figureID()) &&
                description.equals(figure.description()) &&
                DSLDescription.equals(figure.DSLDescription()) &&
                figureStatus.equals(figure.figureStatus()) &&
                keywords.equals(figure.keywords()) &&
                categories.equals(figure.categories()) &&
                ((exclusivity == null && figure.exclusivity() == null) ||
                        (exclusivity != null && exclusivity.equals(figure.exclusivity()))) &&
                showDesigner.equals(figure.showDesigner());

    }

    /**
     * Equals method to compare two Figure objects.
     * @param o the object to compare
     * @return true if the two objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        return DomainEntities.areEqual(this, o);
    }

    /**
     * Compares this Figure object with another Figure object.
     * @param other the object to be compared.
     * @return 1 if this Figure is greater than the other, -1 if less, 0 if equal
     */
    @Override
    public int compareTo(FigureID other) {
        return AggregateRoot.super.compareTo(other);
    }

    /**
     * hashCode method to generate a hash code for the Figure object.
     * @return the hash code for the Figure object
     */
    @Override
    public FigureID identity() {
        return figureID;
    }

    /**
     * Checks if the Figure has the same identity as another Figure.
     * @param id the FigureID to compare
     * @return true if the Figure has the same identity as the other Figure, false otherwise
     */
    @Override
    public boolean hasIdentity(FigureID id) {
        return AggregateRoot.super.hasIdentity(id);
    }

    /**
     * Hash code method to generate a hash code for the Figure object.
     * @return the hash code for the Figure object
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * toString method to generate a string representation of the Figure object.
     * @return the string representation of the Figure object
     */
    @Override
    public String toString() {
        String exclusivity = isExclusive()
                ? exclusivity().customer().name().toString()
                : "None";

        return String.format("Code: %s | Version: %s | Description: %s | Exclusivity: %s",
                identity().code(), identity().version(), description(), exclusivity);
    }

}
