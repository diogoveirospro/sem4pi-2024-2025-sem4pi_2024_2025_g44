package core.ShowProposal.domain.ValueObjects;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ShowConfigurationEntry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Model model;

    @ManyToOne(optional = false)
    private Drone drone;

    protected ShowConfigurationEntry() {
        // For JPA
    }

    public ShowConfigurationEntry(Model model, Drone drone) {
        if (model == null || drone == null)
            throw new IllegalArgumentException("Model and Drone cannot be null");
        this.model = model;
        this.drone = drone;
    }

    public Model model() {
        return model;
    }

    public Drone drone() {
        return drone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowConfigurationEntry that = (ShowConfigurationEntry) o;
        return Objects.equals(model, that.model) && Objects.equals(drone, that.drone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, drone);
    }
}

