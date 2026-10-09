package entity;

import java.sql.Time;
import java.sql.Timestamp;

public class temperatureRecord {
    private Double value;
    private String unit;
    private Timestamp time;

    public temperatureRecord(Double value, String unit, Timestamp time) {
        this.value = value;
        this.unit = unit;
        this.time = time;
    }

    public Double getValue() {
        return value;
    }
    public String getUnit() {
        return unit;
    }
    public Timestamp getTime() {
        return time;
    }
}
