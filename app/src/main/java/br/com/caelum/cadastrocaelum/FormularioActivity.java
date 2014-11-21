package br.com.caelum.cadastrocaelum;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.extras.Extras;
import br.com.caelum.cadastrocaelum.modelo.Aluno;


public class FormularioActivity extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        Intent intent = getIntent();
        final Aluno alunoParaSerAlterado = (Aluno) intent.getSerializableExtra(Extras.ALUNO_SELECIONADO);

        this.helper = new FormularioHelper(this);

        Button botao = (Button) findViewById(R.id.botao);
        if (alunoParaSerAlterado != null) {
            botao.setText("Alterar");
            helper.colocaNoFormulario(alunoParaSerAlterado);
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDAO dao = new AlunoDAO(FormularioActivity.this);

                if (aluno.getId() == null) {
                    dao.insere(aluno);
                } else {
                    dao.altera(aluno);
                }
                dao.close();
                finish();
            }
        });
    }

}
