package view;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class startConvUnitTest {

    @Test
    void constructor_shouldCreateApplicationInstance() {
        startConv app = new startConv();

        assertNotNull(app);
    }
}