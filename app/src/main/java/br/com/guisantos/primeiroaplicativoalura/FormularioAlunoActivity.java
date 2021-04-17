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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(R.string.title_app_bar_activity_formulario_aluno);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        Intent dados = getIntent();
        
        aluno = (Aluno) dados.getSerializableExtra("aluno");

        nameField.setText(aluno.getName());
        phoneField.setText(aluno.getPhone());
        emailField.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvar() {
        Button saveButton = findViewById(R.id.activity_formulario_aluno_btn_salvar);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preencheAluno();
                //salvarAluno(aluno);
                dao.edita(aluno);
                finish();
            }
        });
    }

    private void salvarAluno(Aluno aluno) {
        dao.salva(aluno);
        finish();
    }

    private void preencheAluno() {
        String name = nameField.getText().toString();
        String email = emailField.getText().toString();
        String phone = phoneField.getText().toString();

        aluno.setEmail(email);
        aluno.setName(name);
        aluno.setPhone(phone);
    }

    private void inicializacaoDosCampos() {
        nameField = findViewById(R.id.activity_formulario_aluno_nome);
        phoneField = findViewById(R.id.activity_formulario_aluno_telefone);
        emailField = findViewById(R.id.activity_formulario_aluno_email);
    }
}