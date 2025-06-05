package Shodrone.DTO;

import java.util.List;
import java.util.Map;

public class ShowDTO {

    public String proposalNumber;
    public String requestNumber;
    public Map<String, List<String>> droneConfiguration;
    public String video;
    public List<String> figuresConfiguration;
    public String showDescription;
    public String showLocation;
    public String showDate;
    public String showTime;
    public Long quantityOfDrones;
    public String insurance;
    public String showDuration;

    public ShowDTO(String requestNumber, String proposalNumber,Map<String, List<String>> droneConfiguration, String video,
                   List<String> figuresConfiguration, String showDescription, String showLocation,
                   String showDate, String showTime, Long quantityOfDrones, String insurance, String showDuration) {
        this.requestNumber = requestNumber;
        this.proposalNumber = proposalNumber;
        this.droneConfiguration = droneConfiguration;
        this.video = video;
        this.figuresConfiguration = figuresConfiguration;
        this.showDescription = showDescription;
        this.showLocation = showLocation;
        this.showDate = showDate;
        this.showTime = showTime;
        this.quantityOfDrones = quantityOfDrones;
        this.insurance = insurance;
        this.showDuration = showDuration;
    }
}