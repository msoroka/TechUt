package pl.msoroka.zad05.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.zad05.domain.Plane;

import java.util.List;

@Component
@Transactional
public class PlaneManagerHibernateImpl implements PlaneManager {

    @Autowired
    SessionFactory hsf;

    @Override
    public void addPlane(Plane plane) {
        hsf.getCurrentSession().save(plane);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Plane> getAllPlanes() {
        return hsf.getCurrentSession().getNamedQuery("plane.all").list();
    }

    @Override
    public Plane findById(long id) {
        return (Plane) hsf.getCurrentSession().get(Plane.class, id);

    }

    @Override
    public void updatePlane(Plane plane) {
        hsf.getCurrentSession().update(plane);
    }

    @Override
    public void deletePlane(Plane plane) {
        hsf.getCurrentSession().delete(plane);
    }
}
