/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.controllers;

import com.matoosfe.systravel.entities.TipoCliente;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author martosfre
 */
//No-view-interface
@Stateless //EJB Sin estado @Stateful con estado (shopping cart)
public class TipoClienteFacade extends AbstractFacade<TipoCliente>{

    //Identifico cual es la unidad de persistencia
    @PersistenceContext(unitName = "systravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoClienteFacade() {
        super(TipoCliente.class);
    }
    
}
