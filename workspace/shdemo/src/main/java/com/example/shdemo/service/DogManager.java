package com.example.shdemo.service;

import com.example.shdemo.domain.Dog;

import java.util.List;

public interface DogManager {

    void addDog(Dog dog);
    List<Dog> getAllDogs();
    Dog fingDobById(long id);
    List findDogByName(String name);

}
