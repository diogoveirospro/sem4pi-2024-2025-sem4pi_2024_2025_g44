package inMemory;

import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.ShowRequestID;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.ArrayList;
import java.util.List;

public class InMemoryShowRequestRepository extends InMemoryDomainRepository<ShowRequest, ShowRequestID> implements ShowRequestRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<ShowRequest> findAllCreatedShowRequests() {
        Iterable<ShowRequest> showRequests = findAll();
        List<ShowRequest> result = new ArrayList<>();
        for (ShowRequest showRequest : showRequests) {
            if (showRequest.getShowRequestStatus() == ShowRequestStatus.CREATED) {
                result.add(showRequest);
            }
        }
        return result;
    }
}