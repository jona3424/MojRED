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
@Table(name = "lokacije")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lokacije.findAll", query = "SELECT l FROM Lokacije l")
    , @NamedQuery(name = "Lokacije.findById", query = "SELECT l FROM Lokacije l WHERE l.id = :id")
    , @NamedQuery(name = "Lokacije.findByAdresa", query = "SELECT l FROM Lokacije l WHERE l.adresa = :adresa")})
public class Lokacije implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 120)
    @Column(name = "adresa")
    private String adresa;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLokacije")
    private List<LokacijeFirmi> lokacijeFirmiList;

    public Lokacije() {
    }

    public Lokacije(Integer id) {
        this.id = id;
    }

    public Lokacije(Integer id, String adresa) {
        this.id = id;
        this.adresa = adresa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
//
//    @XmlTransient
//    public List<LokacijeFirmi> getLokacijeFirmiList() {
//        return lokacijeFirmiList;
//    }
//
//    public void setLokacijeFirmiList(List<LokacijeFirmi> lokacijeFirmiList) {
//        this.lokacijeFirmiList = lokacijeFirmiList;
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
        if (!(object instanceof Lokacije)) {
            return false;
        }
        Lokacije other = (Lokacije) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Lokacije[ id=" + id + " ]";
    }
    
}
