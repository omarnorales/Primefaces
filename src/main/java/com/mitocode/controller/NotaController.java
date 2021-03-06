/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.CategoriaFacadeLocal;
import com.mitocode.ejb.NotaFacadeLocal;
import com.mitocode.model.Categoria;
import com.mitocode.model.Nota;
import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author odre
 */
@Named
@ViewScoped
public class NotaController  implements Serializable {
    
    @EJB
    private NotaFacadeLocal notaEJB;
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @Inject
    private Categoria categoria;
    
    @Inject
    private Nota nota;

    private List<Categoria> categorias;
    
    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    
    
    
    @PostConstruct
    public void init(){
        categorias = categoriaEJB.findAll();
    }
    
    public void registrar(){
        
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");        
            nota.setCategoria(categoria);
            nota.setPersona(us.getCodigo());
            notaEJB.create(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se Registró"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error!"));
        }
               
    }
    
        public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
}
