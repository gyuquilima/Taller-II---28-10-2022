/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "reserva_viaje")
@NamedQueries({
    @NamedQuery(name = "ReservaViaje.findAll", query = "SELECT r FROM ReservaViaje r"),
    @NamedQuery(name = "ReservaViaje.findByIdResCli", query = "SELECT r FROM ReservaViaje r WHERE r.idResCli = :idResCli"),
    @NamedQuery(name = "ReservaViaje.findByFechaAperturaRescli", query = "SELECT r FROM ReservaViaje r WHERE r.fechaAperturaRescli = :fechaAperturaRescli"),
    @NamedQuery(name = "ReservaViaje.findByNumeroReservaViaje", query = "SELECT r FROM ReservaViaje r WHERE r.numeroReservaViaje = :numeroReservaViaje")})
public class ReservaViaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_res_cli")
    private Integer idResCli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_apertura_rescli")
    @Temporal(TemporalType.DATE)
    private Date fechaAperturaRescli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "numero_reserva_viaje")
    private String numeroReservaViaje;
    @JoinColumn(name = "id_cli", referencedColumnName = "id_cli")
    @ManyToOne(optional = false)
    private Cliente idCli;

    @OneToMany(mappedBy = "reservaViaje", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<DetalleReservaViaje> detallesReserva;
    
    public ReservaViaje(Integer idResCli) {
        this.idResCli = idResCli;
    }

    public ReservaViaje(Integer idResCli, Date fechaAperturaRescli, String numeroReservaViaje) {
        this.idResCli = idResCli;
        this.fechaAperturaRescli = fechaAperturaRescli;
        this.numeroReservaViaje = numeroReservaViaje;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idResCli != null ? idResCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReservaViaje)) {
            return false;
        }
        ReservaViaje other = (ReservaViaje) object;
        if ((this.idResCli == null && other.idResCli != null) || (this.idResCli != null && !this.idResCli.equals(other.idResCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.systravel.entities.ReservaViaje[ idResCli=" + idResCli + " ]";
    }
    
}
