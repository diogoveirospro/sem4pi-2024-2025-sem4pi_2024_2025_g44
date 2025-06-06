package core.ShowProposal.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowConfigurationBuilder;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;

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
        if (configuration == null) {
            return false;
        }
        showProposal.addConfiguration(createConfiguration(configuration));
        showProposalRepository.save(showProposal);
        return true;
    }

    public ShowConfiguration createConfiguration(ShowConfigurationBuilder configuration) {
        return new ShowConfiguration(configuration);
    }
}
