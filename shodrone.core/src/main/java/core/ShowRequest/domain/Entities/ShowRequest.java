package core.ShowRequest.domain.Entities;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import eapli.framework.domain.model.AggregateRoot;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowRequest implements AggregateRoot<ShowRequestID> {
    private ShowRequestID showRequestID;
    private ShowRequestStatus showRequestStatus;
    private ShowDescription showDescription;
    private LocalDate dateOfShow;
    private LocalTime timeOfShow;
    private Location location;
    private QuantityOfDrones quantityOfDrones;
    private Customer customer;
    private CRMCollaborator crmCollaborator;

    public ShowRequest(ShowDescription showDescription, LocalDate dateOfShow, LocalTime timeOfShow, Location location, QuantityOfDrones quantityOfDrones, Customer customer, CRMCollaborator crmCollaborator) {
        this.showDescription = showDescription;
        this.dateOfShow = dateOfShow;
        this.timeOfShow = timeOfShow;
        this.location = location;
        this.quantityOfDrones = quantityOfDrones;
        this.customer = customer;
        this.crmCollaborator = crmCollaborator;
    }

    public ShowRequestID getShowRequestID() {
        return showRequestID;
    }

    public ShowRequestStatus getShowRequestStatus() {
        return showRequestStatus;
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


    @Override
    public boolean sameAs(Object other) {

        //TODO: implement
        return true;
    }

    @Override
    public ShowRequestID identity() {

        //TODO: implement
        return null;
    }
}