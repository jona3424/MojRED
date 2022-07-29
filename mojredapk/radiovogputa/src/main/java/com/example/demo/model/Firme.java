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
 * @author korisnik
 */
@Entity
@Table(name = "firme")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Firme.findAll", query = "SELECT f FROM Firme f")
    , @NamedQuery(name = "Firme.findById", query = "SELECT f FROM Firme f WHERE f.id = :id")
    , @NamedQuery(name = "Firme.findByNaziv", query = "SELECT f FROM Firme f WHERE f.naziv = :naziv")})
public class Firme implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFirme")
    private List<FirmeObjekata> firmeObjekataList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFirme")
    private List<LokacijeFirmi> lokacijeFirmiList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkFirme")
    private List<UslugeFirmi> uslugeFirmiList;

    public Firme() {
    }

    public Firme(Integer id) {
        this.id = id;
    }

    public Firme(Integer id, String naziv) {
        this.id = id;
        this.naziv = naziv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

//    @XmlTransient
//    public List<FirmeObjekata> getFirmeObjekataList() {
//        return firmeObjekataList;
//    }
//
//    public void setFirmeObjekataList(List<FirmeObjekata> firmeObjekataList) {
//        this.firmeObjekataList = firmeObjekataList;
//    }
//
//    @XmlTransient
//    public List<LokacijeFirmi> getLokacijeFirmiList() {
//        return lokacijeFirmiList;
//    }
//
//    public void setLokacijeFirmiList(List<LokacijeFirmi> lokacijeFirmiList) {
//        this.lokacijeFirmiList = lokacijeFirmiList;
//    }
//
//    @XmlTransient
//    public List<UslugeFirmi> getUslugeFirmiList() {
//        return uslugeFirmiList;
//    }
//
//    public void setUslugeFirmiList(List<UslugeFirmi> uslugeFirmiList) {
//        this.uslugeFirmiList = uslugeFirmiList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Firme)) {
            return false;
        }
        Firme other = (Firme) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Firme[ id=" + id + " ]";
    }
    
}
