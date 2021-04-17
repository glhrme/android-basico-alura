package br.com.guisantos.primeiroaplicativoalura.models;

import androidx.annotation.NonNull;

public class Aluno {

    private final String name;
    private final String email;
    private final String phone;

    public Aluno(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    @NonNull
    @Override
    public String toString() {
        return  name;
    }
}
