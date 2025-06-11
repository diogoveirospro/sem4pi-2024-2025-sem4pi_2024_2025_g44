package core.Figure.domain.ValueObjects;


import core.Figure.application.Service.DSLValidate;
import core.Figure.application.Service.DSLValidationResult;
import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents the description of a Domain-Specific Language (DSL).
 * This class is immutable and implements ValueObject interface.
 */
@Embeddable
public class DSLDescription implements ValueObject, Serializable {

    /**
     * Serial version UID for serialization
     */
    private static final long serialVersionUID = 1L;

    /**
     * The lines of the DSL code
     */
    private String DSLCodeLines;

    /**
     * The version of the DSL
     */
    private String DSLVersion;

    /**
     * Default constructor for JPA.
     */
    protected DSLDescription() {
        this.DSLCodeLines = null;
        this.DSLVersion = null;
    }

    /**
     * Constructor to create a DSLDescription object.
     *
     * @param DSLCodeLines the lines of the DSL code
     * @param DSLVersion the version of the DSL
     */
    public DSLDescription(final List<String> DSLCodeLines, final String DSLVersion) {
        if (DSLCodeLines == null || DSLCodeLines.isEmpty()) {
            throw new IllegalArgumentException("DSL code lines cannot be null or empty");
        }

        if (DSLVersion == null || DSLVersion.isBlank()) {
            throw new IllegalArgumentException("DSL version cannot be null or blank");
        }

        if (!verifyDSLVersion(DSLVersion)) {
            throw new IllegalArgumentException("Invalid DSL version format. Expected format: X.Y.Z");
        }

        this.DSLCodeLines = String.join("\n", DSLCodeLines);
        this.DSLVersion = DSLVersion;
    }



    /**
     * Verifies if the DSL version is valid.
     *
     * @param version the version of the DSL
     * @return true if valid, false otherwise
     */
    private boolean verifyDSLVersion(String version) {
        return version.matches("\\d+\\.\\d+\\.\\d+"); // ex: 1.0.3
    }

    /**
     * Returns the lines of the DSL code.
     *
     * @return the lines of the DSL code
     */
    public List<String> codeLines() {
        return List.of(DSLCodeLines.split("\n"));
    }

    /**
     * Returns the version of the DSL.
     *
     * @return the version of the DSL
     */
    public String version() {
        return DSLVersion;
    }

    /**
     * Returns the DSL Description as a string.
     *
     * @return the DSL Description as a string
     */
    @Override
    public String toString() {
        return String.join("\n", DSLCodeLines) + "\nVersion: " + DSLVersion;
    }

    /**
     * equals method to compare two DSLDescription objects.
     *
     * @param o the object to compare with this object
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DSLDescription that = (DSLDescription) o;
        return DSLCodeLines.equals(that.DSLCodeLines) && DSLVersion.equals(that.DSLVersion);
    }

    /**
     * hashCode method to generate a hash code for the DSLDescription object.
     *
     * @return the hash code for the DSLDescription object
     */
    @Override
    public int hashCode() {
        return Objects.hash(DSLCodeLines, DSLVersion);
    }

    //Retrieves the drone types of the figure
    public Set<String> requiredDroneTypes() {
        Set<String> droneTypes = new HashSet<>();
        if (DSLCodeLines == null || DSLCodeLines.isBlank()) {
            return droneTypes;
        }

        // Define a regex pattern to match drone types (e.g., "droneType: <type>")
        Pattern pattern = Pattern.compile("DroneType\\s*(\\w+)");
        Matcher matcher = pattern.matcher(DSLCodeLines);

        // Extract all matches and add them to the set
        while (matcher.find()) {
            droneTypes.add(matcher.group(1));
        }

        return droneTypes;
    }
}
