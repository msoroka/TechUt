package pl.msoroka.techut.plane.service;

import pl.msoroka.techut.plane.domain.License;
import pl.msoroka.techut.plane.domain.Pilot;
import pl.msoroka.techut.plane.domain.Plane;
import pl.msoroka.techut.plane.domain.Producer;

import java.util.List;

public interface AirlinesManager {

    //Plane
    void addPlane(Plane plane);
    void updatePlane(Plane plane);
    void deletePlane(long id);
    void deleteAllPlanes();
    Plane findPlaneById(long id);
    Plane findPlaneBySideNumber(String sideNumber);
    List<Plane> getAllPlanes();

    //Producer
    void addProducer(Producer producer);
    void updateProducer(Producer producer);
    void deleteProducer(long id);
    void deleteAllProducers();
    Producer findProducerById(long id);
    List<Producer> getAllProducers();

    //Pilot
    void addPilot(Pilot pilot);

    //License
    void addLicense(License license);

    //Relations
    List<Plane> getProducedPlanes(Producer producer);
    List<Pilot> getPlanePilots(Plane plane);
    void producePlane(long producerId, long planeId);
    void assignLicense(long licenseId, long pilotId);
    void relatePilotAndPlane(long pilotId, long planeId);

}
