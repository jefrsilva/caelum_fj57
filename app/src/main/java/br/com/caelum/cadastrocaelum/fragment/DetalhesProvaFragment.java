package br.com.caelum.cadastrocaelum.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.caelum.cadastrocaelum.R;

/**
 * Created by jefrsilva on 24/11/14.
 */
public class DetalhesProvaFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layoutDetalhes = inflater.inflate(R.layout.provas_detalhe, container, false);
        return layoutDetalhes;
    }
}
