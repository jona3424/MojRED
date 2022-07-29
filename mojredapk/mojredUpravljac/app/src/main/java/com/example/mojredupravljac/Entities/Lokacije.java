/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mojredupravljac.Entities;

import java.io.Serializable;
import java.util.List;

public class Lokacije implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String adresa;
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
