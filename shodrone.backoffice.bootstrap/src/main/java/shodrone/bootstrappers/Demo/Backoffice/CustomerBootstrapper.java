package shodrone.bootstrappers.Demo.Backoffice;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.Position;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.User.domain.ShodroneRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.domain.model.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;

import java.util.HashSet;
import java.util.Set;

public class CustomerBootstrapper extends UsersBootstrapperBase implements Action {
    private static final Logger LOGGER = LogManager.getLogger(CustomerBootstrapper.class);
    private static final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    @Override
    public boolean execute() {
        register("EA Sports", "209 Redwood Shores Parkway, Redwood City, CA 94065, USA", "PT13872527904", CustomerType.VIP, "Andrew Wilson", "andrewwilson@gmail.com", "+351", "910222312", "CEO", "eaUser", "PasswordEA");
        register("Ubisoft", "28 Rue Armand Carrel, 93100 Montreuil, France", "FR12345678901", CustomerType.REGULAR, "Yves Guillemot", "yves.guillemot@ubisoft.com", "+33", "123456789", "CEO", "ubisoftUser", "PasswordUBI");
        register("Nintendo", "11-1 Hokotate-cho, Kamitoba, Minami-ku, Kyoto 601-8501, Japan", "AD98765432109", CustomerType.VIP, "Shuntaro Furukawa", "shuntaro.furukawa@nintendo.com", "+81", "987654321", "President", "nintendoUser", "PasswordNIN");
        register("Sony", "1-7-1 Konan, Minato-ku, Tokyo 108-0075, Japan", "CZ12398765432", CustomerType.REGULAR, "Kenichiro Yoshida", "kenichiro.yoshida@sony.com", "+81", "876543210", "CEO", "sonyUser", "PasswordSONY");
        register("Microsoft", "One Microsoft Way, Redmond, WA 98052, USA", "MC123456789", CustomerType.VIP, "Satya Nadella", "satya.nadella@microsoft.com", "+1", "123456789", "CEO", "microsoftUser", "PasswordMS");
        return true;
    }
    private void register(final String nameCustomer, final String address, final String taxNumber, final CustomerType customerType, String nameRepresentative, String email, String countryCode, String nationalNumber, String position, String username, String password) {
        Name name = new Name(nameCustomer);
        Address addressObj = new Address(address);
        VatNumber taxNumberObj = new VatNumber(taxNumber);
        Customer customer = new Customer(name, addressObj, taxNumberObj, customerType);
        Email emailObj = new Email(email);
        Name nameRep = new Name(nameRepresentative);
        Position positionObj = new Position(nationalNumber);
        PhoneNumber phoneNumberObj = new PhoneNumber(countryCode, nationalNumber);
        CustomerRepresentative customerRepresentative = new CustomerRepresentative(nameRep, emailObj, phoneNumberObj, positionObj, customer);
        customer.addCustomerRepresentative(customerRepresentative);
        String firstName = nameRepresentative.split(" ")[0];
        String lastName = nameRepresentative.split(" ")[1];
        Set<Role> roles = new HashSet<>();
        roles.add( ShodroneRoles.CUSTOMERREPRESENTATIVE);
        roles.add(ShodroneRoles.USER);
        registerUser(username, password, firstName, lastName, email, roles, phoneNumberObj);

    }
}
