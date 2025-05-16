package shodrone.bootstrappers.Demo.Backoffice;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.Customer.domain.Entities.Customer;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.PhoneNumber;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.RegisterShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShowRequestBootstrapper extends UsersBootstrapperBase implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ShowRequestBootstrapper.class);
    private static final RegisterShowRequestController controller = new RegisterShowRequestController();

    @Override
    public boolean execute() {
        register("Description", "12-12-2001", "12:12", "Porto", "123", "EA Sports");
        register("Description2", "13-11-2002", "22:22", "Lisbon", "101", "EA Sports");
        register("Description3", "14-10-2003", "12:32", "Aveiro", "50", "Ubisoft");
        return true;
    }
    private void register(String description, String date, String time, String location, String quantityOfDrones, String customerName) {

        CRMCollaborator crmCollaborator = new CRMCollaborator(new Name("Bruce wayne"), new PhoneNumber("+351", "999999999"), new Email("bruce.wayne@showdrone.com"));
        Customer customer = PersistenceContext.repositories().customers().findCustomerByName(new Name(customerName));

        ShowDescription description1 = new ShowDescription(description);
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time1 = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        Location location1 = new Location(location);
        QuantityOfDrones quantityOfDrones1 = new QuantityOfDrones(quantityOfDrones);

        ShowRequest showRequest = new ShowRequest(description1, date1, time1, location1, quantityOfDrones1, customer, crmCollaborator);

        controller.registerShowRequest(showRequest);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Show Request registered: {}" + UtilsUI.RESET, showRequest.toString());
    }
}
