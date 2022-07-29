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
@Table(name = "usluge_firmi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UslugeFirmi.findAll", query = "SELECT u FROM UslugeFirmi u")
    , @NamedQuery(name = "UslugeFirmi.findById", query = "SELECT u FROM UslugeFirmi u WHERE u.id = :id")})
public class UslugeFirmi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsluge")
    private List<Brojevi> brojeviList;
    @JoinColumn(name = "fk_firme", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Firme fkFirme;
    @JoinColumn(name = "fk_usluge", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usluge fkUsluge;

    public UslugeFirmi() {
    }

    public UslugeFirmi(Integer id) {
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

    public Usluge getFkUsluge() {
        return fkUsluge;
    }

    public void setFkUsluge(Usluge fkUsluge) {
        this.fkUsluge = fkUsluge;
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
        if (!(object instanceof UslugeFirmi)) {
            return false;
        }
        UslugeFirmi other = (UslugeFirmi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.UslugeFirmi[ id=" + id + " ]";
    }
    
}
