// src/test/java/dao/temperatureRecordsDAOTest.java
package dao;

import datasource.MariaDBConnection;
import entity.temperatureRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class temperatureRecordsDAOTest {

    @AfterEach
    void tearDown() {
        MariaDBConnection.conn = null;
    }

    @Test
    void addRecord_shouldDoNothingWhenConnectionIsNull() {
        MariaDBConnection.conn = null;

        temperatureRecord record = new temperatureRecord(
                10.0,
                "Celsius",
                Timestamp.valueOf(LocalDateTime.now())
        );

        temperatureRecordsDAO.addRecord(record);
    }

    @Test
    void addRecord_shouldInsertTemperatureRecord() throws Exception {
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        MariaDBConnection.conn = connection;

        when(connection.prepareStatement(anyString())).thenReturn(statement);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2026, 1, 1, 10, 15));
        temperatureRecord record = new temperatureRecord(100.0, "Celsius", timestamp);

        temperatureRecordsDAO.addRecord(record);

        verify(connection).prepareStatement(
                "INSERT INTO temperature_records (temperature_value, temperature_unit, timestamp ) VALUES (?, ?, ?)"
        );
        verify(statement).setDouble(1, 100.0);
        verify(statement).setString(2, "Celsius");
        verify(statement).setTimestamp(3, timestamp);
        verify(statement).executeUpdate();
    }

    @Test
    void getHistory_shouldReturnRecordsFromDatabase() throws Exception {
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ResultSet resultSet = mock(ResultSet.class);

        MariaDBConnection.conn = connection;

        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery("SELECT record_id, temperature_value, temperature_unit, timestamp FROM temperature_records"))
                .thenReturn(resultSet);

        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.of(2026, 1, 1, 9, 0));

        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getInt(1)).thenReturn(1);
        when(resultSet.getDouble(2)).thenReturn(36.6);
        when(resultSet.getString(3)).thenReturn("Celsius");
        when(resultSet.getTimestamp(4)).thenReturn(timestamp);

        temperatureRecordsDAO dao = new temperatureRecordsDAO();

        List<temperatureRecord> records = dao.getHistory();

        assertEquals(1, records.size());
        assertEquals(36.6, records.get(0).getValue());
        assertEquals("Celsius", records.get(0).getUnit());
        assertEquals(timestamp, records.get(0).getTime());
    }
}
