package br.unifor.pin.ssa.restImpl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import br.unifor.pin.ssa.restInteface.RestInterface;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Classe responsavel por instanciar o framework Retrofit de forma unica (Singleton)
 * @author Daniel Jorge
 * Created by Daniel Jorge on 22/02/2016.
 */
public class RestService {

    protected static RestInterface restInterface;

    public static final String URL_PERFIL = "http://52.39.99.15:8080/AgendamentoUnifor/resources/img/perfil/";

    public static RestInterface getRestInterface() {

        if (restInterface == null) {

            /**
             * URL do servico
             */
            String url = "http://52.39.99.15:8080/AgendamentoUnifor/rest";

            /**
             * Instancia da biblioteca GSON do google para conversao de JSON
             */
            Gson gson = new GsonBuilder()
                    .setDateFormat("dd/MM/yyyy HH:mm")
                    .create();

            /**
             * Instancia do framework Retrofit
             */
            RestAdapter restadapter = new RestAdapter.Builder()
                    .setClient(new OkClient(new OkHttpClient()))
                    .setConverter(new GsonConverter(gson))
                    .setEndpoint(url).build();

            /**
             * Criacao da interface do servico
             */
            restInterface = restadapter.create(RestInterface.class);
        }
        return restInterface;
    }
}
