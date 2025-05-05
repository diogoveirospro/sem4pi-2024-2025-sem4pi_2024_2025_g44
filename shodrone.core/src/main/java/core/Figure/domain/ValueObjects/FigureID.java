package core.Figure.domain.ValueObjects;

import eapli.framework.domain.model.ValueObject;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

import java.io.Serializable;

@Embeddable
public class FigureID implements ValueObject, Comparable<FigureID>, Serializable {

    private static final long serialVersionUID = 1L;

    @Embedded
    private Code code;

    @Embedded
    private Version version;

    public FigureID(Code code, Version version) {
        if (code == null || version == null) {
            throw new IllegalArgumentException("Code and Version cannot be null");
        }
        this.code = code;
        this.version = version;
    }

    protected FigureID() {
    }

    public Code code() {
        return code;
    }

    public Version version() {
        return version;
    }

    @Override
    public String toString(){
        return "FigureID{" +
                "code=" + code +
                ", version=" + version +
                '}';
    }

    @Override
    public boolean equals(Object other){
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        FigureID figureID = (FigureID) other;

        if (!code.equals(figureID.code)) return false;
        return version.equals(figureID.version);
    }

    @Override
    public int hashCode() {
        int PRIME = 59;
        int result = 1;
        result = result * PRIME + (code != null ? code.hashCode() : 43);
        result = result * PRIME + (version != null ? version.hashCode() : 43);
        return result;
    }

    @Override
    public int compareTo(FigureID o) {
        if (this == o) return 0;
        if (o == null) return 1;

        int codeComparison = this.code.compareTo(o.code);
        if (codeComparison != 0) {
            return codeComparison;
        }

        return this.version.compareTo(o.version);
    }
}
