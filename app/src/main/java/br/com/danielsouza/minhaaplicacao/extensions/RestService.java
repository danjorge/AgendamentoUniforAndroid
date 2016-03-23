package br.com.danielsouza.minhaaplicacao.extensions;

import android.support.v4.app.Fragment;

import com.squareup.okhttp.OkHttpClient;

import br.com.danielsouza.minhaaplicacao.interfaces.RestInterface;
import retrofit.RestAdapter;
import retrofit.client.OkClient;


/**
 * Created by daniel.souza on 22/02/2016.
 */
public class RestService extends Fragment{

    protected static RestInterface restInterface;

    public static RestInterface getRestInterface() {

        if (restInterface == null) {

            String url = "https://172.26.53.85:8080/AgendamentoUnifor/rest/solicitacao/json";

            RestAdapter restadapter = new RestAdapter.Builder()
                    .setClient(new OkClient(new OkHttpClient()))
                    .setEndpoint(url).build();

            restInterface = restadapter.create(RestInterface.class);
        }
        return restInterface;
    }
}
