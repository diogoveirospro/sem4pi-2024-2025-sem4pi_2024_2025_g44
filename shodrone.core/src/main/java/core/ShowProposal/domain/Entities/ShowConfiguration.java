package core.ShowProposal.domain.Entities;

import core.Drone.domain.Entities.Drone;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.domain.ValueObjects.ShowConfigurationEntry;
import core.ShowProposal.domain.ValueObjects.ShowDSLDescription;
import eapli.framework.domain.model.DomainEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.security.DrbgParameters;
import java.util.*;

@Entity
public class ShowConfiguration implements Serializable, DomainEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com ShowConfigurationEntry
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "show_configuration_id") // FK na tabela ShowConfigurationEntry
    private List<ShowConfigurationEntry> showConfiguration = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "show_configuration_figures",
            joinColumns = @JoinColumn(name = "configuration_id"),
            inverseJoinColumns = @JoinColumn(name = "figure_id")
    )
    private Set<Figure> figures = new LinkedHashSet<>();

    @Embedded
    @Column(name = "show_dsl_description")
    private ShowDSLDescription showDSLDescription;

    protected ShowConfiguration() {
        // Required by JPA
    }

    public ShowConfiguration(Builder builder) {
        if (builder.showConfiguration.isEmpty()) {
            throw new IllegalArgumentException("Drone configuration cannot be empty");
        }
        this.showConfiguration = new ArrayList<>(showConfiguration);
        this.figures = new LinkedHashSet<>(builder.figures);
    }

    public List<ShowConfigurationEntry> showConfiguration() {
        return showConfiguration;
    }

    public Set<Model> droneModels() {
        Set<Model> droneModels = new HashSet<>();
        for (ShowConfigurationEntry entry : showConfiguration) {
            if (entry.drone() == null || entry.model() == null) {
                throw new IllegalStateException("Drone or its model cannot be null in ShowConfigurationEntry");
            }
           droneModels.add(entry.model());
        }
        return droneModels;
    }

    /**
     * Returns the set of figures associated with this ShowConfiguration.
     * @return a Set of Figure objects
     */
    public Set<Figure> figures() {
        return figures;
    }

    /**
     * Returns the ShowDSLDescription associated with this ShowConfiguration.
     * @return the ShowDSLDescription object
     */
    public ShowDSLDescription showDSLDescription() {
        return showDSLDescription;
    }

    /**
     * Adds a ShowDSLDescription to the ShowConfiguration.
     * @param showDSLDescription the ShowDSLDescription to add
     */
    public void addShowDSLDescription(ShowDSLDescription showDSLDescription) {
        if (showDSLDescription == null) {
            throw new IllegalArgumentException("Show DSL Description cannot be null");
        }
        this.showDSLDescription = showDSLDescription;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowConfiguration)) return false;
        ShowConfiguration that = (ShowConfiguration) other;
        return Objects.equals(showConfiguration, that.showConfiguration);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    /**
     * Builder pattern for constructing ShowConfiguration.
     */
    public static class Builder {
        private final List<ShowConfigurationEntry> showConfiguration = new ArrayList<>();
        private final Set<Figure> figures = new LinkedHashSet<>();

        public Builder addDrones(ShowConfigurationEntry showConfigurationEntry) {
            this.showConfiguration.add(showConfigurationEntry);
            return this;
        }

        public ShowConfiguration build() {
            return new ShowConfiguration(this);
        }
        public List<ShowConfigurationEntry> showConfiguration() {
            return showConfiguration;
        }

        public Builder addFigure(Figure figure) {
            this.figures.add(figure);
            return this;
        }
        public Builder addFigures(Collection<Figure> figures) {
            this.figures.addAll(figures);
            return this;
        }
    }
}