/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "firme_objekata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FirmeObjekata.findAll", query = "SELECT f FROM FirmeObjekata f")
    , @NamedQuery(name = "FirmeObjekata.findById", query = "SELECT f FROM FirmeObjekata f WHERE f.id = :id")})
public class FirmeObjekata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "fk_firme", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Firme fkFirme;
    @JoinColumn(name = "fk_objekta", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Objekti fkObjekta;

    public FirmeObjekata() {
    }

    public FirmeObjekata(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Firme getFkFirme() {
        return fkFirme;
    }

    public void setFkFirme(Firme fkFirme) {
        this.fkFirme = fkFirme;
    }

    public Objekti getFkObjekta() {
        return fkObjekta;
    }

    public void setFkObjekta(Objekti fkObjekta) {
        this.fkObjekta = fkObjekta;
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
        if (!(object instanceof FirmeObjekata)) {
            return false;
        }
        FirmeObjekata other = (FirmeObjekata) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.FirmeObjekata[ id=" + id + " ]";
    }
    
}
