package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.agenda.DAO.AlunoDAO;
import br.com.alura.agenda.R;
import br.com.alura.agenda.model.Aluno;

public class FormularioAlunoActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Novo aluno";
    private EditText lblNome, lblTelefone, lblEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        configuraBotaoSalvar();

        Intent dados = getIntent();
        if (dados.hasExtra("aluno")) {
            aluno = (Aluno) dados.getSerializableExtra("aluno");
            lblNome.setText(aluno.getNome());
            lblTelefone.setText(aluno.getTelefone());
            lblEmail.setText(aluno.getEmail());
        } else {
            aluno = new Aluno();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void configuraBotaoSalvar() {
        View btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        btnSalvar.setOnClickListener(view -> {
            preencheAluno();
            if (aluno.temIdValido()) {
                dao.edita(aluno);
            } else {
                dao.salva(aluno);
            }
            finish();
        });
    }

    private void inicializacaoDosCampos() {
        lblNome = findViewById(R.id.activity_formulario_aluno_nome);
        lblTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        lblEmail = findViewById(R.id.activity_formulario_aluno_email);
    }

    private void preencheAluno() {
        String nome = lblNome.getText().toString();
        String telefone = lblTelefone.getText().toString();
        String email = lblEmail.getText().toString();
        aluno.setNome(nome);
        aluno.setTelefone(telefone);
        aluno.setEmail(email);
    }
}