package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;
import jakarta.persistence.*;

@Entity
public class ShowConfigurationFigure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShowConfiguration configuration;

    @ManyToOne(fetch = FetchType.LAZY)
    private Figure figure;

    @Column(name = "figure_order", nullable = false)
    private int order;

    protected ShowConfigurationFigure() {
        // Required by JPA
    }

    public ShowConfigurationFigure(ShowConfiguration configuration, Figure figure, int order) {
        this.configuration = configuration;
        this.figure = figure;
        this.order = order;
    }

    public int getOrder() {
        return order;
    }

    public Figure getFigure() {
        return figure;
    }
}
