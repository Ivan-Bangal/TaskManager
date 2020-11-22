package com.example.taskmanager.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.taskmanager.model.Stakeholder;

import org.springframework.data.repository.CrudRepository;

public interface StakeholderRepository extends CrudRepository<Stakeholder,Long> {
    
    List<Stakeholder> findAll();

    Stakeholder findById(long id);

    void deleteById(long id);

    Stakeholder findByEmail(String email);

    Stakeholder findByEmailAndPassword(String email,String password);

}
