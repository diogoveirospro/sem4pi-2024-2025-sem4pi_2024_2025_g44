package core.ShowProposal.domain.Entities;

import eapli.framework.domain.model.DomainEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "show_configuration")
public class ShowConfiguration implements Serializable, DomainEntity<Long> {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    /**
     * The date and time when the Figure was created
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    @Override
    public Long identity() {
        return 0L;
    }
}
