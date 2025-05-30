package core.Category.application.Service.plugin;

import core.Category.application.Service.TemplateValidationResult;

public interface TemplateValidationPlugin {
    TemplateValidationResult validateTemplate(String code);
}
