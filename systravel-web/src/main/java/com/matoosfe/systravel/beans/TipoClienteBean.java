/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.beans;

import com.matoosfe.systravel.beans.util.AbstractManagedBean;
import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.TipoCliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author martosfre
 */
@Named
@ViewScoped
public class TipoClienteBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private TipoCliente tipoPaciente;
    @Getter
    @Setter
    private TipoCliente tipoPacienteSel; //Seleccionar una fila en la tabla

    @Getter
    @Setter
    private List<TipoCliente> listaTipoPacientes;

    @Inject
    private TipoClienteFacade adminTipoPaciente;

    public TipoClienteBean() {
        this.tipoPaciente = new TipoCliente();
        this.listaTipoPacientes = new ArrayList<>();
    }

    /**
     * Método para guardar o actualizar un tipo de paciente
     */
    public void guardar() {
        try {
            if (tipoPaciente.getIdTipcli() != null) {
                //Llamando al controlador (EJB)
                adminTipoPaciente.actualizar(tipoPaciente);
                //Mensaje de Actualización
                anadirInfo("Tipo de paciente actualizado correctamente");
            } else {
                adminTipoPaciente.guardar(tipoPaciente);
                //Mensaje de Guardar
                anadirInfo("Tipo de paciente guardado correctamente");
            }
            cargarTiposPacientes();
            resetearFormulario();
        } catch (Exception e) {
            //Mensaje de error
            anadirError("Error al procesar operación:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargarTiposPacientes() {
        this.listaTipoPacientes = adminTipoPaciente.buscarTodos();
    }

    /**
     * Seleccionar una fila (tipo de paciente) de la tabla
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<TipoCliente> ev) {
        this.tipoPacienteSel = ev.getObject();
    }

    /**
     * Método para cargar el tipo de paciente
     */
    public void editar() {
        if (tipoPacienteSel != null) {
            this.tipoPaciente = tipoPacienteSel;
        } else {
            anadirError("Se debe seleccionar un registro");
        }
    }

    /**
     * Método para eliminar un tipo de paciente
     */
    public void eliminar() {
        try {
            if (tipoPacienteSel != null) {
                adminTipoPaciente.eliminar(tipoPacienteSel);
                anadirInfo("Tipo de paciente elminado correctamente");
            } else {
                anadirError("Se debe seleccionar un registro");
            }
            resetearFormulario();
            cargarTiposPacientes();
        } catch (Exception e) {
            anadirError("Error al eliminar un tipo de paciente:" + e.getMessage());
        }
    }

    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.tipoPaciente = new TipoCliente();
        this.tipoPacienteSel = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarTiposPacientes();
    }
}
