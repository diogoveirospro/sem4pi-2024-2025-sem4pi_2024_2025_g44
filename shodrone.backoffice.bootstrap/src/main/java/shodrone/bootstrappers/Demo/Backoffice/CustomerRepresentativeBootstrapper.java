package shodrone.bootstrappers.Demo.Backoffice;

import core.Customer.application.AddCustomerRepresentativeController;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.Position;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

public class CustomerRepresentativeBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerRepresentativeBootstrapper.class);
    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    @Override
    public boolean execute() {

        register("Andrew Davis", "andrewdavis@easports.com", "+351", "910000002",
                "Collaborator", "EA Sports", "eaUser2", "PasswordEA2");

        register("Yves Grievance", "yves.griv@ubisoft.com", "+33", "920000002",
                "Manager", "Ubisoft", "ubisoftUser2", "PasswordUBI2");

        register("Shuntaro Furacon", "shuntaro.furacao@nintendo.com", "+376", "930000002",
                "International Manager", "Nintendo", "nintendoUser2", "PasswordNIN2");

        register("Kenichiro Yoshi", "kenichiro.yoshi@sony.com", "+420", "940000002",
                "Intern", "Sony", "sonyUser2", "PasswordSONY2");

        register("Satya Nada", "satya.nada@microsoft.com", "+377", "950000002",
                "Human Recourses Manager", "Microsoft", "microsoftUser2", "PasswordMS2");

        return true;
    }

    private void register(final String name, final String email, final String countryCode, final String phoneNumber,
                          final String position, final String customerName, final String username, final String password) {
        try {

            Name customerNameObj = new Name(customerName);
            Customer customer = customerRepository.findCustomerByName(customerNameObj);
            if (customer == null) {
                throw new IllegalArgumentException("Customer not found: " + customerName);
            }

            CustomerRepresentative representative = new CustomerRepresentative(
                    new Name(name),
                    new Email(email),
                    new PhoneNumber(countryCode, phoneNumber),
                    new Position(position),
                    customer
            );

            controller.addCustomerRepresentative(representative, customer, username, password, new PhoneNumber(countryCode, phoneNumber));

            LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered representative {} for customer " +
                    "{}" + UtilsUI.RESET, name, customer.name());

        } catch (Exception e) {
            LOGGER.error(UtilsUI.BOLD + UtilsUI.RED + "Failed to register representative {}: {}" + UtilsUI.RESET,
                    name, e.getMessage());
        }

    }
}
