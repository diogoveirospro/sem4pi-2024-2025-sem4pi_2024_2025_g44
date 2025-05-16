package core.ModelOfDrone.domain.Entities;

import core.ModelOfDrone.domain.ValueObjects.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    private ModelName modelName;
    private Configuration configuration;
    private Model model;

    @BeforeEach
    void setUp() {
        modelName = new ModelName("TestModel");
        // Setup Configuration with dummy values
        WindSpeed ws = new WindSpeed(0, 10);
        PositionTolerance pt = new PositionTolerance(0.5);
        configuration = new Configuration(
                Map.of(ws, pt),
                SafetyStatus.SAFE
        );

        model = new Model(modelName, configuration);
    }

    @Test
    void testConstructorAndGetters() {
        assertNotNull(model);
        assertEquals(modelName, model.identity());
        assertEquals(configuration, model.getConfiguration());
    }

    @Test
    void testConstructorThrowsOnNullArgs() {
        assertThrows(IllegalArgumentException.class, () -> new Model(null, configuration));
        assertThrows(IllegalArgumentException.class, () -> new Model(modelName, null));
    }

    @Test
    void testSetters() {
        ModelName newName = new ModelName("NewModelName");
        model.setModelName(newName);
        assertEquals(newName, model.identity());

        WindSpeed ws2 = new WindSpeed(10, 20);
        PositionTolerance pt2 = new PositionTolerance(1.0);
        Configuration newConfig = new Configuration(Map.of(ws2, pt2), SafetyStatus.UNSAFE);
        model.setConfiguration(newConfig);
        assertEquals(newConfig, model.getConfiguration());
    }

    @Test
    void testSameAs() {
        Model sameModel = new Model(modelName, configuration);
        Model differentModel = new Model(new ModelName("OtherModel"), configuration);

        assertTrue(model.sameAs(sameModel));
        assertFalse(model.sameAs(differentModel));
        assertFalse(model.sameAs(null));
        assertFalse(model.sameAs("Some String"));
    }

    @Test
    void testEqualsAndHashCode() {
        Model sameModel = new Model(modelName, configuration);
        Model differentModel = new Model(new ModelName("OtherModel"), configuration);

        assertEquals(model, sameModel);
        assertEquals(model.hashCode(), sameModel.hashCode());

        assertNotEquals(model, differentModel);
        assertNotEquals(model.hashCode(), differentModel.hashCode());
    }

    @Test
    void testToStringContainsModelNameAndConfiguration() {
        String toString = model.toString();
        assertTrue(toString.contains(modelName.toString()));
        assertTrue(toString.contains(configuration.toString()));
    }
}
