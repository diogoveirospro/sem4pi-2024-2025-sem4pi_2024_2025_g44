package Shodrone.console.AnalyseProposal.ui;

import Shodrone.DTO.ShowProposalDTO;
import Shodrone.console.AnalyseProposal.controller.AnalyseProposalController;
import Shodrone.console.SendFeedbackProposal.controller.SendFeedbackProposalController;
import Shodrone.console.SendFeedbackProposal.printer.ProposalPrinter;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.exceptions.UserCancelledException;
import core.ProposalDeliveryInfo.domain.ValueObjects.ProposalDeliveryInfoCode;
import core.ShowProposal.domain.Entities.ShowProposal;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;
import java.awt.Desktop;
import java.io.File;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * User Interface for sending feedback on a show proposal.
 * This class allows users to select a proposal, choose a decision (accept or reject),
 * and provide feedback on the proposal.
 */
public class AnalyseProposalUI extends AbstractFancyUI {

    /**
     * Controller for handling the logic of sending feedback on a show proposal.
     * This controller interacts with the backend to perform the necessary operations.
     */
    private final AnalyseProposalController controller = new AnalyseProposalController();

    /**
     * doShow method to display the UI for sending feedback on a show proposal.
     * @return boolean indicating success or failure of the operation.
     */
    @Override
    protected boolean doShow() {
        try {
            String code = Console.readLine("Enter the delivery code:");
            ShowProposalDTO proposalDTO = controller.findProposalByDeliveryCode(code);
            byte[] fileBytes = controller.decodeFile(proposalDTO.file);
            String filePath = controller.createFile(fileBytes, proposalDTO);

            if (filePath != null && !filePath.isEmpty()) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFile created successfully, here is the path: " + filePath + "\n" + UtilsUI.RESET);

                // Tenta abrir automaticamente o ficheiro
                File file = new File(filePath);
                if (file.exists()) {
                    UtilsUI.openInNotepad(file);
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile does not exist: " + filePath + UtilsUI.RESET);
                }

                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailure to create the file\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

        } catch (UserCancelledException e) {
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);

        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
        }

        UtilsUI.goBackAndWait();
        return false;
    }


    @Override
    public String headline() {
        return "Analyse Proposal";
    }
}
