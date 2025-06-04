package core.ShowProposal.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMManager.domain.Entities.CRMManager;
import core.Figure.domain.Entities.Figure;
import core.ModelOfDrone.domain.Entities.Model;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.application.Service.GenerateProposalNumber;
import core.ShowProposal.application.Service.TemplateLoaderService;
import core.ShowProposal.domain.ValueObjects.*;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class ShowProposal implements Serializable, AggregateRoot<ShowProposalNumber> {

    /**
     * Serial version UID for serialization.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The proposal number of the ShowProposal
     */
    @Embedded
    @Column(unique = true, nullable = false)
    private ShowProposalNumber proposalNumber;

    /**
     * The version of the ShowProposal, used for optimistic locking
     */
    @Version
    private Long version;

    /**
     * The date and time when the Figure was created
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the Figure was last updated
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    /**
     * The status of the ShowProposal
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ShowProposalStatus status;

    /**
     * The document of the ShowProposal
     */
    @Embedded
    @Column(name = "document")
    private ShowProposalDocument document;

    /**
     * The video of the ShowProposal
     */

    @Embedded
    @Column(name = "video")
    private Video video;

    /**
     * The request of the ShowProposal
     */
    @ManyToOne
    @JoinColumn(name = "request", nullable = false)
    private ShowRequest request;

    /**
     * The collaborator that created the ShowProposal
     */
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CRMCollaborator creator;

    /**
     * The collaborator that sent the ShowProposal
     */
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private CRMCollaborator sender;

    /**
     * The manager that sent the ShowProposal
     */
    @ManyToOne
    @JoinColumn(name = "crm_manager_id")
    private CRMManager crmManager;

    /**
     * The date and time for the Show
     */
    @Column(name = "date_of_show")
    private LocalDate dateOfShow;

    /**
     * The time of the Show
     */
    @Column(name = "time_of_show")
    private LocalTime timeOfShow;

    /**
     * The duration of the Show
     */
    @Column(name = "duration_of_show")
    private Duration durationOfShow;

    /**
     * The quantity of drones of the ShowProposal
     */
    @Embedded
    @Column(name = "quantityOfDrones")
    private QuantityOfDrones quantityOfDrones;

    /**
     * The value of the insurance of the show
     */
    @Embedded
    @Column(name = "insurance")
    private Insurance insurance;

    /**
     * The email of the feedback of the show
     */
    @Embedded
    @Column(name = "customerFeedback")
    private CustomerFeedback customerFeedback;

    @Enumerated(EnumType.STRING)
    @Column(name = "feedbackStatus")
    private CustFeedbackStatus feedbackStatus;

    /**
     * The configuration of the ShowProposal contains Figures and Drones
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration")
    private ShowConfiguration configuration;

    /**
     * Default constructor for JPA
     */
    protected ShowProposal() {}

    /**
     * Constructor for creating a ShowProposal with all necessary parameters.
     * @param request the ShowRequest associated with this proposal
     * @param dateOfShow the date of the show
     * @param timeOfShow the time of the show
     * @param durationOfShow the duration of the show
     * @param quantityOfDrones the quantity of drones for the show
     * @param insurance the insurance for the show
     * @param creator the collaborator who created the proposal
     */
    public ShowProposal(ShowRequest request, LocalDate dateOfShow, LocalTime timeOfShow,
                        Duration durationOfShow, QuantityOfDrones quantityOfDrones, Insurance insurance, CRMCollaborator creator, CustomerFeedback customerFeedback, CustFeedbackStatus feedbackStatus) {
        this.request = request;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.durationOfShow = durationOfShow;
        this.quantityOfDrones = quantityOfDrones;
        this.insurance = insurance;
        this.creator = creator;
        this.sender = creator; // Initially, the creator is also the sender for simplicity
        this.status = ShowProposalStatus.TESTING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.proposalNumber = new GenerateProposalNumber().generate();
        this.customerFeedback = customerFeedback;
        this.feedbackStatus = CustFeedbackStatus.PENDING;
    }

    /**
     * Constructor for creating a ShowProposal with all necessary parameters, including a proposal number generator.
     * @param request the ShowRequest associated with this proposal
     * @param dateOfShow the date of the show
     * @param timeOfShow the time of the show
     * @param durationOfShow the duration of the show
     * @param quantityOfDrones the quantity of drones for the show
     * @param insurance the insurance for the show
     * @param creator the collaborator who created the proposal
     * @param proposalNumberGenerator the generator for the proposal number
     */
    public ShowProposal(ShowRequest request, LocalDate dateOfShow, LocalTime timeOfShow,
                        Duration durationOfShow, QuantityOfDrones quantityOfDrones, Insurance insurance, CRMCollaborator creator,
                        GenerateProposalNumber proposalNumberGenerator) {
        this.request = request;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.durationOfShow = durationOfShow;
        this.quantityOfDrones = quantityOfDrones;
        this.insurance = insurance;
        this.creator = creator;
        this.sender = creator; // Initially, the creator is also the sender for simplicity
        this.status = ShowProposalStatus.TESTING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.proposalNumber = proposalNumberGenerator.generateWithoutRep(); // for testing purposes
    }

    /**
     * Method sameAs checks if this ShowProposal is the same as another object.
     * @param other the object to compare with this ShowProposal
     * @return true if the other object is a ShowProposal with the same id, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ShowProposal proposal = (ShowProposal) other;
        return id.equals(proposal.id);
    }

    /**
     * Obtains the unique identifier of this ShowProposal.
     * @return the proposal number of this ShowProposal
     */
    @Override
    public ShowProposalNumber identity() {
        return proposalNumber;
    }

    /**
     * Return the creator of the ShowProposal.
     * @return the CRMCollaborator who created the ShowProposal
     */
    public CRMCollaborator creator() {
        return creator;
    }

    /**
     * Return the sender of the ShowProposal.
     * @return the CRMCollaborator who sent the ShowProposal
     */
    public CRMCollaborator sender() {
        return sender;
    }

    /**
     * Return the CRMManager associated with the ShowProposal.
     * @return the CRMManager who manages this ShowProposal
     */
    public CRMManager crmManager() {
        return crmManager;
    }

    /**
     * Add a CRMManager to the ShowProposal.
     * @param crmManager the CRMManager to be added
     */
    public void addCRMManager(CRMManager crmManager) {
        if (crmManager == null) {
            throw new IllegalArgumentException("CRM Manager cannot be null or empty.");
        }
        this.crmManager = crmManager;
    }

    /**
     * Return the ShowRequest associated with this ShowProposal.
     * @return the ShowRequest linked to this ShowProposal
     */
    public ShowRequest request() {
        return request;
    }

    /**
     * Return the date of the show.
     * @return the date of the show
     */
    public LocalDate dateOfShow() {
        return dateOfShow;
    }

    /**
     * Return the time of the show.
     * @return the time of the show
     */
    public LocalTime timeOfShow() {
        return timeOfShow;
    }

    /**
     * Return the duration of the show.
     * @return the duration of the show
     */
    public Duration durationOfShow() {
        return durationOfShow;
    }

    /**
     * Return the number of drones for the show.
     * @return the quantity of drones for the show
     */
    public QuantityOfDrones quantityOfDrones() {
        return quantityOfDrones;
    }

    /**
     * Return the insurance for the show.
     * @return the insurance for the show
     */
    public Insurance insurance() {
        return insurance;
    }

    /**
     * Return the status of the ShowProposal.
     * @return the status of the ShowProposal
     */
    public ShowProposalStatus status() {
        return status;
    }

    /**
     * Return the proposal document of the ShowProposal.
     * @return the ShowProposalDocument associated with this ShowProposal
     */
    public ShowProposalDocument document() {
        return document;
    }

    public void setShowProposalStatus(ShowProposalStatus status) {
        this.status = status;
    }

    /**
     * Add a document to the ShowProposal.
     * @param document the ShowProposalDocument to be added
     */
    public void addDocument(ShowProposalDocument document) {
        if (document == null) {
            throw new IllegalArgumentException("Document cannot be null.");
        }
        this.document = document;
        status = ShowProposalStatus.READY_TO_SEND;
    }

    /**
     * Return the customer feedback for the ShowProposal.
     * @return the CustomerFeedback associated with this ShowProposal
     */
    public CustomerFeedback customerFeedback() {
        return customerFeedback;
    }

    /**
     * Return the show configuration of the ShowProposal.
     * @return the ShowConfiguration associated with this ShowProposal
     */
    public ShowConfiguration configuration() {
        return configuration;
    }

    /**
     * Return the video associated with the ShowProposal.
     * @return the Video associated with this ShowProposal
     */
    public Video video() {
        return video;
    }

    /**
     * Add a configuration to the ShowProposal.
     * @param configuration the ShowConfiguration to be added
     */
    public void addConfiguration(ShowConfiguration configuration) {
        this.configuration = configuration;
    }

        public CustFeedbackStatus feedbackStatus() {
        return feedbackStatus;
    }

    /**
     * Add a video to the ShowProposal.
     * @param video the Video to be added
     */
    public void addVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("Video cannot be null.");
        }
        if (status != ShowProposalStatus.TESTING) {
            throw new IllegalArgumentException("Cannot add video to a non-testing proposal.");
        }
        this.video = video;
    }

    /**
     * Method for determining whether Show Proposal is ready to have a document applied to it
     * @return true if all necessary fields are set, false otherwise
     */
    public boolean isReadyToConfigureDocument (){
        return request.customer() != null && proposalNumber != null && createdAt != null && video != null &&
                insurance != null && request.location() != null && dateOfShow != null && timeOfShow != null
                && durationOfShow != null && configuration.figures() != null && !configuration.figures().isEmpty() &&
                configuration.droneModels() != null && !configuration.droneModels().isEmpty();
    }

    /**
     * Configures the document of the ShowProposal based on the selected template.
     * @param selectedTemplate the template to be used for configuring the document
     * @return the configured document content
     */
    public String configureDocument(String selectedTemplate, CRMManager crmManager) {
        String documentContent;
        try{
            documentContent = new TemplateLoaderService().getTemplateContent(selectedTemplate);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading template: " + e.getMessage());
        }
        ;
        this.crmManager = crmManager;

        // If the template has the name of the Customer Representative
        if (documentContent.contains("[Customer Representative Name]")) {
            documentContent = documentContent.replace("[Customer Representative Name]",
                    "Representative of " + request.customer().name());
        }

        // Write the Customer's information
        documentContent = documentContent.replace("[Company Name]", request.customer().name().toString());
        documentContent = documentContent.replace("[Address with postal code and country]",
                request.customer().address().toString());
        documentContent = documentContent.replace("[VAT Number]", request.customer().vat().toString());

        // Write the Show Proposal's information
        documentContent = documentContent.replace("[proposal number]", proposalNumber.toString());
        documentContent = documentContent.replace("[show proposal number]", proposalNumber.toString());

        documentContent = documentContent.replace("[date]", createdAt.toLocalDate().toString());

        documentContent = documentContent.replace("[link to show video]", video.url());
        documentContent = documentContent.replace("[link to show's simulation video]", video.url());

        documentContent = documentContent.replace("[insurance amount]", insurance.toString());
        documentContent = documentContent.replace("[valor do seguro]", insurance.toString());

        documentContent = documentContent.replace("[CRM Manager Name]", crmManager.name().toString());

        documentContent = documentContent.replace("[GPS coordinates of the location]", request.location().toString());

        documentContent = documentContent.replace("[date of the event]", dateOfShow.toString());
        documentContent = documentContent.replace("[time of the event]", timeOfShow.toString());
        documentContent = documentContent.replace("[duration]", durationOfShow.toString());

        // List of Used Drones
        StringBuilder dronesSection = new StringBuilder();
        Set<Model> models = configuration.droneModels();

        for (Model model : models) {
            long quantity = configuration.showConfiguration().get(model).size();
            dronesSection.append(model.toString())
                    .append(" – ")
                    .append(quantity)
                    .append(" unidades.")
                    .append(System.lineSeparator());
        }

        documentContent = documentContent.replace("[model] – [quantity] units.", dronesSection.toString().trim());
        documentContent = documentContent.replace("[model] – [quantity] unidades.", dronesSection.toString().trim());

        // List of Figures
        StringBuilder figuresSection = new StringBuilder();
        Set<Figure> figures = configuration.figures();

        int position = 1;

        for (Figure figure : figures) {
            figuresSection.append(position)
                    .append(" – ")
                    .append(figure.name())
                    .append(System.lineSeparator());
            position++;
        }

        documentContent = documentContent.replace("[position in show] – [figure name]", figuresSection.toString().trim());

        return documentContent;

    }
}
