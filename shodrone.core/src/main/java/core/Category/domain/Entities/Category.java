package core.Category.domain.Entities;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category implements AggregateRoot<Long> {
    @Id
    private Long id;

    private Designation name;

    public Description description;

    public Category(Designation name, Description description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return 0L;
    }
}
