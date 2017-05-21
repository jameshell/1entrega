/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import edu.co.sergio.mundo.dao.exceptions.Prestamosalon;
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
@Table(name = "salon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salon.findAll", query = "SELECT s FROM Salon s")
    , @NamedQuery(name = "Salon.findByIdsalon", query = "SELECT s FROM Salon s WHERE s.idsalon = :idsalon")
    , @NamedQuery(name = "Salon.findByNombresalon", query = "SELECT s FROM Salon s WHERE s.nombresalon = :nombresalon")})
public class Salon implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsalon")
    private Collection<Prestamosalon> prestamosalonCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idsalon")
    private Integer idsalon;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nombresalon")
    private String nombresalon;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsalon")
    private Collection<Prestamosalon> prestamosalonCollection;
    @OneToMany(mappedBy = "idsalon")
    private Collection<Prestamo> prestamoCollection;

    public Salon() {
    }

    public Salon(Integer idsalon) {
        this.idsalon = idsalon;
    }

    public Salon(Integer idsalon, String nombresalon) {
        this.idsalon = idsalon;
        this.nombresalon = nombresalon;
    }

    public Integer getIdsalon() {
        return idsalon;
    }

    public void setIdsalon(Integer idsalon) {
        this.idsalon = idsalon;
    }

    public String getNombresalon() {
        return nombresalon;
    }

    public void setNombresalon(String nombresalon) {
        this.nombresalon = nombresalon;
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
        hash += (idsalon != null ? idsalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salon)) {
            return false;
        }
        Salon other = (Salon) object;
        if ((this.idsalon == null && other.idsalon != null) || (this.idsalon != null && !this.idsalon.equals(other.idsalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sergio.mundo.vo.Salon[ idsalon=" + idsalon + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prestamosalon> getPrestamosalonCollection() {
        return prestamosalonCollection;
    }

    public void setPrestamosalonCollection(Collection<Prestamosalon> prestamosalonCollection) {
        this.prestamosalonCollection = prestamosalonCollection;
    }
    
}
