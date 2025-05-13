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

public class CustomerRepresentativeBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerRepresentativeBootstrapper.class);
    private final AddCustomerRepresentativeController controller = new AddCustomerRepresentativeController();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    @Override
    public boolean execute() {
        addRepsForCustomer("EA Sports", new String[][]{
                {"Andrew Wilson", "andrewwilson@easports.com", "+351", "910000001", "CEO", "eaUser1", "PasswordEA1"},
                {"Laura Miele", "lauramiele@easports.com", "+351", "910000002", "COO", "eaUser2", "PasswordEA2"},
                {"Blake Jorgensen", "blakejorgensen@easports.com", "+351", "910000003", "CFO", "eaUser3", "PasswordEA3"}
        });

        addRepsForCustomer("Ubisoft", new String[][]{
                {"Yves Guillemot", "yves.guillemot@ubisoft.com", "+33", "920000001", "CEO", "ubisoftUser1", "PasswordUBI1"},
                {"Christine Burgess", "christine.burgess@ubisoft.com", "+33", "920000002", "VP Sales", "ubisoftUser2", "PasswordUBI2"},
                {"Alain Corre", "alain.corre@ubisoft.com", "+33", "920000003", "Executive Director", "ubisoftUser3", "PasswordUBI3"}
        });

        addRepsForCustomer("Nintendo", new String[][]{
                {"Shuntaro Furukawa", "shuntaro.furukawa@nintendo.com", "+376", "930000001", "President", "nintendoUser1", "PasswordNIN1"},
                {"Satoru Shibata", "satoru.shibata@nintendo.com", "+376", "930000002", "Marketing Head", "nintendoUser2", "PasswordNIN2"},
                {"Ko Shiota", "ko.shiota@nintendo.com", "+376", "930000003", "Hardware Lead", "nintendoUser3", "PasswordNIN3"}
        });

        addRepsForCustomer("Sony", new String[][]{
                {"Kenichiro Yoshida", "kenichiro.yoshida@sony.com", "+420", "940000001", "CEO", "sonyUser1", "PasswordSONY1"},
                {"Jim Ryan", "jim.ryan@sony.com", "+420", "940000002", "President", "sonyUser2", "PasswordSONY2"},
                {"Hiroki Totoki", "hiroki.totoki@sony.com", "+420", "940000003", "CFO", "sonyUser3", "PasswordSONY3"}
        });

        addRepsForCustomer("Microsoft", new String[][]{
                {"Satya Nadella", "satya.nadella@microsoft.com", "+377", "950000001", "CEO", "microsoftUser1", "PasswordMS1"},
                {"Phil Spencer", "phil.spencer@microsoft.com", "+377", "950000002", "Gaming Lead", "microsoftUser2", "PasswordMS2"},
                {"Amy Hood", "amy.hood@microsoft.com", "+377", "950000003", "CFO", "microsoftUser3", "PasswordMS3"}
        });

        return true;
    }

    private void addRepsForCustomer(String customerName, String[][] repsInfo) {
        try {
            Name customerNameObj = new Name(customerName);
            Customer customer = customerRepository.findCustomerByName(customerNameObj);
            if (customer == null) {
                throw new IllegalArgumentException("Customer not found: " + customerName);
            }

            for (String[] rep : repsInfo) {
                register(rep[0], rep[1], rep[2], rep[3], rep[4], customer, rep[5], rep[6]);
            }

        } catch (Exception e) {
            LOGGER.error("Error processing representatives for {}: {}", customerName, e.getMessage());
        }
    }

    private void register(final String name, final String email, final String countryCode, final String phoneNumber,
                          final String position, final Customer customer, final String username, final String password) {
        try {
            CustomerRepresentative representative = new CustomerRepresentative(
                    new Name(name),
                    new Email(email),
                    new PhoneNumber(countryCode, phoneNumber),
                    new Position(position),
                    customer
            );

            controller.addCustomerRepresentative(representative, customer, username, password,
                    new PhoneNumber(countryCode, phoneNumber));

            LOGGER.info("Successfully registered representative {} for customer {}", name, customer.name());

        } catch (Exception e) {
            LOGGER.error("Failed to register representative {}: {}", name, e.getMessage());
        }
    }
}
