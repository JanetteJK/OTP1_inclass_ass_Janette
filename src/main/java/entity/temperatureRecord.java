package entity;

import java.sql.Time;
import java.sql.Timestamp;

public class temperatureRecord {
    private Double value;
    private String startUnit;
    private String unit;
    private Timestamp time;

    public temperatureRecord(Double value, String startUnit, String unit, Timestamp time) {
        this.value = value;
        this.startUnit = startUnit;
        this.unit = unit;
        this.time = time;
    }

    public Double getValue() {
        return value;
    }
    public String getStartUnit() {
        return startUnit;
    }
    public String getUnit() {
        return unit;
    }
    public Timestamp getTime() {
        return time;
    }
}
