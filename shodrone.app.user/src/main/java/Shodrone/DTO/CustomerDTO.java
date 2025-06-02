package Shodrone.DTO;

import java.util.List;

public class CustomerDTO {
    public String companyName;
    public String VatNumber;
    public List<String> representatives;

    public CustomerDTO(String companyName, String vatNumber, List<String> representatives) {
        this.companyName = companyName;
        this.VatNumber = vatNumber;
        this.representatives = representatives;
    }
}
