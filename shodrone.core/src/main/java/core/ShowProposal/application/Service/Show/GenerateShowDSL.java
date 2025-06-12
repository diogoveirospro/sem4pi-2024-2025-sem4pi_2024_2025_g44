package core.ShowProposal.application.Service.Show;

import core.Figure.domain.Entities.Figure;
import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPlugin;
import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPluginFactory;
import core.ShowProposal.application.Service.ValidationResult;
import core.ShowProposal.domain.Entities.ShowConfiguration;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowDSLDescription;

import java.util.List;

/**
 * Service to generate Show DSL content from a ShowProposal.
 * This service uses a ShowDSLValidatorPlugin to validate the generated DSL.
 */
public class GenerateShowDSL {

    /**
     * The plugin used for validating the generated Show DSL.
     */
    private final ShowDSLValidatorPlugin plugin;

    /**
     * Constructor to create a GenerateShowDSL service with a specific plugin.
     * @param plugin the ShowDSLValidatorPlugin to use for validation
     */
    public GenerateShowDSL (ShowDSLValidatorPlugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Default constructor that uses the default ShowDSLValidatorPlugin instance.
     */
    public GenerateShowDSL() {
        // Obtain the default plugin instance
        this.plugin = ShowDSLValidatorPluginFactory.getInstance();
    }

    /**
     * Generates Show DSL content from a given ShowProposal.
     * Validates the generated DSL using the configured plugin.
     *
     * @param proposal the ShowProposal to generate the DSL from
     * @return a ShowDSLDescription containing the generated DSL content
     * @throws IllegalArgumentException if the proposal or its configuration is null
     */
    public ShowDSLDescription generateFrom(ShowProposal proposal) {
        if (proposal == null || proposal.configuration() == null) {
            throw new IllegalArgumentException("Proposal or its configuration cannot be null");
        }
        ShowConfiguration configuration = proposal.configuration();
        List<Figure> figures = configuration.figures();

        String showDSLContent = createShowDSLContent(proposal.identity().proposalNumber(), figures);

        ValidationResult validationResult = plugin.validate(showDSLContent);

        if (!validationResult.errors().isEmpty()) {
            throw new IllegalArgumentException("Generated Show DSL is invalid: " + validationResult.errors());
        }
        return new ShowDSLDescription(showDSLContent);
    }

    /**
     * Creates the Show DSL content based on the proposal number and figures.
     * @param proposalNumber the proposal number to include in the DSL
     * @param figures the set of figures to include in the DSL
     * @return the generated Show DSL content as a String
     */
    private String createShowDSLContent(String proposalNumber, List<Figure> figures) {
        StringBuilder showDSLContent = new StringBuilder();

        // Start of the Show DSL content
        showDSLContent.append("Show ").append(proposalNumber).append(";").append(System.lineSeparator());

        // Start of the FigureList
        showDSLContent.append("FigureList{").append(System.lineSeparator());

        // Add each figure's identity and version to the DSL content
        for (Figure fig : figures) {
            showDSLContent.append(fig.identity().code())
                    .append("->")
                    .append(fig.identity().version())
                    .append(";")
                    .append(System.lineSeparator());
        }

        // Close the FigureList
        showDSLContent.append("}");

        return showDSLContent.toString();
    }

}