package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.Pilot;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class PilotTest {

    @Autowired
    AirlinesManager airlinesManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addPilotTest(){
        Pilot pilot = new Pilot("Jan", "Kowalski", new Date(115, 05, 15));

        airlinesManager.addPilot(pilot);

        Pilot retrievedPilot = (Pilot) hsf.getCurrentSession().get(Pilot.class, pilot.getId());

        assertEquals(retrievedPilot.getFirstName(), pilot.getFirstName());
    }
}
