package plugin.java;

import core.Figure.application.Service.DSLValidationResult;
import core.Figure.application.Service.plugin.DSLValidatorPlugin;
import gen.FigureLexer;
import gen.FigureParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.List;

public class ANTLRDSLValidatorPlugin implements DSLValidatorPlugin {

    @Override
    public DSLValidationResult validateDSL(String code) {
        if (code == null || code.isBlank()) {
            return new DSLValidationResult(false, List.of("DSL input is empty or null"));
        }

        CharStream input = CharStreams.fromString(code);
        FigureLexer lexer = new FigureLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        FigureParser parser = new FigureParser(tokens);

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
            return new DSLValidationResult(false, errors);
        }

        // Use Listener for semantic validation
        FigureListener listener = new FigureListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        // Add semantic errors from listener
        errors.addAll(listener.semanticErrors());

        // Use Visitor for additional validation and symbol table building
        FigureVisitor visitor = new FigureVisitor();
        visitor.visit(tree);

        // Add validation errors from visitor
        errors.addAll(visitor.validationErrors());

        // Return validation result
        return new DSLValidationResult(errors.isEmpty(), errors);
    }
}