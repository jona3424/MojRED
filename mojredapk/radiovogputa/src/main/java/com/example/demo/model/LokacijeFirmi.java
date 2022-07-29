/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "lokacije_firmi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LokacijeFirmi.findAll", query = "SELECT l FROM LokacijeFirmi l")
    , @NamedQuery(name = "LokacijeFirmi.findById", query = "SELECT l FROM LokacijeFirmi l WHERE l.id = :id")})
public class LokacijeFirmi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLokacije")
    private List<Brojevi> brojeviList;
    @JoinColumn(name = "fk_firme", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Firme fkFirme;
    @JoinColumn(name = "fk_lokacije", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lokacije fkLokacije;

    public LokacijeFirmi() {
    }

    public LokacijeFirmi(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @XmlTransient
//    public List<Brojevi> getBrojeviList() {
//        return brojeviList;
//    }
//
//    public void setBrojeviList(List<Brojevi> brojeviList) {
//        this.brojeviList = brojeviList;
//    }

    public Firme getFkFirme() {
        return fkFirme;
    }

    public void setFkFirme(Firme fkFirme) {
        this.fkFirme = fkFirme;
    }

    public Lokacije getFkLokacije() {
        return fkLokacije;
    }

    public void setFkLokacije(Lokacije fkLokacije) {
        this.fkLokacije = fkLokacije;
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
        if (!(object instanceof LokacijeFirmi)) {
            return false;
        }
        LokacijeFirmi other = (LokacijeFirmi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.LokacijeFirmi[ id=" + id + " ]";
    }
    
}
