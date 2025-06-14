package Shodrone.console.ShowProposal.ui;

import Shodrone.console.ShowProposal.printer.ShowProposalPrinter;
import Shodrone.console.ShowProposal.printer.TemplatePrinter;
import Shodrone.exceptions.UserCancelledException;
import core.CRMManager.domain.Entities.CRMManager;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Persistence.PersistenceContext;
import core.Shared.domain.ValueObjects.Email;
import core.ShowProposal.application.ConfigureProposalDocumentController;
import core.ShowProposal.domain.Entities.ShowProposal;
import core.User.domain.ShodroneRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ListWidget;
import shodrone.presentation.AbstractFancyUI;
import shodrone.presentation.UtilsUI;

import java.util.ArrayList;
import java.util.List;

public class ConfigureProposalDocumentUI extends AbstractFancyUI {

    private final ConfigureProposalDocumentController controller = new ConfigureProposalDocumentController();
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final CRMManagerRepository crmManagerRepository = PersistenceContext.repositories().crmManagers();

    @Override
    protected boolean doShow() {
        boolean success;

        try {
            if (authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.MANAGER)){

                ShowProposal selectedProposal = showProposalAndSelect();
                String selectedTemplate = showTemplatesAndSelect();
                CRMManager crmManager = null;

                if (authz.session().isPresent()) {
                    Email email = new Email(authz.session().get().authenticatedUser().email().toString());
                    crmManager = crmManagerRepository.findByEmail(email);
                }

                success = controller.configureDocument(selectedProposal, selectedTemplate, crmManager);

                if (success){
                    System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nProposal Document Successfully Configured!\n" + UtilsUI.RESET);
                    UtilsUI.goBackAndWait();
                    return true;
                } else {
                    System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nFailed to Configure the Proposal Document!\n" + UtilsUI.RESET);
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

    private String showTemplatesAndSelect() {

        List<String> templatesList = new ArrayList<>();
        templatesList.add("Portuguese");
        templatesList.add("English (Regular Customer)");
        templatesList.add("English (VIP Customer)");

        TemplatePrinter templatePrinter = new TemplatePrinter();

        ListWidget<String> templates = new ListWidget<>(UtilsUI.BOLD + UtilsUI.BLUE + "\n\nChoose a Template:\n" +
                UtilsUI.RESET, templatesList, templatePrinter);
        templates.show();

        int option;
        do {
            option = UtilsUI.selectsIndex(templatesList);
            if (option == -2) {
                throw new UserCancelledException(UtilsUI.RED + UtilsUI.BOLD + "Selection cancelled." + UtilsUI.RESET);
            }

            if (option < 0 || option > templatesList.size()) {
                System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\n\nInvalid option. Please try again." + UtilsUI.RESET);
            } else {
                String selected = templatesList.get(option);
                System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\n\nSelected Template: " + selected  + "\n"
                        + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }

    @Override
    public String headline() {
        return "Configure Template for Show Proposal";
    }

    public ShowProposal showProposalAndSelect() {
        Iterable<ShowProposal> proposals = controller.configurableProposalList();
        if (proposals == null || !proposals.iterator().hasNext()) {
            throw new IllegalArgumentException("No Show Proposals available.");
        }

        List<ShowProposal> proposalList = new ArrayList<>();
        proposals.forEach(proposalList::add);

        ShowProposalPrinter proposalPrinter = new ShowProposalPrinter();

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
                        selected.identity().proposalNumber() + "\n" + UtilsUI.RESET);
                return selected;
            }

        } while (true);
    }
}
