package core.ShowProposal.application.Service;

import core.Persistence.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TemplateLoaderService {

    /**
     * Loads the content of a predefined proposal template based on the selected type.
     *
     * @param templateType The type of template
     * @return The content of the template file as a String
     * @throws IOException if the resource cannot be read
     */
    public String getTemplateContent(final String templateType) throws IOException {
        final String path;

        switch (templateType) {
            case "Portuguese":
                path = Application.settings().getTemplatePathPortuguese();
                break;
            case "English (Regular Customer)":
                path = Application.settings().getTemplatePathEnglish();
                break;
            case "English (VIP Customer)":
                path = Application.settings().getTemplatePathEnglishVIP();
                break;
            default:
                throw new IllegalArgumentException("Invalid template type: " + templateType);
        }

        final String resourcePath = path.replace("classpath:", "");

        try (InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (in == null) {
                throw new IOException("Template file not found: " + resourcePath);
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }

            return content.toString().trim();
        }
    }
}
