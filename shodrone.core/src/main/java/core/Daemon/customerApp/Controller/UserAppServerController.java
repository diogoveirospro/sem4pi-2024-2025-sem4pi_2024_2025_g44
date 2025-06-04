package core.Daemon.customerApp.Controller;

import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.repositories.ShowRequestRepository;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class UserAppServerController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();
    private final ShowProposalRepository proposalRepository = PersistenceContext.repositories().proposals();
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();

    public ShodroneUser getShodroneUserByUsername(String username) {
        Username userUsername = Username.valueOf(username);
        return userRepository.findByUsername(userUsername);
    }
}
