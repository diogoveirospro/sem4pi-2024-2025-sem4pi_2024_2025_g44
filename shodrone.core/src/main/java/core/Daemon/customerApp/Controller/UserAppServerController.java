package core.Daemon.customerApp.Controller;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Daemon.reporting.shows.ShowReporting;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import core.Persistence.PersistenceContext;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;

public class UserAppServerController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();
    private final ShowReportingRepository showReportingRepository =PersistenceContext.repositories().shows();

    public ShodroneUser getShodroneUserByUsername(String username) {
        Username userUsername = Username.valueOf(username);
        return userRepository.findByUsername(userUsername);
    }

    public Customer getCustomerByRepresentativeEmail(String repEmail) {
        return customerRepository.findCustomerByRepresentativeEmail(repEmail);
    }

    public List<ShowReporting> getShowsByVatNumber(String vatNumber) {
        return showReportingRepository.findShowsOfCustomer(vatNumber);
    }
}
