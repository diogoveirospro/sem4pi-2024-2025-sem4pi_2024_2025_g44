package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.*;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.general.domain.model.Designation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "t_model")
public class Model implements Serializable, AggregateRoot<Designation> {

    @Id
    @GeneratedValue()
    private Long id;

    @Embedded
    @AttributeOverride(name = "modelName", column = @Column(name = "model_name"))
    private ModelName modelName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration")
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

    public Configuration getConfiguration() {
        return configuration;
    }
}
