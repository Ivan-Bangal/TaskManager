package com.example.taskmanager.taskmanager.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.taskmanager.taskmanager.model.Developer;
import com.example.taskmanager.taskmanager.model.Manager;
import com.example.taskmanager.taskmanager.model.Stakeholder;
import com.example.taskmanager.taskmanager.model.Task;
import com.example.taskmanager.taskmanager.model.User;
import com.example.taskmanager.taskmanager.repository.DeveloperRepository;
import com.example.taskmanager.taskmanager.repository.ManagerRepository;
import com.example.taskmanager.taskmanager.repository.StakeholderRepository;
import com.example.taskmanager.taskmanager.repository.TaskRepository;
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

    @Autowired
    private TaskRepository taskService;

    public void saveUser(List<User> users) {
        for (User user : users) {
            if (user instanceof Stakeholder) {
                System.out.println("stake");
                holderRepo.save((Stakeholder) user);
            }
            if (user instanceof Developer) {
                System.out.println("DEV");
                devRepo.save((Developer) user);
            }
            if (user instanceof Manager) {
                System.out.println("Manager");
                managerRepo.save((Manager) user);
            }
            break;
        }
    }

    public void findAndDeleteBy(String email) {
        Stakeholder stake = holderRepo.findByEmail(email);
        Manager manager = managerRepo.findByEmail(email);
        Developer dev = devRepo.findByEmail(email);

        if (dev != null) {
            getAllTasks().stream().forEach(
                task ->{
                    task.getDevelopers().remove(dev);
                    taskService.save(task);
                }
            );
            devRepo.save(dev);

            devRepo.delete(dev);
        }

        if (manager != null) {
            managerRepo.delete(manager);
        }
        if (stake != null) {
            holderRepo.delete(stake);
        }

    }

    public User findUser(String email, String password) {

        Developer dev = devRepo.findByEmailAndPassword(email, password);

        Stakeholder stake = holderRepo.findByEmailAndPassword(email, password);

        Manager manager = managerRepo.findByEmailAndPassword(email, password);

        if (dev != null) {
            System.out.println("DEV");
            return dev;
        }

        if (stake != null) {
            System.out.println("Stake");
            return stake;
        }

        if (manager != null) {
            System.out.println("Manager");
            return manager;
        }

        return null;
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();

        users.addAll(devRepo.findAll());
        users.addAll(holderRepo.findAll());
        users.addAll(managerRepo.findAll());

        return users;
    }

    public List<Task> getAllTasks() {

        return taskService.findAll();
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
