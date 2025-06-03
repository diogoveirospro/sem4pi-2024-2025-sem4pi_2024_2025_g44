package Shodrone.DTO;

import java.util.List;
import java.util.Map;

public class ShowProposalDTO {

    public String proposalNumber;
    public String dateOfProposal;
    public String timeOfProposal;
    public Long quantityOfDrones;
    public double insuranceValue;
    public String insuranceCurrency;
    public String showDuration;
    public String videoUrl;
    public String videoTitle;
    public List<String> figuresConfiguration;
    public Map<String, List<String>> droneConfiguration;

    public ShowProposalDTO(String proposalNumber, String dateOfProposal, String timeOfProposal,
                           Long quantityOfDrones, double insuranceValue, String insuranceCurrency,
                           String showDuration, String videoUrl, String videoTitle,
                           List<String> figuresConfiguration, Map<String, List<String>> droneConfiguration) {
        this.proposalNumber = proposalNumber;
        this.dateOfProposal = dateOfProposal;
        this.timeOfProposal = timeOfProposal;
        this.quantityOfDrones = quantityOfDrones;
        this.insuranceValue = insuranceValue;
        this.insuranceCurrency = insuranceCurrency;
        this.showDuration = showDuration;
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.figuresConfiguration = figuresConfiguration;
        this.droneConfiguration = droneConfiguration;
    }
}