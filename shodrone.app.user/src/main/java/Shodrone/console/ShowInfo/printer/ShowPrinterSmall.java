package Shodrone.console.ShowInfo.printer;

import Shodrone.DTO.ShowDTO;
import eapli.framework.visitor.Visitor;

public class ShowPrinterSmall implements Visitor<ShowDTO> {

    @Override
    public void visit(ShowDTO visitee) {
        System.out.printf(
                "\nShow Request number: %s\nShow Proposal number: %s\nLocation: %s\nDate: %s\nTime: %s\nDuration: %s\n",
                visitee.requestNumber,
                visitee.proposalNumber,
                visitee.showLocation,
                visitee.showDate,
                visitee.showTime,
                visitee.showDuration
        );
    }
}
