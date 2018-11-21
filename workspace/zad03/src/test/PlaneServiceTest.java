package pl.msoroka.zad03.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import pl.msoroka.zad03.domain.Plane;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class PlaneServiceTest {

    PlaneService ps;

    {
        try {
            ps = new PlaneService();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    final private String PLANE_PRODUCER = "Boeing 737";
    final private int PLANE_CAPACITY = 150;
    final private Date PLANE_PRODUCE_DATE = new Date(1999, 01, 01);
    final private double PLANE_COMBUSTION = 33.3;

    @Test
    public void addPlaneCheck() throws SQLException {
        assertNotNull(ps);

        Plane plane = new Plane(PLANE_PRODUCER, PLANE_CAPACITY, PLANE_PRODUCE_DATE, PLANE_COMBUSTION);

        ps.addPlane(plane);

        List<Plane> retrievedPlanes = ps.getAllPlanes();

        Plane retrievedPlane = retrievedPlanes.get(0);

        assertEquals(PLANE_PRODUCER, retrievedPlane.getProducer());
        assertEquals(PLANE_CAPACITY, retrievedPlane.getCapacity());
        assertEquals(PLANE_PRODUCE_DATE, retrievedPlane.getProduceDate());
        assertEquals(PLANE_COMBUSTION, retrievedPlane.getCombustion());
    }
}