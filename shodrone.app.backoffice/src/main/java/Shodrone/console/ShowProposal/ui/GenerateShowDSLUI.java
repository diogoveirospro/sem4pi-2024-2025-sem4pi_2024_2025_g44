package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.AnotherShowProposalPrinter;
import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.exceptions.UserCancelledException;
import core.ShowProposal.application.GenerateShowDSLController;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.ShowProposal.domain.ValueObjects.ShowDSLDescription;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class GenerateShowDSLUI extends AbstractFancyUI {

    private final GenerateShowDSLController controller = new GenerateShowDSLController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        boolean success;

        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.DRONETECH)){

                ShowProposal selectedProposal = showProposalAndSelect();

                success = controller.generateDSL(selectedProposal);

                if (success){
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nShow DSL Successfully Generated!\n" + UtilsUI.RESET);
                    openShowDSLDescriptionFile(selectedProposal);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to Generate the Show DSL!\n" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return false;
                }

            }

            return false;

        } catch (UserCancelledException e) {
            return false;
        } catch (IllegalArgumentException e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        } catch (Exception e) {
            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
            UtilsUI.goBackAndWait();
            return false;
        }
    }

    @Override
    public String headline() {
        return "Generate Show DSL";
    }

    public ShowProposal showProposalAndSelect() {
        Iterable<ShowProposal> proposals = controller.proposalListReadyGenerateShowDSL();
        if (proposals == null || !proposals.iterator().hasNext()) {
            throw new IllegalArgumentException("No Show Proposals available.");
        }

        List<ShowProposal> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        AnotherShowProposalPrinter proposalPrinter = new AnotherShowProposalPrinter();

        ListWidget<ShowProposal> proposalListWidget = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Show Proposal:\n" +
                UtilsUI.RESET, proposalList, proposalPrinter);
        proposalListWidget.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(proposalList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > proposalList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                ShowProposal selected = proposalList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nSelected Show Proposal: " +
                        selected.identity().proposalNumber()  + "\n" + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }

    private void openShowDSLDescriptionFile(ShowProposal showProposal) {
        if (showProposal == null || showProposal.configuration().showDSLDescription() == null) {
            throw new IllegalArgumentException("ShowProposal or its DSL description is null.");
        }

        ShowDSLDescription showDSLDescription = showProposal.configuration().showDSLDescription();
        String dslContent = showDSLDescription.content();
        String fileName = "showDSL_" + showProposal.identity().proposalNumber() + ".txt";

        Path outputDir = Paths.get("ShowDSLFiles");
        Path filePath = outputDir.resolve(fileName);

        try {
            if (!Files.exists(outputDir)) {
                Files.createDirectories(outputDir);
            }
            Files.write(filePath, dslContent.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            if (UtilsUI.confirm(UtilsUI.BOLD + "Do you want to open the generated Show DSL file in Notepad? (Y/N)" + UtilsUI.RESET)) {
                UtilsUI.openInNotepad(filePath.toFile());
            } else {
                System.out.println(UtilsUI.YELLOW + "Show DSL file generated at: " + filePath.toAbsolutePath() + UtilsUI.RESET);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error writing Show DSL file: " + e.getMessage(), e);
        }
    }
}
