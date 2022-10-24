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
import javax.persistence.Lob;
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
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCli", query = "SELECT c FROM Cliente c WHERE c.idCli = :idCli"),
    @NamedQuery(name = "Cliente.findByNombreCli", query = "SELECT c FROM Cliente c WHERE c.nombreCli = :nombreCli"),
    @NamedQuery(name = "Cliente.findByApellidoPaternoCli", query = "SELECT c FROM Cliente c WHERE c.apellidoPaternoCli = :apellidoPaternoCli"),
    @NamedQuery(name = "Cliente.findByApellidoMaternoCli", query = "SELECT c FROM Cliente c WHERE c.apellidoMaternoCli = :apellidoMaternoCli"),
    @NamedQuery(name = "Cliente.findByIdentificacionCli", query = "SELECT c FROM Cliente c WHERE c.identificacionCli = :identificacionCli"),
    @NamedQuery(name = "Cliente.findByTelefonoCelularCli", query = "SELECT c FROM Cliente c WHERE c.telefonoCelularCli = :telefonoCelularCli"),
    @NamedQuery(name = "Cliente.findByDireccionCli", query = "SELECT c FROM Cliente c WHERE c.direccionCli = :direccionCli"),
    @NamedQuery(name = "Cliente.findByCorreoCli", query = "SELECT c FROM Cliente c WHERE c.correoCli = :correoCli"),
    @NamedQuery(name = "Cliente.findByFechaNacimientoCli", query = "SELECT c FROM Cliente c WHERE c.fechaNacimientoCli = :fechaNacimientoCli")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_cli")
    private Integer idCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nombre_cli")
    private String nombreCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "apellido_paterno_cli")
    private String apellidoPaternoCli;
    @Size(max = 25)
    @Column(name = "apellido_materno_cli")
    private String apellidoMaternoCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "identificacion_cli")
    private String identificacionCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "telefono_celular_cli")
    private String telefonoCelularCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "direccion_cli")
    private String direccionCli;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "correo_cli")
    private String correoCli;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_nacimiento_cli")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimientoCli;
    @Lob
    @Column(name = "foto_cli")
    private byte[] fotoCli;
    
    
    //Identifica el nombre de la columna de la relación.
    @JoinColumn(name = "id_tipcli", referencedColumnName = "id_tipcli")
    @ManyToOne(optional = false)
    private TipoCliente idTipcli;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCli")
    private List<ReservaViaje> reservaViajeList;
    

    public Cliente(Integer idCli) {
        this.idCli = idCli;
    }

    public Cliente(Integer idCli, String nombreCli, String apellidoPaternoCli, String identificacionCli, String telefonoCelularCli, String direccionCli, String correoCli, Date fechaNacimientoCli) {
        this.idCli = idCli;
        this.nombreCli = nombreCli;
        this.apellidoPaternoCli = apellidoPaternoCli;
        this.identificacionCli = identificacionCli;
        this.telefonoCelularCli = telefonoCelularCli;
        this.direccionCli = direccionCli;
        this.correoCli = correoCli;
        this.fechaNacimientoCli = fechaNacimientoCli;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCli != null ? idCli.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCli == null && other.idCli != null) || (this.idCli != null && !this.idCli.equals(other.idCli))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.nombreCli + " " + this.apellidoPaternoCli + " " + this.apellidoMaternoCli;
    }
    
}
