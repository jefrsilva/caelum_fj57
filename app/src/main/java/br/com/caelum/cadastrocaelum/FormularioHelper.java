package br.com.caelum.cadastrocaelum;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;

import br.com.caelum.cadastrocaelum.modelo.Aluno;

/**
 * Created by jefrsilva on 21/11/14.
 */
public class FormularioHelper {
    private Aluno aluno;
    private final EditText nome;
    private final EditText telefone;
    private final EditText endereco;
    private final EditText site;
    private final SeekBar nota;
    private final ImageView foto;

    public FormularioHelper(FormularioActivity activity) {
        this.aluno = new Aluno();

        nome = (EditText) activity.findViewById(R.id.nome);
        telefone = (EditText) activity.findViewById(R.id.telefone);
        endereco = (EditText) activity.findViewById(R.id.endereco);
        site = (EditText) activity.findViewById(R.id.site);
        nota = (SeekBar) activity.findViewById(R.id.nota);
        foto = (ImageView) activity.findViewById(R.id.foto);
    }

    public Aluno pegaAlunoDoFormulario() {
        aluno.setNome(nome.getText().toString());
        aluno.setTelefone(telefone.getText().toString());
        aluno.setEndereco(endereco.getText().toString());
        aluno.setSite(site.getText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));

        return aluno;
    }

    public void colocaNoFormulario(Aluno aluno) {
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());
        if (aluno.getCaminhoFoto() != null) {
            carregaImagem(aluno.getCaminhoFoto());
        }

        this.aluno = aluno;
    }

    public ImageView getFoto() {
        return foto;
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, 100, 100, true);
        aluno.setCaminhoFoto(localArquivoFoto);
        foto.setImageBitmap(imagemFotoReduzida);
    }
}
