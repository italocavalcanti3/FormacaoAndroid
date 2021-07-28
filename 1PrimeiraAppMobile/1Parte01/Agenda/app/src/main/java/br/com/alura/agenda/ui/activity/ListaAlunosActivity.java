package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraFabNovoAluno();
    }

    private void configuraFabNovoAluno() {
        FloatingActionButton btnNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        btnNovoAluno.setOnClickListener(view -> abreFormularioAlunoActivity());
    }

    private void abreFormularioAlunoActivity() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista(dao);
    }

    private void configuraLista(AlunoDAO dao) {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_alunos_listview);
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}