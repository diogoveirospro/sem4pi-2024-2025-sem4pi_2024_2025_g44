package core.Customer.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VatNumberTest {

    @Test
    void ensureVatNumberIsCreatedSuccessfully() {
        VatNumber vatNumber = new VatNumber("PT123456789");
        assertNotNull(vatNumber);
        assertEquals("PT123456789", vatNumber.fullVatNumber());
        assertEquals("PT", vatNumber.countryCode());
        assertEquals("123456789", vatNumber.vatNumber());
        assertEquals("Portugal", vatNumber.countryName());
    }

    @Test
    void ensureVatNumberValidationFailsForInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new VatNumber(""));
        assertThrows(IllegalArgumentException.class, () -> new VatNumber(null));
        assertThrows(IllegalArgumentException.class, () -> new VatNumber("123456789"));
        assertThrows(IllegalArgumentException.class, () -> new VatNumber("PT"));
        assertThrows(IllegalArgumentException.class, () -> new VatNumber("PTABC123"));
        assertThrows(IllegalArgumentException.class, () -> new VatNumber("PT12345678901234"));
    }

    @Test
    void ensureEqualsWorksCorrectly() {
        VatNumber vat1 = new VatNumber("PT123456789");
        VatNumber vat2 = new VatNumber("PT123456789");
        VatNumber vat3 = new VatNumber("ES987654321");

        assertEquals(vat1, vat2);
        assertNotEquals(vat1, vat3);
    }

    @Test
    void ensureToStringReturnsCorrectValue() {
        VatNumber vatNumber = new VatNumber("PT123456789");
        assertEquals("PT123456789", vatNumber.toString());
    }

    @Test
    void ensureCompareToWorksCorrectly() {
        VatNumber vat1 = new VatNumber("PT123456789");
        VatNumber vat2 = new VatNumber("ES987654321");

        assertTrue(vat1.compareTo(vat2) > 0);
        assertTrue(vat2.compareTo(vat1) < 0);
        assertEquals(0, vat1.compareTo(new VatNumber("PT123456789")));
    }
}