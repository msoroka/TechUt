package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.Producer;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class ProducerTest {

    @Autowired
    AirlinesManager airlinesManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addProducerCheck() {
        Producer producer = new Producer("Airbus");

        airlinesManager.addProducer(producer);

        long id = producer.getId();

        Producer retrievedProducer = (Producer) hsf.getCurrentSession().get(Producer.class, id);

        assertEquals(producer.getId(), retrievedProducer.getId());
    }

    @Test
    public void getProducerByIdCheck() {
        Producer producer = new Producer("Airbus1");

        airlinesManager.addProducer(producer);

        Producer retrievedProducer = airlinesManager.findProducerById(producer.getId());

        assertEquals(producer.getId(), retrievedProducer.getId());
    }

    @Test
    public void updateProducerCheck() {
        Producer producer = new Producer("Airbus2");

        airlinesManager.addProducer(producer);

        String name = "Airbus3";

        producer.setName(name);

        airlinesManager.updateProducer(producer);

        assertEquals(producer.getName(), name);
    }

    @Test
    public void deleteProducerCheck() {
        Producer producer = new Producer("Airbus4");

        airlinesManager.addProducer(producer);

        List<Producer> allProducersBefore = hsf.getCurrentSession().getNamedQuery("producer.all").list();
        int before = allProducersBefore.size();

        airlinesManager.deleteProducer(producer.getId());

        List<Producer> allProducersAfter = hsf.getCurrentSession().getNamedQuery("producer.all").list();
        int after = allProducersAfter.size();

        assertEquals(after, before - 1);
    }

    @Test
    public void getAllProducersCheck() {
        List<Producer> allProducersBefore = airlinesManager.getAllProducers();
        int before = allProducersBefore.size();


        Producer producer = new Producer("Airbus5");
        airlinesManager.addProducer(producer);

        List<Producer> allProducersAfter = airlinesManager.getAllProducers();
        int after = allProducersAfter.size();

        assertEquals(after, before + 1);
    }

    @Test
    public void deleteAllProducersCheck() {
        Producer producer = new Producer("Airbus6");
        airlinesManager.addProducer(producer);

        List<Producer> allProducersBefore = airlinesManager.getAllProducers();
        int before = allProducersBefore.size();

        airlinesManager.deleteAllProducers();

        List<Producer> allProducersAfter = airlinesManager.getAllProducers();
        int after = allProducersAfter.size();

        assertEquals(after + before, before);
    }
}
