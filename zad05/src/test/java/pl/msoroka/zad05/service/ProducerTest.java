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
import pl.msoroka.zad05.domain.Producer;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ProducerTest {

    @Autowired
    ProducerManager producerManager;

    @Autowired
    PlaneManager planeManager;

    @Test
    public void addProducerCheck() {
        Producer producer = new Producer("Airbus 1");

        producerManager.addProducer(producer);

        Producer retrieved = producerManager.findById(producer.getId());

        assertEquals(producer.getId(), retrieved.getId());
    }

    @Test
    public void getAllProducersCheck() {
        List<Producer> retrievedProducersBefore = producerManager.getAllProducers();

        Producer producer1 = new Producer("Airbus 2");
        Producer producer2 = new Producer("Airbus 3");

        producerManager.addProducer(producer1);
        producerManager.addProducer(producer2);

        List<Producer> retrievedProducersAfter = producerManager.getAllProducers();

        assertEquals(retrievedProducersBefore.size() + 2, retrievedProducersAfter.size());
    }

    @Test
    public void findProducerbyIdCheck() {
        Producer producer = new Producer("Airbus 4");
        producerManager.addProducer(producer);

        long id = producer.getId();

        Producer foundProducer = producerManager.findById(id);

        assertEquals(producer.getId(), foundProducer.getId());
    }

    @Test
    public void deleteProducerCheck() {
        List<Producer> retrievedProducersBefore = producerManager.getAllProducers();

        Producer producer = new Producer("Airbus 5");
        producerManager.addProducer(producer);
        producerManager.deleteProducer(producer);

        List<Producer> retrievedProducersAfter = producerManager.getAllProducers();

        assertEquals(retrievedProducersBefore.size(), retrievedProducersAfter.size());
    }

    @Test
    public void updateProducerCheck() {
        Producer producer = new Producer("Airbus 6");
        producerManager.addProducer(producer);

        String newName = "Airbus 7";

        producer.setName(newName);

        producerManager.updateProducer(producer);

        assertEquals(producer.getName(), newName);
    }

    @Test
    public void assignPlaneCheck() {
        Producer producer = new Producer("Airbus 8");
        producerManager.addProducer(producer);

        Plane plane = new Plane("Airbus 888", "01-01-2015", 2018);
        planeManager.addPlane(plane);

        producerManager.assignPlane(plane.getId(), producer.getId());
    }
}