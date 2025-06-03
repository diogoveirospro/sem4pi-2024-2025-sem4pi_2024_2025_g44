package Shodrone.DTO;

import java.util.List;
import java.util.Map;

public class ShowDTO {

    public String proposalNumber;
    public String requestNumber;
    public Map<String, List<String>> droneConfiguration;
    public String videoUrl;
    public String videoTitle;
    public List<String> figuresConfiguration;
    public String showDescription;
    public String showLocation;
    public String showDate;
    public String showTime;
    public Long quantityOfDrones;
    public double insuranceValue;
    public String insuranceCurrency;
    public String showDuration;

    public ShowDTO(String requestNumber, String proposalNumber,Map<String, List<String>> droneConfiguration, String videoUrl, String videoTitle,
                   List<String> figuresConfiguration, String showDescription, String showLocation,
                   String showDate, String showTime, Long quantityOfDrones, double insuranceValue,
                   String insuranceCurrency, String showDuration) {
        this.droneConfiguration = droneConfiguration;
        this.videoUrl = videoUrl;
        this.videoTitle = videoTitle;
        this.figuresConfiguration = figuresConfiguration;
        this.showDescription = showDescription;
        this.showLocation = showLocation;
        this.showDate = showDate;
        this.showTime = showTime;
        this.quantityOfDrones = quantityOfDrones;
        this.insuranceValue = insuranceValue;
        this.insuranceCurrency = insuranceCurrency;
        this.showDuration = showDuration;
    }
}