/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.sysmed.entities;

import java.io.Serializable;
//import java.util.List;
//import java.util.Objects;
import javax.persistence.Basic;
//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author martosfre
 */
@Getter //Genera todos los getters de las propiedades privadas
@Setter //Genera todos los setters de las propiedades privadas
@NoArgsConstructor //Genera un constructor vacío



/**
 *
 * @author gyuquilima
 */
@Entity
@Table(name = "centro")
@NamedQueries({
    @NamedQuery(name = "Centro.findAll", query = "SELECT c FROM Centro c"),
    @NamedQuery(name = "Centro.findByIdCentro", query = "SELECT c FROM Centro c WHERE c.idCentro = :idCentro"),
    @NamedQuery(name = "Centro.findBySiglasCentro", query = "SELECT c FROM Centro c WHERE c.siglasCentro = :siglasCentro"),
    @NamedQuery(name = "Centro.findByNombreCentro", query = "SELECT c FROM Centro c WHERE c.nombreCentro = :nombreCentro"),
    @NamedQuery(name = "Centro.findByDireccionCentro", query = "SELECT c FROM Centro c WHERE c.direccionCentro = :direccionCentro")})
public class Centro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_centro")
    private Integer idCentro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "siglas_centro")
    private String siglasCentro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombre_centro")
    private String nombreCentro;
    @Basic(optional = true)
    @Size(min = 1, max = 300)
    @Column(name = "direccion_centro")
    private String direccionCentro;

    public Centro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    public Centro(Integer idCentro, String siglasCentro, String nombreCentro, String direccionCentro) {
        this.idCentro = idCentro;
        this.siglasCentro = siglasCentro;
        this.nombreCentro = nombreCentro;
        this.direccionCentro = direccionCentro;
    }

    public Integer getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(Integer idCentro) {
        this.idCentro = idCentro;
    }

    public String getSiglasCentro() {
        return siglasCentro;
    }

    public void setSiglasCentro(String siglasCentro) {
        this.siglasCentro = siglasCentro;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getDireccionCentro() {
        return direccionCentro;
    }

    public void setDireccionCentro(String direccionCentro) {
        this.direccionCentro = direccionCentro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCentro != null ? idCentro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Centro)) {
            return false;
        }
        Centro other = (Centro) object;
        if ((this.idCentro == null && other.idCentro != null) || (this.idCentro != null && !this.idCentro.equals(other.idCentro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.matoosfe.sysmed.entities.Centro[ idCentro=" + idCentro + " ]";
    }
    
}
