package com.example.taskmanager.taskmanager.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class Stakeholder extends User {

    public Stakeholder() {
    }

    public Stakeholder(String nome, String apelido, String email, String num_contacto) {
        super(nome, apelido, email, num_contacto);
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Developer)) {
            return false;
        }
        Stakeholder stakeholder = (Stakeholder) o;
        return getId() == stakeholder.getId();
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