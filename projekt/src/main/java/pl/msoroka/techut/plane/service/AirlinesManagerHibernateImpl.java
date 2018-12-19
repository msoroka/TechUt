package pl.msoroka.techut.plane.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.Plane;

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

    @Override
    public void updatePlane(Plane plane) {
        hsf.getCurrentSession().update(plane);
    }

    @Override
    public void deletePlane(long id) {
        Plane plane = findPlaneById(id);

        hsf.getCurrentSession().delete(plane);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Plane> getAllPlanes() {
        return hsf.getCurrentSession().getNamedQuery("plane.all").list();
    }

    @Override
    public void deleteAllPlanes() {
        hsf.getCurrentSession().createQuery("delete from Plane").executeUpdate();
    }
}
