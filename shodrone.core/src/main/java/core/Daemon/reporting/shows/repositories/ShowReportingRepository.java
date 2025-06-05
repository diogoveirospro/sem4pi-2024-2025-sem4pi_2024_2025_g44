package core.Daemon.reporting.shows.repositories;

import core.Daemon.reporting.shows.ShowReporting;
import eapli.framework.domain.repositories.ReportingRepository;

import java.util.List;

@ReportingRepository
public interface ShowReportingRepository {

    List<ShowReporting> findShowsOfCustomer(String vatNumber);
}
