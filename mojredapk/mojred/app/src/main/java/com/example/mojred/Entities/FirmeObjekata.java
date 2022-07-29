/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mojred.Entities;

import java.io.Serializable;
public class FirmeObjekata implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Firme fkFirme;

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
