package core.ShowProposal.application;

import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.ModelOfDrone.domain.Entities.Configuration;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;
import lombok.Builder;

import javax.swing.plaf.PanelUI;

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
    public boolean configureShow(ShowProposal showProposal, ShowConfiguration.Builder configuration) {
        if (configuration == null) {
            return false;
        }
        showProposal.setConfiguration(createConfiguration(configuration));
        showProposalRepository.save(showProposal);
        return true;
    }

    public ShowConfiguration createConfiguration(ShowConfiguration.Builder configuration) {
        return new ShowConfiguration(configuration);
    }
}
