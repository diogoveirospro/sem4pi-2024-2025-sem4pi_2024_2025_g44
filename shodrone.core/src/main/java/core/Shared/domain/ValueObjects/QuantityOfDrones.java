package core.Shared.domain.ValueObjects;

import eapli.framework.validations.Preconditions;

public class QuantityOfDrones {

    private Integer quantityOfDrones;

    public QuantityOfDrones(String quantityOfDrones) {
        int n = Integer.parseInt(quantityOfDrones);
        Preconditions.ensure(n > 0 && n < Integer.MAX_VALUE, "Invalid Quantity of Drones");
        this.quantityOfDrones = n;
    }

    public Integer getQuantityOfDrones() {
        return quantityOfDrones;
    }
}
