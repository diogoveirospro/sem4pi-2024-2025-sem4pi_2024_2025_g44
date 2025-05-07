package core.Customer.domain.Entities;

import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    private Customer customer;
    private Name name;
    private Address address;
    private VatNumber vat;
    private CustomerType type;

    @BeforeEach
    void setUp() {
        name = new Name("Test Customer");
        address = new Address("123 Test Street");
        vat = new VatNumber("PT123456789");
        type = CustomerType.REGULAR;
        customer = new Customer(name, address, vat, type);
    }

    @Test
    void ensureCustomerIsCreatedCorrectly() {
        assertNotNull(customer);
        assertEquals(name, customer.name());
        assertEquals(address, customer.address());
        assertEquals(vat, customer.vat());
        assertEquals(CustomerStatus.CREATED, customer.status());
        assertEquals(type, customer.type());
    }

    @Test
    void ensureCustomerEquality() {
        Customer anotherCustomer = new Customer(name, address, vat, type);
        assertTrue(customer.sameAs(anotherCustomer));
        assertEquals(customer, anotherCustomer);
    }

    @Test
    void ensureCustomerInequality() {
        VatNumber differentVat = new VatNumber("PT987654321");
        Customer differentCustomer = new Customer(name, address, differentVat, type);
        assertFalse(customer.sameAs(differentCustomer));
        assertNotEquals(customer, differentCustomer);
    }

    @Test
    void ensureCustomerRepresentativeCanBeAdded() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);
        assertNotNull(customer.representativesOfCustomer());
        assertEquals(1, customer.representativesOfCustomer().size());
        assertEquals(representative, customer.representativesOfCustomer().get(0));
    }

    @Test
    void ensureFindCustomerRepresentativeWorks() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);
        CustomerRepresentative found = customer.findCustomerRepresentative(representative);
        assertNotNull(found);
        assertEquals(representative, found);
    }

    @Test
    void ensureFindCustomerRepresentativeReturnsNullForNonExistentRepresentative() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        assertNull(customer.findCustomerRepresentative(representative));
    }
    @Test
    void ensureDisabledRepresentativesAreNotListed() {
        CustomerRepresentative activeRepresentative = new CustomerRepresentative(
                new Name("Active Rep"),
                new Email("active@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );

        CustomerRepresentative disabledRepresentative = new CustomerRepresentative(
                new Name("Disabled Rep"),
                new Email("disabled@example.com"),
                new PhoneNumber("+351", "987654321"),
                new Position("Assistant"),
                customer
        );
        disabledRepresentative.deactivate();

        customer.addCustomerRepresentative(activeRepresentative);
        customer.addCustomerRepresentative(disabledRepresentative);

        ArrayList<CustomerRepresentative> activeRepresentatives = new ArrayList<>();
        for (CustomerRepresentative rep : customer.representativesOfCustomer()) {
            if (rep.status() == CustomerRepresentativeStatus.ACTIVE) {
                activeRepresentatives.add(rep);
            }
        }

        assertEquals(1, activeRepresentatives.size());
        assertEquals(activeRepresentative, activeRepresentatives.get(0));
    }

    @Test
    void ensureRepresentativeRepresentsACustomer() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );

        customer.addCustomerRepresentative(representative);

        assertTrue(customer.representativesOfCustomer().contains(representative));
        assertEquals(customer, representative.representee());
    }

    @Test
    void ensureCustomerRepresentativeInformationCanBeEditedInsideCustomer() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);

        Email oldEmail = representative.email();
        PhoneNumber oldPhoneNumber = representative.phoneNumber();

        representative.changeInfo(new Email("newrep@example.com"), new PhoneNumber("+351", "987654321"));

        CustomerRepresentative updatedRepresentative = customer.findCustomerRepresentative(representative);

        assertNotEquals(oldEmail, updatedRepresentative.email());
        assertNotEquals(oldPhoneNumber, updatedRepresentative.phoneNumber());
    }

    @Test
    void ensureEditedDataIsValidInsideCustomer() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);

        assertThrows(IllegalArgumentException.class, () -> {
            representative.changeInfo(new Email("invalid-email"), new PhoneNumber("+351", "123"));
        });

        representative.changeInfo(new Email("valid@example.com"), new PhoneNumber("+351", "987654321"));


        CustomerRepresentative updatedRepresentative = customer.findCustomerRepresentative(representative);

        assertEquals("valid@example.com", updatedRepresentative.email().toString());
        assertEquals("+351 987654321", updatedRepresentative.phoneNumber().toString());
    }

    @Test
    void ensureValidCustomerRepresentativeCanBeDisabledInsideCustomer() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Valid Rep"),
                new Email("valid@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);

        assertDoesNotThrow(representative::deactivate);

        CustomerRepresentative updatedRepresentative = customer.findCustomerRepresentative(representative);

        assertEquals(CustomerRepresentativeStatus.DISABLE, updatedRepresentative.status());
    }

    @Test
    void ensureDisabledRepresentativeIsNotListedAsActiveInsideCustomer() {
        CustomerRepresentative activeRepresentative = new CustomerRepresentative(
                new Name("Active Rep"),
                new Email("active@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );

        CustomerRepresentative disabledRepresentative = new CustomerRepresentative(
                new Name("Disabled Rep"),
                new Email("disabled@example.com"),
                new PhoneNumber("+351", "987654321"),
                new Position("Assistant"),
                customer
        );
        disabledRepresentative.deactivate();

        customer.addCustomerRepresentative(activeRepresentative);
        customer.addCustomerRepresentative(disabledRepresentative);

        List<CustomerRepresentative> activeRepresentatives = new ArrayList<>();

        for (CustomerRepresentative rep : customer.representativesOfCustomer()) {
            if (rep.status() == CustomerRepresentativeStatus.ACTIVE) {
                activeRepresentatives.add(rep);
            }
        }



        assertEquals(1, activeRepresentatives.size());
        assertEquals(activeRepresentative, activeRepresentatives.get(0));
    }
}