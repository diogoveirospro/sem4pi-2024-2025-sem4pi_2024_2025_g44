package Shodrone.console.Maintenance.ui;

import Shodrone.console.Drone.printer.DronePrinter;
import core.Drone.domain.Entities.Drone;
import core.Maintenance.application.ListDroneMaintenanceHistoryController;
import core.Maintenance.domain.Entities.Maintenance;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ListDroneMaintenanceHistoryUI extends AbstractFancyUI {

    private final ListDroneMaintenanceHistoryController controller = new ListDroneMaintenanceHistoryController();
    private final DronePrinter dronePrinter = new DronePrinter();

    @Override
    protected boolean doShow() {
        try {
            if (!AuthzRegistry.authorizationService().isAuthenticatedUserAuthorizedTo(ShodroneRoles.DRONETECH, ShodroneRoles.POWER_USER)) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAccess denied." + UtilsUI.RESET);
                return false;
            }

            Drone drone = selectDrone();
            if (drone == null) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nNo Drone selected. Operation canceled." + UtilsUI.RESET);
                return false;
            }

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start, end;

            while (true) {
                try {
                    String startDate = UtilsUI.readLineFromConsole("Enter start date (YYYY-MM-DD): ");
                    String endDate = UtilsUI.readLineFromConsole("Enter end date (YYYY-MM-DD): ");
                    start = LocalDate.parse(startDate, formatter);
                    end = LocalDate.parse(endDate, formatter);

                    if (start.isAfter(end)) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nStart date cannot be after end date. Please try again." + UtilsUI.RESET);
                        continue;
                    }

                    if (end.isAfter(LocalDate.now())) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nEnd date cannot be in the future. Please try again." + UtilsUI.RESET);
                        continue;
                    }

                    break;

                } catch (DateTimeParseException e) {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid date format. Use YYYY-MM-DD." + UtilsUI.RESET);
                }
            }

            List<Maintenance> history = controller.historyForDroneBetween(drone, start, end);

            if (history.isEmpty()) {
                System.out.println(UtilsUI.YELLOW + "\nNo maintenance records found in this period." + UtilsUI.RESET);
            } else {
                System.out.println(UtilsUI.BLUE + UtilsUI.BOLD + "\nMaintenance History:\n" + UtilsUI.RESET);
                for (Maintenance m : history) {
                    System.out.printf("- Date: %s | Type: %s | Desc: %s | Usage: %s | Active: %s\n",
                            m.date(), m.type().name(), m.description(),
                            m.drone().usageTime(), m.drone().droneStatus());
                }
            }

            UtilsUI.goBackAndWait();
            return true;

        } catch (Exception e) {
            System.out.println(UtilsUI.RED + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    @Override
    public String headline() {
        return "List Drone Maintenance History";
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
}