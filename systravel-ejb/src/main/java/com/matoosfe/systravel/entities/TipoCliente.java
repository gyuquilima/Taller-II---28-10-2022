/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.matoosfe.systravel.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //Genera todos los getters de las propiedades privadas
@Setter //Genera todos los setters de las propiedades privadas
@NoArgsConstructor //Genera un constructor vacío

/**
 *
 * @author gyuquilima
 */
@Entity
@Table(name = "tipo_cliente")
@NamedQueries({
    @NamedQuery(name = "TipoCliente.findAll", query = "SELECT t FROM TipoCliente t"),
    @NamedQuery(name = "TipoCliente.findByIdTipcli", query = "SELECT t FROM TipoCliente t WHERE t.idTipcli = :idTipcli"),
    @NamedQuery(name = "TipoCliente.findByNombreTipcli", query = "SELECT t FROM TipoCliente t WHERE t.nombreTipcli = :nombreTipcli"),
    @NamedQuery(name = "TipoCliente.findByDescripcionTipcli", query = "SELECT t FROM TipoCliente t WHERE t.descripcionTipcli = :descripcionTipcli")})
public class TipoCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipcli")
    private Integer idTipcli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre_tipcli")
    private String nombreTipcli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcion_tipcli")
    private String descripcionTipcli;
    @Getter(onMethod_= {@XmlTransient, @JsonbTransient})
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipcli")
    private List<Cliente> clienteList;

        
    public TipoCliente(Integer idTipcli) {
        this.idTipcli = idTipcli;
    }

    public TipoCliente(Integer idTipcli, String nombreTipcli, String descripcionTipcli) {
        this.idTipcli = idTipcli;
        this.nombreTipcli = nombreTipcli;
        this.descripcionTipcli = descripcionTipcli;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipcli != null ? idTipcli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        
        if (object == null) {
            return false;
        }
        

        if (getClass() != object.getClass()) {
            return false;
        }
        
        final TipoCliente other = (TipoCliente) object;
        if (!Objects.equals(this.nombreTipcli, other.nombreTipcli)) {
            return false;
        }
        return Objects.equals(this.idTipcli, other.idTipcli);
        
    }

    @Override
    public String toString() {
        return "com.matoosfe.systravel.entities.TipoCliente[ idTipcli=" + idTipcli + " ]";
    }
    
}
