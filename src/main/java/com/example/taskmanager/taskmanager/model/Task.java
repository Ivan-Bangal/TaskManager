package com.example.taskmanager.taskmanager.model;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;

import org.springframework.lang.NonNull;

@Entity
public class Task {
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;

    @Column
    @NonNull
    private String descricao;


    @Column
    @NonNull
    private Date data_inicio;

    @Column
    @NonNull
    private Date deadline;

    @Column
    @NonNull
    private State estadoAtual;

    @OneToOne
    private Stakeholder stakeholder;

    @Column
    @ManyToMany(targetEntity = Developer.class)
    private Set<Developer> developers;



    public Task() {
    }

    public Task(long id, String descricao, Date data_inicio, Date deadline, State estadoAtual) {
        this.id = id;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.deadline = deadline;
        this.estadoAtual = estadoAtual;
    }

    public Task(String descricao, Date data_inicio, Date deadline, State estadoAtual) {
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.deadline = deadline;
        this.estadoAtual = estadoAtual;
    }

    public Task(String descricao, Date data_inicio, Date deadline, State estadoAtual,Set<Developer> developers) {
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.deadline = deadline;
        this.estadoAtual = estadoAtual;
        this.developers = developers;
    }


    public Task(long id, String descricao, Date data_inicio, Date deadline, State estadoAtual, Stakeholder stakeholder, Set<Developer> developers) {
        this.id = id;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.deadline = deadline;
        this.estadoAtual = estadoAtual;
        this.stakeholder = stakeholder;
        this.developers = developers;
    }

    public Stakeholder getStakeholder() {
        return this.stakeholder;
    }

    public void setStakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
    }

    public Set<Developer> getDevelopers() {
        return this.developers;
    }

    public void setDevelopers(Set<Developer> developers) {
        this.developers = developers;
    }

    public Task stakeholder(Stakeholder stakeholder) {
        this.stakeholder = stakeholder;
        return this;
    }

    public Task developers(Set<Developer> developers) {
        this.developers = developers;
        return this;
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_inicio() {
        return this.data_inicio;
    }

    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    public Date getDeadline() {
        return this.deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public State getEstadoAtual() {
        return this.estadoAtual;
    }

    public void setEstadoAtual(State estadoAtual) {
        this.estadoAtual = estadoAtual;
    }

    public Task id(long id) {
        this.id = id;
        return this;
    }

    public Task descricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public Task data_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
        return this;
    }

    public Task deadline(Date deadline) {
        this.deadline = deadline;
        return this;
    }

    public Task estadoAtual(State estadoAtual) {
        this.estadoAtual = estadoAtual;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return id == task.id && Objects.equals(descricao, task.descricao) && Objects.equals(data_inicio, task.data_inicio) && Objects.equals(deadline, task.deadline) && Objects.equals(estadoAtual, task.estadoAtual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, data_inicio, deadline, estadoAtual);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", data_inicio='" + getData_inicio() + "'" +
            ", deadline='" + getDeadline() + "'" +
            ", estadoAtual='" + getEstadoAtual() + "'" +
            "}";
    }




}
