package Shodrone.console.ShowInfo.printer;

import Shodrone.DTO.ShowDTO;
import eapli.framework.visitor.Visitor;
import shodrone.presentation.UtilsUI;

public class ShowPrinterSmall implements Visitor<ShowDTO> {

    @Override
    public void visit(ShowDTO visitee) {
        System.out.printf(
                "\n" +
                        UtilsUI.BOLD + "Show Request Number: " + UtilsUI.RESET +"%s\n" +
                        UtilsUI.BOLD + "Show Proposal Number: " + UtilsUI.RESET +"%s\n" +
                        UtilsUI.BOLD + "Location: " + UtilsUI.RESET +"%s\n" +
                        UtilsUI.BOLD + "Date: " + UtilsUI.RESET +"%s\n" +
                        UtilsUI.BOLD + "Time: " + UtilsUI.RESET +"%s\n" +
                        UtilsUI.BOLD + "Duration: " + UtilsUI.RESET +"%s\n",
                visitee.requestNumber,
                visitee.proposalNumber,
                visitee.showLocation,
                visitee.showDate,
                visitee.showTime,
                visitee.showDuration
        );
    }
}
