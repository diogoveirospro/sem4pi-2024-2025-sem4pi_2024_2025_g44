package core.ShowRequest.repositories;


import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import eapli.framework.domain.repositories.DomainRepository;

public interface ShowRequestRepository extends DomainRepository<ShowRequestID, ShowRequest> {

    Iterable<ShowRequest> findAllCreatedShowRequests();
}
