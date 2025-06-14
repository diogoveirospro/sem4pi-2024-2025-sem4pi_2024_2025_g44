package core.ShowProposal.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowConfigurationBuilder;
import core.ShowProposal.domain.Entities.ShowConfigurationEntry;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;

import java.util.HashSet;
import java.util.Set;

public class ConfigShowPropController {
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private final ModelRepository modelRepository = PersistenceContext.repositories().models();
    private final DroneRepository droneRepository = PersistenceContext.repositories().drone();


    public Iterable<ShowProposal> getShowProposalList(){
        return showProposalRepository.findAllTestingProposals();
    }

    public Iterable<Model> getModelList(){
        return modelRepository.findAllModels();
    }
    public Iterable<Drone> getDrnModelList(Model droneModel) {
        return droneRepository.getDrnModelList(droneModel);
    }

    public boolean configureShow(ShowProposal showProposal, ShowConfigurationBuilder configuration) {

        ShowConfiguration showConfiguration = createConfiguration(configuration);
        if(!verifyConfiguration(showProposal, showConfiguration)){
            return false;
        }
        showProposal.addConfiguration(showConfiguration);
        showProposalRepository.save(showProposal);
        return true;
    }

    public ShowConfiguration createConfiguration(ShowConfigurationBuilder configuration) {
        return new ShowConfiguration(configuration);
    }
    public boolean verifyConfiguration(ShowProposal showProposal, ShowConfiguration configuration) {
        if (configuration == null || configuration.showConfiguration().isEmpty()) {
            throw new IllegalArgumentException("Invalid configuration: configuration cannot be null or empty.");
        }

        int totalAssigned = configuration.showConfiguration().size();
        int requiredQuantity = showProposal.quantityOfDrones().getQuantityOfDrones();

        if (totalAssigned != requiredQuantity) {
            throw new IllegalArgumentException("Invalid configuration: expected " + requiredQuantity + " drones, but " +
                    "got " + totalAssigned + ".");
        }

        Set<Drone> assignedDroneSerialNumbers = new HashSet<>();

        for (ShowConfigurationEntry entry : configuration.showConfiguration()) {
            if (entry == null) {
                throw new IllegalArgumentException("Invalid configuration: entry cannot be null.");
            }
            if (entry.model() == null) {
                throw new IllegalArgumentException("Invalid configuration: model cannot be null.");
            }
            if (entry.drone() == null) {
                throw new IllegalArgumentException("Invalid configuration: drone cannot be null.");
            }

            if (entry.drone().droneStatus() != DroneStatus.ACTIVE) {
                throw new IllegalArgumentException("Invalid configuration: drone " + entry.drone().identity() + " is " +
                        "not active.");
            }

            if (!assignedDroneSerialNumbers.add(entry.drone())) {
                throw new IllegalArgumentException("Drone " + entry.drone().identity() + " is already assigned.");
            }
        }
        return true;
    }


}
