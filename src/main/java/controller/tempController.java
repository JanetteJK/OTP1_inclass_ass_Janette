package controller;

import dao.temperatureRecordsDAO;
import entity.temperatureRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Timestamp;
import java.time.*;
import java.sql.Time;
import java.util.List;

public class tempController {
    private String convFrom;
    private String convTo;
    private double valueToConvert;
    TemperatureConverter converter = new TemperatureConverter();
    temperatureRecord record;
    temperatureRecordsDAO dao = new temperatureRecordsDAO();

    ;

    @FXML
    public ChoiceBox<String> convertTo;

    @FXML
    public ChoiceBox<String> convertFrom;

    @FXML
    public TextField value;

    @FXML
    public ListView historyList;

    @FXML
    public Button convert;

    @FXML
    public Label result;

    public void initialize() {
        convertTo.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");
        convertFrom.getItems().addAll("Celsius", "Fahrenheit", "Kelvin");

    }

    public void getConvertValues() {
        this.convFrom = convertFrom.getValue();
        this.convTo = convertTo.getValue();
        this.valueToConvert = Double.parseDouble(value.getText());
    }

    public void convert() {
        updateHistory();
        getConvertValues();
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        if (convFrom.equals("Celsius") && convTo.equals("Fahrenheit")) {
            double r = (converter.celsiusToFahrenheit(valueToConvert));
            result.setText(String.valueOf(r));
            record = new temperatureRecord(r, "Fahrenheit", time);
            addToHistory(record);
        }
        else if (convFrom.equals("Fahrenheit") && convTo.equals("Celsius")) {
            double r = (converter.fahrenheitToCelsius(valueToConvert));
            result.setText(String.valueOf(r));
            record = new temperatureRecord(r, "Celsius", time);
            addToHistory(record);
        }
        else if (convFrom.equals("Kelvin") && convTo.equals("Celsius")) {
            double r = (converter.kelvinToCelsius(valueToConvert));
            result.setText(String.valueOf(r));
            record = new temperatureRecord(r, "Celsius", time);
            addToHistory(record);
        }
        else if (convFrom.equals("Celsius") && convTo.equals("Kelvin")){
            double r = (converter.celsiusToKelvin(valueToConvert));
            result.setText(String.valueOf(r));
            record = new temperatureRecord(r, "Kelvin", time);
            addToHistory(record);
        }
        else {
            result.setText("Nothing to convert");
        }

        updateHistory();
    }

    public void addToHistory(temperatureRecord t) {
        dao.addRecord(t);
    }

    public void updateHistory(){
        List<temperatureRecord> records = dao.getHistory();
        for (temperatureRecord r : records) {
            historyList.getItems().add(r.getValue() + " " + r.getUnit() + " at " + r.getTime());
        }
    }

}
