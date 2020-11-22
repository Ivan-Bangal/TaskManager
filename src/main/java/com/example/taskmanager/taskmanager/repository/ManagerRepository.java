package com.example.taskmanager.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.taskmanager.model.Manager;

import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager,Long> {
    
    List<Manager> findAll();

    Manager findById(long id);

    void deleteById(long id);

    Manager findByEmail(String email);

    Manager findByEmailAndPassword(String email,String password);

}
