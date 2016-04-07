package br.com.danielsouza.ssa.restInteface;


import br.com.danielsouza.ssa.entity.AgendamentoResponse;
import br.com.danielsouza.ssa.entity.SolicitacoesResponse;
import br.com.danielsouza.ssa.entity.UsuariosResponse;
import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by daniel.souza on 19/02/2016.
 */
public interface RestInterface {

    @GET("/solicitacao/json")
    void getSolicitacaoJSON(Callback<SolicitacoesResponse> callBack);

    @GET("/agendamento/json")
    void getAgendamentoJSON(Callback<AgendamentoResponse> callback);

    @GET("/login/json")
    void getUsuariosJson(Callback<UsuariosResponse> callback);

}
