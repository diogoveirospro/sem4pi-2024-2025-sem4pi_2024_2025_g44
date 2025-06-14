package plugin.java;

import gen.DroneParser;
import gen.DroneParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

/**
 * Custom visitor for the Drone language parser.
 * Extends the generated base visitor to traverse and analyze the parse tree.
 */
public class DroneVisitor extends DroneParserBaseVisitor<Object> {

    private List<String> semanticErrors = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();
    private Map<String, String> declaredVariables = new HashMap<>();
    private Map<String, Object> variableValues = new HashMap<>();

    // Result objects for structured data
    public static class DroneProgram {
        public String version;
        public List<String> languageInfo;
        public Map<String, String> types;
        public Map<String, Object> variables;
        public List<DroneInstruction> instructions;

        public DroneProgram() {
            languageInfo = new ArrayList<>();
            types = new HashMap<>();
            variables = new HashMap<>();
            instructions = new ArrayList<>();
        }
    }

    public static class DroneInstruction {
        public String command;
        public List<Object> parameters;

        public DroneInstruction(String command) {
            this.command = command;
            this.parameters = new ArrayList<>();
        }
    }

    public static class Point3D {
        public double x, y, z;

        public Point3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return String.format("Point(%.2f, %.2f, %.2f)", x, y, z);
        }
    }

    @Override
    public DroneProgram visitStart(DroneParser.StartContext ctx) {
        DroneProgram program = new DroneProgram();

        // Visit version header
        if (ctx.version_header() != null) {
            Object versionInfo = visit(ctx.version_header());
            if (versionInfo instanceof String) {
                program.version = (String) versionInfo;
            }
        }

        // Visit type descriptions
        for (DroneParser.Type_descriptionContext typeCtx : ctx.type_description()) {
            Object typeInfo = visit(typeCtx);
            if (typeInfo instanceof Map.Entry) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) typeInfo;
                program.types.put(entry.getKey(), entry.getValue());
            }
        }

        // Visit variable declarations
        for (DroneParser.Variable_declarationContext varCtx : ctx.variable_declaration()) {
            Object varInfo = visit(varCtx);
            if (varInfo instanceof Map.Entry) {
                Map.Entry<String, Object> entry = (Map.Entry<String, Object>) varInfo;
                program.variables.put(entry.getKey(), entry.getValue());
            }
        }

        // Visit instructions
        for (DroneParser.InstructionContext instrCtx : ctx.instruction()) {
            Object instruction = visit(instrCtx);
            if (instruction instanceof DroneInstruction) {
                program.instructions.add((DroneInstruction) instruction);
            }
        }

        return program;
    }

    @Override
    public String visitVersion_header(DroneParser.Version_headerContext ctx) {
        StringBuilder versionInfo = new StringBuilder();

        // Collect all identifiers
        for (TerminalNode identifier : ctx.IDENTIFIER()) {
            versionInfo.append(identifier.getText()).append(" ");
        }

        // Add the fixed keywords
        versionInfo.append(ctx.PROGRAMMING().getText()).append(" ");
        versionInfo.append(ctx.LANGUAGE().getText()).append(" ");
        versionInfo.append(ctx.VERSION().getText()).append(" ");
        versionInfo.append(ctx.FLOAT().getText());

        String version = versionInfo.toString().trim();

        // Validate version
        try {
            String versionNumber = ctx.FLOAT().getText();
            double versionValue = Double.parseDouble(versionNumber);
            if (versionValue < 1.0) {
                warnings.add("Version " + versionNumber + " is quite old");
            }
        } catch (NumberFormatException e) {
            semanticErrors.add("Invalid version format");
        }

        return version;
    }

    @Override
    public Map.Entry<String, String> visitType_description(DroneParser.Type_descriptionContext ctx) {
        String typeName = ctx.TYPE_NAME().getText();
        String description = visit(ctx.description()).toString();
        return new AbstractMap.SimpleEntry<>(typeName, description);
    }

    @Override
    public String visitDescription(DroneParser.DescriptionContext ctx) {
        StringBuilder desc = new StringBuilder();
        for (int i = 0; i < ctx.getChildCount(); i++) {
            desc.append(ctx.getChild(i).getText());
        }
        return desc.toString().trim();
    }

    @Override
    public Map.Entry<String, Object> visitVariable_declaration(DroneParser.Variable_declarationContext ctx) {
        String typeName = ctx.TYPE_NAME().getText();
        String varName = ctx.IDENTIFIER().getText();

        // Check for duplicate declarations
        if (declaredVariables.containsKey(varName)) {
            semanticErrors.add("Variable '" + varName + "' is already declared");
        } else {
            declaredVariables.put(varName, typeName);
        }

        // Evaluate the expression
        Object value = visit(ctx.expression());
        variableValues.put(varName, value);

        return new AbstractMap.SimpleEntry<>(varName, value);
    }

    @Override
    public DroneInstruction visitInstruction(DroneParser.InstructionContext ctx) {
        DroneInstruction instruction = null;

        if (ctx.TAKEOFF() != null) {
            instruction = new DroneInstruction("takeOff");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 2, "takeOff requires altitude and time parameters");

        } else if (ctx.LAND() != null) {
            instruction = new DroneInstruction("land");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 1, "land requires time parameter");

        } else if (ctx.MOVE() != null) {
            instruction = new DroneInstruction("move");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            if (instruction.parameters.size() != 2 && instruction.parameters.size() != 3) {
                semanticErrors.add("move requires 2 or 3 parameters (x, y) or (x, y, z)");
            }

        } else if (ctx.MOVEPATH() != null) {
            instruction = new DroneInstruction("movePath");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 2, "movePath requires path and time parameters");

        } else if (ctx.MOVECIRCLE() != null) {
            instruction = new DroneInstruction("moveCircle");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 3, "moveCircle requires center, radius, and time parameters");

        } else if (ctx.HOOVER() != null) {
            instruction = new DroneInstruction("hoover");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 1, "hoover requires time parameter");

        } else if (ctx.LIGHTSON() != null) {
            instruction = new DroneInstruction("lightsOn");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            if (instruction.parameters.size() > 1) {
                semanticErrors.add("lightsOn accepts at most 1 parameter");
            }

        } else if (ctx.LIGHTSOFF() != null) {
            instruction = new DroneInstruction("lightsOff");
            if (!ctx.expression().isEmpty()) {
                semanticErrors.add("lightsOff accepts no parameters");
            }

        } else if (ctx.BLINK() != null) {
            instruction = new DroneInstruction("blink");
            for (DroneParser.ExpressionContext expr : ctx.expression()) {
                instruction.parameters.add(visit(expr));
            }
            validateParameterCount(instruction, 1, "blink requires duration parameter");
        }

        return instruction;
    }

    @Override
    public Object visitExpression(DroneParser.ExpressionContext ctx) {
        return visit(ctx.arithmetic());
    }

    @Override
    public Object visitArithmetic(DroneParser.ArithmeticContext ctx) {
        Object left = visit(ctx.term(0));

        for (int i = 1; i < ctx.term().size(); i++) {
            Object right = visit(ctx.term(i));
            String operator = ctx.getChild(2 * i - 1).getText(); // Get the operator

            if (left instanceof Number && right instanceof Number) {
                double leftVal = ((Number) left).doubleValue();
                double rightVal = ((Number) right).doubleValue();

                if ("+".equals(operator)) {
                    left = leftVal + rightVal;
                } else if ("-".equals(operator)) {
                    left = leftVal - rightVal;
                }
            } else {
                semanticErrors.add("Arithmetic operations require numeric operands");
                return 0.0;
            }
        }

        return left;
    }

    @Override
    public Object visitTerm(DroneParser.TermContext ctx) {
        Object left = visit(ctx.factor(0));

        for (int i = 1; i < ctx.factor().size(); i++) {
            Object right = visit(ctx.factor(i));
            String operator = ctx.getChild(2 * i - 1).getText(); // Get the operator

            if (left instanceof Number && right instanceof Number) {
                double leftVal = ((Number) left).doubleValue();
                double rightVal = ((Number) right).doubleValue();

                if ("*".equals(operator)) {
                    left = leftVal * rightVal;
                } else if ("/".equals(operator)) {
                    if (rightVal == 0) {
                        semanticErrors.add("Division by zero");
                        return 0.0;
                    }
                    left = leftVal / rightVal;
                }
            } else {
                semanticErrors.add("Arithmetic operations require numeric operands");
                return 0.0;
            }
        }

        return left;
    }

    @Override
    public Object visitFactor(DroneParser.FactorContext ctx) {
        if (ctx.number() != null) {
            return visit(ctx.number());
        } else if (ctx.PI() != null) {
            return Math.PI;
        } else if (ctx.id != null) {
            String varName = ctx.id.getText();
            if (!declaredVariables.containsKey(varName)) {
                semanticErrors.add("Undefined variable: " + varName);
                return 0.0;
            }
            return variableValues.getOrDefault(varName, 0.0);
        } else if (ctx.vector() != null) {
            return visit(ctx.vector());
        } else if (ctx.point() != null) {
            return visit(ctx.point());
        } else if (ctx.array_of_positions() != null) {
            return visit(ctx.array_of_positions());
        } else if (ctx.expression() != null) {
            return visit(ctx.expression());
        }

        return 0.0;
    }

    @Override
    public Point3D visitPoint(DroneParser.PointContext ctx) {
        List<TerminalNode> floats = ctx.FLOAT();
        if (floats.size() == 3) {
            try {
                double x = Double.parseDouble(floats.get(0).getText());
                double y = Double.parseDouble(floats.get(1).getText());
                double z = Double.parseDouble(floats.get(2).getText());

                // Validate reasonable coordinate ranges
                if (Math.abs(x) > 1000 || Math.abs(y) > 1000 || Math.abs(z) > 1000) {
                    warnings.add("Large coordinate values detected in point");
                }

                return new Point3D(x, y, z);
            } catch (NumberFormatException e) {
                semanticErrors.add("Invalid coordinate format in point");
                return new Point3D(0, 0, 0);
            }
        }

        semanticErrors.add("Point must have exactly 3 coordinates");
        return new Point3D(0, 0, 0);
    }

    @Override
    public Point3D visitVector(DroneParser.VectorContext ctx) {
        return (Point3D) visit(ctx.point());
    }

    @Override
    public List<Point3D> visitArray_of_positions(DroneParser.Array_of_positionsContext ctx) {
        List<Point3D> positions = new ArrayList<>();

        for (DroneParser.PointContext pointCtx : ctx.point()) {
            Point3D point = (Point3D) visit(pointCtx);
            positions.add(point);
        }

        if (positions.isEmpty()) {
            warnings.add("Empty array of positions");
        }

        return positions;
    }

    @Override
    public Number visitNumber(DroneParser.NumberContext ctx) {
        if (ctx.FLOAT() != null) {
            try {
                return Double.parseDouble(ctx.FLOAT().getText());
            } catch (NumberFormatException e) {
                semanticErrors.add("Invalid float format: " + ctx.FLOAT().getText());
                return 0.0;
            }
        } else if (ctx.INTEGER() != null) {
            try {
                return Integer.parseInt(ctx.INTEGER().getText());
            } catch (NumberFormatException e) {
                semanticErrors.add("Invalid integer format: " + ctx.INTEGER().getText());
                return 0;
            }
        }

        return 0;
    }

    // Helper method to validate parameter counts
    private void validateParameterCount(DroneInstruction instruction, int expectedCount, String errorMessage) {
        if (instruction.parameters.size() != expectedCount) {
            semanticErrors.add(errorMessage);
        }
    }

    // Method required by the validation plugin
    public List<String> validationErrors() {
        return new ArrayList<>(semanticErrors);
    }

    // Additional getters for other functionality
    public List<String> getWarnings() {
        return new ArrayList<>(warnings);
    }

    public Map<String, String> getDeclaredVariables() {
        return new HashMap<>(declaredVariables);
    }

    public Map<String, Object> getVariableValues() {
        return new HashMap<>(variableValues);
    }

    public boolean hasErrors() {
        return !semanticErrors.isEmpty();
    }
}