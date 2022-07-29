/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mojred.Entities;

import java.io.Serializable;
import java.util.List;

public class Usluge implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String naziv;

    private int vremeUsluge;
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
