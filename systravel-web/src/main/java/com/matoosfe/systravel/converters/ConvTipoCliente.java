/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.converters;

import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.TipoCliente;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author martosfre
 */
@Named("convTipCli") //Soporte la @Inject
@RequestScoped
public class ConvTipoCliente implements Converter<TipoCliente> {

    @Inject
    private TipoClienteFacade adminTipoCliente;
    
    //Pantalla a la Bdd
    @Override
    public TipoCliente getAsObject(FacesContext fc, UIComponent uic, String idTipPac) {
        TipoCliente tipoPaciente = null;
        if(idTipPac != null){
            tipoPaciente = adminTipoCliente.buscarPorId(Integer.parseInt(idTipPac));
        }
        return tipoPaciente;
    }

    //Bdd a la Pantalla
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, TipoCliente tipoPac) {
        String idTipPac = "";
        if (tipoPac != null) {
            idTipPac = tipoPac.getIdTipcli().toString();
        }
        return idTipPac;
    }

}
