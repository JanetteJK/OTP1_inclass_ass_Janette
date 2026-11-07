package entity;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class temperatureRecordTest {

    @Test
    void constructor_shouldStoreValueStartUnitUnitAndTime() {
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2026, 1, 1, 12, 30));
        temperatureRecord record = new temperatureRecord(25.5, "Celsius", "Fahrenheit", timestamp);

        assertEquals(25.5, record.getValue());
        assertEquals("Celsius", record.getStartUnit());
        assertEquals("Fahrenheit", record.getUnit());
        assertEquals(timestamp, record.getTime());
    }
}