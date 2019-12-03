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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Carlos Avalos
 */
@Entity
@Table(name = "OPCIONES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opciones.findAll", query = "SELECT o FROM Opciones o")
    , @NamedQuery(name = "Opciones.findByIdOpcion", query = "SELECT o FROM Opciones o WHERE o.idOpcion = :idOpcion")
    , @NamedQuery(name = "Opciones.findByDescOpcion", query = "SELECT o FROM Opciones o WHERE o.descOpcion = :descOpcion")
    , @NamedQuery(name = "Opciones.findByTipo", query = "SELECT o FROM Opciones o WHERE o.tipo = :tipo")
    , @NamedQuery(name = "Opciones.findByEstado", query = "SELECT o FROM Opciones o WHERE o.estado = :estado")})
public class Opciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID_OPCION")
    private Integer idOpcion;
    @Size(max = 100)
    @Column(name = "DESC_OPCION")
    private String descOpcion;
    @Size(max = 1)
    @Column(name = "TIPO")
    private String tipo;
    @Size(max = 1)
    @Column(name = "ESTADO")
    private String estado;
    @JoinColumn(name = "ID_EMPRESA", referencedColumnName = "ID_EMPRESA")
    @ManyToOne
    private Empresa idEmpresa;
    
    @JoinColumn(name = "ID_SISTEMA", referencedColumnName = "ID_SISTEMA")
    @ManyToOne
    private Sistemas idSistema;
    
    @OneToMany(mappedBy = "codigoSubmenu")
    private Collection<Opciones> opcionesCollection;
    @JoinColumn(name = "CODIGO_SUBMENU", referencedColumnName = "ID_OPCION")
    @ManyToOne
    private Opciones codigoSubmenu;
    @Column(name = "URL")
    private String url;

    public Sistemas getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Sistemas idSistema) {
        this.idSistema = idSistema;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public Opciones() {
    }

    public Opciones(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    public String getDescOpcion() {
        return descOpcion;
    }

    public void setDescOpcion(String descOpcion) {
        this.descOpcion = descOpcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    @XmlTransient
    public Collection<Opciones> getOpcionesCollection() {
        return opcionesCollection;
    }

    public void setOpcionesCollection(Collection<Opciones> opcionesCollection) {
        this.opcionesCollection = opcionesCollection;
    }

    public Opciones getCodigoSubmenu() {
        return codigoSubmenu;
    }

    public void setCodigoSubmenu(Opciones codigoSubmenu) {
        this.codigoSubmenu = codigoSubmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOpcion != null ? idOpcion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opciones)) {
            return false;
        }
        Opciones other = (Opciones) object;
        if ((this.idOpcion == null && other.idOpcion != null) || (this.idOpcion != null && !this.idOpcion.equals(other.idOpcion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Opciones[ idOpcion=" + idOpcion + " ]";
    }
    
}
