package core.Drone.repositories;

import core.Drone.domain.Entities.Drone;
import core.Drone.domain.ValueObjects.DroneStatus;
import core.Drone.domain.ValueObjects.SerialNumber;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.domain.ValueObjects.ModelName;
import core.ModelOfDrone.repositories.ModelRepository;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.general.domain.model.Designation;

import java.util.List;
import java.util.Optional;

public interface DroneRepository extends DomainRepository<Designation, Drone> {

    List<Drone> getDrnModelList(Model droneModel);

    Iterable<Drone> findAllDronesInventory();

    List<Drone> findDronesOverTimeLimit();

}
