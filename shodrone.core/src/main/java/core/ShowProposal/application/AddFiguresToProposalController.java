package core.ShowProposal.application;

import core.Customer.domain.Entities.Customer;
import core.Figure.domain.Entities.Figure;
import core.Figure.repositories.FigureRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowConfigurationBuilder;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

import java.util.ArrayList;
import java.util.List;

@UseCaseController
public class AddFiguresToProposalController {

    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private final FigureRepository figureRepository = PersistenceContext.repositories().figures();

    public Iterable<ShowProposal> getShowProposalList() {
        return showProposalRepository.findAllTestingProposals();
    }

    public Iterable<Figure> getFigureList(Customer customer) {
        List<Figure> figures = new ArrayList<>(figureRepository.getPublicCatalogue());
        if (customer != null) {
            figures.addAll(figureRepository.findExclusiveFiguresToCostumer(customer));
        }
        return figures;
    }

    public boolean addFiguresToProposalConfiguration(ShowProposal proposal, List<Figure> figures) {
        if (proposal == null || figures == null || figures.isEmpty()) {
            return false;
        }

        ShowConfigurationBuilder configBuilder = new ShowConfigurationBuilder();
        try {
            configBuilder.addFigures(figures);
            ShowConfiguration configuration = configBuilder.build();
            proposal.addConfiguration(configuration);
            showProposalRepository.save(proposal);
            return true;
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding figures to proposal: " + e.getMessage());
            return false;
        }
    }
}