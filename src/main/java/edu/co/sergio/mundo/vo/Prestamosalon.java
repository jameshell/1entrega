/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.co.sergio.mundo.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "prestamosalon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamosalon.findAll", query = "SELECT p FROM Prestamosalon p")
    , @NamedQuery(name = "Prestamosalon.findByIdprestamosalon", query = "SELECT p FROM Prestamosalon p WHERE p.idprestamosalon = :idprestamosalon")
    , @NamedQuery(name = "Prestamosalon.findByFechaentrada", query = "SELECT p FROM Prestamosalon p WHERE p.fechaentrada = :fechaentrada")
    , @NamedQuery(name = "Prestamosalon.findByFechasalida", query = "SELECT p FROM Prestamosalon p WHERE p.fechasalida = :fechasalida")})
public class Prestamosalon implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idprestamosalon")
    private Integer idprestamosalon;
    @Column(name = "fechaentrada")
    @Temporal(TemporalType.DATE)
    private Date fechaentrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechasalida")
    @Temporal(TemporalType.DATE)
    private Date fechasalida;
    @JoinColumn(name = "idpersona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona idpersona;
    @JoinColumn(name = "idsalon", referencedColumnName = "idsalon")
    @ManyToOne(optional = false)
    private Salon idsalon;

    public Prestamosalon() {
    }

    public Prestamosalon(Integer idprestamosalon) {
        this.idprestamosalon = idprestamosalon;
    }

    public Prestamosalon(Integer idprestamosalon, Date fechasalida) {
        this.idprestamosalon = idprestamosalon;
        this.fechasalida = fechasalida;
    }

    public Integer getIdprestamosalon() {
        return idprestamosalon;
    }

    public void setIdprestamosalon(Integer idprestamosalon) {
        this.idprestamosalon = idprestamosalon;
    }

    public Date getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Persona getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(Persona idpersona) {
        this.idpersona = idpersona;
    }

    public Salon getIdsalon() {
        return idsalon;
    }

    public void setIdsalon(Salon idsalon) {
        this.idsalon = idsalon;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idprestamosalon != null ? idprestamosalon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamosalon)) {
            return false;
        }
        Prestamosalon other = (Prestamosalon) object;
        if ((this.idprestamosalon == null && other.idprestamosalon != null) || (this.idprestamosalon != null && !this.idprestamosalon.equals(other.idprestamosalon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sergio.mundo.vo.Prestamosalon[ idprestamosalon=" + idprestamosalon + " ]";
    }
    
}
