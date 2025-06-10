package core.ShowRequest.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.Service.DurationConverter;
import core.ShowRequest.application.Service.GenerateShowRequestID;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entity representing a Show Request in the system.
 * This class encapsulates all the necessary details of a show request,
 */
@Entity
public class ShowRequest implements AggregateRoot<ShowRequestID> {

    /**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Unique identifier for the ShowRequest.
     * This is generated automatically by the persistence provider.
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * The version of the ShowRequest, used for optimistic locking.
     */
    @Version
    private Long version;

    /**
     * The unique identifier of the ShowRequest, encapsulated in a value object.
     * This is generated using the GenerateShowRequestID service.
     */
    @Embedded
    private ShowRequestID showRequestID;

    /**
     * The status of the ShowRequest, indicating its current state in the lifecycle.
     */
    @Enumerated(EnumType.STRING)
    private ShowRequestStatus status;

    /**
     * The description of the show, encapsulated in a value object.
     */
    @Embedded
    private ShowDescription showDescription;

    /**
     * The date when the show is scheduled to take place.
     */
    @Column(name = "date_of_show")
    private LocalDate dateOfShow;

    /**
     * The time when the show is scheduled to start.
     */
    @Column(name = "time_of_show")
    private LocalTime timeOfShow;

    /**
     * The duration of the show, encapsulated in a value object.
     */
    @Column(name = "duration_of_show")
    @Convert(converter = DurationConverter.class)
    private Duration durationOfShow;


    /**
     * The location where the show will take place, encapsulated in a value object.
     */
    @Embedded
    private Location location;

    /**
     * The quantity of drones required for the show, encapsulated in a value object.
     */
    @Embedded
    @Column(name = "quantity_of_drones")
    private QuantityOfDrones quantityOfDrones;

    /**
     * The customer who requested the show.
     * This is a many-to-one relationship with the Customer entity.
     */
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    /**
     * The CRM collaborator responsible for the show request.
     * This is a many-to-one relationship with the CRMCollaborator entity.
     */
    @ManyToOne
    @JoinColumn(name = "collaborator_id", nullable = false)
    private CRMCollaborator crmCollaborator;

    /**
     * Default constructor for JPA.
     * This constructor is protected to prevent instantiation outside of the persistence context.
     */
    protected ShowRequest(){}

    /**
     * Constructs a new ShowRequest with the provided details.
     *
     * @param showDescription   The description of the show.
     * @param dateOfShow        The date when the show is scheduled.
     * @param timeOfShow        The time when the show is scheduled to start.
     * @param durationOfShow    The duration of the show.
     * @param location          The location where the show will take place.
     * @param quantityOfDrones  The quantity of drones required for the show.
     * @param customer          The customer who requested the show.
     * @param crmCollaborator   The CRM collaborator responsible for the request.
     */
    public ShowRequest(ShowDescription showDescription, LocalDate dateOfShow, LocalTime timeOfShow, Duration durationOfShow,
                       Location location, QuantityOfDrones quantityOfDrones, Customer customer, CRMCollaborator crmCollaborator) {
        this.showDescription = showDescription;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.durationOfShow = durationOfShow;
        this.location = location;
        this.quantityOfDrones = quantityOfDrones;
        this.customer = customer;
        this.crmCollaborator = crmCollaborator;

        this.status = ShowRequestStatus.CREATED;
        this.showRequestID = new GenerateShowRequestID().generate();
    }

    /**
     * Constructs a new ShowRequest with the provided details and a custom ID generator.
     * This constructor is primarily used for testing purposes.
     *
     * @param showDescription   The description of the show.
     * @param dateOfShow        The date when the show is scheduled.
     * @param timeOfShow        The time when the show is scheduled to start.
     * @param durationOfShow    The duration of the show.
     * @param location          The location where the show will take place.
     * @param quantityOfDrones  The quantity of drones required for the show.
     * @param customer          The customer who requested the show.
     * @param crmCollaborator   The CRM collaborator responsible for the request.
     * @param generateShowRequestID The custom ID generator for testing purposes.
     */
    public ShowRequest(ShowDescription showDescription, LocalDate dateOfShow, LocalTime timeOfShow, Duration durationOfShow,
                       Location location, QuantityOfDrones quantityOfDrones, Customer customer,
                       CRMCollaborator crmCollaborator, GenerateShowRequestID generateShowRequestID ) {
        this.showDescription = showDescription;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.durationOfShow = durationOfShow;
        this.location = location;
        this.quantityOfDrones = quantityOfDrones;
        this.customer = customer;
        this.crmCollaborator = crmCollaborator;

        this.status = ShowRequestStatus.CREATED;
        this.showRequestID = generateShowRequestID.generateWithoutRep(); // for testing purposes
    }

