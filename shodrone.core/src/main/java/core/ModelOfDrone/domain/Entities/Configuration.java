package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "configuration")
public class Configuration implements Serializable, ValueObject {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "wind_tolerance_config", joinColumns = @JoinColumn(name = "configuration_id"))
    @MapKeyColumn(name = "position_tolerance")
    @Column(name = "wind_speed")
    private Map<PositionTolerance, WindSpeed> config = new LinkedHashMap<>();

    protected Configuration() {
        // for JPA
    }

    public Configuration(Map<PositionTolerance, WindSpeed> config) {
        if (config == null || config.isEmpty()) {
            throw new IllegalArgumentException("Configuration cannot be null or empty");
        }
        this.config = new LinkedHashMap<>(config); // Preserve order
    }

    public Map<PositionTolerance, WindSpeed> config() {
        return new LinkedHashMap<>(config);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Wind Tolerance:\n");
        for (Map.Entry<PositionTolerance, WindSpeed> entry : config.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Configuration)) return false;
        Configuration that = (Configuration) o;
        return Objects.equals(config, that.config);
    }

    @Override
    public int hashCode() {
        return Objects.hash(config);
    }
}
