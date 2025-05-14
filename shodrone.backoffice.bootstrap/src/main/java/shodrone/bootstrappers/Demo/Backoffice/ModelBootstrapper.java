package shodrone.bootstrappers.Demo.Backoffice;

import core.ModelOfDrone.application.CreateModelController;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.domain.ValueObjects.PositionTolerance;
import core.ModelOfDrone.domain.ValueObjects.SafetyStatus;
import core.ModelOfDrone.domain.ValueObjects.WindSpeed;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.presentation.UtilsUI;

import java.sql.Array;
import java.util.HashMap;
import java.util.Map;

public class ModelBootstrapper implements Action {
    private static final Logger LOGGER = LogManager.getLogger(ModelBootstrapper.class);

    private final CreateModelController controller = new CreateModelController();

    @Override
    public boolean execute() {
        Map<WindSpeed, PositionTolerance> config = new HashMap<>();
        config.put(new WindSpeed(0, 5), new PositionTolerance(0.0));
        config.put(new WindSpeed(5, 7), new PositionTolerance(0.3));
        config.put(new WindSpeed(7, 10), new PositionTolerance(0.5));
        config.put(new WindSpeed(10, 15), new PositionTolerance(0.8));
        config.put(new WindSpeed(15, 999), new PositionTolerance(-1));

        register("DroneX-200", new Configuration(config, SafetyStatus.SAFE));

        // Configuração para DroneY-300
        Map<WindSpeed, PositionTolerance> config1 = new HashMap<>();
        config1.put(new WindSpeed(0, 3), new PositionTolerance(0.1));
        config1.put(new WindSpeed(3, 6), new PositionTolerance(0.25));
        config1.put(new WindSpeed(6, 9), new PositionTolerance(0.4));
        config1.put(new WindSpeed(9, 12), new PositionTolerance(0.6));
        config1.put(new WindSpeed(12, 20), new PositionTolerance(-1));

        register("DroneY-300", new Configuration(config1, SafetyStatus.SAFE));

        return true;
    }

    private void register(final String modelNameStr, final Configuration config) {
        try {
            ModelName modelName = new ModelName(modelNameStr);
            Model model = new Model(modelName, config);
            controller.createModel(modelName, config);
            LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered drone model: {}" + UtilsUI.RESET, modelNameStr);
        } catch (Exception e) {
            LOGGER.error(UtilsUI.BOLD + UtilsUI.RED + "Error registering drone model '{}': {}" + UtilsUI.RESET, modelNameStr, e.getMessage());
        }
    }
}
