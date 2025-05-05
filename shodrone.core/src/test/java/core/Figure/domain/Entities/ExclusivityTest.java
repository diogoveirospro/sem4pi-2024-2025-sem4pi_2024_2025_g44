package core.Figure.domain.Entities;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Shared.domain.ValueObjects.Name;
import eapli.framework.time.domain.model.DateInterval;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class ExclusivityTest {

    private Exclusivity buildExclusivity() {
        Customer customer = new Customer(new Name("Customer"), new Address("Rua 1"), new VatNumber("PT123456789"),
                CustomerType.REGULAR);

        Calendar start = Calendar.getInstance();
        start.set(2023, Calendar.JANUARY, 1);

        Calendar end = Calendar.getInstance();
        end.set(2023, Calendar.DECEMBER, 31);

        DateInterval duration = new DateInterval(start, end);

        return new Exclusivity(customer, duration);
    }

    @Test
    public void testExclusivityCreation() {
        Exclusivity exclusivity = buildExclusivity();

        assertNotNull(exclusivity, "Exclusivity should not be null");
        assertNotNull(exclusivity.customer(), "Customer should not be null");
        assertNotNull(exclusivity.duration(), "Duration should not be null");
    }

    @Test
    public void testEqualsAndHashCode_SameObjects() {
        Exclusivity exclusivity1 = buildExclusivity();
        Exclusivity exclusivity2 = buildExclusivity();

        assertEquals(exclusivity1, exclusivity2, "Exclusivity objects should be equal");

        assertEquals(exclusivity1.hashCode(), exclusivity2.hashCode(), "Hash codes should be equal");
    }

    @Test
    public void testEqualsAndHashCode_DifferentObjects() {
        Exclusivity exclusivity1 = buildExclusivity();
        Calendar start2 = Calendar.getInstance();
        start2.set(2024, Calendar.JANUARY, 1);
        Calendar end2 = Calendar.getInstance();
        end2.set(2024, Calendar.DECEMBER, 31);
        DateInterval duration2 = new DateInterval(start2, end2);
        Exclusivity exclusivity2 = new Exclusivity(exclusivity1.customer(), duration2);

        assertNotEquals(exclusivity1, exclusivity2, "Exclusivity objects should not be equal");

        assertNotEquals(exclusivity1.hashCode(), exclusivity2.hashCode(), "Hash codes should be different");
    }

    @Test
    public void testSameAs() {
        Exclusivity exclusivity1 = buildExclusivity();
        Exclusivity exclusivity2 = buildExclusivity();

        assertTrue(exclusivity1.sameAs(exclusivity2), "sameAs() should return true for equal objects");

        Calendar start2 = Calendar.getInstance();
        start2.set(2024, Calendar.JANUARY, 1);
        Calendar end2 = Calendar.getInstance();
        end2.set(2024, Calendar.DECEMBER, 31);
        DateInterval duration2 = new DateInterval(start2, end2);
        Exclusivity exclusivity3 = new Exclusivity(exclusivity1.customer(), duration2);

        assertFalse(exclusivity1.sameAs(exclusivity3), "sameAs() should return false for different objects");
    }

    @Test
    public void testStartTime() {
        Exclusivity exclusivity = buildExclusivity();
        String startTime = exclusivity.startTime();

        assertEquals("01/01/2023", startTime, "Start time should be formatted correctly");
    }

    @Test
    public void testEndTime() {
        Exclusivity exclusivity = buildExclusivity();
        String endTime = exclusivity.endTime();

        assertEquals("31/12/2023", endTime, "End time should be formatted correctly");
    }

    @Test
    public void testExclusivityCreationWithNulls() {
        assertThrows(IllegalArgumentException.class, () -> new Exclusivity(null, null),
                "Should throw IllegalArgumentException when customer or duration is null");
    }

    @Test
    public void testIdentity() {
        Exclusivity exclusivity = buildExclusivity();
        assertNull(exclusivity.identity(), "Identity should be null");
    }
}
