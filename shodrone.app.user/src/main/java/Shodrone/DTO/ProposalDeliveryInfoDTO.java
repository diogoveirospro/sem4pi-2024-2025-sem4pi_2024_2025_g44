package Shodrone.DTO;

import java.util.Date;

public class ProposalDeliveryInfoDTO {
    public String code;
    public String customerName;
    public String customerId;
    public String proposalNumber;
    public String collaboratorName;
    public String collaboratorId;
    public Date deliveryAt;

    public ProposalDeliveryInfoDTO(String code, String customerName, String customerId,
                                   String proposalNumber, String collaboratorName, String collaboratorId,
                                   Date deliveryAt) {
        this.code = code;
        this.customerName = customerName;
        this.customerId = customerId;
        this.proposalNumber = proposalNumber;
        this.collaboratorName = collaboratorName;
        this.collaboratorId = collaboratorId;
        this.deliveryAt = deliveryAt;
    }
}
