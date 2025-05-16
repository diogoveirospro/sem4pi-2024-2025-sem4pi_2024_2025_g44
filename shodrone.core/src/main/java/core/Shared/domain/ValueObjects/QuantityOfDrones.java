package core.Shared.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class QuantityOfDrones implements Serializable, ValueObject {

    private static final long serialVersionUID = 1L;

    private Integer quantityOfDrones;

    protected QuantityOfDrones(){}

    public QuantityOfDrones(String quantityOfDrones) {
        int n = Integer.parseInt(quantityOfDrones);
        Preconditions.ensure(n > 0 && n < Integer.MAX_VALUE, "Invalid Quantity of Drones");
        this.quantityOfDrones = n;
    }

    public Integer getQuantityOfDrones() {
        return quantityOfDrones;
    }

    @Override
    public String toString() {
        return Integer.toString(quantityOfDrones);
    }
}
