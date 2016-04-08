package br.com.danielsouza.ssa.restInteface;


import br.com.danielsouza.ssa.entity.AgendamentoResponse;
import br.com.danielsouza.ssa.entity.SolicitacoesResponse;
import br.com.danielsouza.ssa.entity.UsuariosResponse;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Daniel Jorge on 19/02/2016.
 */
public interface RestInterface {

    @GET("/solicitacao/{matricula}/json")
    void getSolicitacaoJSON(@Path("matricula") String matricula, Callback<SolicitacoesResponse> callback);

    @GET("/agendamento/json")
    void getAgendamentoJSON(Callback<AgendamentoResponse> callback);

    @GET("/login/json")
    void getUsuariosJson(Callback<UsuariosResponse> callback);

}
