package br.com.danielsouza.minhaaplicacao.extensions;

import android.support.v4.app.Fragment;

import br.com.danielsouza.minhaaplicacao.interfaces.RestInterface;
import retrofit.RestAdapter;


/**
 * Created by daniel.souza on 22/02/2016.
 */
public class RestService extends Fragment{

    protected static RestInterface restInterface;

    public static RestInterface getRestInterface() {

        if (restInterface == null) {

            String url = "http://localhost:8080/AgendamentoUnifor/rest/solicitacao/json";

            RestAdapter restadapter = new RestAdapter.Builder().setEndpoint(url).build();

            restInterface = restadapter.create(RestInterface.class);
        }
        return restInterface;
    }
}
