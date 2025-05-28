package core.ShowRequest.repositories;


import core.Customer.domain.Entities.Customer;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShowRequestRepository extends DomainRepository<ShowRequestID, ShowRequest> {

    Iterable<ShowRequest> findAllCreatedShowRequestsByCustomer(Customer customer);

    Iterable<ShowRequest> findAllCreatedShowRequestsByCustomerWithoutProposal(Customer customer);

    boolean existsByProposalNumber(ShowRequestID showRequestID);

    Iterable<ShowRequest> findAllCreatedShowRequests();
}
