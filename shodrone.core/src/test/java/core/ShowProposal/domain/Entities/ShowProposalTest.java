package core.ShowProposal.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.application.Service.GenerateProposalNumber;
import core.ShowProposal.domain.ValueObjects.*;
import core.ShowRequest.application.Service.GenerateShowRequestID;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class ShowProposalTest {

    private ShowRequest showRequest;
    private QuantityOfDrones quantDrones;
    private Insurance insurance;
    private CRMCollaborator collaborator;
    private LocalDate date;
    private LocalTime time;
    private GenerateProposalNumber generateProposalNumber;
    private Duration duration;


    @BeforeEach
    public void setUp() {

        collaborator = setUpCollaborator();
        showRequest = setUpRequest(collaborator);
        quantDrones = new QuantityOfDrones("10");
        insurance = new Insurance("1000.0","€");
        date = LocalDate.now();
        time = LocalTime.now();
        duration = Duration.ofHours(2); // Example duration
        generateProposalNumber = new GenerateProposalNumber();
    }

    private CRMCollaborator setUpCollaborator() {
        Name crm_name = new Name("Test CrmCollaborator");
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        Email crm_email = new Email("crm@email.com");

        return new CRMCollaborator(crm_name, phoneNumber, crm_email);
    }

    private ShowRequest setUpRequest(CRMCollaborator crmCollaborator) {
        Name name = new Name("Test Customer");
        Address address = new Address("123 Test Street");
        VatNumber vat = new VatNumber("PT123456789");
        CustomerType type = CustomerType.REGULAR;
        Customer customer = new Customer(name, address, vat, type);

        ShowDescription showDescription = new ShowDescription("Descripton");
        date = LocalDate.parse("12-12-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = LocalTime.parse("22:22", DateTimeFormatter.ofPattern("HH:mm"));
        Location location = new Location(40.7128, -74.0060, 10.0); // Example coordinates (New York City)
        Duration duration = Duration.ofHours(2); // Example duration
        QuantityOfDrones quantityOfDrones = new QuantityOfDrones("12");
        GenerateShowRequestID generateShowRequestID = new GenerateShowRequestID();

        showRequest = new ShowRequest(showDescription, date, time,duration, location, quantityOfDrones, customer, crmCollaborator, generateShowRequestID);
        return showRequest;
    }

    @Test
    void ensureProposalIncludesTotalNumberOfDrones() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        assertTrue(proposal.quantityOfDrones().getQuantityOfDrones() > 0);
    }

    @Test
    void ensureProposalIsAssociatedWithShowRequest() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        assertNotNull(proposal.request());
    }

    @Test
    void ensureVideoCanBeAddedToProposal() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        String name = "Test Video";
        Video video = new Video(name,"https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        proposal.addVideo(video);
        assertNotNull(proposal.video());
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", proposal.video().url());
        assertEquals("Test Video", proposal.video().title());
    }

    @Test
    void ensureCannotAddNullVideo() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addVideo(null));
    }

    @Test
    void ensureInitialStatusIsTesting() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        assertEquals(ShowProposalStatus.TESTING, proposal.status());
    }

    @Test
    void ensureCreatorIsAlsoInitialSender() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, quantDrones, insurance, collaborator, duration,generateProposalNumber);
        assertEquals(proposal.creator(), proposal.sender());
    }
}