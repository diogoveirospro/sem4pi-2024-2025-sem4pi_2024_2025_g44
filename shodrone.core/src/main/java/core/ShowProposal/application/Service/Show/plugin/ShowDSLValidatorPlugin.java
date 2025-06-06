package core.ShowProposal.application.Service.Show.plugin;

import core.ShowProposal.application.Service.ValidationResult;

public interface ShowDSLValidatorPlugin {
    ValidationResult validate(String showDSLContent);
}
