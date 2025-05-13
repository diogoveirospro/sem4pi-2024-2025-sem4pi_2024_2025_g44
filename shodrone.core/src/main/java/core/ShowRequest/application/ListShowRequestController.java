package core.ShowRequest.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;

public class ListShowRequestController {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();

    public Iterable<ShowRequest> listShowRequest(Customer customer) {
        return showRequestRepository.findAllCreatedShowRequestsByCustomer(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

}
