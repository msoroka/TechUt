package pl.msoroka.zad05.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.zad05.domain.Plane;
import pl.msoroka.zad05.domain.Producer;

import java.util.List;

@Component
@Transactional
public class ProducerManagerHibernateImpl implements ProducerManager {

    @Autowired
    SessionFactory hsf;

    @Override
    public void addProducer(Producer producer) {
        hsf.getCurrentSession().save(producer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Producer> getAllProducers() {
        return hsf.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public Producer findById(long id) {
        return (Producer) hsf.getCurrentSession().get(Producer.class, id);

    }

    @Override
    public void updateProducer(Producer producer) {
        hsf.getCurrentSession().update(producer);
    }

    @Override
    public void deleteProducer(Producer producer) {
        hsf.getCurrentSession().delete(producer);
    }

    @Override
    public void assignPlane(Long planeId, Long producerId) {
        Plane plane = (Plane) hsf.getCurrentSession().get(Plane.class, planeId);
        Producer producer = (Producer) hsf.getCurrentSession().get(Producer.class, producerId);
        producer.getPlanes().add(plane);
    }
}
