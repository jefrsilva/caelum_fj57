package br.com.caelum.cadastrocaelum;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import br.com.caelum.cadastrocaelum.fragment.DetalhesProvaFragment;
import br.com.caelum.cadastrocaelum.fragment.ListaProvasFragment;


public class ProvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        if (isTablet()) {
            transaction
                    .replace(R.id.provas_lista, new ListaProvasFragment())
                    .replace(R.id.provas_view, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }
        transaction.commit();
    }

    public boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }


}
