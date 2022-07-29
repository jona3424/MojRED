/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mojredupravljac.Entities;

import java.io.Serializable;
import java.util.List;

public class UslugeFirmi implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private List<Brojevi> brojeviList;
    private Firme fkFirme;
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
