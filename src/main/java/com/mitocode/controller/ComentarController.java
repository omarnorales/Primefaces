/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.controller;

import com.mitocode.ejb.NotaFacadeLocal;
import com.mitocode.model.Nota;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author odre
 * @ViewScoped se cambia por un @RequestScoped
 * debido a que vamos a inbocar consulta de BD
 * debido a que es una inyeccion por CDI
 */
@Named
@RequestScoped
public class ComentarController  implements Serializable{
    
    @EJB
    private NotaFacadeLocal notaEJB;
    private List<Nota> notas;

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }
    private Nota nota;

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }
    
    @PostConstruct
    public void init(){
        notas = notaEJB.findAll();
    }
    
    public void asignar(Nota nota){
        this.nota = nota;
    }
    
}
