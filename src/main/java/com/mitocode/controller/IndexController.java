/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.UsuarioFacadeLocal;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author odre
 */
@Named
@ViewScoped
public class IndexController  implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal EJBUsuario;
    private Usuario usuario;
    
    @PostConstruct
    public void init(){
        setUsuario(new Usuario());
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String iniciarSesion(){
        
        Usuario us;
        String redireccion = null;
        
        try {
            us=EJBUsuario.iniciarSesion(usuario);
            
            if(us !=null){
                //Almacenar en sesion JSF
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                
                //Si los datos son correctos redirige a principal.xhtml
                //esta es una navegacion implicita
                //redireccion = "/protegido/principal";
                
                //De ser necesaria una nevegacion explicita
                String parseToExplito = "?faces-redirect=true";
                redireccion = "/protegido/principal?";
                redireccion = redireccion+parseToExplito;
            }else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","Credenciales incorrectas"));
            }
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Error!"));
        }
        
        return redireccion;
    }
}
