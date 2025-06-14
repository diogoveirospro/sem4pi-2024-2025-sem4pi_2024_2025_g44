package plugin.java;

import gen.FigureParser;
import gen.FigureParserBaseListener;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FigureListener extends FigureParserBaseListener {
    
    private List<String> semanticErrors = new ArrayList<>();
    private Set<String> declaredPositions = new HashSet<>();
    private Set<String> declaredVelocities = new HashSet<>();
    private Set<String> declaredShapes = new HashSet<>();
    private Set<String> declaredDistances = new HashSet<>();
    private String droneType;
    private String version;
    
    public List<String> semanticErrors() {
        return semanticErrors;
    }
    
    @Override
    public void enterVersion(FigureParser.VersionContext ctx) {
        if (ctx.VERSIONNUMBER() != null) {
            version = ctx.VERSIONNUMBER().getText();
            // Validate version format (should be x.y.z)
            if (!version.matches("\\d+\\.\\d+\\.\\d+")) {
                semanticErrors.add("Invalid version format: " + version + ". Expected format: x.y.z");
            }
        }
    }
    
    @Override
    public void enterDronetype(FigureParser.DronetypeContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            droneType = ctx.IDENTIFIER().getText();
            // Basic validation - drone type should not be empty
            if (droneType.trim().isEmpty()) {
                semanticErrors.add("Drone type cannot be empty");
            }
        }
    }
    
    @Override
    public void enterPositions(FigureParser.PositionsContext ctx) {
        // Collect all declared positions
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            String positionName = ctx.IDENTIFIER(i).getText();
            if (declaredPositions.contains(positionName)) {
                semanticErrors.add("Position '" + positionName + "' is already declared");
            } else {
                declaredPositions.add(positionName);
            }
        }
    }
    
    @Override
    public void enterVelocities(FigureParser.VelocitiesContext ctx) {
        // Collect all declared velocities
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            String velocityName = ctx.IDENTIFIER(i).getText();
            if (declaredVelocities.contains(velocityName)) {
                semanticErrors.add("Velocity '" + velocityName + "' is already declared");
            } else {
                declaredVelocities.add(velocityName);
            }
        }
    }
    
    @Override
    public void enterDistance(FigureParser.DistanceContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String distanceName = ctx.IDENTIFIER().getText();
            if (declaredDistances.contains(distanceName)) {
                semanticErrors.add("Distance '" + distanceName + "' is already declared");
            } else {
                declaredDistances.add(distanceName);
            }
        }
    }
    
    @Override
    public void enterLine(FigureParser.LineContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String shapeName = ctx.IDENTIFIER().getText();
            if (declaredShapes.contains(shapeName)) {
                semanticErrors.add("Shape '" + shapeName + "' is already declared");
            } else {
                declaredShapes.add(shapeName);
            }
        }
        validateShapeArguments(ctx.argumentList3());
    }
    
    @Override
    public void enterRectangle(FigureParser.RectangleContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String shapeName = ctx.IDENTIFIER().getText();
            if (declaredShapes.contains(shapeName)) {
                semanticErrors.add("Shape '" + shapeName + "' is already declared");
            } else {
                declaredShapes.add(shapeName);
            }
        }
        validateShapeArguments(ctx.argumentList4());
    }
    
    @Override
    public void enterCircle(FigureParser.CircleContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String shapeName = ctx.IDENTIFIER().getText();
            if (declaredShapes.contains(shapeName)) {
                semanticErrors.add("Shape '" + shapeName + "' is already declared");
            } else {
                declaredShapes.add(shapeName);
            }
        }
        validateShapeArguments(ctx.argumentList3());
    }
    
    @Override
    public void enterCircumference(FigureParser.CircumferenceContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String shapeName = ctx.IDENTIFIER().getText();
            if (declaredShapes.contains(shapeName)) {
                semanticErrors.add("Shape '" + shapeName + "' is already declared");
            } else {
                declaredShapes.add(shapeName);
            }
        }
        validateShapeArguments(ctx.argumentList3());
    }
    
    @Override
    public void enterStatement(FigureParser.StatementContext ctx) {
        if (ctx.IDENTIFIER() != null) {
            String shapeName = ctx.IDENTIFIER().getText();
            if (!declaredShapes.contains(shapeName)) {
                semanticErrors.add("Undefined shape '" + shapeName + "' used in statement");
            }
        }
    }
    
    @Override
    public void enterMove(FigureParser.MoveContext ctx) {
        // Validate that the velocity identifier is declared
        if (ctx.IDENTIFIER() != null) {
            String velocityName = ctx.IDENTIFIER().getText();
            if (!declaredVelocities.contains(velocityName)) {
                semanticErrors.add("Undefined velocity '" + velocityName + "' used in move command");
            }
        }
    }
    
    @Override
    public void enterRotate(FigureParser.RotateContext ctx) {
        // Validate that position and velocity identifiers are declared
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        if (identifiers.size() >= 2) {
            String positionName = identifiers.get(0).getText();
            String velocityName = identifiers.get(1).getText();
            
            if (!declaredPositions.contains(positionName)) {
                semanticErrors.add("Undefined position '" + positionName + "' used in rotate command");
            }
            if (!declaredVelocities.contains(velocityName)) {
                semanticErrors.add("Undefined velocity '" + velocityName + "' used in rotate command");
            }
        }
    }
    
    @Override
    public void enterMovePos(FigureParser.MovePosContext ctx) {
        // Validate that position and velocity identifiers are declared
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        if (identifiers.size() >= 2) {
            String positionName = identifiers.get(0).getText();
            String velocityName = identifiers.get(1).getText();
            
            if (!declaredPositions.contains(positionName)) {
                semanticErrors.add("Undefined position '" + positionName + "' used in movePos command");
            }
            if (!declaredVelocities.contains(velocityName)) {
                semanticErrors.add("Undefined velocity '" + velocityName + "' used in movePos command");
            }
        }
    }
    
    private void validateShapeArguments(FigureParser.ArgumentList3Context ctx) {
        if (ctx != null && ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!declaredPositions.contains(identifier) && !declaredDistances.contains(identifier)) {
                semanticErrors.add("Undefined identifier '" + identifier + "' used in shape arguments");
            }
        }
    }
    
    private void validateShapeArguments(FigureParser.ArgumentList4Context ctx) {
        if (ctx != null && ctx.IDENTIFIER() != null) {
            String identifier = ctx.IDENTIFIER().getText();
            if (!declaredPositions.contains(identifier) && !declaredDistances.contains(identifier)) {
                semanticErrors.add("Undefined identifier '" + identifier + "' used in shape arguments");
            }
        }
    }
}