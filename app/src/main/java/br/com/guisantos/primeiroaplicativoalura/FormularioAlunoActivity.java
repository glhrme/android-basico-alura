package br.com.guisantos.primeiroaplicativoalura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);

        AlunoDAO dao = new AlunoDAO();
        
        final EditText nameField = findViewById(R.id.activity_formulario_aluno_nome);
        final EditText phoneField = findViewById(R.id.activity_formulario_aluno_telefone);
        final EditText emailField = findViewById(R.id.activity_formulario_aluno_email);
        
        Button saveButton = findViewById(R.id.activity_formulario_aluno_btn_salvar);
        
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameField.getText().toString();
                String email = emailField.getText().toString();
                String phone = phoneField.getText().toString();

                Aluno aluno = new Aluno(name, email, phone);
                dao.salva(aluno);
                startActivity(new Intent(FormularioAlunoActivity.this, MainActivity.class));
            }
        });
    }
}