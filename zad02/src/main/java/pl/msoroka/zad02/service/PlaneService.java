package pl.msoroka.zad02.service;

import pl.msoroka.zad02.domain.Plane;

import java.sql.*;

public class PlaneService {
    private final String URL = "jdbc:hsqldb:hsql://localhost/workdb";
    private final Connection connection;
    private final Statement statement;
    private final String CREATE_TABLE_SQL = "CREATE TABLE Plane (id bigint GENERATED BY DEFAULT AS IDENTITY, producer VARCHAR(30), capacity INT, produceDate DATE, combustion DOUBLE)";

    public PlaneService() throws SQLException {
        connection = DriverManager.getConnection(URL);
        statement = connection.createStatement();

        ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

        boolean tableExists = false;

        while (rs.next()) {
            if ("Plane".equalsIgnoreCase(rs.getString("table_name"))) {
                tableExists = true;
            }
        }

        if (tableExists) {
            // Table exsits
        } else {
            // Table doesn't exist
            statement.executeUpdate(CREATE_TABLE_SQL);
        }
    }

    public void addPlane(Plane plane) throws SQLException{
        String addPlane = "INSERT INTO Plane(producer, capacity, produceDate, combustion) VALUES('" + plane.getProducer() + "', '" + plane.getCapacity() + "', '" + plane.getProduceDate() + "', '" + plane.getCombustion() + "')";

        statement.executeUpdate(addPlane);
    }

    public void showAllPlanes() throws SQLException {
        String allPlanes = "SELECT * FROM Plane";

        ResultSet rs = statement.executeQuery(allPlanes);

        while(rs.next()) {
            System.out.println("Producer: " + rs.getString("producer") + "\nProduction date: " + rs.getDate("produceDate") + "\nCapacity: " + rs.getInt("capacity") + "\nAverage combustion: " + rs.getDouble("combustion") + "\n");
        }
    }

    public void removePlanes() throws SQLException {
        String removePlanes = "DELETE FROM Plane";

        statement.executeQuery(removePlanes);
    }
}
