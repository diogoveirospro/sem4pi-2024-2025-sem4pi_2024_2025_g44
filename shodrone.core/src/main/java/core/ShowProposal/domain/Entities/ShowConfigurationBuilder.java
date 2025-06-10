package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;

import java.util.*;

public class ShowConfigurationBuilder {
    private final List<ShowConfigurationEntry> showConfiguration = new ArrayList<>();
    private final List<Figure> figures = new ArrayList<>();

    public List<ShowConfigurationEntry> showConfiguration() {
        return Collections.unmodifiableList(this.showConfiguration);
    }

    public List<Figure> figures() {
        return Collections.unmodifiableList(this.figures);
    }

    public ShowConfigurationBuilder addDrones(ShowConfigurationEntry showConfigurationEntry) {
        this.showConfiguration.add(showConfigurationEntry);
        return this;
    }

    public ShowConfigurationBuilder addFigure(Figure figure) {
        if (!figures.isEmpty() && figures.get(figures.size() - 1).equals(figure)) {
            throw new IllegalArgumentException("Cannot add the same figure consecutively.");
        }
        this.figures.add(figure);
        return this;
    }

    public ShowConfigurationBuilder addFigures(Collection<Figure> figures) {
        for (Figure figure : figures) {
            addFigure(figure);
        }
        return this;
    }

    public ShowConfiguration build() {
        ShowConfiguration configuration = new ShowConfiguration();
        for (int i = 0; i < figures.size(); i++) {
            configuration.addFigure(figures.get(i), i);
        }
        return configuration;
    }
}
