package com.example.taskmanager.taskmanager.model;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity

public class Developer extends User {
    
    

    @Column
    @ManyToMany(targetEntity = Task.class)
    private Set<Task> tasks;


    public Developer() {
    }

    public Developer(String nome, String apelido, String email, String num_contacto,Set<Task> tasks) {
        super(nome, apelido, email, num_contacto);
        this.tasks= tasks;
    } 



    public Set<Task> getTasks() {
        return this.tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Developer tasks(Set<Task> tasks) {
        this.tasks = tasks;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Developer)) {
            return false;
        }
        Developer developer = (Developer) o;
        return getId() == developer.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return super.toString();
    }
    

    
  
}
