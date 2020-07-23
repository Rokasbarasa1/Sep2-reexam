package main.server.persistence.database;


import java.sql.*;

public class DBConnection implements IDBConnection {

    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/sep2?useTimezone=true&serverTimezone=GMT";
    private final String username = "root";
    private final String password = "2401"; //change to your password


    private final String schemaName;
    private final String receptionistTableName;
    private final String orderTableName;

    private Connection connection;

    public DBConnection() {
        schemaName = "sep2";
        receptionistTableName = "Receptionist";
        orderTableName = "order";
    }

    public Connection getConnection() {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void closeConnection() {
        try {
            if (!connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PreparedStatement executePreparedQuery(String preparedSql) throws DataConnectionException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }
        assert preparedStatement != null;
        return preparedStatement;

    }

    @Override
    public PreparedStatement createPreparedStatement(String preparedSql) throws DataConnectionException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(preparedSql);
        } catch (SQLException e) {
            throw new DataConnectionException("Lost connection to data");
        }
        return preparedStatement;
    }

    @Override
    public void executeUpdate(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

    @Override
    public String getSchemaName() {
        return schemaName;
    }

    @Override
    public String getUserTable() {
        return receptionistTableName;
    }

    @Override
    public String getShiftTable() {
        return orderTableName;
    }
}