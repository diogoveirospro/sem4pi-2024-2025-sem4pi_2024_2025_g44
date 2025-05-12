package Shodrone.console.ShowRequest.ui;

import Shodrone.exceptions.UserCancelledException;
import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Persistence.PersistenceContext;
import core.ShowRequest.application.RegisterShowRequestController;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

public class RegisterShowRequestUI extends AbstractFancyUI {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final RegisterShowRequestController controller = new RegisterShowRequestController();
    private final ShodroneUserRepository userRepository = PersistenceContext.repositories().shodroneUsers();

    @Override
    protected boolean doShow() {
        try {

            //TODO: Understand how to get current user and check information
//            if (authenticatedUser is not a Collaborator)
//            {
//                print error msg
//                return false
//            }


//            ShowDescription showDescription = new ShowDescription(enterValidFilename());
//            Location location = new Location(enterValidLocation());
//            LocalDate date = new LocalDate(enterValidDate());
//            LocalTime time = new LocalTime(enterValidTime());
//            QuantityOfDrones quantityOfDrones = new QuantityOfDrones(enterValidQuantityOfDrones());
//
//            Customer customer = getCustomer();
//            CRMCollaborator crmCollaborator = getCrmCollaborator();
//
//            ShowRequest showRequest = new ShowRequest(showDescription, date, time, location, quantityOfDrones, customer, crmCollaborator);
//
//            controller.save(showRequest);
//
//            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "Show Request added successfully!" + UtilsUI.RESET);
//            UtilsUI.goBackAndWait();
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            return false;
        } catch (
        UserCancelledException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            return false;
        }
    }


    private Customer getCustomer()
    {
        //TODO: get customers and select one to return
        controller.listCustomers();
        return null;
    }

    private CRMCollaborator getCrmCollaborator()
    {
        //TODO: get CRM Collaborator and return it
        return null;
    }




    @Override
    public String headline() {
        return "Register Show Request";
    }
}
