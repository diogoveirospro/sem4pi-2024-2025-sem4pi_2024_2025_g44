package core.ShowProposal.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import eapli.framework.domain.model.DomainEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "show_configuration")
public class ShowConfiguration implements Serializable, DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "show_proposal_config", joinColumns = @JoinColumn(name = "configuration_id"))
    @MapKeyJoinColumn(name = "model_id")
    @Column(name = "drone_list")
    private Map<Model, List<Drone>> drones = new LinkedHashMap<>();

    @ManyToMany
    @JoinTable(
            name = "show_configuration_figures",
            joinColumns = @JoinColumn(name = "configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "figure_id")
    )
    private Set<Figure> figures = new LinkedHashSet<>();


    protected ShowConfiguration() {
    }

    public ShowConfiguration(Builder builder) {
        if (builder.drones.isEmpty()) {
            throw new IllegalArgumentException("Drone configuration cannot be empty");
        }
        this.drones = new LinkedHashMap<>(builder.drones);
        this.figures = new LinkedHashSet<>(builder.figures);
    }

    public Map<Model, List<Drone>> drones() {
        return Collections.unmodifiableMap(drones);
    }

    public Set<Model> droneModels() {
        return Collections.unmodifiableSet(drones.keySet());
    }

    public Set<Figure> figures() {
        return Collections.unmodifiableSet(figures);
    }

    @Transient
    public Map<Model, List<Drone>> dronesByModel() {
        Map<Model, List<Drone>> groupedDrones = new LinkedHashMap<>();
        for (Map.Entry<Model, List<Drone>> entry : drones.entrySet()) {
            groupedDrones.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        return groupedDrones;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ShowConfiguration that = (ShowConfiguration) other;
        return Objects.equals(id, that.id);
    }

    @Override
    public Long identity() {
        return id;
    }

    public static class Builder {
        private final Map<Model, List<Drone>> drones = new LinkedHashMap<>();
        private final Set<Figure> figures = new LinkedHashSet<>();

        public Builder addDrones(Model model, List<Drone> droneList) {
            this.drones.put(model, new ArrayList<>(droneList));
            return this;
        }

        public Builder addDrone(Model model, Drone drone) {
            this.drones.computeIfAbsent(model, k -> new ArrayList<>()).add(drone);
            return this;
        }

        public Builder addFigure(Figure figure) {
            this.figures.add(figure);
            return this;
        }

        public Builder addFigures(Collection<Figure> figures) {
            this.figures.addAll(figures);
            return this;
        }

        public ShowConfiguration build() {
            return new ShowConfiguration(this);
        }
    }
}