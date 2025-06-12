package shodrone.bootstrappers.Demo.Backoffice;

import core.CRMCollaborator.domain.Entities.CRMCollaborator;
import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.CRMManager.domain.Entities.CRMManager;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Drone.domain.Entities.Drone;
import core.Drone.repositories.DroneRepository;
import core.Figure.domain.Entities.Figure;
import core.Figure.repositories.FigureRepository;
import core.ModelOfDrone.domain.Entities.Model;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.QuantityOfDrones;
import core.ShowProposal.application.AddVideoToProposalController;
import core.ShowProposal.application.ConfigShowPropController;
import core.ShowProposal.application.CreateShowProposalController;
import core.ShowProposal.application.Service.ProposalDocumentGenerator;
import core.ShowProposal.application.Service.plugin.DocumentGenerationPluginFactory;
import core.ShowProposal.application.Service.plugin.DocumentGeneratorPlugin;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowConfigurationBuilder;
import core.ShowProposal.domain.Entities.ShowConfigurationEntry;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.Insurance;
import core.ShowProposal.domain.ValueObjects.ShowProposalDocument;
import core.ShowProposal.domain.ValueObjects.ShowProposalStatus;
import core.ShowProposal.domain.ValueObjects.Video;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.domain.Entities.ShowRequest;
import core.ShowRequest.repositories.ShowRequestRepository;
import eapli.framework.actions.Action;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shodrone.bootstrappers.Demo.UsersBootstrapperBase;
import shodrone.presentation.UtilsUI;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class ShowProposalBootstrapper extends UsersBootstrapperBase implements Action {

    private static final ShowRequestRepository showRequestRepository = PersistenceContext.repositories().showRequest();
    private static final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private static final ModelRepository modelRepository = PersistenceContext.repositories().models();
    private static final DroneRepository droneRepository = PersistenceContext.repositories().drone();
    private static final FigureRepository figureRepository = PersistenceContext.repositories().figures();
    private static final CRMCollaboratorRepository crmCollaboratorRepository = PersistenceContext.repositories().crmCollaborators();
    private static final CRMManagerRepository crmManagerRepository = PersistenceContext.repositories().crmManagers();

    private static final Logger LOGGER = LogManager.getLogger(ShowProposalBootstrapper.class);


    @Override
    public boolean execute() {

        cleanShowDSLFiles();

        List<ShowRequest> showRequests = (List<ShowRequest>) showRequestRepository.findAllCreatedShowRequests();
        List<CRMCollaborator> crmCollaborators = (List<CRMCollaborator>) crmCollaboratorRepository.findAll();
        List<Model> models = (List<Model>) modelRepository.findAll();
        List<Drone> drones = (List<Drone>) droneRepository.findAll();
        List<Figure> figures = (List<Figure>) figureRepository.findAll();
        List<CRMManager> managers = (List<CRMManager>) crmManagerRepository.findAll();

        // Without configuration, i.e. no drones, figures or video
        registerWithoutConfiguration(showRequests.get(0), showRequests.get(0).dateOfShow(), showRequests.get(0).timeOfShow(),
                showRequests.get(0).durationOfShow(), showRequests.get(0).quantityOfDrones(),
                new Insurance("1000", "€"), crmCollaborators.get(0));

        registerWithoutConfiguration(showRequests.get(1), showRequests.get(1).dateOfShow(), showRequests.get(1).timeOfShow(),
                showRequests.get(1).durationOfShow(), showRequests.get(1).quantityOfDrones(),
                new Insurance("1500", "€"), crmCollaborators.get(1));

        // With Drones
        Map<Model, Set<Drone>> dronesMapA = new HashMap<>();
        dronesMapA.put(models.get(0), Set.of(drones.get(0), drones.get(1)));
        dronesMapA.put(models.get(1), Set.of(drones.get(2)));
        registerWithDrones(
                showRequests.get(0),
                showRequests.get(0).dateOfShow(),
                showRequests.get(0).timeOfShow(),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("1200", "€"),
                crmCollaborators.get(0),
                dronesMapA
        );

        Map<Model, Set<Drone>> dronesMapB = new HashMap<>();
        dronesMapB.put(models.get(2), Set.of(drones.get(3), drones.get(4)));
        dronesMapB.put(models.get(3), Set.of(drones.get(5), drones.get(6)));
        registerWithDrones(
                showRequests.get(1),
                LocalDate.of(2025, 3, 15),
                LocalTime.of(15, 30),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("2000", "€"),
                crmCollaborators.get(1),
                dronesMapB
        );

        Map<Model, Set<Drone>> dronesMapC = new HashMap<>();
        dronesMapC.put(models.get(4), Set.of(drones.get(7), drones.get(8)));
        dronesMapC.put(models.get(5), Set.of(drones.get(9)));
        registerWithDrones(
                showRequests.get(2),
                LocalDate.of(2026, 7, 21),
                LocalTime.of(10, 0),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("1750", "€"),
                crmCollaborators.get(0),
                dronesMapC
        );

        // With Figures
        List<Figure> figuresSet1 = new ArrayList<>();
        figuresSet1.add(figures.get(2));
        figuresSet1.add(figures.get(3));
        figuresSet1.add(figures.get(4));
        registerWithFigures(
                showRequests.get(0),
                showRequests.get(0).dateOfShow(),
                showRequests.get(0).timeOfShow(),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("1800", "€"),
                crmCollaborators.get(0),
                figuresSet1
        );

        List<Figure> figuresSet2 = new ArrayList<>();
        figuresSet2.add(figures.get(5));
        figuresSet2.add(figures.get(6));
        figuresSet2.add(figures.get(7));
        figuresSet2.add(figures.get(8));
        registerWithFigures(
                showRequests.get(1),
                LocalDate.of(2025, 4, 10),
                LocalTime.of(14, 0),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("2200", "€"),
                crmCollaborators.get(0),
                figuresSet2
        );

        List<Figure> figuresSet3 = new ArrayList<>();
        figuresSet3.add(figures.get(9));
        figuresSet3.add(figures.get(10));
        figuresSet3.add(figures.get(11));
        figuresSet3.add(figures.get(12));
        figuresSet3.add(figures.get(13));
        registerWithFigures(
                showRequests.get(2),
                LocalDate.of(2026, 8, 5),
                LocalTime.of(11, 30),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("2500", "€"),
                crmCollaborators.get(1),
                figuresSet3
        );


        // With Drones and Figures
        registerWithDronesAndFigures(
                showRequests.get(1),
                LocalDate.of(2025, 12, 4),
                LocalTime.of(11, 45),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("250", "€"),
                crmCollaborators.get(0),
                dronesMapA,
                figuresSet1
        );

        registerWithDronesAndFigures(
                showRequests.get(0),
                LocalDate.of(2024, 7, 15),
                LocalTime.of(16, 0),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("500", "€"),
                crmCollaborators.get(1),
                dronesMapB,
                figuresSet2
        );

        registerWithDronesAndFigures(
                showRequests.get(2),
                LocalDate.of(2026, 2, 20),
                LocalTime.of(9, 30),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("800", "€"),
                crmCollaborators.get(1),
                dronesMapC,
                figuresSet3
        );


        // With Video
        Video video1 = new Video(
                "LiteBee Stars 200pcs Drones/ Outdoor Drone Light Shows",
                "https://www.youtube.com/watch?v=G4-2bls6-Z0&ab_channel=DroneSolutionProvider"
        );
        registerWithVideo(
                showRequests.get(0),
                showRequests.get(0).dateOfShow(),
                showRequests.get(0).timeOfShow(),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("150", "€"),
                crmCollaborators.get(0),
                video1
        );

        Video video2 = new Video(
                "Show de drones no Brasil",
                "https://www.youtube.com/watch?v=4oTR5SJQCw4&ab_channel=MaxDroneBrasil"
        );
        registerWithVideo(
                showRequests.get(1),
                LocalDate.of(2025, 5, 20),
                LocalTime.of(18, 0),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("200", "€"),
                crmCollaborators.get(1),
                video2
        );

        Video video3 = new Video(
                "Biggest drone display ever! - Guinness World Records",
                "https://www.youtube.com/watch?v=44KvHwRHb3A&ab_channel=GuinnessWorldRecords"
        );
        registerWithVideo(
                showRequests.get(2),
                LocalDate.of(2026, 9, 10),
                LocalTime.of(20, 30),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("300", "€"),
                crmCollaborators.get(1),
                video3
        );


        // With All
        registerWithAll(
                showRequests.get(0),
                LocalDate.of(2024, 8, 12),
                LocalTime.of(21, 0),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("500", "€"),
                crmCollaborators.get(0),
                dronesMapA,
                figuresSet1,
                video1
        );

        registerWithAll(
                showRequests.get(1),
                LocalDate.of(2025, 11, 3),
                LocalTime.of(19, 15),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("700", "€"),
                crmCollaborators.get(1),
                dronesMapB,
                figuresSet2,
                video2
        );

        registerWithAll(
                showRequests.get(2),
                LocalDate.of(2026, 5, 7),
                LocalTime.of(10, 30),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("300", "€"),
                crmCollaborators.get(0),
                dronesMapC,
                figuresSet3,
                video3
        );


        // With Document
        registerWithDocument(
                showRequests.get(0),
                LocalDate.of(2024, 9, 1),
                LocalTime.of(22, 0),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("600", "€"),
                crmCollaborators.get(0),
                dronesMapA,
                figuresSet1,
                video1,
                "Portuguese",
                managers.get(0)
        );

        registerWithDocument(
                showRequests.get(1),
                LocalDate.of(2025, 10, 5),
                LocalTime.of(20, 30),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("900", "€"),
                crmCollaborators.get(1),
                dronesMapB,
                figuresSet2,
                video2,
                "English (Regular Customer)",
                managers.get(1)
        );

        registerWithDocument(
                showRequests.get(2),
                LocalDate.of(2026, 3, 15),
                LocalTime.of(19, 0),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("1200", "€"),
                crmCollaborators.get(0),
                dronesMapC,
                figuresSet3,
                video3,
                "English (VIP Customer)",
                managers.get(0)
        );

        // With Accepted Status
        registerAccepted(
                showRequests.get(0),
                LocalDate.of(2024, 10, 10),
                LocalTime.of(23, 0),
                showRequests.get(0).durationOfShow(),
                showRequests.get(0).quantityOfDrones(),
                new Insurance("700", "€"),
                crmCollaborators.get(0),
                dronesMapA,
                figuresSet1,
                video1,
                "Portuguese",
                managers.get(0)
        );

        registerAccepted(
                showRequests.get(1),
                LocalDate.of(2025, 12, 20),
                LocalTime.of(21, 30),
                showRequests.get(1).durationOfShow(),
                showRequests.get(1).quantityOfDrones(),
                new Insurance("950", "€"),
                crmCollaborators.get(1),
                dronesMapB,
                figuresSet2,
                video2,
                "English (Regular Customer)",
                managers.get(1)
        );

        registerAccepted(
                showRequests.get(2),
                LocalDate.of(2026, 6, 5),
                LocalTime.of(20, 0),
                showRequests.get(2).durationOfShow(),
                showRequests.get(2).quantityOfDrones(),
                new Insurance("1200", "€"),
                crmCollaborators.get(1),
                dronesMapC,
                figuresSet3,
                video3,
                "English (VIP Customer)",
                managers.get(0)
        );

        return true;
    }

    private void registerWithoutConfiguration(ShowRequest showRequest, LocalDate date, LocalTime time,
                                              Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                                      CRMCollaborator collaborator) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);
        showProposalRepository.save(showProposal);
        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithDrones(ShowRequest showRequest, LocalDate date, LocalTime time,
                                    Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                            CRMCollaborator collaborator, Map<Model, Set<Drone>> drones) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithDrones = addDrones(showProposal, drones, builder);
        showProposalRepository.save(showProposalWithDrones);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithFigures(ShowRequest showRequest, LocalDate date, LocalTime time,
                                   Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                    CRMCollaborator collaborator, List<Figure> figures) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithFigures = addFigures(showProposal, figures, builder);
        showProposalRepository.save(showProposalWithFigures);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithDronesAndFigures(ShowRequest showRequest, LocalDate date, LocalTime time,
                                     Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                              CRMCollaborator collaborator, Map<Model, Set<Drone>> drones, List<Figure> figures) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithDrones = addDrones(showProposal, drones, builder);
        ShowProposal showProposalWithDronesAndFigures = addFigures(showProposalWithDrones, figures, builder);


        showProposalRepository.save(showProposalWithDronesAndFigures);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithVideo(ShowRequest showRequest, LocalDate date, LocalTime time,
                                   Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                   CRMCollaborator collaborator, Video video) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        showProposal.addVideo(video);
        showProposalRepository.save(showProposal);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithAll(ShowRequest showRequest, LocalDate date, LocalTime time,
                                 Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                 CRMCollaborator collaborator, Map<Model, Set<Drone>> drones,
                                 List<Figure> figures, Video video) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithDrones = addDrones(showProposal, drones, builder);
        ShowProposal finalShowProposal = addFigures(showProposalWithDrones, figures, builder);
        finalShowProposal.addVideo(video);
        showProposalRepository.save(finalShowProposal);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerWithDocument(ShowRequest showRequest, LocalDate date, LocalTime time,
                                      Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                      CRMCollaborator collaborator, Map<Model, Set<Drone>> drones,
                                      List<Figure> figures, Video video, String template, CRMManager crmManager) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithDrones = addDrones(showProposal, drones, builder);
        ShowProposal showProposalWithDronesAndFigures = addFigures(showProposalWithDrones, figures, builder);
        showProposalWithDronesAndFigures.addVideo(video);

        ShowProposal showProposalWithDocument = addDocument(showProposalWithDronesAndFigures, template, crmManager);

        showProposalRepository.save(showProposalWithDocument);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }

    private void registerAccepted(ShowRequest showRequest, LocalDate date, LocalTime time,
                                  Duration duration, QuantityOfDrones quantityOfDrones, Insurance insurance,
                                  CRMCollaborator collaborator, Map<Model, Set<Drone>> drones,
                                  List<Figure> figures, Video video, String template, CRMManager crmManager) {

        ShowProposal showProposal = new ShowProposal(showRequest, date, time, duration, quantityOfDrones, insurance,
                collaborator);

        ShowConfigurationBuilder builder = new ShowConfigurationBuilder();
        ShowProposal showProposalWithDrones = addDrones(showProposal, drones, builder);
        ShowProposal showProposalWithDronesAndFigures = addFigures(showProposalWithDrones, figures, builder);
        showProposalWithDronesAndFigures.addVideo(video);

        ShowProposal finalShowProposal = addDocument(showProposalWithDronesAndFigures, template, crmManager);

        finalShowProposal.setShowProposalStatus(ShowProposalStatus.ACCEPTED);

        showProposalRepository.save(finalShowProposal);

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully registered Show Proposal {}" + UtilsUI.RESET,
                showProposal.identity().proposalNumber());
    }



    // Auxiliary Methods

    private ShowProposal addDrones(ShowProposal showProposal, Map<Model, Set<Drone>> drones, ShowConfigurationBuilder builder) {

        for (Model model : drones.keySet()) {
            Set<Drone> droneSet = drones.get(model);
            for (Drone drone : droneSet) {
                ShowConfigurationEntry entry = new ShowConfigurationEntry(model, drone);
                builder.addDrones(entry);
            }
        }

        ShowConfiguration showConfiguration = builder.build();
        showProposal.addConfiguration(showConfiguration);
        return showProposal;
    }

    private ShowProposal addFigures(ShowProposal showProposal, List<Figure> figures, ShowConfigurationBuilder builder) {

        ShowConfiguration showConfiguration = builder.build();
        showProposal.addConfiguration(showConfiguration);

        showProposal.configuration().addFigures(figures);

        return showProposal;
    }

    private ShowProposal addDocument(ShowProposal showProposal, String template, CRMManager crmManager) {
        String documentContent = showProposal.configureDocument(template, crmManager);

        DocumentGeneratorPlugin plugin = DocumentGenerationPluginFactory.getInstance();

        ShowProposalDocument showProposalDocument = new ProposalDocumentGenerator(plugin).
                generateDocument(documentContent, showProposal);

        showProposal.addDocument(showProposalDocument);
        return showProposal;
    }

    private void cleanShowDSLFiles() {
        File pasta = new File("ShowDSLFiles");
        if (pasta.exists() && pasta.isDirectory()) {
            for (File file : pasta.listFiles()) {
                file.delete();
            }
        }

        LOGGER.info(UtilsUI.BOLD + UtilsUI.GREEN + "Successfully cleaned Show DSL files" + UtilsUI.RESET);
    }
}
