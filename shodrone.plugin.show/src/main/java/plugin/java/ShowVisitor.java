package plugin.java;

import gen.ShowParser;
import gen.ShowParserBaseVisitor;

import java.util.*;

public class ShowVisitor extends ShowParserBaseVisitor<Object> {
    
    private List<String> validationErrors = new ArrayList<>();
    private Map<String, Object> showData = new HashMap<>();
    private List<FigureItem> figureItems = new ArrayList<>();
    private String proposalNumber;
    
    public List<String> getValidationErrors() {
        return validationErrors;
    }
    
    public Map<String, Object> getShowData() {
        return showData;
    }
    
    public List<FigureItem> getFigureItems() {
        return figureItems;
    }
    
    public String getProposalNumber() {
        return proposalNumber;
    }
    
    @Override
    public Object visitStart(ShowParser.StartContext ctx) {
        // Extract and validate proposal number
        proposalNumber = ctx.PROPNUMBER().getText();
        showData.put("proposalNumber", proposalNumber);
        
        validateProposalNumber(proposalNumber);
        
        // Visit figure list
        visit(ctx.figurelist());
        
        // Store figure items in show data
        showData.put("figureItems", figureItems);
        showData.put("figureCount", figureItems.size());
        
        return showData;
    }
    
    @Override
    public Object visitFigurelist(ShowParser.FigurelistContext ctx) {
        Set<String> seenFigureCodes = new HashSet<>();
        
        // Process each figure item
        for (ShowParser.FigureitemContext figureCtx : ctx.figureitem()) {
            FigureItem item = (FigureItem) visit(figureCtx);
            
            if (item != null) {
                // Check for duplicates
                if (seenFigureCodes.contains(item.getFigureCode())) {
                    validationErrors.add("Duplicate figure code found: " + item.getFigureCode());
                } else {
                    seenFigureCodes.add(item.getFigureCode());
                    figureItems.add(item);
                }
            }
        }
        
        // Validate figure list constraints
        validateFigureList();
        
        return figureItems;
    }
    
    @Override
    public Object visitFigureitem(ShowParser.FigureitemContext ctx) {
        String figureCode = ctx.FIGURECODE().getText();
        String versionNumber = ctx.VERSIONNUMBER().getText();
        
        // Validate figure code
        validateFigureCode(figureCode);
        
        // Validate version number
        validateVersionNumber(versionNumber);
        
        // Create and return figure item
        FigureItem item = new FigureItem(figureCode, versionNumber);
        return item;
    }
    
    private void validateProposalNumber(String propNumber) {
        if (propNumber == null || propNumber.trim().isEmpty()) {
            validationErrors.add("Proposal number cannot be empty");
            return;
        }
        
        // Check format
        if (!propNumber.matches("PROP-\\d{4}")) {
            validationErrors.add("Invalid proposal number format: " + propNumber + ". Expected: PROP-XXXX");
            return;
        }
        
        // Extract and validate number part
        String numberPart = propNumber.substring(5);
        try {
            int propNum = Integer.parseInt(numberPart);
            if (propNum == 0) {
                validationErrors.add("Proposal number cannot be PROP-0000");
            }
            if (propNum > 9999) {
                validationErrors.add("Proposal number exceeds maximum value: " + propNumber);
            }
        } catch (NumberFormatException e) {
            validationErrors.add("Invalid proposal number format: " + propNumber);
        }
    }
    
    private void validateFigureCode(String figureCode) {
        if (figureCode == null || figureCode.trim().isEmpty()) {
            validationErrors.add("Figure code cannot be empty");
            return;
        }
        
        // Check format
        if (!figureCode.matches("FIG-\\d{4}")) {
            validationErrors.add("Invalid figure code format: " + figureCode + ". Expected: FIG-XXXX");
            return;
        }
        
        // Extract and validate number part
        String numberPart = figureCode.substring(4);
        try {
            int figNum = Integer.parseInt(numberPart);
            if (figNum == 0) {
                validationErrors.add("Figure code cannot be FIG-0000");
            }
            if (figNum > 9999) {
                validationErrors.add("Figure code exceeds maximum value: " + figureCode);
            }
        } catch (NumberFormatException e) {
            validationErrors.add("Invalid figure code format: " + figureCode);
        }
    }
    
