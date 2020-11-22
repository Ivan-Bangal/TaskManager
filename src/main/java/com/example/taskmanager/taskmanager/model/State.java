package com.example.taskmanager.taskmanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public enum State {

    
    
    NEW,
    IN_PROGRESS,
    ON_HOLD,
    TRIAGED,
    RESOLVED,
    CLOSED;
    
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private long id;
    
    
}
