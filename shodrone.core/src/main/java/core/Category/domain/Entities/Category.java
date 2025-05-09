package core.Category.domain.Entities;

import core.Category.domain.ValueObjects.CategoryName;
import core.Category.domain.ValueObjects.CategoryStatus;
import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Category implements AggregateRoot<Long> {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    public CategoryName name;

    @Embedded
    public Description description;

    @Enumerated(EnumType.STRING)
    public CategoryStatus status;

    public Date creationDate;

    public Date lastUpdateDate;

    /**
     * Default constructor for JPA
     */
    protected Category() {

    }

    /**
     * Constructor to create a Category object.
     *
     * @param name        the name of the category
     * @param description the description of the category
     */
    public Category(CategoryName name, Description description) {
        this.name = name;
        this.description = description;
        this.status = CategoryStatus.ACTIVE;
        this.creationDate = new Date();
        this.lastUpdateDate = new Date();
    }

    public CategoryName name() {
        return name;
    }

    public Description description() {
        return description;
    }

    public Date creationDate() {
        return creationDate;
    }

    public Date lastUpdateDate() {
        return lastUpdateDate;
    }

    public CategoryStatus status() {
        return status;
    }

    public void changeStatus(CategoryStatus status) {
        this.status = status;
        this.lastUpdateDate = new Date();
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category that = (Category) o;
        return Objects.equals(this.name, that.name) && Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
