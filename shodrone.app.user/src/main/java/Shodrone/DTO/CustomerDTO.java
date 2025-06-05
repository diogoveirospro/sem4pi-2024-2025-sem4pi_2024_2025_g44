package Shodrone.DTO;

import java.util.List;

public class CustomerDTO {
    public String companyName;
    public String VatNumber;

    public CustomerDTO(String companyName, String vatNumber) {
        this.companyName = companyName;
        this.VatNumber = vatNumber;
    }
}