    private void validateVersionNumber(String versionNumber) {
        if (versionNumber == null || versionNumber.trim().isEmpty()) {
            validationErrors.add("Version number cannot be empty");
            return;
        }
        
        // Check basic format
        if (!versionNumber.matches("\\d+\\.\\d+\\.\\d+")) {
            validationErrors.add("Invalid version format: " + versionNumber + ". Expected: major.minor.patch");
            return;
        }
        
        // Parse and validate version components
        String[] parts = versionNumber.split("\\.");
        try {
            int major = Integer.parseInt(parts[0]);
            int minor = Integer.parseInt(parts[1]);
            int patch = Integer.parseInt(parts[2]);
            
            // Validate component ranges
            if (major < 1) {
                validationErrors.add("Major version must be at least 1: " + versionNumber);
            }
            if (major > 99) {
                validationErrors.add("Major version cannot exceed 99: " + versionNumber);
            }
            if (minor < 0) {
                validationErrors.add("Minor version cannot be negative: " + versionNumber);
            }
            if (minor > 99) {
                validationErrors.add("Minor version cannot exceed 99: " + versionNumber);
            }
            if (patch < 0) {
                validationErrors.add("Patch version cannot be negative: " + versionNumber);
            }
            if (patch > 999) {
                validationErrors.add("Patch version cannot exceed 999: " + versionNumber);
            }
            
        } catch (NumberFormatException e) {
            validationErrors.add("Invalid version number components: " + versionNumber);
        }
    }
    
    private void validateFigureList() {
        if (figureItems.isEmpty()) {
            validationErrors.add("Show must contain at least one figure");
            return;
        }
        
        // Business rule: maximum number of figures
        if (figureItems.size() > 100) {
            validationErrors.add("Show cannot contain more than 100 figures. Found: " + figureItems.size());
        }
        
        // Check for sequential ordering (optional business rule)
        validateFigureOrdering();
        
        // Check for version consistency (optional business rule)
        validateVersionConsistency();
    }
    
    private void validateFigureOrdering() {
        // Optional: Check if figure codes are in sequential order
        List<Integer> figureNumbers = new ArrayList<>();
        
        for (FigureItem item : figureItems) {
            try {
                int figNum = Integer.parseInt(item.getFigureCode().substring(4));
                figureNumbers.add(figNum);
            } catch (NumberFormatException e) {
                // Already validated in validateFigureCode
            }
        }
        
        // Check if figures are in ascending order (warning, not error)
        for (int i = 1; i < figureNumbers.size(); i++) {
            if (figureNumbers.get(i) <= figureNumbers.get(i - 1)) {
                // This could be a warning instead of an error
                break; // Just one warning
            }
        }
    }
    
    private void validateVersionConsistency() {
        // Optional: Check if all figures use consistent major versions
        Set<Integer> majorVersions = new HashSet<>();
        
        for (FigureItem item : figureItems) {
            try {
                String[] parts = item.getVersionNumber().split("\\.");
                int major = Integer.parseInt(parts[0]);
                majorVersions.add(major);
            } catch (Exception e) {
                // Already validated in validateVersionNumber
            }
        }
        
        // If more than 2 different major versions, it might be inconsistent
        if (majorVersions.size() > 3) {
            validationErrors.add("Show contains figures with many different major versions (" + 
                               majorVersions.size() + " different major versions). Consider version consistency.");
        }
    }
    
    // Inner class to represent a figure item
    public static class FigureItem {
        private final String figureCode;
        private final String versionNumber;
        
        public FigureItem(String figureCode, String versionNumber) {
            this.figureCode = figureCode;
            this.versionNumber = versionNumber;
        }
        
        public String getFigureCode() {
            return figureCode;
        }
        
        public String getVersionNumber() {
            return versionNumber;
        }
        
        @Override
        public String toString() {
            return figureCode + " -> " + versionNumber;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            FigureItem that = (FigureItem) obj;
            return Objects.equals(figureCode, that.figureCode) && 
                   Objects.equals(versionNumber, that.versionNumber);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(figureCode, versionNumber);
        }
    }
}