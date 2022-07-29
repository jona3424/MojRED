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
@Table(name = "objekti_u_gradovima")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjektiUGradovima.findAll", query = "SELECT o FROM ObjektiUGradovima o")
    , @NamedQuery(name = "ObjektiUGradovima.findById", query = "SELECT o FROM ObjektiUGradovima o WHERE o.id = :id")})
public class ObjektiUGradovima implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "fk_grada", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Gradovi fkGrada;
    @JoinColumn(name = "fk_objekta", referencedColumnName = "id")
    @ManyToOne(optional = false)
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
