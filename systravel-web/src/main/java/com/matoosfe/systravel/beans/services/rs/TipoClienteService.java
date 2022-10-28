/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.beans.services.rs;

import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.TipoCliente;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 *
 * @author gyuquilima
 */
@Path("/tipoCliente")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class TipoClienteService {
    @Inject
    private TipoClienteFacade adminTipoCliente;

    @GET
    @Path("/{id}")
    public TipoCliente consultarPorId(@PathParam(value = "id") int idTipCli) {
        return adminTipoCliente.buscarPorId(idTipCli);
    }

    @GET
    @Path("/consultarTodos")
    public List<TipoCliente> consultarTodos() {
        return adminTipoCliente.buscarTodos();
    }

    @POST
    public String guardar(TipoCliente tipoCliente) {
        try {
            adminTipoCliente.guardar(tipoCliente);
            return "Tipo Cliente guardado correctamente";
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    @PUT
    public String actualizar(TipoCliente tipoCliente) {
        try {
            adminTipoCliente.actualizar(tipoCliente);
            return "Tipo Cliente actualizado correctamente";
        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    @DELETE
    @Path("/{id}")
    public String eliminar(@PathParam(value = "id")int idTipCli) {
        try {
            TipoCliente tipoCliente = adminTipoCliente.buscarPorId(idTipCli);
            adminTipoCliente.eliminar(tipoCliente);
            return "Tipo Cliente eliminado correctamente";
        } catch (Exception e) {
            return "Error al eliminar:" + e.getMessage();
        }
    }

   
}
