package br.unifor.pin.ssa.entity;

import java.io.Serializable;

/**
 * Entidade Usuarios
 * @author Daniel Jorge
 * Created by Daniel Jorge on 27/01/2016.
 */
public class Usuarios implements Serializable {

    private Integer id;
    private String nome;
    private String matricula;
    private String senha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
