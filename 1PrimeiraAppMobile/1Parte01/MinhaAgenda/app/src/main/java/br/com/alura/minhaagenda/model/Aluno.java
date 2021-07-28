package br.com.alura.minhaagenda.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private String nome, telefone, email;

    public Aluno(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    @NonNull
    @Override
    public String toString() {
        return nome;
    }
}
