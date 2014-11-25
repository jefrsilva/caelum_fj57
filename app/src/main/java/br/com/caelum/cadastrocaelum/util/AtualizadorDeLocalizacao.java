package br.com.caelum.cadastrocaelum.util;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;

import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.LatLng;

import br.com.caelum.cadastrocaelum.fragment.MapaFragment;

/**
 * Created by jefrsilva on 25/11/14.
 */
public class AtualizadorDeLocalizacao implements GooglePlayServicesClient.ConnectionCallbacks, LocationListener {
    private final MapaFragment mapa;
    private LocationClient client;

    public AtualizadorDeLocalizacao(Context context, MapaFragment mapa) {
        this.mapa = mapa;
        this.client = new LocationClient(context, this, null);
        this.client.connect();
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();

        LatLng local = new LatLng(latitude, longitude);
        mapa.centralizaNo(local);
    }

    @Override
    public void onConnected(Bundle bundle) {
        LocationRequest request = LocationRequest.create();
        request.setInterval(2000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setSmallestDisplacement(50);
        this.client.requestLocationUpdates(request, this);
    }

    @Override
    public void onDisconnected() {

    }

    public void cancela() {
        this.client.removeLocationUpdates(this);
        this.client.disconnect();
    }
}
