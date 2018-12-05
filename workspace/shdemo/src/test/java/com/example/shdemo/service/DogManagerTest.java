package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import com.example.shdemo.domain.Dog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class DogManagerTest {

    @Autowired
    DogManager dm;

    @Test
    public void addDogCheck() {
        Dog reksio = new Dog("Reksio", 1999);
        dm.addDog(reksio);

        Dog retrieved = dm.fingDobById(1);

        assertEquals("Reksio", retrieved.getName());
    }

    @Test
    public void getAllCheck() {
        List<Dog> retrievedDogsBefore = dm.getAllDogs();


        Dog reksio = new Dog("Reksio", 1999);
        dm.addDog(reksio);
        Dog szarik = new Dog("Szarik", 1999);
        dm.addDog(szarik);

        List<Dog> retrievedDogsAfter = dm.getAllDogs();


        assertEquals(retrievedDogsBefore.size() + 2, retrievedDogsAfter.size());

        List<Dog> allReksios = dm.findDogByName("Reksio");

        assertEquals(2, allReksios.size());
    }

}
