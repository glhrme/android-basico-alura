package br.com.guisantos.primeiroaplicativoalura;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.adapters.AlunoAdapter;
import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class MainActivity extends AppCompatActivity {

    private final AlunoDAO dao = new AlunoDAO();
    private AlunoAdapter adapter;
    private ListView listaDeAlunos;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.title_app_bar_activity_main_activity);
        configuraFabDeNovoAluno();
        configuraLista();
        for(int i = 0; i <= 5; i++) {
            this.dao.salva(new Aluno("Guilherme", "", "11986868981"));
            this.dao.salva(new Aluno("Brenda", "", "98965653245"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualizaAlunos(this.dao.todos());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_remover, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menu_remover) {
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            removeUsuarioNaDaoEAdapter(this.adapter.getItem(menuInfo.position));
        }
        return super.onContextItemSelected(item);
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
        this.listaDeAlunos = findViewById(R.id.activity_main_listaDeAlunos);
        configuraAdptarDeAlunos();
        configuraItemClickListener();
        registerForContextMenu(this.listaDeAlunos);
    }

    private void configuraAdptarDeAlunos() {
        this.adapter = new AlunoAdapter(this);
        this.listaDeAlunos.setAdapter(this.adapter);
    }

    private void configuraItemClickListener () {
        this.listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                abreFormularioModoEdicao((Aluno) parent.getItemAtPosition(position));
            }
        });
    }

    //Não utilizado mas apenas de exemplo para consultas futuras.
    private void configuraLongItemClickListener () {
        this.listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapaterView, View view, int position, long id) {
                removeUsuarioNaDaoEAdapter((Aluno) adapaterView.getItemAtPosition(position));
                return false;
            }
        });
    }

    private void removeUsuarioNaDaoEAdapter(Aluno clicado) {
        this.dao.remove(clicado);
        this.adapter.remove(clicado);
    }

    private void abreFormularioModoEdicao(Aluno alunoEscolhido) {
        Intent vaiParaFormActivity = new Intent(MainActivity.this, FormularioAlunoActivity.class);
        vaiParaFormActivity.putExtra("aluno", alunoEscolhido);
        startActivity(vaiParaFormActivity);
    }
}
