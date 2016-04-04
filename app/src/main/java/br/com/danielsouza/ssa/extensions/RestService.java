package br.com.danielsouza.ssa.extensions;

import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.net.HttpURLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPermission;
import javax.net.ssl.SSLSocketFactory;

import br.com.danielsouza.ssa.interfaces.RestInterface;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;


/**
 * Created by daniel.souza on 22/02/2016.
 */
public class RestService {

    protected static RestInterface restInterface;

    public static RestInterface getRestInterface() {

        if (restInterface == null) {

            String url = "http://192.168.30.221:8080/AgendamentoUnifor/rest";

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
