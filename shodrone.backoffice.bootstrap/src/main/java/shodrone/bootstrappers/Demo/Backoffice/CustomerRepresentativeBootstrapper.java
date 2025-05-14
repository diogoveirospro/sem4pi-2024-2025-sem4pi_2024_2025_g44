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

        register("Andrew Wilson", "andrewwilson@easports.com", "+351", "910000001",
                "CEO", "EA Sports", "eaUser1", "PasswordEA1");

        register("Yves Guillemot", "yves.guillemot@ubisoft.com", "+33", "920000001",
                "CEO", "Ubisoft", "ubisoftUser1", "PasswordUBI1");

        register("Shuntaro Furukawa", "shuntaro.furukawa@nintendo.com", "+376", "930000001",
                "President", "Nintendo", "nintendoUser1", "PasswordNIN1");

        register("Kenichiro Yoshida", "kenichiro.yoshida@sony.com", "+420", "940000001",
                "CEO", "Sony", "sonyUser1", "PasswordSONY1");

        register("Satya Nadella", "satya.nadella@microsoft.com", "+377", "950000001",
                "CEO", "Microsoft", "microsoftUser1", "PasswordMS1");

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
