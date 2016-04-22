package br.unifor.pin.ssa.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Entidade Agendamento
 * @author Daniel Jorge
 * Created by Daniel Jorge on 29/01/2016.
 */
public class Agendamento implements Serializable{

    private Integer idEvent;
    private String titulo;
    private String dscAgendamentoEvent;
    private Date dataInicio;
    private Date dataFim;
    private Solicitacao solicitacao;
    private Status statusAgendamento;

    public Agendamento(Integer idEvent, String dscAgendamentoEvent, Date dataInicio, Date dataFim, Solicitacao solicitacao, Status statusAgendamento) {
        this.idEvent = idEvent;
        this.dscAgendamentoEvent = dscAgendamentoEvent;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.solicitacao = solicitacao;
        this.statusAgendamento = statusAgendamento;
    }

    public Integer getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Integer idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDscAgendamentoEvent() {
        return dscAgendamentoEvent;
    }

    public void setDscAgendamentoEvent(String dscAgendamentoEvent) {
        this.dscAgendamentoEvent = dscAgendamentoEvent;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public Solicitacao getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(Solicitacao solicitacao) {
        this.solicitacao = solicitacao;
    }

    public Status getStatusAgendamento() {
        return statusAgendamento;
    }

    public void setStatusAgendamento(Status statusAgendamento) {
        this.statusAgendamento = statusAgendamento;
    }
}
