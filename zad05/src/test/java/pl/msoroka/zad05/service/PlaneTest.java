package pl.msoroka.zad05.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.zad05.domain.Plane;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class PlaneTest {

    @Autowired
    PlaneManager pm;

    @Test
    public void addPlaneCheck() {
        Plane plane = new Plane("Airbus A330", "01-01-2018", 2015);

        pm.addPlane(plane);

        Plane retrieved = pm.findById(plane.getId());

        assertEquals(plane.getId(), retrieved.getId());
    }

    @Test
    public void getAllPlanesCheck() {
        List<Plane> retrievedPlanesBefore = pm.getAllPlanes();

        Plane plane1 = new Plane("Airbus A331", "01-01-2018", 2015);
        Plane plane2 = new Plane("Airbus A332", "01-01-2018", 2015);

        pm.addPlane(plane1);
        pm.addPlane(plane2);

        List<Plane> retrievedPlanesAfter = pm.getAllPlanes();

        assertEquals(retrievedPlanesBefore.size() + 2, retrievedPlanesAfter.size());
    }

    @Test
    public void findPlaneByIdCheck() {
        Plane plane = new Plane("Airbus A333", "01-01-2018", 2015);
        pm.addPlane(plane);

        long id = plane.getId();

        Plane foundPlane = pm.findById(id);

        assertEquals(plane.getId(), foundPlane.getId());
    }

    @Test
    public void deletePlaneCheck() {
        List<Plane> retrievedPlanesBefore = pm.getAllPlanes();

        Plane plane = new Plane("Airbus A333", "01-01-2018", 2015);
        pm.addPlane(plane);
        pm.deletePlane(plane);

        List<Plane> retrievedPlanesAfter = pm.getAllPlanes();

        assertEquals(retrievedPlanesBefore.size(), retrievedPlanesAfter.size());
    }

    @Test
    public void updatePlaneCheck() {
        Plane plane = new Plane("Airbus A334", "01-01-2018", 2015);
        pm.addPlane(plane);

        String newName = "Airbus A335";

        plane.setName(newName);

        pm.updatePlane(plane);

        assertEquals(plane.getName(), newName);
    }
}