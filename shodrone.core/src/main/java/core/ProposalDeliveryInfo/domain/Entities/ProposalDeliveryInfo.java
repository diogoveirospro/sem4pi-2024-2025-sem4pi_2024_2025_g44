package core.ProposalDeliveryInfo.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.ProposalDeliveryInfo.application.Service.GenerateProposalDeliveryCode;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.domain.model.AggregateRoot;
import jakarta.persistence.*;

import java.util.Date;

/**
 * Represents the delivery information of a proposal.
 * This entity contains details about the proposal delivery,
 */
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"code"})
})
public class ProposalDeliveryInfo implements AggregateRoot<ProposalDeliveryInfoCode> {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The ID of the ProposalDeliveryInfo in the system
     */
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    /**
     * The version of the ProposalDeliveryInfo in the system
     */
    @Version
    private Long version;

    /**
     * The code of the ProposalDeliveryInfo
     * This code is sent to the user when a proposal is delivered.
     */
    @Embedded
    @Column(name = "code")
    private ProposalDeliveryInfoCode code;

    /**
     * The customer to whom the proposal is delivered
     */
    @ManyToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    /**
     * The proposal that is being delivered
     */
    @OneToOne
    @JoinColumn(name = "proposal", nullable = false)
    private ShowProposal proposal;

    /**
     * The collaborator who is delivering the proposal
     */
    @ManyToOne
    @JoinColumn(name = "collaborator", nullable = false)
    private CRMCollaborator collaborator;

    /**
     * The date when the proposal is delivered
     */
    private Date deliveryAt;


    /**
     * Default constructor for JPA
     */
    protected ProposalDeliveryInfo() {}

    /**
     * Constructor for ProposalDeliveryInfo
     *
     * @param proposal     the proposal that is being delivered
     */
    private ProposalDeliveryInfo(ShowProposal proposal, CRMCollaborator collaborator) {
        if (proposal == null) {
            throw new IllegalArgumentException("Proposal cannot be null");
        }
        this.code = new GenerateProposalDeliveryCode().generate();
        this.customer = proposal.customer();
        this.proposal = proposal;
        this.collaborator = collaborator;
        this.deliveryAt = new Date();
    }

    /**
     * Sends a proposal delivery and creates a new ProposalDeliveryInfo instance.
     * @param proposal the proposal that is being delivered
     * @param collaborator the collaborator who is delivering the proposal
     * @return a new ProposalDeliveryInfo instance
     */
    public static ProposalDeliveryInfo sendDelivery(ShowProposal proposal, CRMCollaborator collaborator) {
        return new ProposalDeliveryInfo(proposal, collaborator);
    }

    /**
     * Returns the customer to whom the proposal is delivered
     * @return the customer to whom the proposal is delivered
     */
    public Customer customer() {
        return this.customer;
    }

    /**
     * Returns the proposal that is being delivered
     * @return the proposal that is being delivered
     */
    public ShowProposal proposal() {
        return this.proposal;
    }

    /**
     * Returns the collaborator who is delivering the proposal
     * @return the collaborator who is delivering the proposal
     */
    public CRMCollaborator collaborator() {
        return this.collaborator;
    }

    /**
     * Returns the date when the proposal is delivered
     * @return the date when the proposal is delivered
     */
    public Date deliveryAt() {
        return this.deliveryAt;
    }

    /**
     * Checks if this ProposalDeliveryInfo is the same as another object.
     * @param other the object to compare with this ProposalDeliveryInfo
     * @return true if the object is a ProposalDeliveryInfo with the same code, customer, proposal, collaborator,
     * and deliveryAt, false otherwise
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ProposalDeliveryInfo)) return false;
        ProposalDeliveryInfo that = (ProposalDeliveryInfo) other;
        return code.equals(that.code) &&
                customer.equals(that.customer) &&
                proposal.equals(that.proposal) &&
                collaborator.equals(that.collaborator) &&
                deliveryAt.equals(that.deliveryAt);
    }

    /**
     * Returns the Identity of this ProposalDeliveryInfo.
     * @return the code of this ProposalDeliveryInfo
     */
    @Override
    public ProposalDeliveryInfoCode identity() {
        return this.code;
    }
}
