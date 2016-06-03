package br.unifor.pin.ssa.entity;

import android.graphics.Bitmap;

import java.io.File;
import java.util.Date;

/**
 * Created by Daniel on 10/05/2016.
 */
public class Anexos {

    private Integer id;
    private Date datAnexo;
    private boolean flgAtivo;
    private String txtDiretorio;
    private String txtNomeArquivo;
    private Usuarios usuarios;
    private String photo;

    public Anexos() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatAnexo() {
        return datAnexo;
    }

    public void setDatAnexo(Date datAnexo) {
        this.datAnexo = datAnexo;
    }

    public boolean isFlgAtivo() {
        return flgAtivo;
    }

    public void setFlgAtivo(boolean flgAtivo) {
        this.flgAtivo = flgAtivo;
    }

    public String getTxtDiretorio() {
        return txtDiretorio;
    }

    public void setTxtDiretorio(String txtDiretorio) {
        this.txtDiretorio = txtDiretorio;
    }

    public String getTxtNomeArquivo() {
        return txtNomeArquivo;
    }

    public void setTxtNomeArquivo(String txtNomeArquivo) {
        this.txtNomeArquivo = txtNomeArquivo;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
