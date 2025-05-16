package core.ShowRequest.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.*;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class ShowRequestTest {
    private Customer customer;
    private Name name;
    private Address address;
    private VatNumber vat;
    private CustomerType type;


    private ShowRequest showRequest;
    private ShowDescription showDescription;
    private LocalDate date;
    private LocalTime time;
    private Location location;
    private QuantityOfDrones quantityOfDrones;


    private CRMCollaborator crmCollaborator;
    private Name crm_name;
    private PhoneNumber phoneNumber;
    private Email crm_email;

    @BeforeEach
    void setUp() {
        name = new Name("Test Customer");
        address = new Address("123 Test Street");
        vat = new VatNumber("PT123456789");
        type = CustomerType.REGULAR;
        customer = new Customer(name, address, vat, type);

        crm_name = new Name("Test CrmCollaborator");
        phoneNumber = new PhoneNumber("+351", "999999999");
        crm_email = new Email("crm@email.com");
        crmCollaborator = new CRMCollaborator(crm_name, phoneNumber, crm_email);

        showDescription = new ShowDescription("Descripton");
        date = LocalDate.parse("12-12-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = LocalTime.parse("22:22", DateTimeFormatter.ofPattern("HH:mm"));
        location = new Location("Porto");
        quantityOfDrones = new QuantityOfDrones("12");

        showRequest = new ShowRequest(showDescription, date, time, location, quantityOfDrones, customer, crmCollaborator);
    }

    @Test
    void ensureShowRequestIsCreatedCorrectly() {
        assertNotNull(showRequest);
        assertEquals(showRequest.getShowDescription(), showDescription);
        assertEquals(showRequest.getDateOfShow(), date);
        assertEquals(showRequest.getTimeOfShow(), time);
        assertEquals(showRequest.getLocation(), location);
        assertEquals(showRequest.getQuantityOfDrones(), quantityOfDrones);
        assertEquals(ShowRequestStatus.CREATED, showRequest.getStatus());
    }

    @Test
    void ensureShowRequestEquality() {
        ShowRequest anotherShowRequest = new ShowRequest(showDescription, date, time, location, quantityOfDrones, customer, crmCollaborator);
        assertTrue(showRequest.sameAs(anotherShowRequest));
        assertEquals(showRequest, anotherShowRequest);
    }

    @Test
    void ensureShowRequestInequality() {
        LocalDate differentDate = LocalDate.parse("11-11-2004", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        ShowRequest differentShowRequest = new ShowRequest(showDescription, differentDate, time, location, quantityOfDrones, customer, crmCollaborator);
        assertFalse(showRequest.sameAs(differentShowRequest));
        assertNotEquals(showRequest, differentShowRequest);
    }

    @Test
    void ensureEditedDataIsValidInsideShowRequest() {

        assertThrows(IllegalArgumentException.class, () -> {

            showRequest.editInformation(new Location(null), showDescription, date, time, new QuantityOfDrones("-10"));
        });

        showRequest.editInformation(new Location("Lisbon"), showDescription, date, time, new QuantityOfDrones("101"));

        assertEquals("Lisbon", showRequest.getLocation().toString());
        assertEquals("101", showRequest.getQuantityOfDrones().toString());
    }
}