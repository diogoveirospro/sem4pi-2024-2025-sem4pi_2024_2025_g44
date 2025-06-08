package core.ShowProposal.application;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.Insurance;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@UseCaseController
public class CreateShowProposalController {
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();
    private final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CRMCollaboratorRepository crmCollaboratorRepository = PersistenceContext.repositories().crmCollaborators();

    public CRMCollaborator getCrmCollaborator() {
        CRMCollaborator collaborator;
        SystemUser user = authz.session().get().authenticatedUser();
        ShodroneUser shodroneUser = userRepository.findByUsername(user.username());
        if (shodroneUser == null) {
            throw new IllegalArgumentException("No user found for the authenticated user.");
        }
        collaborator = crmCollaboratorRepository.findByEmail(shodroneUser.email());
        if (collaborator == null) {
            throw new IllegalArgumentException("No CRM Collaborator found for the authenticated user.");
        }
        return collaborator;
    }

    public Iterable<ShowRequest> listShowRequests(Customer customer) {
        return showRequestRepository.findAllCreatedShowRequestsByCustomer(customer);
    }

    public Iterable<Customer> listCustomers() {
        return customerRepository.findAllCreatedCustomers();
    }

    public void createShowProposal(ShowRequest showRequest, LocalDate date, LocalTime time,
                                   Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance) {
        CRMCollaborator crmCollaborator = getCrmCollaborator();
        Preconditions.noneNull( showRequest, date, time, quantityOfDrones, insurance, crmCollaborator, duration);
        if (!showRequestRepository.contains(showRequest)) {
            throw new IllegalArgumentException("Show request does not exist.");
        }
        if (!crmCollaboratorRepository.contains(crmCollaborator)) {
            throw new IllegalArgumentException("CRM Collaborator does not exist.");
        }
        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                crmCollaborator);
        showProposalRepository.save(showProposal);
    }

    public List<String> listCurrencies() {
        Map<String, String> map;
        map = Insurance.getMainEuropeanCurrencies();
        List<String> currencies = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            currencies.add(entry.getKey());
        }
        return currencies;
    }

    public String getCurrencySymbol(String selectedCurrency) {
        return Insurance.getMainEuropeanCurrencies().get(selectedCurrency);
    }
}
