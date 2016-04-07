package br.com.danielsouza.ssa.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel Jorge on 04/04/2016.
 */
public class SolicitacoesResponse {
    
    @SerializedName("solicitacao")
    List<Solicitacao> listaSolicitacao;

    public SolicitacoesResponse(List<Solicitacao> listaSolicitacao) {
        this.listaSolicitacao = listaSolicitacao;
    }

    public List<Solicitacao> getListaSolicitacao() {
        return listaSolicitacao;
    }

    public void setListaSolicitacao(List<Solicitacao> listaSolicitacao) {
        this.listaSolicitacao = listaSolicitacao;
    }
}
