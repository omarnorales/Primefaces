/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.NotaFacadeLocal;
import com.mitocode.model.Nota;
import java.io.Serializable;
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
public class ValorarController implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    
    @Inject
    private ComentarController comentarController;

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    private Nota nota;
    
    @PostConstruct
    public void init(){
        this.nota = comentarController.getNota();
    }
    
    public void registrar(){
        
        try {
            notaEJB.edit(nota);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se Comentó perfectamente"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error","No se comento"));
        }finally{
            //en Fanally permite guardar el estado del mensaje para navegacion entre paginas con FlashScope
            //con inyeccion de dependencias
            FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
            
        }
        
    }
}
