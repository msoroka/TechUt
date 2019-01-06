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

        Plane plane1 = new Plane("777", "XX-777", 250, new Date(115, 05, 15));
        Plane plane2 = new Plane("747", "XX-747", 250, new Date(115, 05, 15));

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
}
