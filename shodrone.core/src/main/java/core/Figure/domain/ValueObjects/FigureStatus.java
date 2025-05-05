package core.Figure.domain.ValueObjects;

import jakarta.persistence.Embeddable;

/**
 * Represents the status of a Figure in the system.
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public enum FigureStatus {
    ACTIVE,
    DISABLE;
}
