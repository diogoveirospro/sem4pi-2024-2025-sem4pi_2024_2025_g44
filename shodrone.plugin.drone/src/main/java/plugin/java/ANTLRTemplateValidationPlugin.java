package plugin.java;

import core.Category.application.Service.*;
import core.Category.application.Service.plugin.TemplateValidationPlugin;
import gen.DroneLexer;
import gen.DroneParser;
import org.antlr.v4.runtime.*;


import java.util.ArrayList;
import java.util.List;


public class ANTLRTemplateValidationPlugin implements TemplateValidationPlugin {

    @Override
    public TemplateValidationResult validateTemplate(String code) {
        if (code == null || code.isBlank()) {
            return new TemplateValidationResult(false, List.of("DSL input is empty or null"));
        }

        CharStream input = CharStreams.fromString(code);
        DroneLexer lexer = new DroneLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        DroneParser parser = new DroneParser(tokens);

        List<String> errors = new ArrayList<>();

        // Capture syntax errors in a custom error listener
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add("line " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        // Start parsing from the entry point defined in your grammar
        parser.start();

        // Return validation result
        return new TemplateValidationResult(errors.isEmpty(), errors);
    }
}


