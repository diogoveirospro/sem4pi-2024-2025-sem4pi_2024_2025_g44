package core.ModelOfDrone.domain.ValueObjects;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public enum SafetyStatus {
    SAFE,
    UNSAFE;
}
