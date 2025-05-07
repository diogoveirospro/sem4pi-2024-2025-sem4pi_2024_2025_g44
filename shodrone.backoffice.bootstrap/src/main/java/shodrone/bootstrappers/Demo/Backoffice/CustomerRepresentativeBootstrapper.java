package shodrone.bootstrappers.Demo.Backoffice;

import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.Position;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomerRepresentativeBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerRepresentativeBootstrapper.class);

    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();

    @Override
    public boolean execute() {
        register("John Doe", "johndoe@example.com", "+1", "123456789", "Manager");
        return true;
    }

    private void register(final String name, final String email, final String countryCode, final String phoneNumber, final String position) {
        try {
            Iterable<Customer> customers = controller.listCustomers();
            if (!customers.iterator().hasNext()) {
                LOGGER.warn("No customers available to assign a representative.");
                return;
            }

            Customer customer = customers.iterator().next(); // Seleciona o primeiro cliente disponível
            CustomerRepresentative representative = new CustomerRepresentative(
                    new Name(name),
                    new Email(email),
                    new PhoneNumber(countryCode, phoneNumber),
                    new Position(position),
                    customer
            );

            controller.addCustomerRepresentative(representative, customer);
            LOGGER.info("Successfully registered customer representative: {}", name);
        } catch (Exception e) {
            LOGGER.error("Error registering customer representative: {}", e.getMessage());
        }
    }
}