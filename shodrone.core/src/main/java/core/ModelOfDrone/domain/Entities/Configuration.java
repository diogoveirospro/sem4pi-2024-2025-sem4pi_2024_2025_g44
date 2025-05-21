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
        return config;
    }

    public void setConfig(Map<WindSpeed, PositionTolerance> configuration) {
        this.config = configuration;
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
                     setStatus(SafetyStatus.UNSAFE);
                }else {
                    setStatus(SafetyStatus.SAFE);
                }
            }
        }
    }

    public SafetyStatus getSafetyStatus() {
        return safetyStatus;
    }

    public void setStatus(SafetyStatus safetyStatus) {
        this.safetyStatus = safetyStatus;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        config.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> e.getKey().minSpeed()))
                .forEach(entry -> {
                    int min = entry.getKey().minSpeed();
                    int max = entry.getKey().maxSpeed();
                    double tolerance = entry.getValue().value();

                    if (tolerance == 0.0) {
                        if (min == 0) {
                            sb.append("wind <= ").append(max).append(" -> 0m\n");
                        } else {
                            sb.append(min).append(" < wind <= ").append(max).append(" -> 0m\n");
                        }
                    } else if (tolerance == -1.0) {
                        sb.append(min).append(" < wind -> Not safe to fly\n");
                        sb.append("Safety Status: Safe when wind speed is less than ").append(min).append("m/s\n");
                    } else {
                        sb.append(min).append(" < wind <= ").append(max).append(" -> ").append(tolerance).append("m\n");
                    }
                });
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
