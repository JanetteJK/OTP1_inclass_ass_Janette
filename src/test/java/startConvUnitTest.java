// src/test/java/view/startConvTest.java
package view;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class startConvTest {

    @Test
    void constructor_shouldCreateApplicationInstance() {
        startConv app = new startConv();

        assertNotNull(app);
    }
}
