package shodrone.bootstrappers.Demo.Backoffice;

import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerBootstrapper.class);


    @Override
    public boolean execute() {
        register("EA Sports","209 Redwood Shores Parkway, Redwood City, CA 94065, USA", "PT13872527904", CustomerType.VIP);
        return true;
    }

    private void register(final String name, final String address, final String taxNumber, final CustomerType customerType) {
        Address addressObj = new Address(address);
        VatNumber taxNumberObj = new VatNumber(taxNumber);


    }
}
