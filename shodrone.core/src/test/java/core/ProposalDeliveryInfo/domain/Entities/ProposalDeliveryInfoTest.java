package core.ProposalDeliveryInfo.domain.Entities;

class ProposalDeliveryInfoTest {

//    private ShowProposal proposal;
//    private CRMCollaborator collaboratorSender;
//
//    @BeforeEach
//    public void setUp() {
//        setUpProposal();
//        collaboratorSender = new CRMCollaborator(new Name("Test Collaborator2"), new PhoneNumber("+351",
//                "123456789"), new Email("testCollaborator2@gmail.com"));
//    }
//
//    private void setUpProposal() {
//
//        // Initialize the required objects for ShowProposal
//        CRMCollaborator collaborator = setUpCollaborator();
//        ShowRequest showRequest = setUpRequest(collaborator);
//        QuantityOfDrones quantDrones = new QuantityOfDrones("10");
//        Insurance insurance = new Insurance("1000.0", "€");
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        Duration duration = Duration.ofHours(2);
//        GenerateProposalNumber generateProposalNumber = new GenerateProposalNumber();
//        CRMManager manager = setUpManager();
//        Figure figure = setUpPublicFigure();
//        Model model = setUpModel();
//        Drone drone = setUpDrone(model);
//        Video video = new Video("http://example.com/video.mp4", "Test Video");
//        ShowConfiguration showConfiguration = new ShowConfiguration.Builder().addDrones(model, List.of(drone)).
//                addFigure(figure).build();
//        ShowProposalDocument document = new ShowProposalDocument("Test Document Content", "test/path/to/document");
//
//        // Create a ShowProposal object with the required parameters
//        proposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator,
//                generateProposalNumber);
//
//        proposal.addCRMManager(manager);
//        proposal.addVideo(video);
//        proposal.addConfiguration(showConfiguration);
//        proposal.addDocument(document);
//
//    }
//
//    private ShowProposal setUpAnotherProposal() {
//        // Create another ShowProposal object with the required parameters
//        CRMCollaborator collaborator = setUpCollaborator();
//        ShowRequest showRequest = setUpRequest(collaborator);
//        QuantityOfDrones quantDrones = new QuantityOfDrones("5");
//        Insurance insurance = new Insurance("500.0", "€");
//        LocalDate date = LocalDate.now();
//        LocalTime time = LocalTime.now();
//        Duration duration = Duration.ofHours(1);
//        GenerateProposalNumber generateProposalNumber = new GenerateProposalNumber();
//        CRMManager manager = setUpManager();
//        Figure figure = setUpPublicFigure();
//        Model model = setUpModel();
//        Drone drone = setUpDrone(model);
//        Video video = new Video("http://example.com/video2.mp4", "Test Video 2");
//        ShowConfiguration showConfiguration = new ShowConfiguration.Builder().addDrones(model, List.of(drone)).
//                addFigure(figure).build();
//        ShowProposalDocument document = new ShowProposalDocument("Test Document Content 2", "test/path/to/document2");
//
//        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantDrones, insurance, collaborator,
//                generateProposalNumber);
//
//        showProposal.addCRMManager(manager);
//        showProposal.addVideo(video);
//        showProposal.addConfiguration(showConfiguration);
//        showProposal.addDocument(document);
//        return showProposal;
//    }
//
//    private Drone setUpDrone(Model model) {
//        SerialNumber serialNumber = new SerialNumber(1234);
//        Map<Date, String> reasons = new HashMap<>();
//        Date now = new Date();
//        String wewe = "Test";
//        reasons.put(now, wewe);
//        RemovalReason removalReason = new RemovalReason(reasons);
//        return new Drone(serialNumber, model, removalReason, DroneStatus.ACTIVE);
//    }
//
//    private Model setUpModel() {
//        ModelName modelName = new ModelName("TestModel");
//        // Setup Configuration with dummy values
//        WindSpeed ws = new WindSpeed(0, 10);
//        PositionTolerance pt = new PositionTolerance(0.5);
//        Configuration configuration = new Configuration(
//                Map.of(ws, pt),
//                SafetyStatus.SAFE
//        );
//
//        return new Model(modelName, configuration);
//    }
//
//    private Figure setUpPublicFigure() {
//        // Create a Figure object with the required parameters
//        Code code = new Code("FIG-1234");
//        Version version = new Version("1.0.0");
//        Name name = new Name("Test Figure");
//        Description figureDescription = new Description("A test figure");
//        DSLDescription dslDescription = new DSLDescription(List.of("line1", "line2"), "1.0.0");
//
//        Set<Keyword> keywords = new HashSet<>();
//        keywords.add(new Keyword("keyword1"));
//        keywords.add(new Keyword("keyword2"));
//        keywords.add(new Keyword("keyword3"));
//
//        Set<Category> categories = new HashSet<>();
//        categories.add(new Category(new CategoryName("Category1"), new Description("Description1")));
//        categories.add(new Category(new CategoryName("Category2"), new Description("Description2")));
//        categories.add(new Category(new CategoryName("Category3"), new Description("Description3")));
//
//        ShowDesigner showDesigner = new ShowDesigner(new Name("ShowDesigner1"),
//                new PhoneNumber("+351", "912345678"),
//                new Email("showdesigner1@shodrone.com"));
//
//        return new Figure(code, version, name, figureDescription, dslDescription, keywords, categories, showDesigner);
//    }
//
//    private CRMManager setUpManager() {
//        Name crm_name = new Name("Test CRM Manager");
//        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
//        Email crm_email = new Email("crmmanager@email.com");
//
//        return new CRMManager(crm_name, phoneNumber, crm_email);
//    }
//
//    private CRMCollaborator setUpCollaborator() {
//        Name crm_name = new Name("Test CrmCollaborator");
//        PhoneNumber phoneNumber = new PhoneNumber("+351", "999999999");
//        Email crm_email = new Email("crm@email.com");
//
//        return new CRMCollaborator(crm_name, phoneNumber, crm_email);
//    }
//
//    private ShowRequest setUpRequest(CRMCollaborator crmCollaborator) {
//        Name name = new Name("Test Customer");
//        Address address = new Address("123 Test Street");
//        VatNumber vat = new VatNumber("PT123456789");
//        CustomerType type = CustomerType.REGULAR;
//        Customer customer = new Customer(name, address, vat, type);
//
//        ShowDescription showDescription = new ShowDescription("Descripton");
//        LocalDate date = LocalDate.parse("12-12-2003", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
//        LocalTime time = LocalTime.parse("22:22", DateTimeFormatter.ofPattern("HH:mm"));
//        Location location = new Location(40.7128, -74.0060, 10.0); // Example coordinates (New York City)
//        Duration duration = Duration.ofHours(2); // Example duration
//        QuantityOfDrones quantityOfDrones = new QuantityOfDrones("12");
//        GenerateShowRequestID generateShowRequestID = new GenerateShowRequestID();
//
//        return new ShowRequest(showDescription, date, time, duration, location, quantityOfDrones, customer,
//                crmCollaborator, generateShowRequestID);
//    }
//
//    @Test
//    void ensureSendDeliveryCreatesValidInstance() {
//        ProposalDeliveryInfo deliveryInfo = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//
//        assertNotNull(deliveryInfo);
//        assertNotNull(deliveryInfo.identity());
//        assertEquals(proposal, deliveryInfo.proposal());
//        assertEquals(collaboratorSender, deliveryInfo.collaborator());
//        assertNotNull(deliveryInfo.deliveryAt());
//    }
//
//    @Test
//    void ensureSendDeliveryThrowsExceptionForNullProposal() {
//        assertThrows(IllegalArgumentException.class, () -> ProposalDeliveryInfo.sendDelivery(null, collaboratorSender));
//    }
//
//    @Test
//    void ensureCustomerIsCorrectlySet() {
//        ProposalDeliveryInfo deliveryInfo = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//
//        assertEquals(proposal.customer(), deliveryInfo.customer());
//    }
//
//    @Test
//    void ensureSameAsReturnsTrueForEqualObjects() {
//        ProposalDeliveryInfo deliveryInfo1 = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//        ProposalDeliveryInfo deliveryInfo2 = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//
//        assertTrue(deliveryInfo1.sameAs(deliveryInfo2));
//    }
//
//    @Test
//    void ensureSameAsReturnsFalseForDifferentObjects() {
//        ProposalDeliveryInfo deliveryInfo1 = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//        ShowProposal differentProposal = setUpAnotherProposal();
//        ProposalDeliveryInfo deliveryInfo2 = ProposalDeliveryInfo.sendDelivery(differentProposal, collaboratorSender);
//
//        assertFalse(deliveryInfo1.sameAs(deliveryInfo2));
//    }
//
//    @Test
//    void ensureIdentityReturnsCorrectCode() {
//        ProposalDeliveryInfo deliveryInfo = ProposalDeliveryInfo.sendDelivery(proposal, collaboratorSender);
//
//        ProposalDeliveryInfoCode code = deliveryInfo.identity();
//        assertNotNull(code);
//        assertEquals(deliveryInfo.identity(), code);
//    }
}