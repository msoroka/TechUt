package pl.msoroka.techut.plane.service;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.msoroka.techut.plane.domain.License;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class LicenseTest {

    @Autowired
    AirlinesManager airlinesManager;

    @Autowired
    private SessionFactory hsf;

    @Test
    public void addLicenseTest(){
        License license = new License("XX-123");
        airlinesManager.addLicense(license);

        License retrievedLicense = (License) hsf.getCurrentSession().get(License.class, license.getId());

        assertEquals(retrievedLicense.getLicenseNumber(), license.getLicenseNumber());
    }
}
