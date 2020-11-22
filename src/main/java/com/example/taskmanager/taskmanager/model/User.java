package com.example.taskmanager.taskmanager.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import org.springframework.lang.NonNull;

/**
 * User
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO )
    private long id;

    @Column
    @NonNull
    String nome;

    @Column
    @NonNull
    String apelido;

    @Column(unique = true)
    @NonNull
    String email;

    @Column
    @NonNull
    String password;

    @Column
    @NonNull
    String num_contacto;


    @Column
    private String typeUser;


    
    public User() {
    }
    public User(long id, String nome, String apelido, String email, String num_contacto) {
        this.id = id;
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.num_contacto = num_contacto;
    }
    
    public User(String nome, String apelido, String email, String num_contacto) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.num_contacto = num_contacto;
    }


    public User( String nome, String apelido, String email, String password, String num_contacto) {
        this.nome = nome;
        this.apelido = apelido;
        this.email = email;
        this.password = password;
        this.num_contacto = num_contacto;
    }

    public String getTypeUser() {
        return this.typeUser;
    }

    public void setTypeUser(String typeUser) {
        this.typeUser = typeUser;
    }

    public User typeUser(String typeUser) {
        this.typeUser = typeUser;
        return this;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User password(String password) {
        this.password = password;
        return this;
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido() {
        return this.apelido;
    }



    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User id(long id) {
        this.id = id;
        return this;
    }


    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNum_contacto() {
        return this.num_contacto;
    }

    public void setNum_contacto(String num_contacto) {
        this.num_contacto = num_contacto;
    }

    public User nome(String nome) {
        this.nome = nome;
        return this;
    }

    public User apelido(String apelido) {
        this.apelido = apelido;
        return this;
    }

    public User email(String email) {
        this.email = email;
        return this;
    }

    public User num_contacto(String num_contacto) {
        this.num_contacto = num_contacto;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(nome, user.nome) && Objects.equals(apelido, user.apelido) && Objects.equals(email, user.email) && Objects.equals(num_contacto, user.num_contacto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, apelido, email, num_contacto);
    }

    @Override
    public String toString() {
        return "{" +
            " nome='" + getNome() + "'" +
            ", apelido='" + getApelido() + "'" +
            ", email='" + getEmail() + "'" +
            ", num_contacto='" + getNum_contacto() + "'" +
            "}";
    }


    
}