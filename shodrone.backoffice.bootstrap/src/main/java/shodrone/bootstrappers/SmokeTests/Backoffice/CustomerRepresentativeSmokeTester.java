package shodrone.bootstrappers.SmokeTests.Backoffice;

import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.application.DeactivateCustomerRepresentativeController;
import core.Customer.application.EditCustomerRepresentativeController;
import core.Customer.application.ListCustomerRepresentativesController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import eapli.framework.actions.Action;
import eapli.framework.validations.Invariants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Smoke tests for Customer Representative management.
 */
public class CustomerRepresentativeSmokeTester implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerRepresentativeSmokeTester.class);

    private final AddCustomerRepresentativeController addController = new AddCustomerRepresentativeController();
    private final ListCustomerRepresentativesController listController = new ListCustomerRepresentativesController();
    private final EditCustomerRepresentativeController editController = new EditCustomerRepresentativeController();
    private final DeactivateCustomerRepresentativeController deactivateController = new DeactivateCustomerRepresentativeController();

    @Override
    public boolean execute() {
        testAddCustomerRepresentative();
        testListCustomerRepresentatives();
        testEditCustomerRepresentative();
        testDeactivateCustomerRepresentative();
        return true;
    }

    private void testAddCustomerRepresentative() {
        final Iterable<Customer> customers = addController.listCustomers();
        Invariants.nonNull(customers);
        final Customer customer = customers.iterator().next();

        final CustomerRepresentative representative = new CustomerRepresentative(
                new core.Shared.domain.ValueObjects.Name("Michael Smith"),
                new core.Shared.domain.ValueObjects.Email("michaelSmith@example.com"),
                new core.Shared.domain.ValueObjects.PhoneNumber("+1", "123456789"),
                new core.Customer.domain.ValueObjects.Position("Manager"),
                customer
        );

        addController.addCustomerRepresentative(representative, customer);
        LOGGER.info("»»» Added customer representative: {}", representative);
    }

    private void testListCustomerRepresentatives() {
        final Iterable<Customer> customers = listController.listCustomers();
        Invariants.nonNull(customers);
        final Customer customer = customers.iterator().next();

        final Iterable<CustomerRepresentative> representatives = listController.listRepresentativesOfCustomer(customer);
        Invariants.nonNull(representatives);
        LOGGER.info("»»» Listed customer representatives for customer {}: {}", customer, representatives);
    }

    private void testEditCustomerRepresentative() {
        final Iterable<Customer> customers = editController.listCustomers();
        Invariants.nonNull(customers);
        final Customer customer = customers.iterator().next();

        final Iterable<CustomerRepresentative> representatives = editController.listRepresentativesOfCustomer(customer);
        Invariants.nonNull(representatives);
        final CustomerRepresentative representative = representatives.iterator().next();

        editController.changeCustomerRepresentativeInfo(
                customer,
                representative,
                new core.Shared.domain.ValueObjects.Email("newemail@example.com"),
                new core.Shared.domain.ValueObjects.PhoneNumber("+1", "987654321")
        );
        LOGGER.info("»»» Edited customer representative: {}", representative);
    }

    private void testDeactivateCustomerRepresentative() {
        final Iterable<Customer> customers = deactivateController.listCustomers();
        Invariants.nonNull(customers);
        final Customer customer = customers.iterator().next();

        final Iterable<CustomerRepresentative> representatives = deactivateController.listRepresentativesOfCustomer(customer);
        Invariants.nonNull(representatives);
        final CustomerRepresentative representative = representatives.iterator().next();

        deactivateController.deactivateCustomerRepresentative(customer, representative);
        LOGGER.info("»»» Deactivated customer representative: {}", representative);
    }
}