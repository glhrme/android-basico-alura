package br.com.guisantos.primeiroaplicativoalura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    private EditText nameField;
    private EditText phoneField;
    private EditText emailField;
    final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    private boolean hasAlunoExtra = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(R.string.title_app_bar_activity_formulario_aluno);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        validacaoDeExtra();
    }

    private void validacaoDeExtra() {
        Intent dados = getIntent();
        if(dados.hasExtra("aluno")) {
            this.hasAlunoExtra = true;
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            this.nameField.setText(aluno.getName());
            this.phoneField.setText(aluno.getPhone());
            this.emailField.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    private void configuraBotaoSalvar() {
        Button saveButton = findViewById(R.id.activity_formulario_aluno_btn_salvar);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencheAluno();
                if(aluno.temIdValido()) {
                    dao.edita(aluno);
                } else {
                    preencheAluno();
                    dao.salva(aluno);
                }
                finish();
            }
        });
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