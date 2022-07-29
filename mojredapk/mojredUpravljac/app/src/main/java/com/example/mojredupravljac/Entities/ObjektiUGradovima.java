/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.mojredupravljac.Entities;

import java.io.Serializable;

public class ObjektiUGradovima implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Gradovi fkGrada;
    private Objekti fkObjekta;

    public ObjektiUGradovima() {
    }

    public ObjektiUGradovima(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Gradovi getFkGrada() {
        return fkGrada;
    }

    public void setFkGrada(Gradovi fkGrada) {
        this.fkGrada = fkGrada;
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
        if (!(object instanceof ObjektiUGradovima)) {
            return false;
        }
        ObjektiUGradovima other = (ObjektiUGradovima) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.model.ObjektiUGradovima[ id=" + id + " ]";
    }
    
}
