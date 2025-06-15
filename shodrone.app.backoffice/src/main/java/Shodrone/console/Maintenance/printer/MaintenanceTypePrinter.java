package Shodrone.console.Maintenance.printer;

import core.Maintenance.domain.Entities.MaintenanceType;
import eapli.framework.visitor.Visitor;

public class MaintenanceTypePrinter implements Visitor<MaintenanceType> {

    @Override
    public void visit(MaintenanceType maintenanceType) {

        System.out.printf(
                "%-20s |  %-10s |\n",
                maintenanceType.name().toString(),
                maintenanceType.resetUsageTime() ? "Yes" : "No"
        );
    }
}