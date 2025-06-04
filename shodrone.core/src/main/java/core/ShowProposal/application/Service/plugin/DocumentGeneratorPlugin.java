package core.ShowProposal.application.Service.plugin;

import core.ShowProposal.application.Service.ValidationResult;

public interface DocumentGeneratorPlugin {
    ValidationResult processDocument(String code);
    //String generateDocument(String documentContent);
}
