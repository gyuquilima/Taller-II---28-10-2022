/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 *
 * @author gyuquilima
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "detalle_reserva_viaje")
@NamedQueries({
    @NamedQuery(name = "DetalleReservaViaje.findAll", query = "SELECT d FROM DetalleReservaViaje d"),
    @NamedQuery(name = "DetalleReservaViaje.findByIdDetrescli", query = "SELECT d FROM DetalleReservaViaje d WHERE d.idDetrescli = :idDetrescli"),
    @NamedQuery(name = "DetalleReservaViaje.findByFechaAtencionDetrescli", query = "SELECT d FROM DetalleReservaViaje d WHERE d.fechaAtencionDetrescli = :fechaAtencionDetrescli"),
    @NamedQuery(name = "DetalleReservaViaje.findByObservacionesDetrescli", query = "SELECT d FROM DetalleReservaViaje d WHERE d.observacionesDetrescli = :observacionesDetrescli"),
    @NamedQuery(name = "DetalleReservaViaje.findByPrescripcionDetrescli", query = "SELECT d FROM DetalleReservaViaje d WHERE d.prescripcionDetrescli = :prescripcionDetrescli")})

public class DetalleReservaViaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_dethiscli")
    private Integer idDetrescli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_atencion_dethiscli")
    @Temporal(TemporalType.DATE)
    private Date fechaAtencionDetrescli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "observaciones_dethiscli")
    private String observacionesDetrescli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "prescripcion_dethiscli")
    private String prescripcionDetrescli;
    
    @ManyToOne
    @JoinColumn(name = "id_res_cli", referencedColumnName = "id_res_cli")
    private ReservaViaje reservaViaje;


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDetrescli != null ? idDetrescli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetalleReservaViaje)) {
            return false;
        }
        DetalleReservaViaje other = (DetalleReservaViaje) object;
        if ((this.idDetrescli == null && other.idDetrescli != null) || (this.idDetrescli != null && !this.idDetrescli.equals(other.idDetrescli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.sysmed.entities.DetalleReservaViaje[ idDetrescli=" + idDetrescli + " ]";
    }
    
}
