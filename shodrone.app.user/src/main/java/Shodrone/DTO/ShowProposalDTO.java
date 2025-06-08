package Shodrone.DTO;

/**
 * Data Transfer Object for Show Proposal.
 * This class is used to transfer show proposal data between different layers of the application.
 */
public class ShowProposalDTO {

    /**
     * Unique identifier for the show proposal.
     */
    public String proposalNumber;

    /**
     * Date of the proposal in the format YYYY-MM-DD.
     */
    public String dateOfProposal;

    /**
     * Time of the proposal in the format HH:MM.
     */
    public String timeOfProposal;

    /**
     * Duration of the show in the format HH:MM.
     */
    public String showDuration;

    /**
     * Location where the show will take place.
     */
    public String showLocation;

    /**
     * ShowProposalDTO constructor.
     * @param proposalNumber Unique identifier for the show proposal.
     * @param dateOfProposal Date of the proposal in the format YYYY-MM-DD.
     * @param timeOfProposal Time of the proposal in the format HH:MM.
     * @param showDuration Duration of the show in the format HH:MM.
     * @param showLocation Location where the show will take place.
     */
    public ShowProposalDTO(String proposalNumber, String dateOfProposal, String timeOfProposal,
                           String showDuration, String showLocation) {
        this.proposalNumber = proposalNumber;
        this.dateOfProposal = dateOfProposal;
        this.timeOfProposal = timeOfProposal;
        this.showDuration = showDuration;
        this.showLocation = showLocation;
    }
}