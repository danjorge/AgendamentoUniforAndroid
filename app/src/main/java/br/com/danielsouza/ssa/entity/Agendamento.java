package br.com.danielsouza.ssa.entity;

import java.util.Date;

/**
 * Created by daniel.souza on 29/01/2016.
 */
public class Agendamento {

    private Integer id;
    private String dscAgendamento;
    private Date dataInicio;
    private Date dataFim;
    private Solicitacao solicitacao;
    private Status statusAgendamento;

    public Agendamento(Integer id, String dscAgendamento, Date dataInicio, Date dataFim, Solicitacao solicitacao, Status statusAgendamento) {
        this.id = id;
        this.dscAgendamento = dscAgendamento;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.solicitacao = solicitacao;
        this.statusAgendamento = statusAgendamento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDscAgendamento() {
        return dscAgendamento;
    }

    public void setDscAgendamento(String dscAgendamento) {
        this.dscAgendamento = dscAgendamento;
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
