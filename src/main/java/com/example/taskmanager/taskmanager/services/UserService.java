package com.example.taskmanager.taskmanager.services;

import com.example.taskmanager.taskmanager.model.Developer;
import com.example.taskmanager.taskmanager.model.Manager;
import com.example.taskmanager.taskmanager.model.Stakeholder;
import com.example.taskmanager.taskmanager.model.User;
import com.example.taskmanager.taskmanager.repository.DeveloperRepository;
import com.example.taskmanager.taskmanager.repository.ManagerRepository;
import com.example.taskmanager.taskmanager.repository.StakeholderRepository;
import com.example.taskmanager.taskmanager.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;

    @Autowired
    private DeveloperRepository devRepo;

    @Autowired
    private StakeholderRepository holderRepo;

    @Autowired
    private ManagerRepository managerRepo;


    public void saveUser(User user){
       repo.save(user);
    }

    public User findUser(String email,String password){
    
        Developer dev =devRepo.findByEmailAndPassword(email, password);

        Stakeholder stake = holderRepo.findByEmailAndPassword(email, password);

        Manager manager = managerRepo.findByEmailAndPassword(email, password);

        if(dev != null)
            return dev;

        if(stake != null)
            return stake;
        
        if(manager != null)
            return manager;
        
        return null;
    }

    

    /**
     * @return the repo
     */
    public UserRepository getRepo() {
        return repo;
    }

    /**
     * @return the devRepo
     */
    public DeveloperRepository getDevRepo() {
        return devRepo;
    }

    /**
     * @return the holderRepo
     */
    public StakeholderRepository getHolderRepo() {
        return holderRepo;
    }

    /**
     * @return the managerRepo
     */
    public ManagerRepository getManagerRepo() {
        return managerRepo;
    }

}