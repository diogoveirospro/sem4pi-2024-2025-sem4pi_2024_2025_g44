package plugin.java;

import core.Drone.application.Service.*;
import core.Drone.application.Service.plugin.DroneValidationPlugin;
import gen.DroneLexer;
import gen.DroneParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.util.ArrayList;
import java.util.List;

public class ANTLRDroneValidationPlugin implements DroneValidationPlugin {

    @Override
    public DroneValidationResult validateTemplate(String code) {
        if (code == null || code.isBlank()) {
            return new DroneValidationResult(false, List.of("Drone language input is empty or null"));
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
                errors.add("Syntax error at line " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        // Start parsing from the entry point defined in your grammar
        ParseTree tree = parser.start();

        // If there are syntax errors, return early
        if (!errors.isEmpty()) {
            return new DroneValidationResult(false, errors);
        }

        // Use Listener for semantic validation
        DroneListener listener = new DroneListener();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(listener, tree);

        // Add semantic errors from listener
        errors.addAll(listener.semanticErrors());

        // Use Visitor for additional validation and symbol table building
        DroneVisitor visitor = new DroneVisitor();
        visitor.visit(tree);

        // Add validation errors from visitor
        errors.addAll(visitor.validationErrors());

        // Return validation result
        return new DroneValidationResult(errors.isEmpty(), errors);
    }
}