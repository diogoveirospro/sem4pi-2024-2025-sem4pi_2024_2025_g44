package core.ShowProposal.application.Service;

import core.Persistence.PersistenceContext;
import core.ShowProposal.application.Service.plugin.DocumentGeneratorPlugin;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowProposalDocument;
import core.ShowProposal.repositories.ShowProposalRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProposalDocumentGenerator {

    private final DocumentGeneratorPlugin plugin;
    private final ShowProposalRepository showProposalRepository = PersistenceContext.repositories().proposals();

    public ProposalDocumentGenerator(DocumentGeneratorPlugin plugin) {
        this.plugin = plugin;
    }

    public ShowProposalDocument generateDocument(String documentContent, ShowProposal proposal) {
        ValidationResult result = plugin.processDocument(documentContent);
        if (result.isInvalid()) {
            throw new IllegalArgumentException("Document generation failed: " + result.errors());
        }

        // Define the file path
        Path proposalsDirectory = Paths.get("shodrone.core/src/main/resources/proposals");
        String proposalName = proposal.identity().proposalNumber();
        Path filePath = proposalsDirectory.resolve(proposalName + ".txt");

        try {
            // Ensure the directory exists
            Files.createDirectories(proposalsDirectory);

            // Write the content to the file
            Files.writeString(filePath, documentContent);

            // Save the proposal with the new document
            showProposalRepository.save(proposal);

        } catch (IOException e) {
            throw new IllegalArgumentException("Error writing document to file: " + e.getMessage(), e);
        }

        // Return the ShowProposalDocument with the file path
        return new ShowProposalDocument(documentContent, filePath.toString());
    }
}