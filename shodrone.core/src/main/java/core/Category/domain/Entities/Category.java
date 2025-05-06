package core.Category.domain.Entities;

import core.Shared.domain.ValueObjects.Description;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
    private Name name;

    @Embedded
    public Description description;

    /**
     * Default constructor for JPA
     */
    protected Category() {

    }

    public Category(Name name, Description description) {
        this.name = name;
        this.description = description;
    }

    public Name name() {
        return name;
    }

    public Description description() {
        return description;
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
