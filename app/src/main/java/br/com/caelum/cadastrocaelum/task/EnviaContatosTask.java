package br.com.caelum.cadastrocaelum.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastrocaelum.converter.AlunoConverter;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.modelo.Aluno;
import br.com.caelum.cadastrocaelum.support.WebClient;

/**
 * Created by jefrsilva on 24/11/14.
 */
public class EnviaContatosTask extends AsyncTask<Object, Object, String> {
    private final String endereco = "http://www.caelum.com.br/mobile";
    private final Context context;
    private ProgressDialog progress;

    public EnviaContatosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);
    }

    @Override
    protected String doInBackground(Object... objects) {
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.getLista();
        dao.close();

        AlunoConverter converter = new AlunoConverter();
        String json = converter.toJSON(alunos);

        WebClient client = new WebClient(endereco);
        String resposta = client.post(json);
        return resposta;
    }

    @Override
    protected void onPostExecute(String result) {
        progress.dismiss();
        Toast.makeText(context, result, Toast.LENGTH_LONG).show();
    }
}
