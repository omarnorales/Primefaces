/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.CategoriaFacadeLocal;
import com.mitocode.model.Categoria;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author odre
 */
@Named
@ViewScoped
public class CategoriaController implements Serializable{
    //Aqio se inyecta el ejb
    @EJB
    private CategoriaFacadeLocal categoriaEJB;
    
    @Inject
    private Categoria categoria;
    
    
    
    @PostConstruct
    public void init(){
        /*
        //no necesario inicializar de esta forma si se emplean las DCI
        //Inyecciones de Dependencia(CDI), solamente implementamos
        //la notacion @Inject en la instancia de objeto deseada
        //acompañada de la creacion de un archivo bean.xlm en WEB-INF
        //deltro del bean.xml cambiamos bean-discovery-mode="all"
        */
        
        
        
    }
    
    public void registrar(){
        try {
            categoriaEJB.create(getCategoria());
        } catch (Exception e) {
        }
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
}
