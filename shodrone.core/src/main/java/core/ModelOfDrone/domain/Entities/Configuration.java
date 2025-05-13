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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "wind_tolerance_config", joinColumns = @JoinColumn(name = "wind_tolerance_id"))
    @MapKeyColumn(name = "position_tolerance")
    private Map<PositionTolerance, WindSpeed> config = new LinkedHashMap<>();

    protected Configuration() {
        // for JPA
    }

    public Configuration(Map<PositionTolerance, WindSpeed> config) {
        if (config == null || config.isEmpty()) {
            throw new IllegalArgumentException("Configuration cannot be null or empty");
        }
        this.config = new HashMap<>(config);
    }

    public Map<PositionTolerance, WindSpeed> config() {
        return new HashMap<>(config);
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
