package br.com.caelum.cadastrocaelum.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Aluno;

/**
 * Created by jefrsilva on 21/11/14.
 */
public class ListaAlunosAdapter extends BaseAdapter {
    private Activity activity;
    private List<Aluno> alunos;

    public ListaAlunosAdapter(Activity activity, List<Aluno> alunos) {
        this.activity = activity;
        this.alunos = alunos;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return alunos.get(i).getId();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater().inflate(R.layout.item, null);

        Aluno aluno = (Aluno) getItem(posicao);
        ImageView foto = (ImageView) view.findViewById(R.id.foto);
        Bitmap bm = null;
        if (aluno.getCaminhoFoto() != null) {
            bm = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
        } else {
            bm = BitmapFactory.decodeResource(activity.getResources(), R.drawable.ic_launcher);
        }
        bm = Bitmap.createScaledBitmap(bm, 100, 100, true);
        foto.setImageBitmap(bm);

        TextView nome = (TextView) view.findViewById(R.id.nome);
        nome.setText(aluno.getNome());

        if (posicao % 2 == 0) {
            view.setBackgroundColor(activity.getResources().getColor(R.color.linha_par));
        }

        TextView telefone = (TextView) view.findViewById(R.id.telefone);
        if (telefone != null) {
            telefone.setText(aluno.getTelefone());
        }

        TextView site = (TextView) view.findViewById(R.id.site);
        if (site != null) {
            site.setText(aluno.getSite());
        }

        return view;
    }
}
