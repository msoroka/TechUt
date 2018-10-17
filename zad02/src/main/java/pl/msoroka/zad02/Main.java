package pl.msoroka.zad02;

import pl.msoroka.zad02.domain.Plane;
import pl.msoroka.zad02.service.PlaneService;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Plane plane1 = new Plane();
        Plane plane2 = new Plane();

        plane1.setProducer("Boeing 730");
        plane1.setCapacity(375);
        // Date below is 2018-05-30
        plane1.setProduceDate(new Date(118, 05, 30));
        plane1.setCombustion(135.5);

        plane2.setProducer("Airbus A320");
        plane2.setCapacity(220);
        // Date below is 1989-02-15
        plane2.setProduceDate(new Date(89, 01, 15));
        plane2.setCombustion(95.5);

        PlaneService ps = new PlaneService();

        ps.removePlanes();

        ps.addPlane(plane1);
        ps.addPlane(plane2);

        ps.showAllPlanes();
    }
}
