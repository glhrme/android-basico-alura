package br.com.guisantos.primeiroaplicativoalura.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id = 0;


    private String name;
    private String email;
    private String phone;

    public Aluno(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @NonNull
    @Override
    public String toString() {
        return  name;
    }

    public void setId(int contadorDeIds) {
        this.id = contadorDeIds;
    }

    public int getId() {
        return id;
    }
}
