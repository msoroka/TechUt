package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.Plane;

@Component
@Transactional
public class AirlinesManagerHibernateImpl implements AirlinesManager{

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
        return (Plane) hsf.getCurrentSession().get(Plane.class, sideNumber);
    }

}
