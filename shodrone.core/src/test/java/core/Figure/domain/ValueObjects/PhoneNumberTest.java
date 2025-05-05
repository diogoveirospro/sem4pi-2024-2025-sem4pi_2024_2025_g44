package core.Figure.domain.ValueObjects;

import core.Shared.domain.ValueObjects.PhoneNumber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberTest {

    @Test
    void ensurePhoneNumberIsCreatedSuccessfully() {
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        assertNotNull(phoneNumber);
        assertEquals("+351 912345678", phoneNumber.toString());
        assertEquals("912345678", phoneNumber.phoneNumber());
        assertEquals("Portugal", phoneNumber.country());
    }

    @Test
    void ensurePhoneNumberValidationFailsForInvalidCountryCode() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+999", "912345678"));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber(null, "912345678"));
    }

    @Test
    void ensurePhoneNumberValidationFailsForInvalidNationalNumber() {
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", ""));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", null));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", "123"));
        assertThrows(IllegalArgumentException.class, () -> new PhoneNumber("+351", "1234567890123456"));
    }

    @Test
    void ensureCountryCodeOfCountryWorksCorrectly() {
        assertEquals("+351", PhoneNumber.countryCodeOfCountry("Portugal"));
        assertNull(PhoneNumber.countryCodeOfCountry("Unknown Country"));
        assertNull(PhoneNumber.countryCodeOfCountry(null));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        PhoneNumber phone1 = new PhoneNumber("+351", "912345678");
        PhoneNumber phone2 = new PhoneNumber("+351", "912345678");
        PhoneNumber phone3 = new PhoneNumber("+44", "123456789");

        assertEquals(phone1, phone2);
        assertNotEquals(phone1, phone3);
    }

    @Test
    void ensureHashCodeWorksCorrectly() {
        PhoneNumber phone1 = new PhoneNumber("+351", "912345678");
        PhoneNumber phone2 = new PhoneNumber("+351", "912345678");

        assertEquals(phone1.hashCode(), phone2.hashCode());
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        PhoneNumber phoneNumber = new PhoneNumber("+351", "912345678");
        assertEquals("+351 912345678", phoneNumber.toString());
    }
}