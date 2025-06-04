package plugin.java;

import core.ShowProposal.application.Service.ValidationResult;
import core.ShowProposal.application.Service.plugin.DocumentGeneratorPlugin;
import gen.ProposalLexer;
import gen.ProposalParser;
import org.antlr.v4.runtime.*;

import java.util.ArrayList;
import java.util.List;

public class ANTLRDocumentGenerationPlugin implements DocumentGeneratorPlugin {

    @Override
    public ValidationResult processDocument(String document) {
        if(document == null || document.isEmpty()) {
            return new ValidationResult(false, List.of("Document content cannot be null or empty"));
        }
        CharStream input = CharStreams.fromString(document);
        ProposalLexer lexer = new ProposalLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ProposalParser parser = new ProposalParser(tokens);

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

