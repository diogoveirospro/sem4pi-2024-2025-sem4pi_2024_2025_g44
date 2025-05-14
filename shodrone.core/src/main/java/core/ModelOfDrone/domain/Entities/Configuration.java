package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
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
    private Map<WindSpeed, PositionTolerance> config = new LinkedHashMap<>();

    @Embedded
    @Enumerated(EnumType.STRING)
    @Column(name = "safetyStatus")
    private SafetyStatus safetyStatus;
    protected Configuration() {
        // for JPA
    }

    public Configuration(Map<WindSpeed, PositionTolerance> config, SafetyStatus safetyStatus) {
        if (config == null || config.isEmpty()) {
            throw new IllegalArgumentException("Configuration cannot be null or empty");
        }
        this.config = new LinkedHashMap<>(config); // Preserve order
        this.safetyStatus = safetyStatus;
    }

    public Map<WindSpeed, PositionTolerance> config() {
        return new LinkedHashMap<>(config);
    }

    public PositionTolerance getToleranceForWindSpeed(int windSpeed) {
        for (WindSpeed windSpeedTest : config.keySet()) {
            if (windSpeedTest.minSpeed() < windSpeed && windSpeedTest.maxSpeed() >= windSpeed) {
                return config.get(windSpeedTest);
            }
        }
        return null; // or throw an exception if no match is found
    }

    public void setSafetyStatus(int windSpeed) {
        for (WindSpeed windSpeedTest : config.keySet()) {
            if (windSpeedTest.maxSpeed() == 999) {
                if (windSpeed > windSpeedTest.minSpeed()) {
                    this.safetyStatus = SafetyStatus.UNSAFE;
                    break;
                }
            }
        }
        this.safetyStatus = SafetyStatus.SAFE;
    }

    public SafetyStatus getSafetyStatus() {
        return safetyStatus;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Wind Tolerance:\n");
        for (Map.Entry<WindSpeed, PositionTolerance> entry : config.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        sb.append("Safety Status: ").append(safetyStatus);
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
