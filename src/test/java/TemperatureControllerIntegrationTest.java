// src/test/java/controller/tempControllerTest.java
package controller;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

class tempControllerTest {

    @BeforeAll
    static void startJavaFxToolkit() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException alreadyStarted) {
            latch.countDown();
        }

        latch.await(5, TimeUnit.SECONDS);
    }

    @Test
    void initialize_shouldAddTemperatureOptions() throws Exception {
        tempController controller = createController();

        runOnJavaFxThread(controller::initialize);

        assertEquals(3, controller.convertFrom.getItems().size());
        assertEquals(3, controller.convertTo.getItems().size());

        assertEquals("Celsius", controller.convertFrom.getItems().get(0));
        assertEquals("Fahrenheit", controller.convertFrom.getItems().get(1));
        assertEquals("Kelvin", controller.convertFrom.getItems().get(2));
    }

    @Test
    void getConvertValues_shouldReadSelectedValuesAndInputText() throws Exception {
        tempController controller = createController();

        runOnJavaFxThread(() -> {
            controller.initialize();
            controller.convertFrom.setValue("Celsius");
            controller.convertTo.setValue("Kelvin");
            controller.value.setText("20.5");
            controller.getConvertValues();
        });

        assertEquals("Celsius", getPrivateField(controller, "convFrom"));
        assertEquals("Kelvin", getPrivateField(controller, "convTo"));
        assertEquals(20.5, (double) getPrivateField(controller, "valueToConvert"), 0.001);
    }

    private static tempController createController() {
        tempController controller = new tempController();

        controller.convertFrom = new ChoiceBox<>();
        controller.convertTo = new ChoiceBox<>();
        controller.value = new TextField();
        controller.historyList = new ListView<>();
        controller.convert = new Button();
        controller.result = new Label();

        return controller;
    }

    private static void runOnJavaFxThread(Runnable action) throws Exception {
        CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                action.run();
            } finally {
                latch.countDown();
            }
        });

        latch.await(5, TimeUnit.SECONDS);
    }

    private static Object getPrivateField(Object target, String fieldName) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(target);
    }
}
