package Shodrone.console.AnalyseProposal.ui;

import Shodrone.DTO.ShowProposalDTO;
import Shodrone.console.AnalyseProposal.controller.AnalyseProposalController;
import Shodrone.exceptions.UserCancelledException;
import eapli.framework.io.util.Console;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.File;

public class AnalyseProposalUI extends AbstractFancyUI {

    private final AnalyseProposalController controller = new AnalyseProposalController();

    @Override
    protected boolean doShow() {
        try {
            String code = Console.readLine("Enter the delivery code:");
            ShowProposalDTO proposalDTO = controller.findProposalByDeliveryCode(code);

            // Mostrar dados da proposta
            System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nProposal Details:\n" + UtilsUI.RESET);
            System.out.printf("Proposal Number: %s\n", proposalDTO.proposalNumber);
            System.out.printf("Date: %s\n", proposalDTO.dateOfProposal);
            System.out.printf("Time: %s\n", proposalDTO.timeOfProposal);
            System.out.printf("Duration: %s\n", proposalDTO.showDuration);
            System.out.printf("Location: %s\n", proposalDTO.showLocation);

            // Perguntar se quer fazer download do ficheiro
            boolean download = Console.readBoolean("Do you want to download the proposal file? (y/n)");

            if (!download) {
                System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nFile download failed.\n" + UtilsUI.RESET);
                UtilsUI.goBackAndWait();
                return false;
            }

            // Proceder ao download
            byte[] fileBytes = controller.decodeFile(proposalDTO.file);
            String filePath = controller.createFile(fileBytes, proposalDTO);

            if (filePath != null && !filePath.isEmpty()) {
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nFile created successfully, here is the path: " + filePath + "\n" + UtilsUI.RESET);

                File file = new File(filePath);
                if (file.exists()) {
                    UtilsUI.openInNotepad(file);
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFile does not exist: " + filePath + UtilsUI.RESET);
                }
            } else {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailure to create the file\n" + UtilsUI.RESET);
            }

            UtilsUI.goBackAndWait();
            return true;

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
