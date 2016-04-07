package br.com.danielsouza.ssa.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Salviano on 04/04/2016.
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
