package br.com.guisantos.primeiroaplicativoalura;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.dao.AlunoDAO;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlunoDAO dao = new AlunoDAO();
        setTitle("Lista de Alunos");
        ListView listView = findViewById(R.id.activity_main_listaDeAlunos);
        listView.setAdapter(
            new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                dao.todos()
            )
        );
    }

}
