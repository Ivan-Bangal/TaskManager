package com.example.taskmanager.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.taskmanager.model.Developer;

import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends CrudRepository<Developer,Long> {
    
    List<Developer> findAll();

    Developer findById(long id);

    void deleteById(long id);

    Developer findByEmail(String email);

    Developer findByEmailAndPassword(String email,String password);
}
