package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ShowConfigurationFigure implements Serializable {

    @ManyToOne(optional = false)
    private Figure figure;

    protected ShowConfigurationFigure() {
        // For JPA
    }

    public ShowConfigurationFigure(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null");
        }
        this.figure = figure;
    }

    public Figure figure() {
        return figure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShowConfigurationFigure that = (ShowConfigurationFigure) o;
        return Objects.equals(figure, that.figure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(figure);
    }
}