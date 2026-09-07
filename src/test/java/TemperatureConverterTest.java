import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private final TemperatureConverter converter = new TemperatureConverter();

    @Test
    void fahrenheitToCelsius_shouldConvertBoilingPoint() {
        assertEquals(100.0, converter.fahrenheitToCelsius(212.0), 0.001);
    }

    @Test
    void fahrenheitToCelsius_shouldConvertFreezingPoint() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32.0), 0.001);
    }

    @Test
    void fahrenheitToCelsius_shouldConvertNegativeForty() {
        assertEquals(-40.0, converter.fahrenheitToCelsius(-40.0), 0.001);
    }

    @Test
    void celsiusToFahrenheit_shouldConvertBoilingPoint() {
        assertEquals(212.0, converter.celsiusToFahrenheit(100.0), 0.001);
    }

    @Test
    void celsiusToFahrenheit_shouldConvertFreezingPoint() {
        assertEquals(32.0, converter.celsiusToFahrenheit(0.0), 0.001);
    }

    @Test
    void celsiusToFahrenheit_shouldConvertNegativeForty() {
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40.0), 0.001);
    }

    @Test
    void isExtremeTemperature_shouldReturnTrueForLowerBoundary() {
        assertTrue(converter.isExtremeTemperature(-40.0));
    }

    @Test
    void isExtremeTemperature_shouldReturnTrueForUpperBoundary() {
        assertTrue(converter.isExtremeTemperature(50.0));
    }

    @Test
    void isExtremeTemperature_shouldReturnTrueBelowLowerBoundary() {
        assertTrue(converter.isExtremeTemperature(-41.0));
    }

    @Test
    void isExtremeTemperature_shouldReturnTrueAboveUpperBoundary() {
        assertTrue(converter.isExtremeTemperature(51.0));
    }

    @Test
    void isExtremeTemperature_shouldReturnFalseForNormalTemperature() {
        assertFalse(converter.isExtremeTemperature(25.0));
    }

    @Test
    void isExtremeTemperature_shouldReturnFalseJustInsideBoundaries() {
        assertFalse(converter.isExtremeTemperature(-39.9));
        assertFalse(converter.isExtremeTemperature(49.9));
    }
}