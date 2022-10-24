/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.beans;

import com.matoosfe.systravel.beans.util.AbstractManagedBean;
import com.matoosfe.systravel.controllers.ReservaViajeFacade;
import com.matoosfe.systravel.controllers.ClienteFacade;
import com.matoosfe.systravel.entities.DetalleReservaViaje;
import com.matoosfe.systravel.entities.ReservaViaje;
import com.matoosfe.systravel.entities.Cliente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Named("resViaBean")
@ViewScoped
public class ReservaViajeBean extends AbstractManagedBean implements Serializable {

    @Getter
    @Setter
    private ReservaViaje historiaClinica;
    @Getter
    @Setter
    private ReservaViaje historiaClinicaSel;
    @Getter
    @Setter
    private List<ReservaViaje> historiasClinicas;
    @Getter
    @Setter
    private DetalleReservaViaje detHisClinica;
    @Getter
    @Setter
    private List<DetalleReservaViaje> detallesHistoriaClinica;
    @Getter
    @Setter
    private String numeroHistoria;
    @Getter
    @Setter
    private boolean bloquearHistoria;

    @Getter
    @Setter
    private boolean panelNuevo;

    @Inject
    private ClienteFacade adminPaciente;
    @Inject
    private ReservaViajeFacade adminHistoriaCli;

    public ReservaViajeBean() {
        this.historiaClinica = new ReservaViaje();
        this.historiasClinicas = new ArrayList<>();
        this.detHisClinica = new DetalleReservaViaje();
        this.detallesHistoriaClinica = new ArrayList<>();
    }

    /**
     * Método para buscar pacientes
     *
     * @param valorApellido
     * @return
     */
    public List<Cliente> completarPacientes(String valorApellido) {
        String queryLowerCase = valorApellido.toLowerCase();
        List<Cliente> pacientes = adminPaciente.buscarTodos();
        return pacientes.stream().filter(pac -> pac.getApellidoPaternoCli().toLowerCase()
                .contains(queryLowerCase)).collect(Collectors.toList());
    }

    /**
     * Método para activar el panel de nuevo
     */
    public void activarPanel() {
        this.panelNuevo = true;
    }

    /**
     * Método para desactivar el panel de nuevo
     */
    public void desactivarPanel() {
        this.panelNuevo = false;
    }

    /**
     * Método para guardar o actualizar la historia con su atención
     */
    public void guardar() {
        try {
            detHisClinica.setReservaViaje(historiaClinica); //Padre
            if (historiaClinica.getIdResCli() != null) {
                historiaClinica.getDetallesReserva().add(detHisClinica);//Hijos
                adminHistoriaCli.actualizar(historiaClinica);
                anadirInfo("Historia clinica actualizada correctamente");
            } else {
                List<DetalleReservaViaje> detalles = new ArrayList<>();
                detalles.add(detHisClinica);
                historiaClinica.setDetallesReserva(detalles);
                adminHistoriaCli.guardar(historiaClinica);
                anadirInfo("Historia clinica registrada correctamente");
            }
            resetearFormulario();

        } catch (Exception e) {
            anadirError("Error al guardar historia clínica:" + e.getMessage());
        }
    }

    /**
     * Método para buscar las historia clínicas por su número
     */
    public void buscar() {
        this.historiasClinicas = adminHistoriaCli.buscarPorNumero(numeroHistoria);
        if (historiasClinicas.isEmpty()) {
            anadirInfo("No existen historias clínicas con ese criterio");
        }
    }

    /**
     * Método para obtener el número de atenciones de la historia clínica
     *
     * @param his
     * @return
     */
    public int obtenerAtenciones(ReservaViaje his) {
        return his.getDetallesReserva().size();
    }

    /**
     * Método para cargar las atenciones
     *
     * @param his
     */
    public void cargarHistoriaClinica(ReservaViaje his) {
        this.historiaClinica = his;
        this.detallesHistoriaClinica = his.getDetallesReserva();
        activarPanel();
        this.bloquearHistoria = true;
    }

    /**
     * Método para limpiar el formulario
     */
    public void resetearFormulario() {
        this.historiaClinica = new ReservaViaje();
        this.detHisClinica = new DetalleReservaViaje();
        this.bloquearHistoria = false;
        this.numeroHistoria = null;
        this.detallesHistoriaClinica.clear();
        this.historiasClinicas.clear();
        desactivarPanel();
    }

}
