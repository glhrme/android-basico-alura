package br.com.guisantos.primeiroaplicativoalura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class MainActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    private ArrayAdapter<Aluno> adapter;
    private ListView listaDeAlunos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_app_bar_activity_main_activity);
        configuraFabDeNovoAluno();
        configuraLista();

    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunosNaTela();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("Remover");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        removeUsuarioNaDaoEAdapter(adapter.getItem(menuInfo.position));
        return super.onContextItemSelected(item);
    }

    private void atualizaAlunosNaTela() {
        adapter.clear();
        adapter.addAll(dao.todos());
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
        listaDeAlunos = findViewById(R.id.activity_main_listaDeAlunos);
        configuraAdptarDeAlunos();
        configuraItemClickListener();
        registerForContextMenu(listaDeAlunos);
    }

    private void configuraAdptarDeAlunos() {
        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()
        );
        listaDeAlunos.setAdapter(adapter);
    }

    private void configuraItemClickListener () {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abreFormularioModoEdicao((Aluno) parent.getItemAtPosition(position));
            }
        });
    }

    //Não utilizado mas apenas de exemplo para consultas futuras.
    private void configuraLongItemClickListener () {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapaterView, View view, int position, long id) {
                Aluno clicado = (Aluno) adapaterView.getItemAtPosition(position);
                removeUsuarioNaDaoEAdapter(clicado);
                return false;
            }
        });
    }

    private void removeUsuarioNaDaoEAdapter(Aluno clicado) {
        dao.remove(clicado);
        adapter.remove(clicado);
    }

    private void abreFormularioModoEdicao(Aluno alunoEscolhido) {
        Intent vaiParaFormActivity = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        vaiParaFormActivity.putExtra("aluno", alunoEscolhido);
        startActivity(vaiParaFormActivity);
    }
}
