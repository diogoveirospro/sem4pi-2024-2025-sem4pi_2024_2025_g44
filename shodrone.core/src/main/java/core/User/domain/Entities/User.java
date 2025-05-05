package core.User.domain.Entities;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.infrastructure.authz.domain.model.Username;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class User implements Serializable, AggregateRoot<Username> {
    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    private Long id;


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Username identity() {
        return null;
    }
}
