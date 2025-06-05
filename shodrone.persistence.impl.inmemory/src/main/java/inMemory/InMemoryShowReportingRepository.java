package inMemory;

import core.Daemon.reporting.shows.ShowReporting;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import inMemory.persistence.InMemoryInitializer;

import java.util.List;

public class InMemoryShowReportingRepository implements ShowReportingRepository {
    static {
        InMemoryInitializer.init();
    }

    @Override
    public List<ShowReporting> findShowsOfCustomer(String vatNumber) {
        return List.of();
    }
}
