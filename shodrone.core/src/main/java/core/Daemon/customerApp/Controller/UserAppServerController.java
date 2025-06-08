package core.Daemon.customerApp.Controller;

import core.Customer.domain.Entities.Customer;
import core.Customer.repositories.CustomerRepository;
import core.Daemon.reporting.proposals.DeliveryReporting;
import core.Daemon.reporting.proposals.repositories.DeliveryReportingRepository;
import core.Daemon.reporting.shows.ShowReporting;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.User.domain.Entities.ShodroneUser;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.List;

/**
 * Controller for the User App Server.
 * This class provides methods to interact with the user-related data and operations.
 */
public class UserAppServerController {

    /**
     * CustomerRepository to access customer data.
     */
    private final CustomerRepository customerRepository = PersistenceContext.repositories().customers();

    /**
     * ShodroneUserRepository to access user data.
     */
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();

    /**
     * ShowReportingRepository to access show reporting data.
     */
    private final ShowReportingRepository showReportingRepository = PersistenceContext.repositories().shows();

    /**
     * DeliveryReportingRepository to access delivery reporting data.
     */
    private final DeliveryReportingRepository deliveryReportingRepository = PersistenceContext.repositories().deliveriesReporting();

    /**
     * ShowProposalRepository to access show proposal data.
     */
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();

    /**
     * Retrieves a ShodroneUser by their username.
     *
     * @param username the username of the user to retrieve
     * @return the ShodroneUser associated with the given username, or null if not found
     */
    public ShodroneUser getShodroneUserByUsername(String username) {
        Username userUsername = Username.valueOf(username);
        return userRepository.findByUsername(userUsername);
    }

    /**
     * Retrieves a Customer by their representative's email.
     *
     * @param repEmail the email of the representative
     * @return the Customer associated with the given representative's email, or null if not found
     */
    public Customer getCustomerByRepresentativeEmail(String repEmail) {
        return customerRepository.findCustomerByRepresentativeEmail(repEmail);
    }

    /**
     * Retrieves a show reporting list by the customer's VAT number.
     *
     * @param vatNumber the VAT number of the customer
     * @return a list of ShowReporting objects associated with the given VAT number
     */
    public List<ShowReporting> getShowsByVatNumber(String vatNumber) {
        return showReportingRepository.findShowsOfCustomer(vatNumber);
    }

    /**
     * Retrieves a list of delivery proposals associated with a customer identified by their VAT number.
     *
     * @param vatNumber the VAT number of the customer
     * @return a list of DeliveryReporting objects representing the delivery proposals for the customer
     */
    public List<DeliveryReporting> getDeliveryProposalsOfCustomer(String vatNumber) {
        return deliveryReportingRepository.findAllProposalsByCustomer(vatNumber);
    }

    /**
     * Handles the feedback for a proposal identified by its proposal number.
     *
     * @return true if the proposal status and feedback were successfully updated, false otherwise
     */
    public boolean handleProposalFeedback(String proposalNumber, String decision, String feedback) {
        return showProposalRepository.updateProposalStatusAndFeedback(proposalNumber, decision, feedback);
    }
}
