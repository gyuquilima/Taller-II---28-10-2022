/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package com.matoosfe.systravel.beans.services.ws;

import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.TipoCliente;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author gyuquilima
 */
@WebService(serviceName = "TipoClienteService")
public class TipoClienteService {


    @Inject
    private TipoClienteFacade adminTipoCliente;

    /**
     * This is a sample web service operation
     *
     * @param id ID del cliente
     * @return Cliente
     */
    @WebMethod(operationName = "consultarTipClientePorId")
    public TipoCliente consultarPorId(@WebParam(name = "id") Integer id) {
        return adminTipoCliente.buscarPorId(id);
    }

    @WebMethod(operationName = "consultarTodos")
    public List<TipoCliente> consultarTodos() {
        return adminTipoCliente.buscarTodos();
    }

    /**
     * Método para guardar un tipo de tipoCliente
     *
     * @param tipoCliente
     * @return
     */
    public String guardarTipoCliente(TipoCliente tipoCliente) {
        try {
            adminTipoCliente.guardar(tipoCliente);
            return "Tipo Cliente registrado correctamente";
        } catch (Exception e) {
            return "Error al Guardar:" + e.getMessage();
        }
    }

    /**
     * Método para actualizar el tipo de tipoCliente
     *
     * @param tipoCliente tipo de paciente que existe en la bdd
     * @return mensaje de confirmación
     */
    @WebMethod(operationName = "actualizarTipCli")
    public String actualizarTipoCliente(TipoCliente tipoCliente) {
        try {
            if (adminTipoCliente.buscarPorId(tipoCliente.getIdTipcli()) != null) {
                adminTipoCliente.actualizar(tipoCliente);
                return "Tipo Cliente actualizado correctamente";
            } else {
                return "Tipo de Cliente no existe";
            }

        } catch (Exception e) {
            return "Error al Actualizar:" + e.getMessage();
        }
    }

    /**
     * Método para eliminar el tipo de cliente
     *
     * @param idTipCli identificador de tipo de cliente
     * @return mensaje de confirmación
     */
    public String eliminarTipoCliente(@WebParam(name = "identificadorTipCli") int idTipCli) {
        try {
            TipoCliente tipoCliente = adminTipoCliente.buscarPorId(idTipCli);
            if (tipoCliente != null) {
                adminTipoCliente.eliminar(tipoCliente);
                return "Tipo Cliente eliminado correctamente";
            } else {
                return "Tipo de Cliente no existe";
            }

        } catch (Exception e) {
            return "Error al Eliminar:" + e.getMessage();
        }
    }
    
}
