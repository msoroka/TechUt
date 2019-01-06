package pl.msoroka.techut.plane.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.License;
import pl.msoroka.techut.plane.domain.Pilot;
import pl.msoroka.techut.plane.domain.Plane;
import pl.msoroka.techut.plane.domain.Producer;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class AirlinesManagerHibernateImpl implements AirlinesManager {

    @Autowired
    private SessionFactory hsf;

    @Override
    public void addPlane(Plane plane) {
        hsf.getCurrentSession().save(plane);
    }

    @Override
    public void updatePlane(Plane plane) {
        hsf.getCurrentSession().update(plane);
    }

    @Override
    public void deletePlane(long id) {
        Plane plane = findPlaneById(id);

        hsf.getCurrentSession().delete(plane);
    }

    @Override
    public void deleteAllPlanes() {
        hsf.getCurrentSession().createQuery("delete from Plane").executeUpdate();
    }

    @Override
    public Plane findPlaneById(long id) {
        return (Plane) hsf.getCurrentSession().get(Plane.class, id);
    }

    @Override
    public Plane findPlaneBySideNumber(String sideNumber) {
        Query query = hsf.getCurrentSession().
                createQuery("from Plane where sideNumber=:sideNumber");
        query.setParameter("sideNumber", sideNumber);
        Plane plane = (Plane) query.uniqueResult();

        return plane;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Plane> getAllPlanes() {
        return hsf.getCurrentSession().getNamedQuery("plane.all").list();
    }

    @Override
    public void addProducer(Producer producer) {
        hsf.getCurrentSession().save(producer);

    }

    @Override
    public void updateProducer(Producer producer) {
        hsf.getCurrentSession().update(producer);

    }

    @Override
    public void deleteProducer(long id) {
        Producer producer = findProducerById(id);

        hsf.getCurrentSession().delete(producer);
    }

    @Override
    public void deleteAllProducers() {
        hsf.getCurrentSession().createQuery("delete from Producer").executeUpdate();
    }

    @Override
    public Producer findProducerById(long id) {
        return (Producer) hsf.getCurrentSession().get(Producer.class, id);
    }

    @Override
    public List<Producer> getAllProducers() {
        return hsf.getCurrentSession().getNamedQuery("producer.all").list();
    }

    @Override
    public List<Plane> getProducedPlanes(Producer producer){
        producer = (Producer) hsf.getCurrentSession().get(Producer.class, producer.getId());

        List<Plane> planes = new ArrayList<Plane>(producer.getPlanes());

        return planes;
    }

    @Override
    public List<Pilot> getPlanePilots(Plane plane){
        plane = (Plane) hsf.getCurrentSession().get(Plane.class, plane.getId());

        List<Pilot> pilots = new ArrayList<Pilot>(plane.getPilots());

        return pilots;
    }

    @Override
    public void producePlane(long producerId, long planeId){
        Producer producer = findProducerById(producerId);
        Plane plane = findPlaneById(planeId);

        producer.getPlanes().add(plane);
    }

    @Override
    public void assignLicense(long licenseId, long pilotId){
        License license = (License) hsf.getCurrentSession().get(License.class, licenseId);
        Pilot pilot = (Pilot) hsf.getCurrentSession().get(Pilot.class, pilotId);

        pilot.setLicense(license);
    }

    @Override
    public void relatePilotAndPlane(long pilotId, long planeId){
        Pilot pilot = (Pilot) hsf.getCurrentSession().get(Pilot.class, pilotId);
        Plane plane = findPlaneById(planeId);

        plane.getPilots().add(pilot);
    }

    @Override
    public void addPilot(Pilot pilot) {
        hsf.getCurrentSession().save(pilot);
    }

    @Override
    public void addLicense(License license) {
        hsf.getCurrentSession().save(license);
    }
}
