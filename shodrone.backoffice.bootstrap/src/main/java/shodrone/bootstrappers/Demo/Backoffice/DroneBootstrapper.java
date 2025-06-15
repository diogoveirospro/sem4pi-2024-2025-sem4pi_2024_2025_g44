package shodrone.bootstrappers.Demo.Backoffice;

import core.Drone.application.AddDroneController;
import core.Drone.application.ListDroneController;
import core.Drone.domain.ValueObjects.RemovalReason;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

import java.io.File;
import java.util.*;

public class DroneBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(DroneBootstrapper.class);
    private final AddDroneController controller = new AddDroneController();
    private final ListDroneController listDroneController = new ListDroneController();


    @Override
    public boolean execute() {
        cleanReportFiles();

        List<Model> modelList = new ArrayList<>();
        listDroneController.listModels().forEach(modelList::add);

        if (modelList.isEmpty()) {
            LOGGER.error(UtilsUI.RED + "No models available to register drones." + UtilsUI.RESET);
            return false;
        }

        registerDrone(1000, randomModel(modelList));
        registerDrone(1001, randomModel(modelList));
        registerDrone(1002, randomModel(modelList));
        registerDrone(1003, randomModel(modelList));
        registerDrone(1004, randomModel(modelList));
        registerDrone(1005, randomModel(modelList));
        registerDrone(1006, randomModel(modelList));
        registerDrone(1007, randomModel(modelList));
        registerDrone(1008, randomModel(modelList));
        registerDrone(1009, randomModel(modelList));
        registerDrone(1010, randomModel(modelList));
        registerDrone(1011, randomModel(modelList));
        registerDrone(1012, randomModel(modelList));
        registerDrone(1013, randomModel(modelList));
        registerDrone(1014, randomModel(modelList));
        registerDrone(1015, randomModel(modelList));
        registerDrone(1016, randomModel(modelList));
        registerDrone(1017, randomModel(modelList));
        registerDrone(1018, randomModel(modelList));
        registerDrone(1019, randomModel(modelList));
        registerDrone(1020, randomModel(modelList));
        registerDrone(1021, randomModel(modelList));
        registerDrone(1022, randomModel(modelList));
        registerDrone(1023, randomModel(modelList));
        registerDrone(1024, randomModel(modelList));
        registerDrone(1025, randomModel(modelList));
        registerDrone(1026, randomModel(modelList));
        registerDrone(1027, randomModel(modelList));
        registerDrone(1028, randomModel(modelList));
        registerDrone(1029, randomModel(modelList));
        registerDrone(1030, randomModel(modelList));

        return true;
    }

    private Model randomModel(List<Model> modelList) {
        int randomIndex = (int) (Math.random() * modelList.size());
        return modelList.get(randomIndex);
    }

    private void registerDrone(final int serialNumberInt, final Model model) {
        try {
            SerialNumber serial = new SerialNumber(serialNumberInt);
            controller.addDrone(serial, model);
            LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Drone '{}' registered for model '{}'" + UtilsUI.RESET,
                    serialNumberInt, model.identity());
        } catch (Exception e) {
            LOGGER.error(UtilsUI.BOLD + UtilsUI.RED + "Failed to register drone '{}': {}" + UtilsUI.RESET,
                    serialNumberInt, e.getMessage());
        }
    }

    private void cleanReportFiles() {
        File folder = new File("shodrone.app.testing/src/main/resources/Reports");
        if (folder.exists() && folder.isDirectory()) {
            for (File file : Objects.requireNonNull(folder.listFiles())) {
                file.delete();
            }
        }

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully cleaned Report files" + UtilsUI.RESET);
    }
}
