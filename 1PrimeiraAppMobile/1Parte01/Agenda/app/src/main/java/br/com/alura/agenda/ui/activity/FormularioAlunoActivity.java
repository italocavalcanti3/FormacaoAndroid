package br.com.alura.agenda.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText lblNome, lblTelefone, lblEmail;
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);

        inicializacaoDosCampos();
        configuraBotaoSalvar();
    }

    private void configuraBotaoSalvar() {
        View btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        btnSalvar.setOnClickListener(view -> {
            Aluno alunoCriado = criaAluno();
            salva(alunoCriado);
        });
    }

    private void inicializacaoDosCampos() {
        lblNome = findViewById(R.id.activity_formulario_aluno_nome);
        lblTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        lblEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void salva(Aluno aluno) {
        dao.salva(aluno);
        finish(); //Desempilha a activity anterior
    }

    @NotNull
    private Aluno criaAluno() {
        String nome = lblNome.getText().toString();
        String telefone = lblTelefone.getText().toString();
        String email = lblEmail.getText().toString();
        return new Aluno(nome, telefone, email);
    }
}