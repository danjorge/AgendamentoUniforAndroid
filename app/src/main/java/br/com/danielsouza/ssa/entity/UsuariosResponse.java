package br.com.danielsouza.ssa.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Daniel Jorge on 04/04/2016.
 */
public class UsuariosResponse {

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
