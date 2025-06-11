package core.Drone.domain.ValueObjects;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ProgramingLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ProgramingLanguage() {
        // Default constructor for JPA
    }
}
