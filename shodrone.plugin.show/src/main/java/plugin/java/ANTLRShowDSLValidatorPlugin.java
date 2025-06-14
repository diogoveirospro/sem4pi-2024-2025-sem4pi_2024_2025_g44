package plugin.java;

import core.ShowProposal.application.Service.Show.plugin.ShowDSLValidatorPlugin;
import core.ShowProposal.application.Service.ValidationResult;
import gen.ShowLexer;
import gen.ShowParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

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
                errors.add("Syntax error at line " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        // Start parsing from the entry point defined in your grammar
        ParseTree tree = parser.start();

        // If there are syntax errors, return early
        if (!errors.isEmpty()) {
            return new ValidationResult(false, errors);
        }

        // Use Listener for semantic validation
        ShowListener listener = new ShowListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        // Add semantic errors from listener
        errors.addAll(listener.getSemanticErrors());

        // Use Visitor for additional validation and data extraction
        ShowVisitor visitor = new ShowVisitor();
        visitor.visit(tree);

        // Add validation errors from visitor
        errors.addAll(visitor.getValidationErrors());

        // Return validation result
        return new ValidationResult(errors.isEmpty(), errors);
    }
}