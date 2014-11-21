package br.com.caelum.cadastrocaelum;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.cadastrocaelum.adapter.ListaAlunosAdapter;
import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.extras.Extras;
import br.com.caelum.cadastrocaelum.modelo.Aluno;


public class ListaAlunosActivity extends Activity {

    private ListView listaAlunos;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_alunos);

        this.listaAlunos = (ListView) findViewById(R.id.lista_alunos);

        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View convertView, int posicao, long id) {
                Intent edicao = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(posicao);
                edicao.putExtra(Extras.ALUNO_SELECIONADO, aluno);
                startActivity(edicao);
            }
        });

        listaAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View convertView, int posicao, long id) {
                Aluno aluno = (Aluno) adapterView.getItemAtPosition(posicao);
                Toast.makeText(ListaAlunosActivity.this, "Clique longo: " + aluno.getNome(), Toast.LENGTH_LONG).show();
                return false;
            }
        });

        registerForContextMenu(listaAlunos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.carregaLista();
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        alunos = dao.getLista();
        dao.close();
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this, alunos);
        listaAlunos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_novo:
                Intent intent = new Intent(this, FormularioActivity.class);
                startActivity(intent);
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_aluno, menu);
        AdapterView.AdapterContextMenuInfo adapterMenuInfo = (AdapterView.AdapterContextMenuInfo) menuInfo;
        Aluno alunoSelecionado = (Aluno) listaAlunos.getItemAtPosition(adapterMenuInfo.position);

        MenuItem itemLigar = menu.findItem(R.id.ligar);
        Intent ligar = new Intent(Intent.ACTION_CALL);
        ligar.setData(Uri.parse("tel:" + alunoSelecionado.getTelefone()));
        itemLigar.setIntent(ligar);

        MenuItem itemSms = menu.findItem(R.id.sms);
        Intent sms = new Intent(Intent.ACTION_VIEW);
        sms.setData(Uri.parse("sms:" + alunoSelecionado.getTelefone()));
        sms.putExtra("sms_body", "Mensagem");
        itemSms.setIntent(sms);

        String siteDoAluno = alunoSelecionado.getSite();
        if (!siteDoAluno.startsWith("http:")) {
            siteDoAluno = "http:" + alunoSelecionado.getSite();
        }

        MenuItem itemSite = menu.findItem(R.id.site);
        Intent site = new Intent(Intent.ACTION_VIEW);
        site.setData(Uri.parse("http:" + siteDoAluno));
        itemSite.setIntent(site);

        MenuItem itemMapa = menu.findItem(R.id.mapa);
        Intent mapa = new Intent(Intent.ACTION_VIEW);
        mapa.setData(Uri.parse("geo:0,0?z=14&q=" + Uri.encode(alunoSelecionado.getEndereco())));
        itemMapa.setIntent(mapa);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deletar:
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(menuInfo.position);
                AlunoDAO dao = new AlunoDAO(this);
                dao.deletar(aluno);
                dao.close();
                carregaLista();
                return true;
            default:
                return false;
        }
    }
}
