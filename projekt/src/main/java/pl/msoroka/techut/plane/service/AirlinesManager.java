package pl.msoroka.techut.plane.service;

import pl.msoroka.techut.plane.domain.Plane;

public interface AirlinesManager {

    void addPlane(Plane plane);
    Plane findPlaneById(long id);
    Plane findPlaneBySideNumber(String sideNumber);

}
