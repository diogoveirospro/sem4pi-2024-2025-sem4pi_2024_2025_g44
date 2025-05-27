package core.ShowProposal.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Figure.domain.Entities.Figure;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.domain.ValueObjects.*;
import core.ShowRequest.domain.Entities.ShowRequest;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ShowProposal implements Serializable, AggregateRoot<Long> {

    /**
     * Serial version UID for serialization.
     */
    @Id
    @GeneratedValue
    private Long id;


    @Version
    private Long version;

    /**
     * The date and time when the Figure was created
     */
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    /**
     * The status of the ShowProposal
     */
    @Enumerated(EnumType.STRING)
    private ShowProposalStatus status;

    /**
     * The template of the ShowProposal
     */
    @Embedded
    private ShowProposalTemplate template;

    @Embedded
    private Video video;


    /**
     * The request of the ShowProposal
     */
    @ManyToOne
    @JoinColumn(name = "request_id", nullable = false)
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
     * The figures of the ShowProposal
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "show_proposal_id")
    private List<Figure> figures;

    /**
     * The date and time for the Show
     */
    @Column(name = "date_of_show")
    private LocalDate dateOfShow;

    @Column(name = "time_of_show")
    private LocalTime timeOfShow;

    /**
     * The quantity of drones of the ShowProposal
     */
    @Embedded
    private QuantityOfDrones quantityOfDrones;
    /**
     * The value of the insurance of the show
     */
    @Embedded
    private Insurance insurance;

    /**
     * The email of the feedback of the show
     */
    @Embedded
    private FeedbackEmail feedbackEmail;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "configuration")
    private ShowConfiguration configuration;

    protected ShowProposal() {}

    public  ShowProposal(ShowRequest request, LocalDate dateOfShow, LocalTime timeOfShow,
                          QuantityOfDrones quantityOfDrones, Insurance insurance, CRMCollaborator creator) {
        this.request = request;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.quantityOfDrones = quantityOfDrones;
        this.insurance = insurance;
        this.creator = creator;
        this.sender = creator; // Initially, the creator is also the sender for simplicity
        this.status = ShowProposalStatus.TESTING;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
        this.figures = new ArrayList<>();
    }


    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        ShowProposal proposal = (ShowProposal) other;
        return id.equals(proposal.id);
    }

    @Override
    public Long identity() {
        return id;
    }

    public CRMCollaborator creator() {
        return creator;
    }

    public CRMCollaborator sender() {
        return sender;
    }

    public ShowRequest request() {
        return request;
    }

    public LocalDate dateOfShow() {
        return dateOfShow;
    }

    public LocalTime timeOfShow() {
        return timeOfShow;
    }

    public QuantityOfDrones quantityOfDrones() {
        return quantityOfDrones;
    }

    public Insurance insurance() {
        return insurance;
    }

    public ShowProposalStatus status() {
        return status;
    }

    public ShowProposalTemplate template() {
        return template;
    }

    public List<Figure> figures() {
        return figures;
    }

    public FeedbackEmail feedbackEmail() {
        return feedbackEmail;
    }

    public ShowConfiguration configuration() {
        return configuration;
    }

    public void addVideo(Video video) {
        if (video == null) {
            throw new IllegalArgumentException("Video cannot be null.");
        }
        if (status != ShowProposalStatus.TESTING) {
            throw new IllegalArgumentException("Cannot add video to a non-testing proposal.");
        }
        this.video = video;
    }
}
