package br.com.caelum.cadastrocaelum.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import br.com.caelum.cadastrocaelum.R;
import br.com.caelum.cadastrocaelum.modelo.Prova;

/**
 * Created by jefrsilva on 24/11/14.
 */
public class DetalhesProvaFragment extends Fragment {
    private Prova prova;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutDetalhes = inflater.inflate(R.layout.provas_detalhe, container, false);

        if (getArguments() != null) {
            prova = (Prova) getArguments().getSerializable("prova");
        }

        if (prova != null) {
            TextView materia = (TextView) layoutDetalhes.findViewById(R.id.detalhe_prova_materia);
            TextView data = (TextView) layoutDetalhes.findViewById(R.id.detalhe_prova_data);
            ListView topicos = (ListView) layoutDetalhes.findViewById(R.id.detalhe_prova_topicos);

            materia.setText(prova.getMateria());
            data.setText(prova.getData());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, prova.getTopicos());
            topicos.setAdapter(adapter);
        }

        return layoutDetalhes;
    }
}
