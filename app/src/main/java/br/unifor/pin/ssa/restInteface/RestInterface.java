package br.unifor.pin.ssa.restInteface;


import java.util.List;

import br.unifor.pin.ssa.entity.Agendamento;
import br.unifor.pin.ssa.entity.Solicitacao;
import br.unifor.pin.ssa.entity.Usuarios;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * classe que contem os metodos necessarios para a utilizacao do servico
 * Created by Daniel Jorge on 19/02/2016.
 */
public interface RestInterface {

    /**
     * Metodo responsavel por retornar (via JSON) todas as solicitacoes cadastradas para o curso do usuario.
     * Utiliza metodo GET com endpoint do servico
     * @param matricula
     * @param callback
     */
    @GET("/solicitacao/{matricula}/json")
    void getSolicitacaoJSON(@Path("matricula") String matricula, Callback<List<Solicitacao>> callback);

    /**
     * Metodo responsavel por retornar (via JSON) todos os agendamentos cadastrados para o usuario.
     * Utiliza matodo GET com endpoint do serviao
     * @param callback
     */
    @GET("/agendamento/{matricula}/json")
    void getAgendamentoJSON(@Path("matricula") String matricula, Callback<List<Agendamento>> callback);

    /**
     * Metodo responsavel por retornar (via JSON) uma lista de usuarios.
     * Utiliza matodo GET com endpoint do servico
     * @param callback
     */
    @GET("/login/json")
    void getUsuariosJson(Callback<List<Usuarios>> callback);

    /**
     * Metodo responsavel por salvar uma solicitacao enviando o objeto (solicitacao) no corpo da requisicao via POST.
     * Utiliza metodo post com com endpoint do servico
     * @param solicitacao
     * @param callback
     */
    @POST("/solicitacao/salvarSolicitacao")
    void salvarSolicitacao(@Body Solicitacao solicitacao, Callback<Solicitacao> callback);

}
