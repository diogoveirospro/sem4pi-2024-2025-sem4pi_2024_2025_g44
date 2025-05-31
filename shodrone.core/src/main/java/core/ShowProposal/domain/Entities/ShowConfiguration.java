package core.ShowProposal.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.domain.model.DomainEntity;
import jakarta.persistence.*;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "show_configuration")
public class ShowConfiguration implements Serializable, DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "show_proposal_config", joinColumns = @JoinColumn(name = "configuration_id"))
    @MapKeyJoinColumn(name = "model_id") // model é chave estrangeira
    @Column(name = "drones")
    private Map<Model, List<Drone>> config = new LinkedHashMap<>();

    // Constructor privado usado pelo builder
    public ShowConfiguration(Builder builder) {
        if (builder.config == null || builder.config.isEmpty()) {
            throw new IllegalArgumentException("Configuration cannot be null or empty");
        }
        this.config = Map.copyOf(builder.config);
    }

    protected ShowConfiguration() {
        // Default constructor for JPA
    }

    public Map<Model, List<Drone>> drones() {
        return config;
    }

    public static class Builder {
        private final Map<Model, List<Drone>> config = new LinkedHashMap<>();

        public Builder addDrones(Model model, List<Drone> drones) {
            this.config.computeIfAbsent(model, k -> new ArrayList<>()).addAll(drones);
            return this;
        }

        public Map<Model, List<Drone>> getConfig() {
            return config;
        }

        public ShowConfiguration build() {
            return new ShowConfiguration(this);
        }
    }

    @Override
    public boolean sameAs(Object other) {
        return this == other;
    }

    @Override
    public Long identity() {
        return id;
    }
}

