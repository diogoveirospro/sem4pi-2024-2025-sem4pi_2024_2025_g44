package core.ShowProposal.application;

import core.CRMManager.domain.Entities.CRMManager;
import core.Persistence.PersistenceContext;
import core.ShowProposal.application.Service.ProposalDocumentGenerator;
import core.ShowProposal.application.Service.plugin.DocumentGenerationPluginFactory;
import core.ShowProposal.application.Service.plugin.DocumentGeneratorPlugin;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalDocument;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ConfigureProposalDocumentController {

    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();

    public boolean configureDocument(ShowProposal proposal, String selectedTemplate, CRMManager crmManager) {
        if (proposal == null) {
            throw new IllegalArgumentException("Proposal cannot be null.");
        }
        if (selectedTemplate == null || selectedTemplate.isEmpty()) {
            throw new IllegalArgumentException("Template cannot be null or empty.");
        }

        String documentContent = proposal.configureDocument(selectedTemplate, crmManager);

        try {
            ShowProposalDocument showProposalDocument = generateDocument(documentContent, proposal);
            proposal.addDocument(showProposalDocument);
            showProposalRepository.save(proposal);
            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error generating document: " + e.getMessage(), e);
        }
    }

    private ShowProposalDocument generateDocument(String documentContent, ShowProposal proposal) {
        DocumentGeneratorPlugin plugin = DocumentGenerationPluginFactory.getInstance();
        ProposalDocumentGenerator documentValidate = new ProposalDocumentGenerator(plugin);
        return documentValidate.generateDocument(documentContent, proposal);
    }

    public Iterable<ShowProposal> configurableProposalList() {
        return showProposalRepository.findConfigurableProposals();
    }
}
