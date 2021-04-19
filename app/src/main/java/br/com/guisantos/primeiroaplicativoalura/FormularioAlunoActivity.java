package br.com.guisantos.primeiroaplicativoalura;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(R.string.title_app_bar_activity_formulario_aluno);
        inicializacaoDosCampos();
        validacaoDeExtra();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_menu_salvar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.activity_formulario_aluno_btn_salvar_aluno) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void validacaoDeExtra() {
        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            this.nameField.setText(aluno.getName());
            this.phoneField.setText(aluno.getPhone());
            this.emailField.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void finalizaFormulario() {
        preencheAluno();
        if(aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            preencheAluno();
            dao.salva(aluno);
        }
        finish();
    }

    private void preencheAluno() {
        String name = this.nameField.getText().toString();
        String email = this.emailField.getText().toString();
        String phone = this.phoneField.getText().toString();

        aluno.setEmail(email);
        aluno.setName(name);
        aluno.setPhone(phone);
    }

    private void inicializacaoDosCampos() {
        this.nameField = findViewById(R.id.activity_formulario_aluno_nome);
        this.phoneField = findViewById(R.id.activity_formulario_aluno_telefone);
        this.emailField = findViewById(R.id.activity_formulario_aluno_email);
    }
}