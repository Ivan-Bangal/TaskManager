package com.example.taskmanager.taskmanager.controller;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import com.example.taskmanager.taskmanager.model.Developer;
import com.example.taskmanager.taskmanager.model.Manager;
import com.example.taskmanager.taskmanager.model.Stakeholder;
import com.example.taskmanager.taskmanager.model.State;
import com.example.taskmanager.taskmanager.model.Task;
import com.example.taskmanager.taskmanager.model.User;
import com.example.taskmanager.taskmanager.repository.TaskRepository;
import com.example.taskmanager.taskmanager.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/main")
public class MainController {

    @Autowired
    private UserService service;

    @Autowired
    private TaskRepository taskService;

    @GetMapping
    public String main(Model model, HttpSession session) {

        User repoUser = (User) session.getAttribute("loggedUser");
        setDefaultParams(model, session);

        if (repoUser == null) {
            model.addAttribute("user", repoUser);
            return "/mainMenu/menu";
        } else {
            List<User> users = service.getUsers();
            model.addAttribute("users", users);
            users.removeIf(userInList -> userInList.equals(repoUser));
            if (repoUser instanceof Developer) {

                // Screen Related
                Task[] tasks = (Task[]) service.getAllTasks().stream()
                        .filter(task -> task.getDevelopers().contains((Developer) repoUser)).toArray();
                model.addAttribute("tasks", tasks);
                model.addAttribute("job", "Developer");

                // Session Related
                session.setAttribute("job", "Developer");
            }
            if (repoUser instanceof Manager) {
                // Screen Related
                model.addAttribute("tasks", service.getAllTasks());
                model.addAttribute("job", "Manager");

                // Session Related
                session.setAttribute("job", "Manager");
            }
            if (repoUser instanceof Stakeholder) {
                // Screen Related
                model.addAttribute("job", "Stakeholder");
                model.addAttribute("tasks", service.getAllTasks());

                // Session Related
                session.setAttribute("job", "Stakeholder");
            }

            model.addAttribute("loggedUser", repoUser);

            session.setAttribute("loggedUser", repoUser);
            return "/mainMenu/menu";
        }
    }

    @PostMapping("/login")
    public String loginInit(@ModelAttribute User user, Model model, HttpSession session) {
        System.out.println(user.toString());
        setDefaultParams(model, session);
        User repoUser = service.findUser(user.getEmail(), user.getPassword());

        if (repoUser == null) {
            return "redirect://";
        } else {
            List<User> users = service.getUsers();
            model.addAttribute("users", users);
            users.removeIf(userInList -> userInList.equals(repoUser));
            if (repoUser instanceof Developer) {

                // Screen Related
                List<Task> tasks = service.getAllTasks().stream()
                        .filter(task -> task.getDevelopers().contains((Developer) repoUser))
                        .collect(Collectors.toList());

                model.addAttribute("tasks", tasks);
                model.addAttribute("job", "Developer");

                // Session Related
                session.setAttribute("job", "Developer");
            }
            if (repoUser instanceof Manager) {
                // Screen Related
                model.addAttribute("tasks", service.getAllTasks());
                model.addAttribute("job", "Manager");

                // Session Related
                session.setAttribute("job", "Manager");
            }
            if (repoUser instanceof Stakeholder) {
                // Screen Related
                model.addAttribute("job", "Stakeholder");
                model.addAttribute("tasks", service.getAllTasks());

                // Session Related
                session.setAttribute("job", "Stakeholder");
            }
            model.addAttribute("loggedUser",repoUser);

            session.setAttribute("loggedUser", repoUser);
            return "/mainMenu/menu";
        }
    }

    @GetMapping("/tasks")
    public String tasks(Model model, HttpSession session) {
        setDefaultParams(model, session);

        User user = (User) session.getAttribute("loggedUser");
        if (user == null) {
            System.out.println("ok");
        } else {
            System.out.println("Value of User : " + user.toString());
            if (user instanceof Developer) {
                List<Task> tasks = service.getAllTasks().stream()
                        .filter(task -> task.getDevelopers().contains((Developer) user))
                        .collect(Collectors.toList());
                model.addAttribute("tasks", tasks);
            } else {
                model.addAttribute("tasks", service.getAllTasks());
            }
        }

        model.addAttribute("loggedUser",user);

        session.setAttribute("loggedUser", user);

        return "/mainMenu/tasks";
    }

    @GetMapping("/task/create")
    public String createTask(Model model, HttpSession session) {
        setDefaultParams(model, session);
        User user = (User) session.getAttribute("loggedUser");

        model.addAttribute("task", new Task());
        System.out.println(service.getHolderRepo().findAll());
        model.addAttribute("developers", service.getDevRepo().findAll());
        model.addAttribute("stakeholders", service.getHolderRepo().findAll());

        model.addAttribute("loggedUser",user);

        session.setAttribute("loggedUser", user);

        return "/mainMenu/createTask";
    }

    @PostMapping("/createTask")
    public String taskCreate(Model model, HttpSession session, @ModelAttribute Task task) {

        task.setEstadoAtual(State.NEW);
        taskService.save(task);

        setDefaultParams(model, session);

        return "/mainMenu/menu";
    }

    @GetMapping("/edit/task/{id}")
    public String editTask(@PathVariable String id, Model model, HttpSession session) {
        setDefaultParams(model, session);
        Task task = taskService.findById(Long.parseLong(id));

        if (task != null) {
            model.addAttribute("task", task);
            model.addAttribute("states", State.values());
        }

        return "/mainMenu/editTask";
    }

    @PostMapping("/updateTask")
    public String postMethodName(@ModelAttribute Task task, Model model, HttpSession session) {
        // TODO: process POST request
        setDefaultParams(model, session);

        taskService.save(task);

        return "/mainMenu/menu";
    }

    @GetMapping("/delete/task/{id}")
    public String deleteTask(@PathVariable String id, Model model, HttpSession session) {
        setDefaultParams(model, session);
        Task task = taskService.findById(Long.parseLong(id));

        if (task != null) {
            taskService.delete(task);
        }
        return "redirect:/main/";
    }

    @GetMapping("/delete/user/{email}")
    public String deleteUser(@PathVariable String email, Model model, HttpSession session) {
        service.findAndDeleteBy(email);

        setDefaultParams(model, session);
        return "redirect:/main/";
    }

    private void setDefaultParams(Model model, HttpSession session) {
        User repoUser = (User) session.getAttribute("loggedUser");
        List<User> users = service.getUsers();
        users.removeIf(userInList -> userInList.equals(repoUser));
        model.addAttribute("users", users);

        if (repoUser != null && repoUser instanceof Developer) {
            System.out.println("Entered Dev Route");

            // Screen Related
            List<Task> tasks = service.getAllTasks().stream()
                    .filter(task -> task.getDevelopers().contains((Developer) repoUser)).collect(Collectors.toList());
            model.addAttribute("tasks", tasks);
            model.addAttribute("job", "Developer");

            // Session Related
            session.setAttribute("job", "Developer");
        }
        if (repoUser != null && repoUser instanceof Manager) {
            // Screen Related
            model.addAttribute("job", "Manager");
            model.addAttribute("tasks", service.getAllTasks());

            // Session Related
            session.setAttribute("job", "Manager");
        }
        if (repoUser != null && repoUser instanceof Stakeholder) {
            // Screen Related
            model.addAttribute("job", "Stakeholder");
            model.addAttribute("tasks", service.getAllTasks());

            // Session Related
            session.setAttribute("job", "Stakeholder");
        }

        model.addAttribute("loggedUser",repoUser);

        session.setAttribute("loggedUser", repoUser);
    }

}
