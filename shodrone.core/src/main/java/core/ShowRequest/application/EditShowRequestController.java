package core.ShowRequest.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.validations.Preconditions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class EditShowRequestController {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();


    public Iterable<ShowRequest> listShowRequestWithoutProposal(Customer customer) {
        return showRequestRepository.findAllCreatedShowRequestsByCustomerWithoutProposal(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }


    public void editShowRequest(ShowRequest showRequest, Location location, ShowDescription showDescription,
                                LocalDate date, LocalTime time, Duration duration, QuantityOfDrones quantityOfDrones) {
        Preconditions.noneNull(showRequest);
        showRequest.editInformation(location, showDescription, date, time, duration, quantityOfDrones);
        showRequestRepository.save(showRequest);
    }
}
