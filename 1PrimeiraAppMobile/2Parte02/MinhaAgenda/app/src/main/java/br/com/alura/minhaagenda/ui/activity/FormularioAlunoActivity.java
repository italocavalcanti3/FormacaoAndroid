package br.com.alura.minhaagenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.com.alura.minhaagenda.DAO.AlunoDAO;
import br.com.alura.minhaagenda.R;
import br.com.alura.minhaagenda.model.Aluno;

import static br.com.alura.minhaagenda.ui.activity.ConstantesActivities.CHAVE_ALUNO;

public class FormularioAlunoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR_NOVO_ALUNO = "Novo aluno";
    private static final String TITULO_APPBAR_EDITA_ALUNO = "Edita aluno";
    private EditText lblNome, lblTelefone, lblEmail;
    private final AlunoDAO dao = new AlunoDAO();
    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_aluno);
        inicializacaoDosCampos();
        configuraBotaoSalvar();
        carregaAluno();
    }

    private void carregaAluno() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_ALUNO)) {
            setTitle(TITULO_APPBAR_EDITA_ALUNO);
            aluno = (Aluno) dados.getSerializableExtra(CHAVE_ALUNO);
            preencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_ALUNO);
            aluno = new Aluno();
        }
    }

    private void preencheCampos() {
        lblNome.setText(aluno.getNome());
        lblTelefone.setText(aluno.getTelefone());
        lblEmail.setText(aluno.getEmail());
    }

    private void configuraBotaoSalvar() {
        View btnSalvar = findViewById(R.id.activity_formulario_aluno_botao_salvar);
        btnSalvar.setOnClickListener(view -> {
            finalizaFormulario();
        });
    }

    private void finalizaFormulario() {
        preencheAluno();
        if (aluno.temIdValido()) {
            dao.edita(aluno);
        } else {
            dao.salva(aluno);
        }
        finish();
    }

    private void preencheAluno() {
        aluno.setNome(lblNome.getText().toString());
        aluno.setTelefone(lblTelefone.getText().toString());
        aluno.setEmail(lblEmail.getText().toString());
    }

    private void inicializacaoDosCampos() {
        lblNome = findViewById(R.id.activity_formulario_aluno_nome);
        lblTelefone = findViewById(R.id.activity_formulario_aluno_telefone);
        lblEmail = findViewById(R.id.activity_formulario_aluno_email);
    }
}