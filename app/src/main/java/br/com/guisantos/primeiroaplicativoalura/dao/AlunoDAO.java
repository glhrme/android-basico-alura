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

    public int buscaAlunoPeloId(int id) {
        Aluno encontrado = null;
        for(Aluno a : alunos) {
            if(a.getId() == id) {
                encontrado = a;
            }
        }
        if(encontrado != null) {
            return alunos.indexOf(encontrado);
        }
        return -1;
    }

    public void edita(Aluno aluno) {
        int index = buscaAlunoPeloId(aluno.getId());
        if(index >= 0) {
            alunos.set(index, aluno);
        }
    }

    public void remove(Aluno aluno) {
        int indexAluno = buscaAlunoPeloId(aluno.getId());
        if(indexAluno >= 0) {
            alunos.remove(indexAluno);
        }
    }
}
