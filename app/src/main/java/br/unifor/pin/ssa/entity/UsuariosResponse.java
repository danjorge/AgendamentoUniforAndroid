package br.unifor.pin.ssa.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Classe responsavel por criar uma lista de usuarios trazidos de um servico
 * @author Daniel Jorge
 * Created by Daniel Jorge on 04/04/2016.
 */
public class UsuariosResponse {

    //Atributo Serializado que atribui o JSON do serviço ao objeto.
    @SerializedName("usuarios")
    List<Usuarios> usuarios;

    public UsuariosResponse(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuarios> usuarios) {
        this.usuarios = usuarios;
    }
}
