package plugin.java;

import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPlugin;
import core.ShowProposal.application.Service.ValidationResult;
import gen.ShowLexer;
import gen.ShowParser;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

public class ANTLRShowDSLValidatorPlugin implements ShowDSLValidatorPlugin {

    @Override
    public ValidationResult validate(String showDSLContent) {
        if (showDSLContent == null || showDSLContent.isBlank()) {
            return new ValidationResult(false, List.of("Show DSL input is empty or null"));
        }

        CharStream input = CharStreams.fromString(showDSLContent);
        ShowLexer lexer = new ShowLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ShowParser parser = new ShowParser(tokens);

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
        return new ValidationResult(errors.isEmpty(), errors);
    }
}