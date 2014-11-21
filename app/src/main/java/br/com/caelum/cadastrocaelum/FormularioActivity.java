package br.com.caelum.cadastrocaelum;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastrocaelum.modelo.Aluno;


public class FormularioActivity extends Activity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario);

        this.helper = new FormularioHelper(this);

        Button botao = (Button) findViewById(R.id.botao);
        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Aluno aluno = helper.pegaAlunoDoFormulario();
                Toast.makeText(FormularioActivity.this, "Aluno preenchido: " + aluno.getNome(), Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }

}
