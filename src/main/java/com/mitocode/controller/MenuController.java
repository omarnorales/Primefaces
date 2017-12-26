/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.MenuFacadeLocal;
import com.mitocode.model.Menu;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 *
 * @author odre
 */

//@ViewScoped: no tiene valores de sesion en memoria
//@SessionScoped es porque se va llamar en cada pagina y no seria
//conveiente  hacer consulta para algo q no va variar, por tanto no 
//seria combieniente estar pidiendolo constantemente(base de datos)
@Named
@SessionScoped
public class MenuController implements Serializable {
    
    @EJB
    private MenuFacadeLocal EJBMenu;
    private List<Menu> lista;
    private MenuModel model;

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }
    
    @PostConstruct
    public void init(){
        this.listarMenus();
        model= new DefaultMenuModel();
        this.establecerPermisos();
    }
    
    public void listarMenus(){
        try {
            lista = EJBMenu.findAll();
        } catch (Exception e) {
            //mensaje jsf
        }
        
    }
    
    public void establecerPermisos(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        
        System.out.print("");
        
        for (Menu m : lista) {
            
            if(m.getTipo().equals("S") && m.getTipoUsuario().equals(us.getTipo()) ){
                DefaultSubMenu firstSubmenu = new DefaultSubMenu(m.getNombre());
                for (Menu i : lista) {
                    Menu submenu = i.getSubmenu();
                    if(submenu != null){
                        if(submenu.getCodigo() == m.getCodigo()){
                            DefaultMenuItem item = new DefaultMenuItem(i.getNombre());
                            item.setUrl(i.getUrl());
                            firstSubmenu.addElement(item);
                        }
                    }
                }
                model.addElement(firstSubmenu);
            }else{
                if((m.getSubmenu() == null) && m.getTipoUsuario().equals(us.getTipo())){
                    DefaultMenuItem item = new DefaultMenuItem(m.getNombre());
                    item.setUrl(m.getUrl());
                    model.addElement(item);
                }
                
            }            
        }
    }
    
    public void cerrarSesion(){
        //destruimos los valores que teniamos almacenados en el FacesContext
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public String mostrarUsuarioLogeado(){
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return us.getUsuario();
    }
    
}
