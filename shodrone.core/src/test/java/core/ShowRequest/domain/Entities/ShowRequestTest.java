package core.ShowRequest.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.Service.GenerateShowRequestID;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class ShowRequestTest {

    private ShowRequest showRequest;
    private ShowDescription showDescription;
    private LocalDate date;
    private LocalTime time;
    private Duration duration;
    private Location location;
    private QuantityOfDrones quantityOfDrones;
    private GenerateShowRequestID generateShowRequestID;

    private Customer customer;
    private Name name;
    private Address address;
    private VatNumber vat;
    private CustomerType type;

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

        showDescription = new ShowDescription("Test Show");
        date = LocalDate.of(2023, 10, 1);
        time = LocalTime.of(20, 0);
        duration = Duration.ofMinutes(90);
        location = new Location(38.7169, -9.1399, 100.0);
        quantityOfDrones = new QuantityOfDrones("10");
        generateShowRequestID = new GenerateShowRequestID();

        showRequest = new ShowRequest(showDescription, date, time, duration, location, quantityOfDrones, customer,
                crmCollaborator, generateShowRequestID);
    }

    @Test
    void testStatus() {
        assertEquals(ShowRequestStatus.CREATED, showRequest.status());
    }

    @Test
    void testShowDescription() {
        assertEquals(showDescription, showRequest.showDescription());
    }

    @Test
    void testDateOfShow() {
        assertEquals(date, showRequest.dateOfShow());
    }

    @Test
    void testTimeOfShow() {
        assertEquals(time, showRequest.timeOfShow());
    }

    @Test
    void testDurationOfShow() {
        assertEquals(duration, showRequest.durationOfShow());
    }

    @Test
    void testLocation() {
        assertEquals(location, showRequest.location());
    }

    @Test
    void testQuantityOfDrones() {
        assertEquals(quantityOfDrones, showRequest.quantityOfDrones());
    }

    @Test
    void testCustomer() {
        assertEquals(customer, showRequest.customer());
    }

    @Test
    void testCrmCollaborator() {
        assertEquals(crmCollaborator, showRequest.crmCollaborator());
    }

    @Test
    void testEditInformation() {
        Location newLocation = new Location(40.0, -8.0, 50.0);
        ShowDescription newDescription = new ShowDescription("Updated Show");
        LocalDate newDate = LocalDate.of(2023, 12, 25);
        LocalTime newTime = LocalTime.of(18, 0);
        Duration newDuration = Duration.ofMinutes(120);
        QuantityOfDrones newQuantity = new QuantityOfDrones("20");

        showRequest.editInformation(newLocation, newDescription, newDate, newTime, newDuration, newQuantity);

        assertEquals(newLocation, showRequest.location());
        assertEquals(newDescription, showRequest.showDescription());
        assertEquals(newDate, showRequest.dateOfShow());
        assertEquals(newTime, showRequest.timeOfShow());
        assertEquals(newDuration, showRequest.durationOfShow());
        assertEquals(newQuantity, showRequest.quantityOfDrones());
    }

    @Test
    void testSameAs() {
        ShowRequest anotherShowRequest = new ShowRequest(showDescription, date, time, duration, location,
                quantityOfDrones, customer, crmCollaborator, generateShowRequestID);
        assertTrue(showRequest.sameAs(anotherShowRequest));
    }

    @Test
    void testIdentity() {
        ShowRequestID id = showRequest.identity();
        assertNotNull(id);
        assertEquals(id, showRequest.identity());
    }

    @Test
    void testHasIdentity() {
        ShowRequestID id = showRequest.identity();
        assertTrue(showRequest.hasIdentity(id));
        assertFalse(showRequest.hasIdentity(new ShowRequestID("REQ-123432")));
    }

    @Test
    void testEquals() {
        ShowRequest anotherShowRequest = new ShowRequest(showDescription, date, time, duration, location,
                quantityOfDrones, customer, crmCollaborator, generateShowRequestID);
        assertEquals(showRequest, anotherShowRequest);
    }

    @Test
    void testToString() {
        String expected = "Show -> " + showRequest.identity().toString() + "\nStatus - " + showRequest.status() + "\n";
        assertEquals(expected, showRequest.toString());
    }
}