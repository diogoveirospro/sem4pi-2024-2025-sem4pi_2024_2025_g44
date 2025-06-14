package plugin.java;

import gen.FigureParser;
import gen.FigureParserBaseVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.*;

public class FigureVisitor extends FigureParserBaseVisitor<Object> {
    
    private List<String> validationErrors = new ArrayList<>();
    private Map<String, Object> symbolTable = new HashMap<>();
    private Set<String> declaredShapes = new HashSet<>();
    
    public List<String> validationErrors() {
        return validationErrors;
    }
    
    @Override
    public Object visitStart(FigureParser.StartContext ctx) {
        // Visit header first
        visit(ctx.header());
        
        // Then visit main body
        visit(ctx.mainBody());
        
        return null;
    }
    
    @Override
    public Object visitHeader(FigureParser.HeaderContext ctx) {
        visit(ctx.version());
        visit(ctx.dronetype());
        return null;
    }
    
    @Override
    public Object visitVersion(FigureParser.VersionContext ctx) {
        String version = ctx.VERSIONNUMBER().getText();
        symbolTable.put("version", version);
        
        // Validate version format
        if (!version.matches("\\d+\\.\\d+\\.\\d+")) {
            validationErrors.add("Invalid version format: " + version);
        }
        
        return version;
    }
    
    @Override
    public Object visitDronetype(FigureParser.DronetypeContext ctx) {
        String droneType = ctx.IDENTIFIER().getText();
        symbolTable.put("droneType", droneType);
        return droneType;
    }
    
    @Override
    public Object visitPositions(FigureParser.PositionsContext ctx) {
        Map<String, Object> positions = new HashMap<>();
        
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            String positionName = ctx.IDENTIFIER(i).getText();
            Object vectorValue = visit(ctx.vector(i));
            
            if (positions.containsKey(positionName)) {
                validationErrors.add("Duplicate position declaration: " + positionName);
            } else {
                positions.put(positionName, vectorValue);
            }
        }
        
