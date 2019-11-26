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
@Table(name = "SISTEMAS_ROLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SistemasRole.findAll", query = "SELECT s FROM SistemasRole s")
    , @NamedQuery(name = "SistemasRole.findByIdSistemasRole", query = "SELECT s FROM SistemasRole s WHERE s.idSistemasRole = :idSistemasRole")})
public class SistemasRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SISTEMAS_ROLE")
    private Integer idSistemasRole;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    @ManyToOne
    private Roles idRol;
    @JoinColumn(name = "ID_SISTEMA", referencedColumnName = "ID_SISTEMA")
    @ManyToOne
    private Sistemas idSistema;

    public SistemasRole() {
    }

    public SistemasRole(Integer idSistemasRole) {
        this.idSistemasRole = idSistemasRole;
    }

    public Integer getIdSistemasRole() {
        return idSistemasRole;
    }

    public void setIdSistemasRole(Integer idSistemasRole) {
        this.idSistemasRole = idSistemasRole;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Roles getIdRol() {
        return idRol;
    }

    public void setIdRol(Roles idRol) {
        this.idRol = idRol;
    }

    public Sistemas getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Sistemas idSistema) {
        this.idSistema = idSistema;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSistemasRole != null ? idSistemasRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SistemasRole)) {
            return false;
        }
        SistemasRole other = (SistemasRole) object;
        if ((this.idSistemasRole == null && other.idSistemasRole != null) || (this.idSistemasRole != null && !this.idSistemasRole.equals(other.idSistemasRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.SistemasRole[ idSistemasRole=" + idSistemasRole + " ]";
    }
    
}
