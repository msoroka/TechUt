package pl.msoroka.zad02;

import pl.msoroka.zad02.domain.Plane;
import pl.msoroka.zad02.service.PlaneService;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Date below is 2018-05-30
        Plane plane1 = new Plane("Boeing 730", 375, new Date(118, 05, 30), 135.5);

        // Date below is 1989-02-15
        Plane plane2 = new Plane("Airbus A320", 375, new Date(89, 01, 15), 95.5);

        PlaneService ps = new PlaneService();

        ps.removePlanes();

        ps.addPlane(plane1);
        ps.addPlane(plane2);

        ps.showAllPlanes();
    }
}
