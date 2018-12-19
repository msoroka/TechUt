package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.Plane;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class AirlinesManagerTest {

    @Autowired
    AirlinesManager airlinesManager;

    @Test
    public void addPlaneCheck() {
        Plane plane = new Plane("A330","XX-100", 250, new Date(115, 05, 15));

        airlinesManager.addPlane(plane);

        Plane retrievedPlane = airlinesManager.findPlaneById(plane.getId());

        assertEquals(plane.getId(), retrievedPlane.getId());
    }

}
