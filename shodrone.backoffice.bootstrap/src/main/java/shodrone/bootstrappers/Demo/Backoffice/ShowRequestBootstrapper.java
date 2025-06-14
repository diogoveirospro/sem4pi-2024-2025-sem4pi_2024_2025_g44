package shodrone.bootstrappers.Demo.Backoffice;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.Customer.domain.Entities.Customer;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.Shared.domain.ValueObjects.Name;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowRequest.application.RegisterShowRequestController;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.domain.ValueObjects.Location;
import core.ShowRequest.domain.ValueObjects.ShowDescription;
import core.ShowRequest.domain.ValueObjects.ShowRequestStatus;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;
import shodrone.presentation.UtilsUI;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ShowRequestBootstrapper extends UsersBootstrapperBase implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ShowRequestBootstrapper.class);
    private static final RegisterShowRequestController controller = new RegisterShowRequestController();
    private static final CRMCollaboratorRepository collaboratorRepository = PersistenceContext.repositories().crmCollaborators();

    @Override
    public boolean execute() {
        register("Description", "12-12-2027", "12:12", 40.7128, -74.0060, 10.0, "10", "EA Sports", Duration.ofHours(2));
        register("Description2", "13-11-2026", "22:22", 34.0522, -118.2437, 15.0, "10", "EA Sports", Duration.ofHours(3));
        register("Description3", "14-10-2025", "12:32", 51.5074, -0.1278, 20.0, "15", "Ubisoft", Duration.ofHours(1));
        register("Description4", "15-09-2024", "14:45", 48.8566, 2.3522, 5.0, "10", "Microsoft", Duration.ofHours(4));
        register("Description5", "16-08-2023", "16:30", 35.6895, 139.6917, 8.0, "10", "EA Sports", Duration.ofHours(5));
        return true;
    }

    private void register(String description, String date, String time, double latitude, double longitude, double altitude, String quantityOfDrones, String customerName, Duration duration) {
        Email email = new Email("bruce.wayne@showdrone.com");
        CRMCollaborator crmCollaborator = collaboratorRepository.findByEmail(email);
        Customer customer = PersistenceContext.repositories().customers().findCustomerByName(new Name(customerName));

        ShowDescription description1 = new ShowDescription(description);
        LocalDate date1 = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalTime time1 = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
        Location location1 = new Location(latitude, longitude, altitude);
        QuantityOfDrones quantityOfDrones1 = new QuantityOfDrones(quantityOfDrones);

        ShowRequest showRequest = new ShowRequest(description1, date1, time1,duration, location1, quantityOfDrones1, customer, crmCollaborator);
        controller.registerShowRequest(showRequest);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Show Request '{}' registered" + UtilsUI.RESET, showRequest.identity());
    }
}