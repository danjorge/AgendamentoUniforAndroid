package br.com.danielsouza.minhaaplicacao.interfaces;



import br.com.danielsouza.minhaaplicacao.entity.Solicitacao;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by daniel.souza on 19/02/2016.
 */
public interface RestInterface {

    @GET("/89/json")
    void getSolicitacaoByIdJSON(Callback<Solicitacao> callBack);

}
