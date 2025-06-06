package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;
import core.ShowProposal.domain.ValueObjects.ShowConfigurationEntry;
import java.util.*;

public class ShowConfigurationBuilder {
    private final List<ShowConfigurationEntry> showConfiguration = new ArrayList<>();
    private final Set<Figure> figures = new LinkedHashSet<>();

    public List<ShowConfigurationEntry> showConfiguration() {
        return Collections.unmodifiableList(this.showConfiguration);
    }

    public Set<Figure> figures() {
        return Collections.unmodifiableSet(this.figures);
    }

    public ShowConfigurationBuilder addDrones(ShowConfigurationEntry showConfigurationEntry) {
        this.showConfiguration.add(showConfigurationEntry);
        return this;
    }

    public ShowConfigurationBuilder addFigure(Figure figure) {
        this.figures.add(figure);
        return this;
    }

    public ShowConfigurationBuilder addFigures(Collection<Figure> figures) {
        this.figures.addAll(figures);
        return this;
    }

    public ShowConfiguration build() {
        return new ShowConfiguration(showConfiguration, figures);
    }
}
