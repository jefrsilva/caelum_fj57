package br.com.caelum.cadastrocaelum.fragment;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.cadastrocaelum.dao.AlunoDAO;
import br.com.caelum.cadastrocaelum.modelo.Aluno;
import br.com.caelum.cadastrocaelum.util.Localizador;

/**
 * Created by jefrsilva on 25/11/14.
 */
public class MapaFragment extends SupportMapFragment {
    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());
        LatLng local = localizador.getCoordenada("Rua Vergueiro 3185, Vila Mariana, São Paulo");

        centralizaNo(local);

        AlunoDAO dao = new AlunoDAO(getActivity());
        List<Aluno> alunos = dao.getLista();
        dao.close();
        for (Aluno aluno : alunos) {
            local = localizador.getCoordenada(aluno.getEndereco());
            MarkerOptions marcador = new MarkerOptions();
            marcador.title(aluno.getNome()).position(local);
            getMap().addMarker(marcador);
        }
    }

    private void centralizaNo(LatLng local) {
        GoogleMap mapa = getMap();
        CameraUpdate camera = CameraUpdateFactory.newLatLngZoom(local, 17);
        mapa.moveCamera(camera);
    }
}