        symbolTable.put("positions", positions);
        return positions;
    }
    
    @Override
    public Object visitVelocities(FigureParser.VelocitiesContext ctx) {
        Map<String, Object> velocities = new HashMap<>();
        
        for (int i = 0; i < ctx.IDENTIFIER().size(); i++) {
            String velocityName = ctx.IDENTIFIER(i).getText();
            Object velocityValue = extractVelocityValue(ctx, i);
            
            if (velocities.containsKey(velocityName)) {
                validationErrors.add("Duplicate velocity declaration: " + velocityName);
            } else {
                velocities.put(velocityName, velocityValue);
            }
        }
        
        symbolTable.put("velocities", velocities);
        return velocities;
    }
    
    @Override
    public Object visitDistance(FigureParser.DistanceContext ctx) {
        String distanceName = ctx.IDENTIFIER().getText();
        String distanceValue = ctx.POSNUMBER().getText();
        
        Map<String, Object> distances = (Map<String, Object>) symbolTable.computeIfAbsent("distances", k -> new HashMap<>());
        distances.put(distanceName, Integer.parseInt(distanceValue));
        
        return Integer.parseInt(distanceValue);
    }
    
    @Override
    public Object visitVector(FigureParser.VectorContext ctx) {
        List<Integer> coordinates = new ArrayList<>();
        
        // Extract the three coordinates from the vector
        for (int i = 0; i < 3; i++) {
            if (i < ctx.NEGNUMBER().size()) {
                coordinates.add(Integer.parseInt(ctx.NEGNUMBER(i).getText()));
            } else if (i - ctx.NEGNUMBER().size() < ctx.POSNUMBER().size()) {
                coordinates.add(Integer.parseInt(ctx.POSNUMBER(i - ctx.NEGNUMBER().size()).getText()));
            }
        }
        
        if (coordinates.size() != 3) {
            validationErrors.add("Vector must have exactly 3 coordinates");
        }
        
        return coordinates;
    }
    
    @Override
    public Object visitLine(FigureParser.LineContext ctx) {
        String shapeName = ctx.IDENTIFIER().getText();
        
        if (declaredShapes.contains(shapeName)) {
            validationErrors.add("Shape '" + shapeName + "' is already declared");
        } else {
            declaredShapes.add(shapeName);
        }
        
        // Visit argument list to validate references
        visit(ctx.argumentList3());
        
        return shapeName;
    }
    
    @Override
    public Object visitRectangle(FigureParser.RectangleContext ctx) {
        String shapeName = ctx.IDENTIFIER().getText();
        
        if (declaredShapes.contains(shapeName)) {
            validationErrors.add("Shape '" + shapeName + "' is already declared");
        } else {
            declaredShapes.add(shapeName);
        }
        
        // Visit argument list to validate references
        visit(ctx.argumentList4());
        
        return shapeName;
    }
    
    @Override
    public Object visitCircle(FigureParser.CircleContext ctx) {
        String shapeName = ctx.IDENTIFIER().getText();
        
        if (declaredShapes.contains(shapeName)) {
            validationErrors.add("Shape '" + shapeName + "' is already declared");
        } else {
            declaredShapes.add(shapeName);
        }
        
        // Visit argument list to validate references
        visit(ctx.argumentList3());
        
        return shapeName;
    }
    
    @Override
    public Object visitCircumference(FigureParser.CircumferenceContext ctx) {
        String shapeName = ctx.IDENTIFIER().getText();
        
        if (declaredShapes.contains(shapeName)) {
            validationErrors.add("Shape '" + shapeName + "' is already declared");
        } else {
            declaredShapes.add(shapeName);
        }
        
        // Visit argument list to validate references
        visit(ctx.argumentList3());
        
        return shapeName;
    }
    
    @Override
    public Object visitArgumentList3(FigureParser.ArgumentList3Context ctx) {
        // Validate that the identifier references a declared position or distance
        String identifier = ctx.IDENTIFIER().getText();
        validateIdentifierReference(identifier);
        
        // Visit expressions
        for (FigureParser.ExpressionContext expr : ctx.expression()) {
            visit(expr);
        }
        
        return null;
    }
    
    @Override
    public Object visitArgumentList4(FigureParser.ArgumentList4Context ctx) {
        // Validate that the identifier references a declared position or distance
        String identifier = ctx.IDENTIFIER().getText();
        validateIdentifierReference(identifier);
        
        // Visit expressions
        for (FigureParser.ExpressionContext expr : ctx.expression()) {
            visit(expr);
        }
        
        return null;
    }
    
    @Override
    public Object visitStatement(FigureParser.StatementContext ctx) {
        String shapeName = ctx.IDENTIFIER().getText();
        
        if (!declaredShapes.contains(shapeName)) {
            validationErrors.add("Undefined shape '" + shapeName + "' used in statement");
        }
        
        // Visit the shape function
        visit(ctx.shapeFunction());
        
        return null;
    }
    
    @Override
    public Object visitMove(FigureParser.MoveContext ctx) {
        // Validate velocity identifier
        String velocityName = ctx.IDENTIFIER().getText();
        Map<String, Object> velocities = (Map<String, Object>) symbolTable.get("velocities");
        
        if (velocities == null || !velocities.containsKey(velocityName)) {
            validationErrors.add("Undefined velocity '" + velocityName + "' used in move command");
        }
        
        // Visit vector
        visit(ctx.vector());
        
        return null;
    }
    
    @Override
    public Object visitRotate(FigureParser.RotateContext ctx) {
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        
        if (identifiers.size() >= 2) {
            String positionName = identifiers.get(0).getText();
            String velocityName = identifiers.get(1).getText();
            
            // Validate position
            Map<String, Object> positions = (Map<String, Object>) symbolTable.get("positions");
            if (positions == null || !positions.containsKey(positionName)) {
                validationErrors.add("Undefined position '" + positionName + "' used in rotate command");
            }
            
            // Validate velocity
            Map<String, Object> velocities = (Map<String, Object>) symbolTable.get("velocities");
            if (velocities == null || !velocities.containsKey(velocityName)) {
                validationErrors.add("Undefined velocity '" + velocityName + "' used in rotate command");
            }
        }
        
        return null;
    }
    
    @Override
    public Object visitMovePos(FigureParser.MovePosContext ctx) {
        List<TerminalNode> identifiers = ctx.IDENTIFIER();
        
        if (identifiers.size() >= 2) {
            String positionName = identifiers.get(0).getText();
            String velocityName = identifiers.get(1).getText();
            
            // Validate position
            Map<String, Object> positions = (Map<String, Object>) symbolTable.get("positions");
            if (positions == null || !positions.containsKey(positionName)) {
                validationErrors.add("Undefined position '" + positionName + "' used in movePos command");
            }
            
            // Validate velocity
            Map<String, Object> velocities = (Map<String, Object>) symbolTable.get("velocities");
            if (velocities == null || !velocities.containsKey(velocityName)) {
                validationErrors.add("Undefined velocity '" + velocityName + "' used in movePos command");
            }
        }
        
        return null;
    }
    
    private Object extractVelocityValue(FigureParser.VelocitiesContext ctx, int index) {
        // This is a simplified extraction - you might need to adjust based on your grammar structure
        // The velocity can be PI/number, double, or positive number
        return "velocity_" + index; // Placeholder - implement based on your specific needs
    }
    
    private void validateIdentifierReference(String identifier) {
        Map<String, Object> positions = (Map<String, Object>) symbolTable.get("positions");
        Map<String, Object> distances = (Map<String, Object>) symbolTable.get("distances");
        
        boolean found = false;
        
        if (positions != null && positions.containsKey(identifier)) {
            found = true;
        }
        
        if (distances != null && distances.containsKey(identifier)) {
            found = true;
        }
        
        if (!found) {
            validationErrors.add("Undefined identifier '" + identifier + "' used in arguments");
        }
    }
}