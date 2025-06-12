package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.ShowProposal.domain.ValueObjects.ShowDSLDescription;
import eapli.framework.domain.model.DomainEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
public class ShowConfiguration implements Serializable, DomainEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "drones")
    private List<ShowConfigurationEntry> configurationDrones = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "show_configuration_figures",
            joinColumns = @JoinColumn(name = "configuration_id") // Column in the join table referencing ShowConfiguration
    )
    private List<ShowConfigurationFigure> figures = new ArrayList<>();

    @Embedded
    @Column(name = "show_dsl_description")
    private ShowDSLDescription showDSLDescription;

    protected ShowConfiguration() {
        // Required by JPA
    }

    public ShowConfiguration(ShowConfigurationBuilder builder) {
        this.configurationDrones = new ArrayList<>(builder.showConfiguration());
    }

    public ShowConfiguration(List<ShowConfigurationEntry> configurationDrones, List<ShowConfigurationFigure> figures) {
        this.configurationDrones = configurationDrones;
        this.figures = figures;
    }

    public List<ShowConfigurationEntry> showConfiguration() {
        return configurationDrones;
    }

    public Set<Model> droneModels() {
        Set<Model> droneModels = new HashSet<>();
        for (ShowConfigurationEntry entry : configurationDrones) {
            if (entry.drone() == null || entry.model() == null) {
                throw new IllegalStateException("Drone or its model cannot be null in ShowConfigurationEntry");
            }
            droneModels.add(entry.model());
        }
        return droneModels;
    }

    public List<Figure> figures() {
        List<Figure> figureList = new ArrayList<>();
        for (ShowConfigurationFigure figure : figures) {
            if (figure == null || figure.figure() == null) {
                throw new IllegalStateException("Figure cannot be null in ShowConfigurationFigure");
            }
            figureList.add(figure.figure());
        }
        return Collections.unmodifiableList(figureList);
    }

    public ShowDSLDescription showDSLDescription() {
        return showDSLDescription;
    }

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
        return Objects.equals(configurationDrones, that.configurationDrones);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    public void addFigures(List<Figure> figures) {
        if (figures == null || figures.isEmpty()) {
            throw new IllegalArgumentException("Figures collection cannot be null or empty.");
        }
        for (Figure figure : figures) {
            addFigure(figure);
        }
    }

    private void addFigure(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null.");
        }
        if (!figures.isEmpty() && figures.get(figures.size() - 1).figure().equals(figure)) {
            throw new IllegalArgumentException("Cannot add the same figure consecutively.");
        }
        figures.add(new ShowConfigurationFigure(figure));
    }

    public void removeFigure(ShowConfigurationFigure figure) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null.");
        }
        if (!this.figures.remove(figure)) {
            throw new IllegalStateException("Figure not found in the configuration.");
        }
    }

    public void addDrone(ShowConfigurationEntry entry) {
        if (entry == null || entry.drone() == null || entry.model() == null) {
            throw new IllegalArgumentException("Drone and its model cannot be null in ShowConfigurationEntry");
        }
        configurationDrones.add(entry);
    }
}