package br.com.caelum.cadastrocaelum.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Prova;

/**
 * Created by jefrsilva on 24/11/14.
 */
public class ListaProvasFragment extends Fragment {
    private ListView listViewProvas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutProvas = inflater.inflate(R.layout.provas_lista, container, false);
        listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas);

        Prova prova1 = new Prova("20/03/2012", "Matematica");
        prova1.setTopicos(Arrays.asList("Algebra linear", "Integral", "Diferencial"));

        Prova prova2 = new Prova("25/03/2012", "Portugues");
        prova2.setTopicos(Arrays.asList("Complemento nominal", "Oracoes Subordinadas"));

        List<Prova> provas = Arrays.asList(prova1, prova2);
        listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Prova selecionada = (Prova) adapterView.getItemAtPosition(posicao);
                Toast.makeText(getActivity(), "Prova selecionada: " + selecionada.toString(), Toast.LENGTH_LONG).show();
            }
        });

        return layoutProvas;
    }
}
