/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mitocode.ejb;

import com.mitocode.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author odre
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {
    @PersistenceContext(unitName = "primePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    @Override
    public Usuario iniciarSesion(Usuario us){
       Usuario usuario = null;
        
        try {
            String consulta  = "FROM Usuario u WHERE u.usuario = ?1 and u.clave = ?2"; 
            Query query = em.createQuery(consulta);
            query.setParameter(1, us.getUsuario());
            query.setParameter(2, us.getClave());
            List<Usuario> lista = query.getResultList();
            if(!lista.isEmpty()){
                //en este caso solo retorna un elemento en la lista
                usuario = lista.get(0);
            }
            
        } catch (Exception e) {
            //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso","UsuarioFacade error"));
            throw e;
            
        }//finally{
            /*
            if you created it using EntityManagerFactory you will have 
            to close it no matter what framework you use
            */
            //em.close();
            
            /*if you obtained it using dependency injection (eg using EJE
            and @PersistenceContext Annotation) you should not close it by hand
            (AFAIK it will cause RuntimeException)
            */
        //}
        
        return usuario;
    }
}
