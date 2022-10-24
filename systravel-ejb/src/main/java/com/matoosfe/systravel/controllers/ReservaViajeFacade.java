/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.controllers;

import com.matoosfe.systravel.entities.ReservaViaje;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author martosfre
 */
@Stateless
public class ReservaViajeFacade extends AbstractFacade<ReservaViaje>{
    
    @PersistenceContext(unitName = "systravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ReservaViajeFacade() {
        super(ReservaViaje.class);
    }

    /**
     * Método para buscar las historias clínicas por su número.
     * Se utilizará JPQL (Sql de objetos)
     * @param numeroHistoria
     * @return 
     */
    public List<ReservaViaje> buscarPorNumero(String numeroHistoria) {
        TypedQuery<ReservaViaje> conHisNum = em.createQuery("Select his from ReservaViaje his "
                + " where his.numeroHistoriaClinica like :numero", ReservaViaje.class);
        conHisNum.setParameter("numero", numeroHistoria + "%");
        return conHisNum.getResultList();
    }
   
    
}
