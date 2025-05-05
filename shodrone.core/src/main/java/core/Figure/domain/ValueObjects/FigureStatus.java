package core.Figure.domain.ValueObjects;

import jakarta.persistence.Embeddable;

@Embeddable
public enum FigureStatus {
    ACTIVE,
    DISABLE;
}
