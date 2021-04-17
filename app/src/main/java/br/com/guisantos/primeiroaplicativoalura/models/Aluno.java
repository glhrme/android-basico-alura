package br.com.guisantos.primeiroaplicativoalura.models;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Aluno implements Serializable {
    private final String name;
    private final String email;
    private final String phone;

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

    @NonNull
    @Override
    public String toString() {
        return  name;
    }
}
