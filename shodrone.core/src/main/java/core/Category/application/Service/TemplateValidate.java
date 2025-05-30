package core.Category.application.Service;

import core.Category.application.Service.plugin.TemplateValidationPlugin;
import core.Category.application.Service.plugin.TemplateValidationPluginFactory;

public class TemplateValidate {

    private final TemplateValidationPlugin plugin;

    public TemplateValidate(TemplateValidationPlugin plugin) {
        this.plugin = plugin;
    }

    public TemplateValidate() {
        // Obtain the default plugin instance
        this.plugin = TemplateValidationPluginFactory.getInstance();
    }

    public TemplateValidationResult validateTemplate(String templateCode) {
        return plugin.validateTemplate(templateCode);
    }
}