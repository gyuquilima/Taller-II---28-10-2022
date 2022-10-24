/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.controllers;

import com.matoosfe.systravel.entities.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author martosfre
 */
@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

    @PersistenceContext(unitName = "systravelPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }

    /**
     * Método para buscar un paciente por identificación o su apellido paterno
     *
     * @param identificacionApellido
     * @return
     */
    public List<Cliente> buscarIdentificacionApellido(String identificacionApellido) {
        Query conPac = em.createNativeQuery("select p.*\n"
                + "from cliente p \n"
                + "where p.identificacion_cli like ?1 or \n"
                + "      p.apellido_paterno_cli  like ?1 or\n"
                + "      p.apellido_materno_cli like ?1", Cliente.class);
        conPac.setParameter(1, "%" + identificacionApellido + "%");
        return conPac.getResultList();
    }

}
