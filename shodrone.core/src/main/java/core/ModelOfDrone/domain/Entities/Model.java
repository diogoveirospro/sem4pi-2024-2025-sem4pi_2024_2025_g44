package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class Model implements Serializable, AggregateRoot<Designation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_name")

    @Embedded
    private ModelName modelName;

    private Configuration configuration;

    protected Model() {
        // for ORM
    }

    public Model(ModelName modelName, Configuration configuration) {
        if (modelName == null || configuration == null) {
            throw new IllegalArgumentException("Model name and wind tolerance must not be null");
        }
        this.modelName = modelName;
        this.configuration = configuration;
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof Model)) return false;
        Model model = (Model) other;
        return Objects.equals(modelName, model.modelName);
    }

    @Override
    public ModelName identity() {
        return modelName;
    }

    @Override
    public String toString() {
        return "Model{" +
                ", modelName=" + modelName +
                ", configuration=" + configuration +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(modelName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Model)) return false;
        Model model = (Model) o;
        return Objects.equals(modelName, model.modelName);
    }
}
