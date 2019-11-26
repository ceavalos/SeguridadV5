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
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Avalos
 */
@Entity
@Table(name = "PLANTILLACONTROLLER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Plantillacontroller.findAll", query = "SELECT p FROM Plantillacontroller p")
    , @NamedQuery(name = "Plantillacontroller.findById", query = "SELECT p FROM Plantillacontroller p WHERE p.id = :id")})
public class Plantillacontroller implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;

    public Plantillacontroller() {
    }

    public Plantillacontroller(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Plantillacontroller)) {
            return false;
        }
        Plantillacontroller other = (Plantillacontroller) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Plantillacontroller[ id=" + id + " ]";
    }
    
}
