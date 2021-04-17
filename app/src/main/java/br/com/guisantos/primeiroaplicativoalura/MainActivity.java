package br.com.guisantos.primeiroaplicativoalura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class MainActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_app_bar_activity_main_activity);
        configuraFabDeNovoAluno();
        Aluno alunoEntrada = new Aluno("Guilherme", "guisantos@guisantos.com.br", "19");
        dao.salva(alunoEntrada);
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraFabDeNovoAluno() {
        FloatingActionButton buttonFAB = findViewById(R.id.floatingActionButton);

        buttonFAB.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                iniciaActivityFormulario();
            }
        });
    }

    private void iniciaActivityFormulario() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.activity_main_listaDeAlunos);
        List<Aluno> alunos = dao.todos();
        listView.setAdapter(
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_list_item_1,
                        alunos
                )
        );
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno alunoEscolhido = alunos.get(position);
                Intent vaiParaFormActivity = new Intent(MainActivity.this, FormularioAlunoActivity.class);
                vaiParaFormActivity.putExtra("aluno", alunoEscolhido);
                startActivity(vaiParaFormActivity);
            }
        });
    }
}
