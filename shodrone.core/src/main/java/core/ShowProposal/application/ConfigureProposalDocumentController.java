package core.ShowProposal.application;

import core.CRMManager.domain.Entities.CRMManager;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Persistence.PersistenceContext;
import core.ShowProposal.application.Service.DocumentValidate;
import core.ShowProposal.application.Service.ValidationResult;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalDocument;
import core.ShowProposal.repositories.ShowProposalRepository;
import eapli.framework.application.UseCaseController;

@UseCaseController
public class ConfigureProposalDocumentController {

    private ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();
    private CRMManagerRepository crmManagerRepository = PersistenceContext.repositories().crmManagers();

    public boolean configureDocument(ShowProposal proposal, String selectedTemplate, CRMManager crmManager) {
        if (proposal == null) {
            throw new IllegalArgumentException("Proposal cannot be null.");
        }
        if (selectedTemplate == null || selectedTemplate.isEmpty()) {
            throw new IllegalArgumentException("Template cannot be null or empty.");
        }

        ShowProposalDocument documentContent = proposal.configureDocument(selectedTemplate, crmManager);

        if (validate(documentContent.toString()).errors().isEmpty()) {
            proposal.addDocument(documentContent);
            showProposalRepository.save(proposal);
            return true;
        }
        return false;
    }

    private ValidationResult validate(String documentContent) {
        DocumentValidate documentValidate = new DocumentValidate();
        return documentValidate.validateDocument(documentContent);
    }

    public Iterable<ShowProposal> configurableProposalList() {
        return showProposalRepository.findConfigurableProposals();
    }
}
