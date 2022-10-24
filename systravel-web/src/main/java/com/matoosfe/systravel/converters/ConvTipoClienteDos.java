/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.converters;

import com.matoosfe.systravel.controllers.AbstractFacade;
import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.TipoCliente;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convTipPacDos") //Soporte la @Inject
@RequestScoped
public class ConvTipoClienteDos extends ConvGenerico<TipoCliente> {

    @Inject
    private TipoClienteFacade adminTipoPaciente;

    @Override
    protected AbstractFacade<TipoCliente> getAdminGenerico() {
       return adminTipoPaciente;
    }

}
