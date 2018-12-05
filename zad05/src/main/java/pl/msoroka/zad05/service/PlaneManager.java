package pl.msoroka.zad05.service;

import pl.msoroka.zad05.domain.Plane;

import java.util.List;

public interface PlaneManager {

    void addPlane(Plane plane);

    List<Plane> getAllPlanes();

    Plane findById(long id);

    void updatePlane(Plane plane);

    void deletePlane(Plane plane);
}
