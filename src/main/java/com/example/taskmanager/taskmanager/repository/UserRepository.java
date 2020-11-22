package com.example.taskmanager.taskmanager.repository;

import java.util.List;

import com.example.taskmanager.taskmanager.model.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    
    User findById(long id);

    List<User> findAll();

    User findByEmail(String email);

    User findByEmailAndPassword(String email,String password);

    

}
