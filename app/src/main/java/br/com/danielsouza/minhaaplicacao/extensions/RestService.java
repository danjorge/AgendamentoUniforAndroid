package br.com.danielsouza.minhaaplicacao.extensions;

import android.support.v4.app.Fragment;

import br.com.danielsouza.minhaaplicacao.interfaces.RestInterface;
import retrofit.RestAdapter;

/**
 * Created by daniel.souza on 22/02/2016.
 */
public class RestService extends Fragment{

    String url = "http://localhost:8080/AgendamentoUnifor/rest/solicitacao";

    RestAdapter restadapter = new RestAdapter.Builder().setEndpoint(url).build();

    protected RestInterface restInterface = restadapter.create(RestInterface.class);
}
