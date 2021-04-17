package br.com.guisantos.primeiroaplicativoalura.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }

    public String getNameAluno(int posicao) {
        return alunos.get(posicao).toString();
    }

    public void edita(Aluno aluno) {
        Aluno encontrado = null;
        for(Aluno a : alunos) {
            if(a.getId() == aluno.getId()) {
                encontrado = a;
            }
        }
        if(encontrado != null) {
            alunos.set(alunos.indexOf(encontrado), aluno);
        }
    }
}
