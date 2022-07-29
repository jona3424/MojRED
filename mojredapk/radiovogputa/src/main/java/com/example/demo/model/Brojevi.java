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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author korisnik
 */
@Entity
@Table(name = "brojevi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Brojevi.findAll", query = "SELECT b FROM Brojevi b")
    , @NamedQuery(name = "Brojevi.findById", query = "SELECT b FROM Brojevi b WHERE b.id = :id")
    , @NamedQuery(name = "Brojevi.findByUredu", query = "SELECT b FROM Brojevi b WHERE b.uredu = :uredu")
    /* @NamedQuery(name = "Brojevi.stigaoNaRed", query = "UPDATE brojevi SET uredu=0 WHERE id = :id")}*/})
public class Brojevi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uredu")
    private boolean uredu;
    @JoinColumn(name = "fk_korisnici", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Korisnici fkKorisnici;
    @JoinColumn(name = "fk_usluge", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UslugeFirmi fkUsluge;
    @JoinColumn(name = "fk_lokacije", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LokacijeFirmi fkLokacije;

    public Brojevi() {
    }

    public Brojevi(Integer id) {
        this.id = id;
    }

    public Brojevi(Integer id, boolean uredu) {
        this.id = id;
        this.uredu = uredu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getUredu() {
        return uredu;
    }

    public void setUredu(boolean uredu) {
        this.uredu = uredu;
    }

    public Korisnici getFkKorisnici() {
        return fkKorisnici;
    }

    public void setFkKorisnici(Korisnici fkKorisnici) {
        this.fkKorisnici = fkKorisnici;
    }

    public UslugeFirmi getFkUsluge() {
        return fkUsluge;
    }

    public void setFkUsluge(UslugeFirmi fkUsluge) {
        this.fkUsluge = fkUsluge;
    }

    public LokacijeFirmi getFkLokacije() {
        return fkLokacije;
    }

    public void setFkLokacije(LokacijeFirmi fkLokacije) {
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
        if (!(object instanceof Brojevi)) {
            return false;
        }
        Brojevi other = (Brojevi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Brojevi[ id=" + id + " ]";
    }
    
}
