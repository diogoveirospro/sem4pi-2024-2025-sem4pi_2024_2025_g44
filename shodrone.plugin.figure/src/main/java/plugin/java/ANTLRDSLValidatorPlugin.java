package plugin.java;

import core.Figure.application.Service.DSLValidationResult;
import core.Figure.application.Service.DSLValidatorPlugin;
import gen.FigureLexer;
import gen.FigureParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;


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
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener() {
            @Override
            public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                                    int line, int charPositionInLine, String msg, RecognitionException e) {
                errors.add("line " + line + ":" + charPositionInLine + " " + msg);
            }
        });

        ParseTree tree = parser.start();

        // Optionally: invoke semantic visitor here (US341 responsibility)
        // new DSLFigureVisitor().visit(tree);

        return new DSLValidationResult(errors.isEmpty(), errors);
    }
}
