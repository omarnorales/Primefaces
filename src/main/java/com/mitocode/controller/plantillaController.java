/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.model.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author odre
 */
@Named
@ViewScoped
public class plantillaController  implements Serializable {
    
    public void verificarSesion(){
        try {
            
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            
            if(us == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../permisos.xhtml");
            }
        } catch (Exception e) {
            //log para registrsode error
        }
    }
    
}
