/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author odre
 */
@Entity
@Table(name = "nota")
public class Nota implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int codigo;
    
    @ManyToOne
    @JoinColumn(name = "codigo_persona", nullable = false)
    private  Persona persona;
    
    @ManyToOne
    @JoinColumn(name = "codigo_categoria", nullable = false)
    private Categoria categoria;
    
    @Column(name = "encabezado")
    private String encabezado;
    
    @Column(name = "cuerpo")
    private String cuerpo;
    
    @Temporal(TemporalType.TIMESTAMP )
    @Column(name = "fecha",insertable = false)
    private Date fecha;
    
    @Column(name = "comentarioAdmin")
    private String comentarioAdmin;
    
    @Column(name = "valorizacion",insertable = false)
    private Integer valorizacion ;        

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the encabezado
     */
    public String getEncabezado() {
        return encabezado;
    }

    /**
     * @param encabezado the encabezado to set
     */
    public void setEncabezado(String encabezado) {
        this.encabezado = encabezado;
    }

    /**
     * @return the cuerpo
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * @param cuerpo the cuerpo to set
     */
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the comentarioAdmin
     */
    public String getComentarioAdmin() {
        return comentarioAdmin;
    }

    /**
     * @param comentarioAdmin the comentarioAdmin to set
     */
    public void setComentarioAdmin(String comentarioAdmin) {
        this.comentarioAdmin = comentarioAdmin;
    }

    /**
     * @return the valorizacion
     */
    public Integer getValorizacion() {
        return valorizacion;
    }

    /**
     * @param valorizacion the valorizacion to set
     */
    public void setValorizacion(Integer valorizacion) {
        this.valorizacion = valorizacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.codigo;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Nota other = (Nota) obj;
        if (this.codigo != other.codigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Nota{" + "codigo=" + codigo + '}';
    }
    
    
}
