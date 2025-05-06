package core.Customer.domain.Entities;

import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerRepresentativeTest {

    private CustomerRepresentative representative;
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer(
                new Name("Test Customer"),
                new Address("123 Test Street"),
                new VatNumber("PT123456789"),
                CustomerType.REGULAR
        );

        representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
    }

    @Test
    void ensureRepresentativeIsCreatedCorrectly() {
        assertNotNull(representative);
        assertEquals("Rep Name", representative.name().toString());
        assertEquals("rep@example.com", representative.email().toString());
        assertEquals("+351 912345678", representative.phoneNumber().toString());
        assertEquals("Manager", representative.position().toString());
        assertEquals(CustomerRepresentativeStatus.ACTIVE, representative.status());
        assertEquals(customer, representative.representee());
    }

    @Test
    void ensureRepresentativeCanChangeInfo() {
        Email newEmail = new Email("newrep@example.com");
        PhoneNumber newPhone = new PhoneNumber("+351", "987654321");

        representative.changeInfo(newEmail, newPhone);

        assertEquals("newrep@example.com", representative.email().toString());
        assertEquals("+351 987654321", representative.phoneNumber().toString());
    }

    @Test
    void ensureRepresentativeCanBeDeactivated() {
        representative.deactivate();
        assertEquals(CustomerRepresentativeStatus.DISABLE, representative.status());
    }

    @Test
    void ensureRepresentativeEquality() {
        CustomerRepresentative anotherRepresentative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );

        assertTrue(representative.equals(anotherRepresentative));

        assertEquals(representative, anotherRepresentative);
    }

    @Test
    void ensureRepresentativeInequality() {
        CustomerRepresentative differentRepresentative = new CustomerRepresentative(
                new Name("Different Name"),
                new Email("different@example.com"),
                new PhoneNumber("+351", "987654321"),
                new Position("Assistant"),
                customer
        );

        assertNotEquals(representative, differentRepresentative);
    }

    @Test
    void ensureRepresentativeCannotBeCreatedWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new CustomerRepresentative(
                    new Name("Invalid Email Rep"),
                    new Email("invalid-email"),
                    new PhoneNumber("+351", "912345678"),
                    new Position("Manager"),
                    customer
            );
        });
    }

    @Test
    void ensureCustomerRepresentativeRepresentsACustomer() {
        // Simula a criação de um representante de cliente
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Valid User"),
                new Email("user@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );

        // Verifica se o representante está associado ao cliente
        assertNotNull(representative.representee());
        assertEquals(customer, representative.representee());
    }

    @Test
    void ensureCustomerRepresentativeInformationCanBeEdited() {
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

        assertNotEquals(oldEmail, representative.email());
        assertNotEquals(oldPhoneNumber, representative.phoneNumber());
    }

    @Test
    void ensureEditedDataIsValid() {
        CustomerRepresentative representative = new CustomerRepresentative(
                new Name("Rep Name"),
                new Email("rep@example.com"),
                new PhoneNumber("+351", "912345678"),
                new Position("Manager"),
                customer
        );
        customer.addCustomerRepresentative(representative);

        // Testa com dados inválidos
        assertThrows(IllegalArgumentException.class, () -> {
            representative.changeInfo(new Email("invalid-email"), new PhoneNumber("+351", "123"));
        });

        // Testa com dados válidos
        representative.changeInfo(new Email("valid@example.com"), new PhoneNumber("+351", "987654321"));
        assertEquals("valid@example.com", representative.email().toString());
        assertEquals("+351 987654321", representative.phoneNumber().toString());
    }

    @Test
    void ensureValidCustomerRepresentativeCanBeDisabled() {
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
}