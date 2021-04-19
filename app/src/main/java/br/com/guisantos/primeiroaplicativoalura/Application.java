package br.com.guisantos.primeiroaplicativoalura;

import android.util.Log;

import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class Application extends android.app.Application {
    private final AlunoDAO dao = new AlunoDAO();
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosDeTeste();
    }

    private void criaAlunosDeTeste() {
        for(int i = 0; i <= 5; i++) {
            this.dao.salva(new Aluno("Guilherme", "", "11986868981"));
            this.dao.salva(new Aluno("Brenda", "", "98965653245"));
        }
    }
}
