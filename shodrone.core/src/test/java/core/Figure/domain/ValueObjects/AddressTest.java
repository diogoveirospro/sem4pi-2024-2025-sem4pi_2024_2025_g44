package core.Figure.domain.ValueObjects;

import core.Customer.domain.ValueObjects.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void ensureAddressIsCreatedSuccessfully() {
        Address address = new Address("Valid Address");
        assertNotNull(address);
        assertEquals("Valid Address", address.toString());
    }

    @Test
    void ensureAddressValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Address(""));
        assertThrows(IllegalArgumentException.class, () -> new Address(null));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        Address address1 = new Address("Same Address");
        Address address2 = new Address("Same Address");
        Address address3 = new Address("Different Address");

        assertEquals(address1, address2);
        assertNotEquals(address1, address3);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        Address address = new Address("Test Address");
        assertEquals("Test Address", address.toString());
    }
}