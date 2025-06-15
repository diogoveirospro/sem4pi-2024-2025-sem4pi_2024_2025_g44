package Shodrone.console.AnalyseProposal.ui;

import Shodrone.DTO.ShowProposalDTO;
import Shodrone.console.AnalyseProposal.controller.AnalyseProposalController;
import Shodrone.console.AnalyseProposal.printer.ProposalPrinter;
import Shodrone.exceptions.FailedRequestException;
import Shodrone.exceptions.UserCancelledException;
import eapli.framework.io.util.Console;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.File;
import java.io.IOException;

public class AnalyseProposalUI extends AbstractFancyUI {

    private final AnalyseProposalController controller = new AnalyseProposalController();

    @Override
    protected boolean doShow() {
        try {
            ShowProposalDTO proposalDTO = enterValidDeliveryCode();

            return downloadProposalFile(proposalDTO);

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

    private boolean downloadProposalFile(ShowProposalDTO proposalDTO) {
        if (!UtilsUI.confirm("Do you want to Download the Proposal File? (Y/N)")) {
            System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nFile Download Skipped.\n" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return true;
        }

        byte[] fileBytes = controller.decodeFile(proposalDTO.file);
        String filePath = controller.createFile(fileBytes, proposalDTO);

        if (filePath != null && !filePath.isEmpty()) {
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nFile created successfully, here is the path: "
                    + filePath + "\n" + UtilsUI.RESET);

            File file = new File(filePath);
            if (file.exists()) {
                UtilsUI.openInNotepad(file);
                UtilsUI.goBackAndWait();
                return true;
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile does not exist: " + filePath + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }
        } else {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailure to create the file\n" + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    private ShowProposalDTO enterValidDeliveryCode() throws FailedRequestException, IOException {
        String code = UtilsUI.readLineFromConsole(
                "Enter the Delivery Code in the format DELIVERY-<number between 1 and 10000> (or type 'cancel' to go back): "
        );

        assert code != null;
        if (code.equalsIgnoreCase("cancel")) {
            throw new UserCancelledException("User cancelled the operation.");
        }

        if (!code.matches("DELIVERY-\\d{1,5}")) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid format! It should be DELIVERY-<number between 1 and 10000>." + UtilsUI.RESET);
            enterValidDeliveryCode();
        }

        int number = Integer.parseInt(code.substring(9));
        if (number < 1 || number > 10000) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nThe number must be between 1 and 10000." + UtilsUI.RESET);
            enterValidDeliveryCode();
        }

        ShowProposalDTO proposalDTO = controller.findProposalByDeliveryCode(code);

        System.out.println(UtilsUI.BLUE + UtilsUI.BOLD + "\n\nProposal Details:\n" + UtilsUI.RESET);
        ProposalPrinter printer = new ProposalPrinter();
        printer.visit(proposalDTO);

        return proposalDTO;
    }

    @Override
    public String headline() {
        return "Analyse Proposal";
    }
}
