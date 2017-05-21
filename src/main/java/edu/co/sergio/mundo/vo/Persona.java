/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona")
    , @NamedQuery(name = "Persona.findByNombrepersona", query = "SELECT p FROM Persona p WHERE p.nombrepersona = :nombrepersona")
    , @NamedQuery(name = "Persona.findByApellidopersona", query = "SELECT p FROM Persona p WHERE p.apellidopersona = :apellidopersona")
    , @NamedQuery(name = "Persona.findBySemestrepersona", query = "SELECT p FROM Persona p WHERE p.semestrepersona = :semestrepersona")
    , @NamedQuery(name = "Persona.findByCarrerapersona", query = "SELECT p FROM Persona p WHERE p.carrerapersona = :carrerapersona")
    , @NamedQuery(name = "Persona.findByCargopersona", query = "SELECT p FROM Persona p WHERE p.cargopersona = :cargopersona")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpersona")
    private Integer idpersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombrepersona")
    private String nombrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellidopersona")
    private String apellidopersona;
    @Column(name = "semestrepersona")
    private Integer semestrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "carrerapersona")
    private String carrerapersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "cargopersona")
    private String cargopersona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersona")
    private Collection<Prestamosalon> prestamosalonCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idpersona")
    private Collection<Prestamo> prestamoCollection;

    public Persona() {
    }

    public Persona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public Persona(Integer idpersona, String nombrepersona, String apellidopersona, String carrerapersona, String cargopersona) {
        this.idpersona = idpersona;
        this.nombrepersona = nombrepersona;
        this.apellidopersona = apellidopersona;
        this.carrerapersona = carrerapersona;
        this.cargopersona = cargopersona;
    }

    public Integer getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Integer idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getApellidopersona() {
        return apellidopersona;
    }

    public void setApellidopersona(String apellidopersona) {
        this.apellidopersona = apellidopersona;
    }

    public Integer getSemestrepersona() {
        return semestrepersona;
    }

    public void setSemestrepersona(Integer semestrepersona) {
        this.semestrepersona = semestrepersona;
    }

    public String getCarrerapersona() {
        return carrerapersona;
    }

    public void setCarrerapersona(String carrerapersona) {
        this.carrerapersona = carrerapersona;
    }

    public String getCargopersona() {
        return cargopersona;
    }

    public void setCargopersona(String cargopersona) {
        this.cargopersona = cargopersona;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prestamosalon> getPrestamosalonCollection() {
        return prestamosalonCollection;
    }

    public void setPrestamosalonCollection(Collection<Prestamosalon> prestamosalonCollection) {
        this.prestamosalonCollection = prestamosalonCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prestamo> getPrestamoCollection() {
        return prestamoCollection;
    }

    public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
        this.prestamoCollection = prestamoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sergio.mundo.vo.Persona[ idpersona=" + idpersona + " ]";
    }
    
}
