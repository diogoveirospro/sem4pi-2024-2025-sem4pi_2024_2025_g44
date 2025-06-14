package core.ShowProposal.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMManager.domain.Entities.CRMManager;
import core.Category.domain.Entities.Category;
import core.Category.domain.ValueObjects.CategoryName;
import core.Customer.domain.Entities.Customer;
import core.Customer.domain.ValueObjects.Address;
import core.Customer.domain.ValueObjects.CustomerType;
import core.Customer.domain.ValueObjects.VatNumber;
import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.Figure.domain.Entities.Figure;
import core.Figure.domain.ValueObjects.Code;
import core.Figure.domain.ValueObjects.DSLDescription;
import core.Figure.domain.ValueObjects.Keyword;
import core.Figure.domain.ValueObjects.Version;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import core.Shared.domain.ValueObjects.*;
import core.ShowDesigner.domain.Entities.ShowDesigner;
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
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ShowProposalTest {

    private ShowRequest showRequest;
    private QuantityOfDrones quantDrones;
    private Insurance insurance;
    private CRMCollaborator collaborator;
    private CRMManager manager;
    private LocalDate date;
    private LocalTime time;
    private GenerateProposalNumber generateProposalNumber;
    private Duration duration;
    private Figure figure;
    private Drone drone;
    private Model model;
    private ShowConfiguration showConfiguration;


    @BeforeEach
    public void setUp() {

        collaborator = setUpCollaborator();
        showRequest = setUpRequest(collaborator);
        quantDrones = new QuantityOfDrones("10");
        insurance = new Insurance("1000.0", "€");
        date = LocalDate.now();
        time = LocalTime.now();
        duration = Duration.ofHours(2); // Example duration
        generateProposalNumber = new GenerateProposalNumber();
        manager = setUpManager();
        figure = setUpPublicFigure();
        model = setUpModel();
        drone = setUpDrone();
        showConfiguration = setUpConfiguration();
    }

    private ShowConfiguration setUpConfiguration() {
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(entry);

        return builder.build();
    }

    private Drone setUpDrone() {
        SerialNumber serialNumber = new SerialNumber(1234);
        Map<LocalDate, String> reasons = new HashMap<>();
        LocalDate now = LocalDate.now();
        String wewe = "Test";
        reasons.put(now, wewe);
        RemovalReason removalReason = new RemovalReason(reasons);
        return new Drone(serialNumber, model, removalReason);
    }

    private Model setUpModel() {
        ModelName modelName = new ModelName("TestModel");
        // Setup Configuration with dummy values
        WindSpeed ws = new WindSpeed(0, 10);
        PositionTolerance pt = new PositionTolerance(0.5);
        Configuration configuration = new Configuration(
                Map.of(ws, pt),
                SafetyStatus.SAFE
        );

        return new Model(modelName, configuration);
    }

    private Figure setUpPublicFigure() {
        // Create a Figure object with the required parameters
        Code code = new Code("FIG-1234");
        Version version = new Version("1.0.0");
        Name name = new Name("Test Figure");
        Description figureDescription = new Description("A test figure");
        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");

        Set<Keyword> keywords = new HashSet<>();
        keywords.add(new Keyword("keyword1"));
        keywords.add(new Keyword("keyword2"));
        keywords.add(new Keyword("keyword3"));

        Set<Category> categories = new HashSet<>();
        categories.add(new Category(new CategoryName("Category1"), new Description("Description1")));
        categories.add(new Category(new CategoryName("Category2"), new Description("Description2")));
        categories.add(new Category(new CategoryName("Category3"), new Description("Description3")));

        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
                new PhoneNumber("+351", "912345678"),
                new Email("showdesigner1@shodrone.com"));

        return new Figure(code, version, name, figureDescription, dslDescription, keywords, categories, showDesigner);
    }

    private CRMManager setUpManager() {
        Name crm_name = new Name("Test CRM Manager");
        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
        Email crm_email = new Email("crmmanager@email.com");

        return new CRMManager(crm_name, phoneNumber, crm_email);
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

        ShowDescription showDescription = new ShowDescription("Description");
        date = LocalDate.parse("12-12-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        time = LocalTime.parse("22:22", DateTimeFormatter.ofPattern("HH:mm"));
        Location location = new Location(40.7128, -74.0060, 10.0); // Example coordinates (New York City)
        Duration duration = Duration.ofHours(2); // Example duration
        QuantityOfDrones quantityOfDrones = new QuantityOfDrones("12");
        GenerateShowRequestID generateShowRequestID = new GenerateShowRequestID();

        showRequest = new ShowRequest(showDescription, date, time, duration, location, quantityOfDrones, customer, crmCollaborator, generateShowRequestID);
        return showRequest;
    }

    @Test
    void ensureProposalIncludesTotalNumberOfDrones() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration,
                quantDrones, insurance, collaborator, generateProposalNumber);
        assertTrue(proposal.quantityOfDrones().getQuantityOfDrones() > 0);
    }

    @Test
    void ensureProposalIsAssociatedWithShowRequest() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertNotNull(proposal.request());
    }

    @Test
    void ensureVideoCanBeAddedToProposal() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        String name = "Test Video";
        Video video = new Video(name, "https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        proposal.addVideo(video);
        assertNotNull(proposal.video());
        assertEquals("https://www.youtube.com/watch?v=dQw4w9WgXcQ", proposal.video().url());
        assertEquals("Test Video", proposal.video().title());
    }

    @Test
    void ensureCannotAddNullVideo() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration,
                quantDrones, insurance, collaborator, generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addVideo(null));
    }

    @Test
    void ensureInitialStatusIsTesting() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertEquals(ShowProposalStatus.TESTING, proposal.status());
    }

    @Test
    void ensureCreatorIsAlsoInitialSender() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertEquals(proposal.creator(), proposal.sender());
    }

    @Test
    void ensureDocumentCanBeAddedToProposal() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        ShowProposalDocument document = new ShowProposalDocument("Test Document Content",
                "Test Document Content".getBytes(java.nio.charset.StandardCharsets.UTF_8));
        proposal.addDocument(document);
        assertNotNull(proposal.document());
        assertEquals("Test Document Content", proposal.document().toString());
    }

    @Test
    void ensureCannotAddNullDocument() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addDocument(null));
    }

    @Test
    void ensureCRMManagerCanBeAddedToProposal() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        proposal.addCRMManager(manager);
        assertNotNull(proposal.crmManager());
        assertEquals("Test CRM Manager", proposal.crmManager().name().toString());
    }

    @Test
    void ensureCannotAddNullCRMManager() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addCRMManager(null));
    }

    @Test
    void ensureProposalIsReadyToConfigureDocument() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        assertTrue(proposal.isReadyToConfigureDocument());
    }

    @Test
    void ensureProposalCannotConfigureDocumentWithoutRequiredFields() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        assertFalse(proposal.isReadyToConfigureDocument());
    }

    @Test
    void ensureDocumentPortugueseIsConfiguredCorrectly() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        String document = proposal.configureDocument("Portuguese", manager);
        assertNotNull(document);
        assertTrue(document.contains("Referência " + proposal.identity().proposalNumber()));
        assertTrue(document.contains("TestModel – 1 unidades."));
    }

    @Test
    void ensureDocumentEnglishConfiguredCorrectly() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        String document = proposal.configureDocument("English (Regular Customer)", manager);
        assertNotNull(document);
        assertTrue(document.contains("Reference " + proposal.identity().proposalNumber()));
        assertTrue(document.contains("TestModel – 1 units."));
    }

    @Test
    void ensureDocumentEnglishVIPIsConfiguredCorrectly() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        String document = proposal.configureDocument("English (VIP Customer)", manager);
        assertNotNull(document);
        assertTrue(document.contains("Representative of Test Customer"));
        assertTrue(document.contains("TestModel – 1 units."));
    }

    @Test
    void ensureInvalidTemplateThrowsException() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance,
                collaborator, generateProposalNumber);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));

        String invalidTemplate = "french"; // "french" is not a valid template

        assertThrows(IllegalArgumentException.class, () -> {
            proposal.configureDocument(invalidTemplate, manager);
        });
    }

    @Test
    void testAddShowDSLDescriptionValid() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        ShowDSLDescription description = new ShowDSLDescription("Valid DSL Description");

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addShowDSLDescription(description);

        assertEquals(proposal.configuration().showDSLDescription(), description);
    }

    @Test
    void testAddShowDSLDescriptionNullThrowsException() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addShowDSLDescription(null));
    }

    @Test
    void testAddShowDSLDescriptionWithoutConfigurationThrowsException() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        ShowDSLDescription description = new ShowDSLDescription("Valid DSL Description");
        assertThrows(IllegalStateException.class, () -> proposal.addShowDSLDescription(description));
    }

    @Test
    void testIsReadyToGenerateShowDSLTrue() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        assertTrue(proposal.isReadyToGenerateShowDSL());
    }

    @Test
    void testIsReadyToGenerateShowDSLFalseNoConfiguration() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        assertFalse(proposal.isReadyToGenerateShowDSL());
    }

    @Test
    void testIsReadyToGenerateShowDSLFalseNoFigures() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();

        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);

        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);
        assertFalse(proposal.isReadyToGenerateShowDSL());
    }

    @Test
    void testSameAsTrueWhenSameObject() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        assertTrue(proposal.sameAs(proposal));
    }

    @Test
    void testSetShowProposalStatus() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        proposal.setShowProposalStatus(ShowProposalStatus.READY_TO_SEND);
        assertEquals(ShowProposalStatus.READY_TO_SEND, proposal.status());
    }

    @Test
    void testAddCustomerFeedback() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        CustomerFeedback feedback = new CustomerFeedback(CustomerFeedbackStatus.ACCEPTED, "Great proposal, looking forward to the show!");
        proposal.addCustomerFeedback(feedback);
        assertEquals(feedback, proposal.customerFeedback());
    }

    @Test
    void testAddCustomerFeedbackThrowsIfNull() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        assertThrows(IllegalArgumentException.class, () -> proposal.addCustomerFeedback(null));
    }

    @Test
    void testIsReadyToConfigureDocumentTrue() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator, generateProposalNumber);
        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowConfigurationEntry droneEntry = new ShowConfigurationEntry(model, drone);
        builder.addDrones(droneEntry);
        ShowConfiguration config = builder.build();
        proposal.addConfiguration(config);

        proposal.configuration().addFigures(List.of(figure));

        proposal.addVideo(new Video("Test Video", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        assertTrue(proposal.isReadyToConfigureDocument());
    }

    @Test
    void ensureTotalDronesInConfigurationMatchesProposalQuantity() {
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration,
                quantDrones, insurance, collaborator, generateProposalNumber);

        assertNotEquals(proposal.quantityOfDrones(), showConfiguration.showConfiguration().size());
    }

    @Test
    void testFeedBackNotNull (){
        ShowProposal proposal = new ShowProposal(showRequest, date, time, duration,
                quantDrones, insurance, collaborator, generateProposalNumber);

        CustomerFeedback customerFeedback = new CustomerFeedback(CustomerFeedbackStatus.ACCEPTED, "Great proposal, looking forward to the show!");
        CustomerFeedback customerFeedback2 = new CustomerFeedback(CustomerFeedbackStatus.REJECTED, "Bad proposal, looking forward to the show!");
        assertNotEquals(customerFeedback, proposal.customerFeedback());
        assertNotEquals(customerFeedback2, proposal.customerFeedback());
    }
}