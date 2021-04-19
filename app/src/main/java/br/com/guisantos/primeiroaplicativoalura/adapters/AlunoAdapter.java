package br.com.guisantos.primeiroaplicativoalura.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.guisantos.primeiroaplicativoalura.MainActivity;
import br.com.guisantos.primeiroaplicativoalura.R;
import br.com.guisantos.primeiroaplicativoalura.models.Aluno;

public class AlunoAdapter extends BaseAdapter {
    private final List<Aluno> alunos = new ArrayList<>();
    private final Context context;

    public AlunoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Aluno getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View viewCriada = LayoutInflater.from(this.context).inflate(R.layout.item_aluno, parent, false);
        TextView nome = viewCriada.findViewById(R.id.item_aluno_nome);
        TextView email = viewCriada.findViewById(R.id.item_aluno_email);
        nome.setText(alunos.get(position).getName());
        email.setText(alunos.get(position).getPhone());

        return viewCriada;
    }

    public void clear() {
        this.alunos.clear();
    }

    public void addAll(List<Aluno> todos) {
        this.alunos.addAll(todos);
    }

    public void remove(Aluno clicado) {
        this.alunos.remove(clicado);
    }
}
