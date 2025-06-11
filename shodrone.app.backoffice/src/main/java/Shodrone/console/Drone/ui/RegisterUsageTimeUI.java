package Shodrone.console.Drone.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.application.RegisterUsageTimeController;
import core.Drone.domain.Entities.Drone;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class RegisterUsageTimeUI extends AbstractFancyUI {

    private final RegisterUsageTimeController controller = new RegisterUsageTimeController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DronePrinter dronePrinter = new DronePrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {
                Drone drone = selectDrone();
                if (drone == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Drone selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                LocalTime time = inputUsageTime();

                controller.registerUsageTime(drone, time);

                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nAdded usage time successfully!" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
            }
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }catch (UserCancelledException e) {
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    @Override
    public String headline() {
        return "Add Usage Time to Drone";
    }

    private Drone selectDrone() {
        Iterable<Drone> drones = controller.findAllDronesInventory();
        if (drones == null || !drones.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo drone available." + UtilsUI.RESET);
            return null;
        }

        List<Drone> droneList = new ArrayList<>();
        drones.forEach(droneList::add);


        int option;
        do {
            ListWidget<Drone> droneListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "Choose a drone:\n" + UtilsUI.RESET, droneList, dronePrinter);
            droneListWidget.show();
            option = UtilsUI.selectsIndex(droneList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled.\n" + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return droneList.get(option);
            }
        } while (true);
    }

    private LocalTime inputUsageTime() {
        String imputTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        do {
            try {
                imputTime = UtilsUI.readLineFromConsole(UtilsUI.BOLD + UtilsUI.BLUE + "\nEnter usage time in HH:mm format (or type 'cancel' to exit): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(imputTime)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }else {
                    LocalTime time = LocalTime.parse(imputTime, formatter);
                    // verificar se o formato é o correto e se vai para a exceção
                    if (time.getSecond() != 0 || time.getNano() != 0) {
                        throw new IllegalArgumentException("Invalid time format. Only hours and minutes are allowed.");
                    }

                    return  time;

                }
            }catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid time format. Please try again." + UtilsUI.RESET);
            }

        } while (true);
    }
}