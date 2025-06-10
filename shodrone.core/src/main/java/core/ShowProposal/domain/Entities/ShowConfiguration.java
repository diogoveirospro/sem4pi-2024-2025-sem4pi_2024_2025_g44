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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "show_configuration_id")
    private List<ShowConfigurationEntry> showConfiguration = new ArrayList<>();

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowConfigurationFigure> configurationFigures = new ArrayList<>();

    @Embedded
    @Column(name = "show_dsl_description")
    private ShowDSLDescription showDSLDescription;

    protected ShowConfiguration() {
        // Required by JPA
    }

    public ShowConfiguration(ShowConfigurationBuilder builder) {
        this.showConfiguration = new ArrayList<>(builder.showConfiguration());
        for (int i = 0; i < builder.figures().size(); i++) {
            this.configurationFigures.add(new ShowConfigurationFigure(this, builder.figures().get(i), i));
        }
    }

    public ShowConfiguration(List<ShowConfigurationEntry> showConfiguration, List<Figure> figures) {
        this.showConfiguration = showConfiguration;
        for (int i = 0; i < figures.size(); i++) {
            this.configurationFigures.add(new ShowConfigurationFigure(this, figures.get(i), i));
        }
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

    public List<Figure> figures() {
        configurationFigures.sort(Comparator.comparingInt(ShowConfigurationFigure::getOrder));
        List<Figure> figures = new ArrayList<>();
        for (ShowConfigurationFigure configFigure : configurationFigures) {
            figures.add(configFigure.getFigure());
        }
        return figures;
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
        return Objects.equals(showConfiguration, that.showConfiguration);
    }

    @Override
    public Long identity() {
        return this.id;
    }

    public void addFigure(Figure figure, int i) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null");
        }
        if (i < 0 || i > configurationFigures.size()) {
            throw new IndexOutOfBoundsException("Index out of bounds for adding figure");
        }
        ShowConfigurationFigure configFigure = new ShowConfigurationFigure(this, figure, i);
        configurationFigures.add(i, configFigure);
    }
}