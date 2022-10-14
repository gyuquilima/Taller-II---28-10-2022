/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.beans;

import com.matoosfe.sysmed.beans.util.AbstractManagedBean;
import com.matoosfe.sysmed.controllers.CentroFacade;
import com.matoosfe.sysmed.entities.Centro;
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
 * @author gyuquilima
 */
@Named
@ViewScoped
public class CentroBean extends AbstractManagedBean implements Serializable {    
    
    @Getter
    @Setter
    private Centro centro;
    
    @Getter
    @Setter
    private Centro centroSel; //Seleccionar una fila en la tabla

    @Getter
    @Setter
    private List<Centro> listaCentros;

    @Inject
    private CentroFacade adminCentro;

    public CentroBean() {
        this.centro = new Centro();
        this.listaCentros = new ArrayList<>();
    }


    /**
     * Método para guardar o actualizar un tipo de paciente
     */
    public void guardar() {
        try {
            if (centro.getIdCentro()!= null) {
                //Llamando al controlador (EJB)
                adminCentro.actualizar(centro);
                //Mensaje de Actualización
                anadirInfo("Centro actualizado correctamente");
            } else {
                adminCentro.guardar(centro);
                //Mensaje de Guardar
                anadirInfo("Centro guardado correctamente");
            }
            cargarCentros();
            resetearFormulario();
        } catch (Exception e) {
            //Mensaje de error
            anadirError("Error al procesar operación:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargarCentros() {
        this.listaCentros = adminCentro.buscarTodos();
    }


    /**
     * Seleccionar una fila (tipo de paciente) de la tabla
     *
     * @param ev
     */
    public void seleccionarFila(SelectEvent<Centro> ev) {
        this.centroSel = ev.getObject();
    }

    /**
     * Método para cargar el tipo de paciente
     */
    public void editar() {
        if (centroSel != null) {
            this.centro= centroSel;
        } else {
            anadirError("Se debe seleccionar un registro");
        }
    }

    /**
     * Método para eliminar un tipo de paciente
     */
    public void eliminar() {
        try {
            if (centroSel != null) {
                adminCentro.eliminar(centroSel);
                anadirInfo("Tipo de paciente elminado correctamente");
            } else {
                anadirError("Se debe seleccionar un registro");
            }
            resetearFormulario();
            cargarCentros();
        } catch (Exception e) {
            anadirError("Error al eliminar un tipo de paciente:" + e.getMessage());
        }
    }

    
    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.centro = new Centro();
        this.centroSel = null;
    }
    
    @PostConstruct
    public void inicializar(){
        cargarCentros();
    }
    
    
}
