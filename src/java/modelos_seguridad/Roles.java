/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos_seguridad;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Avalos
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r")
    , @NamedQuery(name = "Roles.findByIdRol", query = "SELECT r FROM Roles r WHERE r.idRol = :idRol")
    , @NamedQuery(name = "Roles.findByNombreRol", query = "SELECT r FROM Roles r WHERE r.nombreRol = :nombreRol")})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_ROL")
    private Integer idRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE_ROL")
    private String nombreRol;
    @OneToMany(mappedBy = "idRol")
    private Collection<SistemasRole> sistemasRoleCollection;
    @OneToMany(mappedBy = "idRol")
    private Collection<OpcionesRoles> opcionesRolesCollection;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public Roles() {
    }

    public Roles(Integer idRol) {
        this.idRol = idRol;
    }

    public Roles(Integer idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @XmlTransient
    public Collection<SistemasRole> getSistemasRoleCollection() {
        return sistemasRoleCollection;
    }

    public void setSistemasRoleCollection(Collection<SistemasRole> sistemasRoleCollection) {
        this.sistemasRoleCollection = sistemasRoleCollection;
    }

    @XmlTransient
    public Collection<OpcionesRoles> getOpcionesRolesCollection() {
        return opcionesRolesCollection;
    }

    public void setOpcionesRolesCollection(Collection<OpcionesRoles> opcionesRolesCollection) {
        this.opcionesRolesCollection = opcionesRolesCollection;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRol != null ? idRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRol == null && other.idRol != null) || (this.idRol != null && !this.idRol.equals(other.idRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Roles[ idRol=" + idRol + " ]";
    }
    
}
