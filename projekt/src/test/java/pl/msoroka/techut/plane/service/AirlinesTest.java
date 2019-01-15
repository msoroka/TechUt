package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.License;
import pl.msoroka.techut.plane.domain.Pilot;
import pl.msoroka.techut.plane.domain.Plane;
import pl.msoroka.techut.plane.domain.Producer;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class AirlinesTest {

    @Autowired
    AirlinesManager airlinesManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void producePlaneCheck(){
        Producer producer = new Producer("Boeing");

        airlinesManager.addProducer(producer);

        List<Plane> producedPlanesBefore = airlinesManager.getProducedPlanes(producer);
        int before = producedPlanesBefore.size();

        Plane plane1 = new Plane("777", "XX-777" + before + 1, 250, new Date(115, 05, 15));
        Plane plane2 = new Plane("747", "XX-747" + before + 2, 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane1);
        airlinesManager.addPlane(plane2);

        airlinesManager.producePlane(producer.getId(), plane1.getId());
        airlinesManager.producePlane(producer.getId(), plane2.getId());

        List<Plane> producedPlanesAfter = airlinesManager.getProducedPlanes(producer);
        int after = producedPlanesAfter.size();

        assertEquals(before + 2, after);
        assertEquals(plane1.getSideNumber(), producedPlanesAfter.get(after-2).getSideNumber());
        assertEquals(plane2.getSideNumber(), producedPlanesAfter.get(after-1).getSideNumber());
    }

    @Test
    public void assignLicenseTest(){
        Pilot pilot = new Pilot("Dawid", "Nowak", new Date(115,05,15));
        airlinesManager.addPilot(pilot);

        License license = new License(pilot.getFirstName() + "123" + pilot.getId());
        airlinesManager.addLicense(license);

        airlinesManager.assignLicense(license.getId(), pilot.getId());

        assertEquals(pilot.getLicense().getId(), license.getId());
    }

    @Test
    public void relatePilotAndPlaneCheck(){
        Pilot pilot1 = new Pilot("Adam", "Małysz", new Date(115,05,15));
        Pilot pilot2 = new Pilot("Dawid", "Kubacki", new Date(115,05,15));

        airlinesManager.addPilot(pilot1);
        airlinesManager.addPilot(pilot2);


        Plane plane1 = new Plane("777", "XX-777" + pilot1.getId() + 10, 250, new Date(115, 05, 15));
        Plane plane2 = new Plane("747", "XX-747" + pilot2.getId() + 20, 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane1);
        airlinesManager.addPlane(plane2);

        List<Pilot> pilotsOneBefore = airlinesManager.getPlanePilots(plane1);
        List<Pilot> pilotsTwoBefore = airlinesManager.getPlanePilots(plane2);
        int beforeOne = pilotsOneBefore.size();
        int beforeTwo = pilotsTwoBefore.size();

        airlinesManager.relatePilotAndPlane(pilot1.getId(), plane1.getId());
        airlinesManager.relatePilotAndPlane(pilot1.getId(), plane2.getId());
        airlinesManager.relatePilotAndPlane(pilot2.getId(), plane1.getId());
        airlinesManager.relatePilotAndPlane(pilot2.getId(), plane2.getId());

        List<Pilot> pilotsOneAfter = airlinesManager.getPlanePilots(plane1);
        List<Pilot> pilotsTwoAfter = airlinesManager.getPlanePilots(plane2);
        int afterOne = pilotsOneAfter.size();
        int afterTwo = pilotsTwoAfter.size();

        assertEquals(beforeOne + 2, afterOne);
        assertEquals(beforeTwo + 2, afterTwo);
        assertEquals(plane1.getPilots().get(afterOne-2).getFirstName(), pilot1.getFirstName());
        assertEquals(plane1.getPilots().get(afterOne-1).getFirstName(), pilot2.getFirstName());
        assertEquals(plane2.getPilots().get(afterTwo-2).getFirstName(), pilot1.getFirstName());
        assertEquals(plane2.getPilots().get(afterTwo-1).getFirstName(), pilot2.getFirstName());
    }

    @Test
    public void getPilotByLicenseNumberTest(){
        Pilot pilot = new Pilot("Mateusz", "Antonowicz", new Date(115,05,15));
        airlinesManager.addPilot(pilot);

        License license = new License(pilot.getFirstName() + "123" + pilot.getId());
        airlinesManager.addLicense(license);

        airlinesManager.assignLicense(license.getId(), pilot.getId());

        Pilot retrievedPilot = airlinesManager.findPilotByLicenseNumber(license.getLicenseNumber());

        assertEquals(pilot.getId(), retrievedPilot.getId());
    }
}
