package br.com.danielsouza.ssa.restImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import br.com.danielsouza.ssa.restInteface.RestInterface;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Created by Daniel Jorge on 22/02/2016.
 */
public class RestService {

    protected static RestInterface restInterface;

    public static RestInterface getRestInterface() {

        if (restInterface == null) {

            String url = "http://192.168.0.12:8080/AgendamentoUnifor/rest";

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")
                    .create();

            RestAdapter restadapter = new RestAdapter.Builder()
                    .setClient(new OkClient(new OkHttpClient()))
                    .setConverter(new GsonConverter(gson))
                    .setEndpoint(url).build();

            restInterface = restadapter.create(RestInterface.class);
        }
        return restInterface;
    }
}
