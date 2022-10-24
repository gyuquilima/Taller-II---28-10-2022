/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.converters;

import com.matoosfe.systravel.controllers.AbstractFacade;
import com.matoosfe.systravel.controllers.ClienteFacade;
import com.matoosfe.systravel.entities.Cliente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convCliente") //Soporte la @Inject
@RequestScoped
public class ConvCliente extends ConvGenerico<Cliente> {

    @Inject
    private ClienteFacade adminCliente;

    @Override
    protected AbstractFacade<Cliente> getAdminGenerico() {
       return adminCliente;
    }

}
