package core.ModelOfDrone.domain.ValueObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelNameTest {

    @Test
    void testValidModelNameCreation() {
        ModelName modelName = new ModelName("PhantomX");
        assertNotNull(modelName);
        assertEquals("PhantomX", modelName.toString());
    }

    @Test
    void testModelNameEquality() {
        ModelName modelName1 = new ModelName("PhantomX");
        ModelName modelName2 = new ModelName("PhantomX");

        assertEquals(modelName1, modelName2);
        assertEquals(modelName1.hashCode(), modelName2.hashCode());
    }

    @Test
    void testModelNameInequality() {
        ModelName modelName1 = new ModelName("PhantomX");
        ModelName modelName2 = new ModelName("FalconZ");

        assertNotEquals(modelName1, modelName2);
    }

    @Test
    void testModelNameToString() {
        ModelName modelName = new ModelName("FalconZ");
        assertEquals("FalconZ", modelName.toString());
    }

    @Test
    void testEqualsWithDifferentObjectType() {
        ModelName modelName = new ModelName("PhantomX");
        assertNotEquals(modelName, "PhantomX");
    }

    @Test
    void testEqualsWithNull() {
        ModelName modelName = new ModelName("PhantomX");
        assertNotEquals(modelName, null);
    }

    @Test
    void testHashCodeConsistency() {
        ModelName modelName = new ModelName("FalconZ");
        int hash1 = modelName.hashCode();
        int hash2 = modelName.hashCode();
        assertEquals(hash1, hash2);
    }

    @Test
    void testProtectedConstructorForORM() {
        ModelName modelName = new ModelName(); // Simula uso por ORM
        assertNotNull(modelName);
    }
}
