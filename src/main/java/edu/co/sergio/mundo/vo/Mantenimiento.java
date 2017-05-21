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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "mantenimiento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mantenimiento.findAll", query = "SELECT m FROM Mantenimiento m")
    , @NamedQuery(name = "Mantenimiento.findByIdmantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.idmantenimiento = :idmantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByReffabricante", query = "SELECT m FROM Mantenimiento m WHERE m.reffabricante = :reffabricante")
    , @NamedQuery(name = "Mantenimiento.findByEnservicio", query = "SELECT m FROM Mantenimiento m WHERE m.enservicio = :enservicio")
    , @NamedQuery(name = "Mantenimiento.findByNivelimportancia", query = "SELECT m FROM Mantenimiento m WHERE m.nivelimportancia = :nivelimportancia")
    , @NamedQuery(name = "Mantenimiento.findByTipomantenimiento", query = "SELECT m FROM Mantenimiento m WHERE m.tipomantenimiento = :tipomantenimiento")
    , @NamedQuery(name = "Mantenimiento.findByEntidadcargo", query = "SELECT m FROM Mantenimiento m WHERE m.entidadcargo = :entidadcargo")
    , @NamedQuery(name = "Mantenimiento.findByMombretecnico", query = "SELECT m FROM Mantenimiento m WHERE m.mombretecnico = :mombretecnico")
    , @NamedQuery(name = "Mantenimiento.findByFechainicio", query = "SELECT m FROM Mantenimiento m WHERE m.fechainicio = :fechainicio")
    , @NamedQuery(name = "Mantenimiento.findByFechafinal", query = "SELECT m FROM Mantenimiento m WHERE m.fechafinal = :fechafinal")})
public class Mantenimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmantenimiento")
    private Integer idmantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "reffabricante")
    private String reffabricante;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "enservicio")
    private String enservicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivelimportancia")
    private int nivelimportancia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "tipomantenimiento")
    private String tipomantenimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "entidadcargo")
    private String entidadcargo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "mombretecnico")
    private String mombretecnico;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafinal")
    @Temporal(TemporalType.DATE)
    private Date fechafinal;
    @JoinColumn(name = "idherramienta", referencedColumnName = "idherramienta")
    @ManyToOne(optional = false)
    private Herramienta idherramienta;

    public Mantenimiento() {
    }

    public Mantenimiento(Integer idmantenimiento) {
        this.idmantenimiento = idmantenimiento;
    }

    public Mantenimiento(Integer idmantenimiento, String reffabricante, String enservicio, int nivelimportancia, String tipomantenimiento, String entidadcargo, String mombretecnico) {
        this.idmantenimiento = idmantenimiento;
        this.reffabricante = reffabricante;
        this.enservicio = enservicio;
        this.nivelimportancia = nivelimportancia;
        this.tipomantenimiento = tipomantenimiento;
        this.entidadcargo = entidadcargo;
        this.mombretecnico = mombretecnico;
    }

    public Integer getIdmantenimiento() {
        return idmantenimiento;
    }

    public void setIdmantenimiento(Integer idmantenimiento) {
        this.idmantenimiento = idmantenimiento;
    }

    public String getReffabricante() {
        return reffabricante;
    }

    public void setReffabricante(String reffabricante) {
        this.reffabricante = reffabricante;
    }

    public String getEnservicio() {
        return enservicio;
    }

    public void setEnservicio(String enservicio) {
        this.enservicio = enservicio;
    }

    public int getNivelimportancia() {
        return nivelimportancia;
    }

    public void setNivelimportancia(int nivelimportancia) {
        this.nivelimportancia = nivelimportancia;
    }

    public String getTipomantenimiento() {
        return tipomantenimiento;
    }

    public void setTipomantenimiento(String tipomantenimiento) {
        this.tipomantenimiento = tipomantenimiento;
    }

    public String getEntidadcargo() {
        return entidadcargo;
    }

    public void setEntidadcargo(String entidadcargo) {
        this.entidadcargo = entidadcargo;
    }

    public String getMombretecnico() {
        return mombretecnico;
    }

    public void setMombretecnico(String mombretecnico) {
        this.mombretecnico = mombretecnico;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafinal() {
        return fechafinal;
    }

    public void setFechafinal(Date fechafinal) {
        this.fechafinal = fechafinal;
    }

    public Herramienta getIdherramienta() {
        return idherramienta;
    }

    public void setIdherramienta(Herramienta idherramienta) {
        this.idherramienta = idherramienta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmantenimiento != null ? idmantenimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mantenimiento)) {
            return false;
        }
        Mantenimiento other = (Mantenimiento) object;
        if ((this.idmantenimiento == null && other.idmantenimiento != null) || (this.idmantenimiento != null && !this.idmantenimiento.equals(other.idmantenimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.co.sergio.mundo.vo.Mantenimiento[ idmantenimiento=" + idmantenimiento + " ]";
    }
    
}
