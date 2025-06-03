package Shodrone.DTO;

public class ShowRequest {

    public String showRequestNumber;
    public String showDescription;
    public String showLocation;
    public String showDate;
    public String showTime;
    public Long quantityOfDrones;
    public String showDuration;

    public ShowRequest(String showRequestNumber, String showDescription, String showLocation,
                       String showDate, String showTime, Long quantityOfDrones, String showDuration) {
        this.showRequestNumber = showRequestNumber;
        this.showDescription = showDescription;
        this.showLocation = showLocation;
        this.showDate = showDate;
        this.showTime = showTime;
        this.quantityOfDrones = quantityOfDrones;
        this.showDuration = showDuration;
    }
}