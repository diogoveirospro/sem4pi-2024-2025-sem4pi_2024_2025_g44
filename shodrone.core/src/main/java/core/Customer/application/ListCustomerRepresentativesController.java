package core.Customer.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.domain.Entities.CustomerRepresentative;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class ListCustomerRepresentativesController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }


    public Iterable<CustomerRepresentative> listRepresentativesOfCustomer(Customer customer) {
        return customerRepository.findAllActiveCustomerRepresentatives(customer);
    }
}
