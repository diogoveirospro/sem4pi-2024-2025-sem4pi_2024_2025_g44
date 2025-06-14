package plugin.java;

import gen.ShowParser;
import gen.ShowParserBaseListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShowListener extends ShowParserBaseListener {
    
    private List<String> semanticErrors = new ArrayList<>();
    private Set<String> declaredFigureCodes = new HashSet<>();
    private String proposalNumber;
    
    public List<String> getSemanticErrors() {
        return semanticErrors;
    }
    
    public String getProposalNumber() {
        return proposalNumber;
    }
    
    @Override
    public void enterStart(ShowParser.StartContext ctx) {
        if (ctx.PROPNUMBER() != null) {
            proposalNumber = ctx.PROPNUMBER().getText();
            validateProposalNumber(proposalNumber);
        }
    }
    
    @Override
    public void enterFigureitem(ShowParser.FigureitemContext ctx) {
        if (ctx.FIGURECODE() != null && ctx.VERSIONNUMBER() != null) {
            String figureCode = ctx.FIGURECODE().getText();
            String versionNumber = ctx.VERSIONNUMBER().getText();
            
            // Check for duplicate figure codes
            if (declaredFigureCodes.contains(figureCode)) {
                semanticErrors.add("Duplicate figure code: " + figureCode);
            } else {
                declaredFigureCodes.add(figureCode);
            }
            
            // Validate figure code format
            validateFigureCode(figureCode);
            
            // Validate version number format
            validateVersionNumber(versionNumber);
        }
    }
    
    @Override
    public void exitFigurelist(ShowParser.FigurelistContext ctx) {
        // Validate that at least one figure item is present
        if (ctx.figureitem().isEmpty()) {
            semanticErrors.add("Figure list cannot be empty - at least one figure must be specified");
        }
        
        // Check for minimum and maximum number of figures (business rule)
        int figureCount = ctx.figureitem().size();
        if (figureCount > 50) {
            semanticErrors.add("Too many figures in show - maximum 50 figures allowed, found " + figureCount);
        }
    }
    
    private void validateProposalNumber(String propNumber) {
        if (propNumber == null || propNumber.isEmpty()) {
            semanticErrors.add("Proposal number cannot be empty");
            return;
        }
        
        // Validate PROP-XXXX format
        if (!propNumber.matches("PROP-\\d{4}")) {
            semanticErrors.add("Invalid proposal number format: " + propNumber + ". Expected format: PROP-XXXX");
        }
        
        // Validate that the number part is not all zeros
        String numberPart = propNumber.substring(5); // Remove "PROP-"
        if ("0000".equals(numberPart)) {
            semanticErrors.add("Invalid proposal number: " + propNumber + ". Number cannot be 0000");
        }
    }
    
    private void validateFigureCode(String figureCode) {
        if (figureCode == null || figureCode.isEmpty()) {
            semanticErrors.add("Figure code cannot be empty");
            return;
        }
        
        // Validate FIG-XXXX format
        if (!figureCode.matches("FIG-\\d{4}")) {
            semanticErrors.add("Invalid figure code format: " + figureCode + ". Expected format: FIG-XXXX");
        }
        
        // Validate that the number part is not all zeros
        String numberPart = figureCode.substring(4); // Remove "FIG-"
        if ("0000".equals(numberPart)) {
            semanticErrors.add("Invalid figure code: " + figureCode + ". Number cannot be 0000");
        }
    }
    
    private void validateVersionNumber(String versionNumber) {
        if (versionNumber == null || versionNumber.isEmpty()) {
            semanticErrors.add("Version number cannot be empty");
            return;
        }
        
        // Validate version format (x.y.z where z can be multiple digits)
        if (!versionNumber.matches("\\d+\\.\\d+\\.\\d+")) {
            semanticErrors.add("Invalid version number format: " + versionNumber + ". Expected format: x.y.z");
            return;
        }
        
        // Parse version parts and validate ranges
        String[] parts = versionNumber.split("\\.");
        try {
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);
            int patch = Integer.parseInt(parts[2]);
            
            // Validate version number ranges (business rules)
            if (major < 1 || major > 99) {
                semanticErrors.add("Invalid major version: " + major + ". Must be between 1 and 99");
            }
            if (minor < 0 || minor > 99) {
                semanticErrors.add("Invalid minor version: " + minor + ". Must be between 0 and 99");
            }
            if (patch < 0 || patch > 999) {
                semanticErrors.add("Invalid patch version: " + patch + ". Must be between 0 and 999");
            }
            
        } catch (NumberFormatException e) {
            semanticErrors.add("Invalid version number format: " + versionNumber + ". Contains non-numeric parts");
        }
    }
    
    public Set<String> getDeclaredFigureCodes() {
        return new HashSet<>(declaredFigureCodes);
    }
    
    public int getFigureCount() {
        return declaredFigureCodes.size();
    }
}