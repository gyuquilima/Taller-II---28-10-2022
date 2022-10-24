/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.beans;

import com.matoosfe.systravel.beans.util.AbstractManagedBean;
import com.matoosfe.systravel.controllers.ClienteFacade;
import com.matoosfe.systravel.controllers.TipoClienteFacade;
import com.matoosfe.systravel.entities.Cliente;
import com.matoosfe.systravel.entities.TipoCliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author martosfre
 */
@Named
@ViewScoped
public class ClienteBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private Cliente paciente;
    @Getter
    @Setter
    private Cliente pacienteSel;
    @Getter
    @Setter
    private List<Cliente> listaPacientes;
    @Getter
    @Setter
    private String identificacionApellido;
    @Getter
    @Setter
    private List<SelectItem> listaTipoPacientes;
    @Getter
    @Setter
    private List<TipoCliente> listaTipoPacientesObj;
    @Getter
    @Setter
    private int idTipPac;
    @Getter
    @Setter
    private String tipoIden;
    @Getter
    @Setter
    private String mascaraIden;
    @Getter
    @Setter
    private String pathImagen;

    @Inject
    private ClienteFacade adminPaciente;
    @Inject
    private TipoClienteFacade adminTipoPaciente;

    public ClienteBean() {
        this.paciente = new Cliente();
        this.listaPacientes = new ArrayList<>();
        this.listaTipoPacientes = new ArrayList<>();
        this.listaTipoPacientesObj = new ArrayList<>();
        this.tipoIden = "CED";
        this.mascaraIden = "9999999999";
        this.pathImagen = "/resources/img/usuario.webp";
    }

    /**
     * Método para buscar pacientes
     */
    public void buscarPacientes() {
        try {
            this.listaPacientes = adminPaciente.buscarIdentificacionApellido(identificacionApellido);
            if (listaPacientes.isEmpty()) {
                anadirInfo("No existen pacientes con ese criterio");
            }
        } catch (Exception e) {
            anadirError("Error al buscar los pacientes con ese criterio:" + e.getMessage());
        }
    }

    /**
     * Método para cargar los tipos de pacientes
     */
    private void cargarTipoPacientes() {
        this.listaTipoPacientes.clear();
        this.listaTipoPacientesObj.clear();
        adminTipoPaciente.buscarTodos().forEach(tipPac -> {
            this.listaTipoPacientes.add(new SelectItem(tipPac.getIdTipcli(), tipPac.getNombreTipcli()));
            this.listaTipoPacientesObj.add(tipPac);
        });

//        for(TipoPaciente tipPac:adminTipoPaciente.buscarTodos()){
//             this.listaTipoPacientes.add(new SelectItem(tipPac.getIdTippac(), tipPac.getNombreTippac()));
//        }
    }

    /**
     * Método para actualizar la máscara según tipo de identificación
     */
    public void actualizarMascaraIdentificacion() {
        switch (tipoIden) {
            case "CED":
                this.mascaraIden = "9999999999";
                break;
            case "RUC":
                this.mascaraIden = "9999999999999";
                break;
            default:
                this.mascaraIden = "999999";
                break;
        }
    }

    /**
     * Método para guardar o actualizar un paciente
     */
    public void guardar() {
        try {
            //Recuperar el tipo de paciente y seteandole a paciente
            TipoCliente tipoPaciente = adminTipoPaciente.buscarPorId(idTipPac);
            paciente.setIdTipcli(tipoPaciente);

            if (paciente.getIdCli() != null) {
                adminPaciente.actualizar(paciente);
                anadirInfo("Paciente actualizado correctamente");
            } else {
                adminPaciente.guardar(paciente);
                anadirInfo("Paciente guardado correctamente");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }

    /**
     * Método para guardar o actualizar un paciente
     */
    public void guardarConvertidor() {
        try {
            if (paciente.getIdCli() != null) {
                adminPaciente.actualizar(paciente);
                anadirInfo("Paciente actualizado correctamente");
            } else {
                adminPaciente.guardar(paciente);
                anadirInfo("Paciente guardado correctamente");
            }
            resetearFormulario();
        } catch (Exception e) {
            anadirError("Error al procesar la operación:" + e.getMessage());
        }
    }
    

    /**
     * Método para seleccionar un paciente
     * @param ev 
     */
    public void seleccionarFila(SelectEvent<Cliente> ev) {
        this.pacienteSel = ev.getObject();
    }
    
    /**
     * Método para cargar un paciente
     */
    public void editar(){
        if(pacienteSel != null){
            this.paciente = pacienteSel;
            this.idTipPac = paciente.getIdTipcli().getIdTipcli(); //cuando no se utiliza convertidor
            //Validar la máscara
            switch (paciente.getIdentificacionCli().length()) {
                case 10:
                    this.tipoIden = "CED";
                    break;
                case 13:
                    this.tipoIden ="RUC"; 
                    break;
                default:
                    this.tipoIden = "PAS";
                    break;
            }
            actualizarMascaraIdentificacion();
            PrimeFaces.current().executeScript("PF('diaNuePac').show();");
        }else{
            anadirError("Se debe seleccionar un paciente");
        }
    }
    
    /**
     * Método para eliminar un paciente
     */
    public void eliminar(){
        if(pacienteSel != null){
            adminPaciente.eliminar(pacienteSel);
            anadirInfo("Paciente eliminado correctamente");
            buscarPacientes();
            resetearFormulario();
        }else{
             anadirError("Se debe seleccionar un paciente");
        }
    }

    /**
     * Método para resetear el formulario
     */
    public void resetearFormulario() {
        this.paciente = new Cliente();
        this.pacienteSel = null;
        this.idTipPac = 0;
        this.identificacionApellido = null;
    }

    @PostConstruct
    public void inicializar() {
        cargarTipoPacientes();
    }

}
