package br.unifor.pin.ssa.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Classe responsavel por criar uma lista de agendamentos trazidos de um servico
 * @author Daniel Jorge
 * Created by Daniel Jorge on 04/04/2016.
 */
public class AgendamentoResponse {

    @SerializedName("agendamento")
    List<Agendamento> listaAgendamento;

    public AgendamentoResponse(List<Agendamento> listaAgendamento) {
        this.listaAgendamento = listaAgendamento;
    }

    public List<Agendamento> getListaAgendamento() {
        return listaAgendamento;
    }

    public void setListaAgendamento(List<Agendamento> listaAgendamento) {
        this.listaAgendamento = listaAgendamento;
    }
}
