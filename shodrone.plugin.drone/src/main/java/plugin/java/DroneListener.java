package plugin.java;

import gen.DroneParser;
import gen.DroneParserBaseListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom listener for the Drone language parser.
 * Extends the generated base listener to handle specific drone language constructs.
 */
public class DroneListener extends DroneParserBaseListener {

    private List<String> semanticErrors = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();

    // Tracking declared variables and their types
    private java.util.Map<String, String> declaredVariables = new java.util.HashMap<>();

    @Override
    public void enterStart(DroneParser.StartContext ctx) {
        System.out.println("Starting drone program validation...");
    }

    @Override
    public void exitStart(DroneParser.StartContext ctx) {
        System.out.println("Finished drone program validation.");
        if (!semanticErrors.isEmpty()) {
            System.out.println("Semantic errors found: " + semanticErrors.size());
        }
        if (!warnings.isEmpty()) {
            System.out.println("Warnings found: " + warnings.size());
        }
    }

    @Override
    public void enterVersion_header(DroneParser.Version_headerContext ctx) {
        // Validate version header format
        if (ctx.FLOAT() != null) {
            String version = ctx.FLOAT().getText();
            try {
                float versionNum = Float.parseFloat(version);
                if (versionNum < 1.0) {
                    warnings.add("Version " + version + " is quite old, consider updating");
                }
            } catch (NumberFormatException e) {
                semanticErrors.add("Invalid version format: " + version);
            }
        }
    }

    @Override
    public void enterType_description(DroneParser.Type_descriptionContext ctx) {
        String typeName = ctx.TYPE_NAME().getText();
        System.out.println("Found type description for: " + typeName);
    }

    @Override
    public void enterVariable_declaration(DroneParser.Variable_declarationContext ctx) {
        String typeName = ctx.TYPE_NAME().getText();
        String varName = ctx.IDENTIFIER().getText();

        // Check for duplicate variable declarations
        if (declaredVariables.containsKey(varName)) {
            semanticErrors.add("Variable '" + varName + "' is already declared");
        } else {
            declaredVariables.put(varName, typeName);
            System.out.println("Declared variable: " + varName + " of type " + typeName);
        }
    }

    @Override
    public void enterInstruction(DroneParser.InstructionContext ctx) {
        // Validate different instruction types
        if (ctx.TAKEOFF() != null) {
            validateTakeoffInstruction(ctx);
        } else if (ctx.LAND() != null) {
            validateLandInstruction(ctx);
        } else if (ctx.MOVE() != null) {
            validateMoveInstruction(ctx);
        } else if (ctx.MOVEPATH() != null) {
            validateMovePathInstruction(ctx);
        } else if (ctx.MOVECIRCLE() != null) {
            validateMoveCircleInstruction(ctx);
        } else if (ctx.HOOVER() != null) {
            validateHooverInstruction(ctx);
        } else if (ctx.LIGHTSON() != null) {
            validateLightsOnInstruction(ctx);
        } else if (ctx.LIGHTSOFF() != null) {
            validateLightsOffInstruction(ctx);
        } else if (ctx.BLINK() != null) {
            validateBlinkInstruction(ctx);
        }
    }

    @Override
    public void enterFactor(DroneParser.FactorContext ctx) {
        // Check if identifier is declared
        if (ctx.id != null) {
            String varName = ctx.id.getText();
            if (!declaredVariables.containsKey(varName)) {
                semanticErrors.add("Undefined variable: " + varName);
            }
        }
    }

    @Override
    public void enterPoint(DroneParser.PointContext ctx) {
        // Validate point coordinates
        List<TerminalNode> floats = ctx.FLOAT();
        if (floats.size() == 3) {
            for (TerminalNode floatNode : floats) {
                try {
                    float coordinate = Float.parseFloat(floatNode.getText());
                    if (Math.abs(coordinate) > 1000) {
                        warnings.add("Large coordinate value detected: " + coordinate);
                    }
                } catch (NumberFormatException e) {
                    semanticErrors.add("Invalid coordinate format: " + floatNode.getText());
                }
            }
        }
    }

    // Validation methods for specific instructions
    private void validateTakeoffInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating takeOff instruction");
        // takeOff should have altitude and time parameters
        if (ctx.expression().size() != 2) {
            semanticErrors.add("takeOff instruction requires exactly 2 parameters (altitude, time)");
        }
    }

    private void validateLandInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating land instruction");
        // land should have time parameter
        if (ctx.expression().size() != 1) {
            semanticErrors.add("land instruction requires exactly 1 parameter (time)");
        }
    }

    private void validateMoveInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating move instruction");
        // move can have 2 or 3 parameters (x, y) or (x, y, z)
        int paramCount = ctx.expression().size();
        if (paramCount != 2 && paramCount != 3) {
            semanticErrors.add("move instruction requires 2 or 3 parameters");
        }
    }

    private void validateMovePathInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating movePath instruction");
        // movePath should have array of positions and time
        if (ctx.expression().size() != 2) {
            semanticErrors.add("movePath instruction requires exactly 2 parameters (path, time)");
        }
    }

    private void validateMoveCircleInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating moveCircle instruction");
        // moveCircle should have center, radius, and time
        if (ctx.expression().size() != 3) {
            semanticErrors.add("moveCircle instruction requires exactly 3 parameters (center, radius, time)");
        }
    }

    private void validateHooverInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating hoover instruction");
        // hoover should have time parameter
        if (ctx.expression().size() != 1) {
            semanticErrors.add("hoover instruction requires exactly 1 parameter (time)");
        }
    }

    private void validateLightsOnInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating lightsOn instruction");
        // lightsOn can have 0 or 1 parameter
        int paramCount = ctx.expression().size();
        if (paramCount > 1) {
            semanticErrors.add("lightsOn instruction accepts at most 1 parameter");
        }
    }

    private void validateLightsOffInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating lightsOff instruction");
        // lightsOff should have no parameters
        if (!ctx.expression().isEmpty()) {
            semanticErrors.add("lightsOff instruction accepts no parameters");
        }
    }

    private void validateBlinkInstruction(DroneParser.InstructionContext ctx) {
        System.out.println("Validating blink instruction");
        // blink should have duration parameter
        if (ctx.expression().size() != 1) {
            semanticErrors.add("blink instruction requires exactly 1 parameter (duration)");
        }
    }

    // Method required by the validation plugin
    public List<String> semanticErrors() {
        return new ArrayList<>(semanticErrors);
    }

    // Additional getters for other functionality
    public List<String> getWarnings() {
        return new ArrayList<>(warnings);
    }

    public java.util.Map<String, String> getDeclaredVariables() {
        return new java.util.HashMap<>(declaredVariables);
    }

    public boolean hasErrors() {
        return !semanticErrors.isEmpty();
    }
}