    /**
     * Returns the status of the ShowRequest.
     *
     * @return The current status of the ShowRequest.
     */
    public ShowRequestStatus status() {
        return status;
    }

    /**
     * Returns the description of the show.
     *
     * @return The description of the show.
     */
    public ShowDescription showDescription() {
        return showDescription;
    }

    /**
     * Returns the date when the show is scheduled to take place.
     *
     * @return The date of the show.
     */
    public LocalDate dateOfShow() {
        return dateOfShow;
    }

    /**
     * Returns the time when the show is scheduled to start.
     *
     * @return The time of the show.
     */
    public LocalTime timeOfShow() {
        return timeOfShow;
    }

    /**
     * Returns the duration of the show.
     *
     * @return The duration of the show.
     */
    public Duration durationOfShow() {
        return durationOfShow;
    }

    /**
     * Returns the location where the show will take place.
     *
     * @return The location of the show.
     */
    public Location location() {
        return location;
    }

    /**
     * Returns the quantity of drones required for the show.
     *
     * @return The quantity of drones.
     */
    public QuantityOfDrones quantityOfDrones() {
        return quantityOfDrones;
    }

    /**
     * Returns the customer who requested the show.
     *
     * @return The customer associated with the show request.
     */
    public Customer customer() {
        return customer;
    }

    /**
     * Returns the CRM collaborator responsible for the show request.
     *
     * @return The CRM collaborator associated with the show request.
     */
    public CRMCollaborator crmCollaborator() {
        return crmCollaborator;
    }

    /**
     * Edits the information of the ShowRequest.
     * This method allows updating the details of the show request.
     *
     * @param location          The new location for the show.
     * @param showDescription   The new description of the show.
     * @param date              The new date for the show.
     * @param time              The new time for the show.
     * @param duration          The new duration of the show.
     * @param quantityOfDrones  The new quantity of drones required for the show.
     */
    public void editInformation(Location location, ShowDescription showDescription, LocalDate date, LocalTime time,
                                Duration duration, QuantityOfDrones quantityOfDrones)
    {
        this.location = location;
        this.showDescription = showDescription;
        this.dateOfShow = date;
        this.timeOfShow = time;
        this.durationOfShow = duration;
        this.quantityOfDrones = quantityOfDrones;
    }

    /**
     * Method to check if this ShowRequest is the same as another object.
     * @param other The object to compare with this ShowRequest.
     * @return true if the other object is a ShowRequest with the same details, false otherwise.
     */
    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowRequest)) return false;
        ShowRequest that = (ShowRequest) other;
        return this.customer.equals(that.customer) &&
                this.crmCollaborator.equals(that.crmCollaborator) &&
                this.timeOfShow.equals(that.timeOfShow) &&
                this.dateOfShow.equals(that.dateOfShow) &&
                this.durationOfShow.equals(that.durationOfShow);
    }

    /**
     * Compares this ShowRequest with another ShowRequestID.
     * This method is used to compare the unique identifier of the ShowRequest.
     *
     * @param other The ShowRequestID to compare with.
     * @return A negative integer, zero, or a positive integer as this ShowRequestID is less than, equal to, or
     * greater than the specified ShowRequestID.
     */
    @Override
    public int compareTo(ShowRequestID other) {
        if (other == null) {
            return 1;
        }
        return this.showRequestID.compareTo(other);
    }

    /**
     * Returns the unique identifier of this ShowRequest.
     *
     * @return The ShowRequestID of this ShowRequest.
     */
    public ShowRequestID identity() {
        return this.showRequestID;
    }

    /**
     * Checks if this ShowRequest has the same identity as the provided ShowRequestID.
     * @param id The ShowRequestID to check against this ShowRequest's identity.
     * @return true if the provided ShowRequestID matches this ShowRequest's identity, false otherwise.
     */
    @Override
    public boolean hasIdentity(ShowRequestID id) {
        if (id == null) {
            return false;
        }
        return this.showRequestID.equals(id);
    }

    /**
     * Checks if this ShowRequest is equal to another object.
     * @param o The object to compare with this ShowRequest.
     * @return true if the other object is a ShowRequest with the same identity, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        return this.sameAs(o);
    }

    /**
     * Returns the hash code of this ShowRequest.
     * This method is used to generate a hash code based on the identity of the ShowRequest.
     *
     * @return The hash code of this ShowRequest.
     */
    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    /**
     * Returns a string representation of this ShowRequest.
     * This method provides a human-readable description of the ShowRequest, including its ID and status.
     *
     * @return A string representation of this ShowRequest.
     */
    @Override
    public String toString() {
        return ("Show -> " + this.showRequestID.toString() + "\nStatus - " + this.status + "\n");
    }
}