package core.ShowProposal.domain.Entities;

import core.Figure.domain.Entities.Figure;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class ShowConfigurationFigure implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Figure figure;

    @Version
    private Long version;

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