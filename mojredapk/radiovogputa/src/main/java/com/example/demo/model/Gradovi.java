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
@Table(name = "gradovi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gradovi.findAll", query = "SELECT g FROM Gradovi g")
    , @NamedQuery(name = "Gradovi.findById", query = "SELECT g FROM Gradovi g WHERE g.id = :id")
    , @NamedQuery(name = "Gradovi.findByNaziv", query = "SELECT g FROM Gradovi g WHERE g.naziv = :naziv")})
public class Gradovi implements Serializable {

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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkGrada")
    private List<ObjektiUGradovima> objektiUGradovimaList;

    public Gradovi() {
    }

    public Gradovi(Integer id) {
        this.id = id;
    }

    public Gradovi(Integer id, String naziv) {
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
//    public List<ObjektiUGradovima> getObjektiUGradovimaList() {
//        return objektiUGradovimaList;
//    }
//
//    public void setObjektiUGradovimaList(List<ObjektiUGradovima> objektiUGradovimaList) {
//        this.objektiUGradovimaList = objektiUGradovimaList;
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
        if (!(object instanceof Gradovi)) {
            return false;
        }
        Gradovi other = (Gradovi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.Gradovi[ id=" + id + " ]";
    }
    
}
