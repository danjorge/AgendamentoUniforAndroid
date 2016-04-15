package br.com.danielsouza.ssa.entity;

import java.io.Serializable;

/**
 * Entidade solicitacao
 * @author Daniel Jorge
 * Created by Daniel Jorge on 27/01/2016.
 */
public class Solicitacao implements Serializable{

    private Integer id;
    private String assunto;
    private String descricao;
    private String respostaSolicitacao;
    private String segundaRespostaSolicitacao;
    private Status statusSolicitacao;
    private Usuarios usuario;

    public Solicitacao() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRespostaSolicitacao() {
        return respostaSolicitacao;
    }

    public void setRespostaSolicitacao(String respostaSolicitacao) {
        this.respostaSolicitacao = respostaSolicitacao;
    }

    public String getSegundaRespostaSolicitacao() {
        return segundaRespostaSolicitacao;
    }

    public void setSegundaRespostaSolicitacao(String segundaRespostaSolicitacao) {
        this.segundaRespostaSolicitacao = segundaRespostaSolicitacao;
    }

    public Status getStatusSolicitacao() {
        return statusSolicitacao;
    }

    public void setStatusSolicitacao(Status statusSolicitacao) {
        this.statusSolicitacao = statusSolicitacao;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
}
