package core.ShowRequest.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.domain.repositories.IntegrityViolationException;

public class RegisterShowRequestController {
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public void registerShowRequest(ShowRequest showRequest) {
        try {
            showRequestRepository.save(showRequest);
        } catch (IntegrityViolationException e) {
            throw new IllegalArgumentException("Error saving the show request: " + e.getMessage());
        }
    }

    public Iterable<Customer> listCustomers() { return  customerRepository.findAllCreatedCustomers(); }
}
