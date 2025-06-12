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
            System.out.println("Configuration is null or empty.");
            return false;
        }

        int totalAssigned = configuration.showConfiguration().size();
        int requiredQuantity = showProposal.quantityOfDrones().getQuantityOfDrones();

        if (totalAssigned != requiredQuantity) {
            System.out.println("The total number of configured drones (" + totalAssigned + ") does not match the required number (" + requiredQuantity + ").");
            return false;
        }

        Set<Drone> assignedDroneSerialNumbers = new HashSet<>();

        for (ShowConfigurationEntry entry : configuration.showConfiguration()) {
            if (entry == null) {
                System.out.println("Invalid configuration entry: entry is null.");
                return false;
            }
            if (entry.model() == null) {
                System.out.println("Invalid configuration entry: model is null.");
                return false;
            }
            if (entry.drone() == null) {
                System.out.println("Invalid configuration entry: drone is null.");
                return false;
            }

            // AC01: Verificar se o drone realmente existe
            if (entry.drone().droneStatus() != DroneStatus.ACTIVE) {
                System.out.println("Invalid drone: " + entry.drone().identity() + " does not exist in the iventory.");
                return false;
            }

            // AC03: Verificar se o mesmo drone não está sendo usado mais de uma vez
            if (!assignedDroneSerialNumbers.add(entry.drone())) {
                System.out.println("Drone " + entry.drone().identity() + " is assigned more than once in the configuration.");
                return false;
            }
        }
        return true;
    }


}
