package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import datasource.MariaDBConnection;
import entity.temperatureRecord;

import static datasource.MariaDBConnection.conn;

public class temperatureRecordsDAO {
    public static void addRecord(temperatureRecord t) {
        if (conn == null) {
            System.out.println("Connection is null!");
            return;
        }

        String sql = "INSERT INTO temperature_records (temperature_value, temperature_unit, timestamp ) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setDouble(1, t.getValue());
            stmt.setString(2, t.getUnit());
            stmt.setTimestamp(3, t.getTime());


            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<temperatureRecord> getHistory() {
        String sql = "SELECT record_id, temperature_value, temperature_unit, timestamp FROM temperature_records";
        List<temperatureRecord> records = new ArrayList<temperatureRecord>();

        try {
            Statement s = conn.createStatement();
            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt(1);
                Double value = rs.getDouble(2);
                String unit = rs.getString(3);
                Timestamp time = rs.getTimestamp(4);
                records.add(new temperatureRecord(value, unit, time));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }

}
