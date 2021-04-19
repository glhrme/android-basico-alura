package br.com.guisantos.primeiroaplicativoalura;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
            confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void confirmaRemocao(@NonNull final MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Remover Aluno");
        dialog.setMessage("Quer mesmo?");
        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                removeUsuarioNaDaoEAdapter(adapter.getItem(menuInfo.position));
            }
        });
        dialog.setNegativeButton("Não", null);
        dialog.show();
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
