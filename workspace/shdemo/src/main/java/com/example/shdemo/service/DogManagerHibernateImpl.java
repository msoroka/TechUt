package com.example.shdemo.service;

import com.example.shdemo.domain.Dog;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional
public class DogManagerHibernateImpl implements DogManager{

    @Autowired
    SessionFactory hsf;

    @Override
    public void addDog(Dog dog) {
        hsf.getCurrentSession().save(dog);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dog> getAllDogs() {
        return hsf.getCurrentSession().getNamedQuery("dog.all").list();
    }

    @Override
    public Dog fingDobById(long id) {
        return (Dog) hsf.getCurrentSession().get(Dog.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dog> findDogByName(String name) {
        return hsf.getCurrentSession().getNamedQuery("dog.byName").setParameter("dogname", name).list();
    }
}
