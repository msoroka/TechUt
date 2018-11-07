package pl.msoroka.zad03;

import pl.msoroka.zad03.domain.Plane;
import pl.msoroka.zad03.service.PlaneService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Date below is 2018-05-30
        Plane plane1 = new Plane("Boeing", 375, new Date(118, 05, 30), 135.5);

        // Date below is 1989-02-15
        Plane plane2 = new Plane("Airbus", 375, new Date(89, 01, 15), 95.5);

        PlaneService ps = new PlaneService();

        ps.removePlanes();

        ps.addPlane(plane1);
        ps.addPlane(plane2);

        System.out.println("Wyświetl wszystkie samoloty:");
        ps.showAllPlanes();

        System.out.println("------------------------------\n");

        System.out.println("Znajdź samolot Airbus:");
        ps.searchPlaneByName("Airbus");

        System.out.println("------------------------------\n");

        System.out.println("Usuń samolot Airbus i wyświetl wszystkie samoloty:");
        ps.removePlane("Airbus");
        ps.showAllPlanes();

        System.out.println("------------------------------\n");

        System.out.println("Zrób update samolotu Boeing i wyświetl go:");
        System.out.println("Przed:");
        ps.searchPlaneByName("Boeing");
        System.out.println("Po:");
        ps.updatePlane("Boeing", 300, new Date(89, 01, 15), 103.5);
        ps.searchPlaneByName("Boeing");
        ps.removePlanes();

        System.out.println("------------------------------\n");

        System.out.println("Dodaj kolejne samoloty (5), a w nich duplikat i wyświetl je (Powinnno być ich 3, ponieważ transakcja zostanie cofnięta):");
        List<Plane> planes = new ArrayList<>();

        Plane plane5 = new Plane("Bombardier", 375, new Date(118, 05, 30), 135.5);
        Plane plane6 = new Plane("Boeing", 375, new Date(118, 05, 30), 135.5);
        Plane plane7 = new Plane("Cessna", 375, new Date(118, 05, 30), 135.5);
        planes.add(plane5);
        planes.add(plane6);
        planes.add(plane7);
        ps.addPlanes(planes);

        List<Plane> planes2 = new ArrayList<>();

        Plane plane51 = new Plane("Cessna", 375, new Date(118, 05, 30), 135.5);
        Plane plane61 = new Plane("Airbus", 375, new Date(118, 05, 30), 135.5);
        planes.add(plane51);
        planes.add(plane61);
        ps.addPlanes(planes2);

        ps.showAllPlanes();
    }
}
