/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.UsuarioFacadeLocal;
import com.mitocode.model.Persona;
import com.mitocode.model.Usuario;
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
public class UsuarioController implements Serializable{
    
    @EJB
    private UsuarioFacadeLocal usuarioEjB;
    
    @Inject
    private Usuario usuario;
    @Inject
    private Persona persona;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void registrar(){
        
        try {
            this.usuario.setCodigo(persona);
            usuarioEjB.create(usuario);
            //mostrar mensaje
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso","Se registró"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","Se registró"));
        }
    }
    
    //notacion @PostConstruct se ejecuta hsata q el constructor es inicializado 
    //aunque en este ejemplo no se esta definiendo
    @PostConstruct
    public void init(){
        
        /*
        //no necesario inicializar de esta forma si se emplean las DCI
        //Inyecciones de Dependencia(CDI), solamente implementamos
        //la notacion @Inject en la instancia de objeto deseada
        //acompañada de la creacion de un archivo bean.xlm  en WEB-INF
        //deltro del bean.xml cambiamos bean-discovery-mode="all"
        usuario = new Usuario();
        persona = new Persona();
        */
    }
}
