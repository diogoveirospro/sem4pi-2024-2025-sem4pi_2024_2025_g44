package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Model implements Serializable, AggregateRoot<Designation> {
    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private Long id;
    @Embedded
    private ModelName modelName;
    @Embedded
    private  WindTolerance windTolerance;
    @Embedded
    private  WindSpeed windSpeed;
    @Embedded
    private  PositionTolerance positionTolerance;
    @Enumerated(EnumType.STRING)
    private  SafetyStatus safetyStatus;

    protected Model() {
        // for ORM
    }
    public Model(ModelName modelName, WindTolerance windTolerance, WindSpeed windSpeed,
                 PositionTolerance positionTolerance, SafetyStatus safetyStatus) {
        this.modelName = modelName;
        this.windTolerance = windTolerance;
        this.windSpeed = windSpeed;
        this.positionTolerance = positionTolerance;
        this.safetyStatus = safetyStatus;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Model model = (Model) other;
        return modelName != null && modelName.equals(model.modelName);
    }

    @Override
    public ModelName identity() {
        return modelName;
    }
}
