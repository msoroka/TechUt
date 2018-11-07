package pl.msoroka.zad03.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import pl.msoroka.zad03.domain.Plane;

public interface PlaneManager {

    public void addPlane(Plane plane) throws SQLException;
    public void showAllPlanes() throws SQLException;
    public void searchPlaneByName(String producer) throws SQLException;
    public void updatePlane(String producer, int capacity, Date produceDate, double combustion) throws SQLException;
    public void removePlanes() throws SQLException;
    public void removePlane(String producer) throws SQLException;
    public boolean addPlanes(List<Plane> planes);
}
