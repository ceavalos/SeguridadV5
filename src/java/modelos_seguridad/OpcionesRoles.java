/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos_seguridad;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Avalos
 */
@Entity
@Table(name = "OPCIONES_ROLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OpcionesRoles.findAll", query = "SELECT o FROM OpcionesRoles o")
    , @NamedQuery(name = "OpcionesRoles.findByIdOpcionRol", query = "SELECT o FROM OpcionesRoles o WHERE o.idOpcionRol = :idOpcionRol")})
public class OpcionesRoles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCION_ROL")
    private Integer idOpcionRol;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "ID_OPCION", referencedColumnName = "ID_OPCION")
    @ManyToOne
    private Opciones idOpcion;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private Roles idRol;

    public OpcionesRoles() {
    }

    public OpcionesRoles(Integer idOpcionRol) {
        this.idOpcionRol = idOpcionRol;
    }

    public Integer getIdOpcionRol() {
        return idOpcionRol;
    }

    public void setIdOpcionRol(Integer idOpcionRol) {
        this.idOpcionRol = idOpcionRol;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Opciones getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Opciones idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcionRol != null ? idOpcionRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OpcionesRoles)) {
            return false;
        }
        OpcionesRoles other = (OpcionesRoles) object;
        if ((this.idOpcionRol == null && other.idOpcionRol != null) || (this.idOpcionRol != null && !this.idOpcionRol.equals(other.idOpcionRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.OpcionesRoles[ idOpcionRol=" + idOpcionRol + " ]";
    }
    
}
