package pl.msoroka.techut.plane.service;

import pl.msoroka.techut.plane.domain.Plane;

import java.util.List;

public interface AirlinesManager {

    void addPlane(Plane plane);
    Plane findPlaneById(long id);
    Plane findPlaneBySideNumber(String sideNumber);
    void updatePlane(Plane plane);
    void deletePlane(long id);
    public List<Plane> getAllPlanes();
    public void deleteAllPlanes();

}
