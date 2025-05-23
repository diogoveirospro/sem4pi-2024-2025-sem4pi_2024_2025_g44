package core.ShowRequest.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class ShowRequest implements AggregateRoot<ShowRequestID> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Long version;

    @Embedded
    private ShowRequestID showRequestID;

    @Enumerated(EnumType.STRING)
    private ShowRequestStatus status;

    @Embedded
    private ShowDescription showDescription;

    @Column(name = "date_of_show")
    private LocalDate dateOfShow;

    @Column(name = "time_of_show")
    private LocalTime timeOfShow;

    @Embedded
    private Location location;

    @Embedded
    @Column(name = "quantity_of_drones")
    private QuantityOfDrones quantityOfDrones;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "collaborator_id", nullable = false)
    private CRMCollaborator crmCollaborator;

    protected ShowRequest(){}

    public ShowRequest(ShowDescription showDescription, LocalDate dateOfShow, LocalTime timeOfShow, Location location, QuantityOfDrones quantityOfDrones, Customer customer, CRMCollaborator crmCollaborator) {
        this.showDescription = showDescription;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.location = location;
        this.quantityOfDrones = quantityOfDrones;
        this.customer = customer;
        this.crmCollaborator = crmCollaborator;

        this.status = ShowRequestStatus.CREATED;
        this.showRequestID = new ShowRequestID(customer.identity().toString(), crmCollaborator.identity().toString(), dateOfShow.toString(), timeOfShow.toString());
    }

    public ShowRequestID getShowRequestID() {
        return showRequestID;
    }

    public ShowRequestStatus getStatus() {
        return status;
    }

    public ShowDescription getShowDescription() {
        return showDescription;
    }

    public LocalDate getDateOfShow() {
        return dateOfShow;
    }

    public LocalTime getTimeOfShow() {
        return timeOfShow;
    }

    public Location getLocation() {
        return location;
    }

    public QuantityOfDrones getQuantityOfDrones() {
        return quantityOfDrones;
    }

    public Customer getCustomer() {
        return customer;
    }

    public CRMCollaborator getCrmCollaborator() {
        return crmCollaborator;
    }

    public void editInformation(Location location, ShowDescription showDescription, LocalDate date, LocalTime time, QuantityOfDrones quantityOfDrones)
    {
        this.location = location;
        this.showDescription = showDescription;
        this.dateOfShow = date;
        this.timeOfShow = time;
        this.quantityOfDrones = quantityOfDrones;

        this.showRequestID = new ShowRequestID(customer.identity().toString(), crmCollaborator.identity().toString(), dateOfShow.toString(), timeOfShow.toString());
    }

    @Override
    public boolean sameAs(Object other) {
        if (this == other) return true;
        if (!(other instanceof ShowRequest)) return false;
        ShowRequest that = (ShowRequest) other;
        return this.customer.equals(that.customer) &&
                this.crmCollaborator.equals(that.crmCollaborator) &&
                this.timeOfShow.equals(that.timeOfShow) &&
                this.dateOfShow.equals(that.dateOfShow);
    }

    @Override
    public int compareTo(ShowRequestID other) {
        if (other == null) {
            return 1;
        }
        return this.showRequestID.compareTo(other);
    }

    public ShowRequestID identity() {
        return this.showRequestID;
    }

    @Override
    public boolean hasIdentity(ShowRequestID id) {
        if (id == null) {
            return false;
        }
        return this.showRequestID.equals(id);
    }

    @Override
    public boolean equals(Object o) {
        return this.sameAs(o);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public String toString() {
        return ("Show -> " + this.showRequestID.toString() + "\nStatus - " + this.status + "\n");
    }
}