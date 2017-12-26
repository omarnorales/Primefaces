/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author odre
 */
@Entity
@Table(name = "menu")
public class Menu implements Serializable {
    
    @Id
    @Column(name = "codigo")
    private Short codigo;
    
    @Column(name = "nombre")
    private String nombre;
    
    @Column(name = "tipo")
    private String tipo;
    
    @Column(name = "tipoUsuario")
    private String tipoUsuario;
    
    @Column(name = "estado")
    private boolean estado = true;
    
    @Column(name = "url")
    private String url ;
    
    @ManyToOne
    @JoinColumn(name = "codigo_submenu")
    private Menu submenu;

    public Menu() {
    }

    public Menu(Short codigo) {
        this.codigo = codigo;
    }

    public Menu(Short codigo, String nombre, String tipo, String tipoUsuario, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
    }

    public Short getCodigo() {
        return codigo;
    }

    public void setCodigo(Short codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public void setUrl(String url){
        this.url = url;
    }
    
    
    public String getUrl(){
        return url;
    }

    public Menu getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Menu Submenu) {
        this.submenu = Submenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mitocode.model.Menu[ codigo=" + codigo + " ]";
    }
    
}
