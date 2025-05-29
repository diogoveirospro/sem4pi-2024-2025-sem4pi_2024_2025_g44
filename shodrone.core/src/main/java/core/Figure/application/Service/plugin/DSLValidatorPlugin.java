package core.Figure.application.Service.plugin;

import core.Figure.application.Service.DSLValidationResult;

public interface DSLValidatorPlugin {
    DSLValidationResult validateDSL(String code);
}
