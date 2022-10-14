/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.controllers;

import com.matoosfe.sysmed.entities.Centro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 *
 * @author gyuquilima
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class CentroFacade extends AbstractFacade<Centro>{

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "sysmedPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CentroFacade() {
        super(Centro.class);
    }
    
}
