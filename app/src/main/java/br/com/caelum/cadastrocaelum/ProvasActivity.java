package br.com.caelum.cadastrocaelum;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import br.com.caelum.cadastrocaelum.fragment.ListaProvasFragment;


public class ProvasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provas);

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.provas_view, new ListaProvasFragment());
        transaction.commit();
    }

}
