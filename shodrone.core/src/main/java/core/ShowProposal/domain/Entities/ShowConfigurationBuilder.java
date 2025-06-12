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

    public ShowConfiguration build() {
        ShowConfiguration configuration = new ShowConfiguration();

        for (ShowConfigurationEntry entry : showConfiguration) {
            configuration.addDrone(entry);
        }
        return configuration;
    }
}
