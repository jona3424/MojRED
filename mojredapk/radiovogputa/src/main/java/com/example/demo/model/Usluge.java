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
@Table(name = "usluge")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usluge.findAll", query = "SELECT u FROM Usluge u")
    , @NamedQuery(name = "Usluge.findById", query = "SELECT u FROM Usluge u WHERE u.id = :id")
    , @NamedQuery(name = "Usluge.findByNaziv", query = "SELECT u FROM Usluge u WHERE u.naziv = :naziv")
    , @NamedQuery(name = "Usluge.findByVremeUsluge", query = "SELECT u FROM Usluge u WHERE u.vremeUsluge = :vremeUsluge")})
public class Usluge implements Serializable {

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
    @Basic(optional = false)
    @NotNull
    @Column(name = "vreme_usluge")
    private int vremeUsluge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkUsluge")
    private List<UslugeFirmi> uslugeFirmiList;

    public Usluge() {
    }

    public Usluge(Integer id) {
        this.id = id;
    }

    public Usluge(Integer id, String naziv, int vremeUsluge) {
        this.id = id;
        this.naziv = naziv;
        this.vremeUsluge = vremeUsluge;
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

    public int getVremeUsluge() {
        return vremeUsluge;
    }

    public void setVremeUsluge(int vremeUsluge) {
        this.vremeUsluge = vremeUsluge;
    }

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
        if (!(object instanceof Usluge)) {
            return false;
        }
        Usluge other = (Usluge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Usluge[ id=" + id + " ]";
    }
    
}
