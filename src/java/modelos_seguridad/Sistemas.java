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
@Table(name = "SISTEMAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sistemas.findAll", query = "SELECT s FROM Sistemas s")
    , @NamedQuery(name = "Sistemas.findByIdSistema", query = "SELECT s FROM Sistemas s WHERE s.idSistema = :idSistema")
    , @NamedQuery(name = "Sistemas.findByNombreSistema", query = "SELECT s FROM Sistemas s WHERE s.nombreSistema = :nombreSistema")
    , @NamedQuery(name = "Sistemas.findByDescripcion", query = "SELECT s FROM Sistemas s WHERE s.descripcion = :descripcion")
    , @NamedQuery(name = "Sistemas.findByRutaInicio", query = "SELECT s FROM Sistemas s WHERE s.rutaInicio = :rutaInicio")
    , @NamedQuery(name = "Sistemas.findByIcono", query = "SELECT s FROM Sistemas s WHERE s.icono = :icono")
    , @NamedQuery(name = "Sistemas.findByEstado", query = "SELECT s FROM Sistemas s WHERE s.estado = :estado")})
public class Sistemas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_SISTEMA")
    private Integer idSistema;
    @Size(max = 100)
    @Column(name = "NOMBRE_SISTEMA")
    private String nombreSistema;
    @Size(max = 200)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Size(max = 100)
    @Column(name = "RUTA_INICIO")
    private String rutaInicio;
    @Size(max = 100)
    @Column(name = "ICONO")
    private String icono;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "idSistema")
    private Collection<SistemasRole> sistemasRoleCollection;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;

    public Sistemas() {
    }

    public Sistemas(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public Sistemas(Integer idSistema, String estado) {
        this.idSistema = idSistema;
        this.estado = estado;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getNombreSistema() {
        return nombreSistema;
    }

    public void setNombreSistema(String nombreSistema) {
        this.nombreSistema = nombreSistema;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRutaInicio() {
        return rutaInicio;
    }

    public void setRutaInicio(String rutaInicio) {
        this.rutaInicio = rutaInicio;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<SistemasRole> getSistemasRoleCollection() {
        return sistemasRoleCollection;
    }

    public void setSistemasRoleCollection(Collection<SistemasRole> sistemasRoleCollection) {
        this.sistemasRoleCollection = sistemasRoleCollection;
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
        hash += (idSistema != null ? idSistema.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sistemas)) {
            return false;
        }
        Sistemas other = (Sistemas) object;
        if ((this.idSistema == null && other.idSistema != null) || (this.idSistema != null && !this.idSistema.equals(other.idSistema))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Sistemas[ idSistema=" + idSistema + " ]";
    }
    
}
