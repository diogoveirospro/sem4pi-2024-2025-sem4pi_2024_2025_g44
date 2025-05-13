package core.ShowRequest.application;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;

public class EditShowRequestController {

    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();


    public Iterable<ShowRequest> listShowRequestWithoutProposal(Customer customer) {
        return showRequestRepository.findAllCreatedShowRequestsByCustomerWithoutProposal(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

//    public void editShowRequest(Customer customer, CustomerRepresentative representative, Email newEmail, PhoneNumber newPhone) {
//        Preconditions.noneNull(representative);
//        CustomerRepresentative existingRepresentative = customer.findCustomerRepresentative(representative);
//        if (existingRepresentative == null) {
//            throw new IllegalArgumentException("Customer representative not found.");
//        }
//        ShodroneUser user = userRepository.findByEmail(new Email(representative.email().toString()));
//        existingRepresentative.changeInfo(newEmail, newPhone);
//        customerRepository.save(customer);
//        user.changeEmail(newEmail);
//        user.changePhoneNumber(newPhone);
//        userRepository.save(user);
//    }
}
