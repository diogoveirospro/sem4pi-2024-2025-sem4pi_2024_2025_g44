package core.ShowProposal.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InsuranceTest {

    @Test
    void ensureInsuranceIsCreatedWithValidArguments() {
        Insurance insurance = new Insurance("1000.00", "€");
        assertEquals("1000.00", insurance.obtainAmount());
        assertEquals("€", insurance.obtainCurrencySymbol());
    }

    @Test
    void ensureExceptionIsThrownForNullAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Insurance(null, "€"));
    }

    @Test
    void ensureExceptionIsThrownForNullCurrencySymbol() {
        assertThrows(IllegalArgumentException.class, () -> new Insurance("1000.00", null));
    }

    @Test
    void ensureExceptionIsThrownForInvalidAmountFormat() {
        assertThrows(IllegalArgumentException.class, () -> new Insurance("invalid", "€"));
    }

    @Test
    void ensureExceptionIsThrownForNegativeAmount() {
        assertThrows(IllegalArgumentException.class, () -> new Insurance("-1000.00", "€"));
    }

    @Test
    void ensureExceptionIsThrownForInvalidCurrencySymbol() {
        assertThrows(IllegalArgumentException.class, () -> new Insurance("1000.00", "$"));
    }

    @Test
    void ensureToStringReturnsCorrectFormat() {
        Insurance insurance = new Insurance("1000.00", "€");
        assertEquals("1000.00 €", insurance.toString());
    }

    @Test
    void ensureMainEuropeanCurrenciesAreAccessible() {
        assertTrue(Insurance.getMainEuropeanCurrencies().containsKey("EUR - Euro"));
        assertEquals("€", Insurance.getMainEuropeanCurrencies().get("EUR - Euro"));
    }
}