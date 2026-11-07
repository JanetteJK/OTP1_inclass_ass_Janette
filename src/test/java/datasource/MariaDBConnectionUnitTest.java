package datasource;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;

class MariaDBConnectionUnitTest {

    @AfterEach
    void tearDown() {
        MariaDBConnection.conn = null;
    }

    @Test
    void connect_shouldReturnExistingConnectionWhenAlreadyConnected() {
        Connection mockConnection = mock(Connection.class);
        MariaDBConnection.conn = mockConnection;

        Connection result = MariaDBConnection.connect();

        assertSame(mockConnection, result);
    }
}