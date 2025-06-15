package Shodrone.console.Maintenance.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import Shodrone.console.Maintenance.printer.MaintenanceTypePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.Drone.domain.Entities.Drone;
import core.Maintenance.application.AddMaintenanceRecordDroneController;
import core.Maintenance.domain.Entities.MaintenanceType;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AddMaintenanceRecordDroneUI extends AbstractFancyUI {
    private final AddMaintenanceRecordDroneController controller = new AddMaintenanceRecordDroneController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final DronePrinter dronePrinter = new DronePrinter();
    private final MaintenanceTypePrinter maintenanceTypePrinter = new MaintenanceTypePrinter();

    @Override
    protected boolean doShow() {
        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)) {
                Drone drone = selectDrone();
                if (drone == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Drone selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                MaintenanceType maintenanceType = selectMaintenanceType();
                if (maintenanceType == null) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Maintenance Type Selected. Operation canceled." + UtilsUI.RESET);
                    return false;
                }

                String maintenanceDescription = maintenanceDescription();

                controller.addMaintenance(drone, maintenanceType,
                        LocalDate.now(),maintenanceDescription);

                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nAdd Maintenance to a drone successes!" + UtilsUI.RESET);
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

    private MaintenanceType selectMaintenanceType() {
        Iterable<MaintenanceType> maintenanceTypes = controller.findAllMaintenanceType();
        if (maintenanceTypes == null || !maintenanceTypes.iterator().hasNext()) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Maintenance Types available." + UtilsUI.RESET);
            return null;
        }

        List<MaintenanceType> maintenanceTypeList = new ArrayList<>();
        maintenanceTypes.forEach(maintenanceTypeList::add);


        int option;
        do {
            ListWidget<MaintenanceType> maintenanceTypeListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE +
                    "\n\nChoose a Maintenance Type:\n" + UtilsUI.RESET, maintenanceTypeList, maintenanceTypePrinter);
            maintenanceTypeListWidget.show();
            option = UtilsUI.selectsIndex(maintenanceTypeList);
            if (option == -2) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nSelection cancelled.\n" + UtilsUI.RESET);
                return null;
            }
            if (option == -1) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid option. Please try again.\n" + UtilsUI.RESET);
            } else {
                return maintenanceTypeList.get(option);
            }
        } while (true);
    }

    private String maintenanceDescription() {

        String description;

        do {
            try {
                description = UtilsUI.readLineFromConsole(UtilsUI.BOLD + UtilsUI.BLUE + "\nEnter Maintenance Description to a drone (or type 'cancel' to exit): " + UtilsUI.RESET);

                if ("cancel".equalsIgnoreCase(description)) {
                    throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                }else {
                    if (description.isBlank()) {
                        throw new IllegalArgumentException("Description cannot be empty.");
                    }
                    return  description;
                }
            }catch (IllegalArgumentException | NullPointerException e) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid description. Please try again." + UtilsUI.RESET);
            }

        } while (true);


    }

    @Override
    public String headline() {
        return "Add Maintenance Record to Drone";
    }
}