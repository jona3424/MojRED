
package com.example.mojred.Entities;

import java.io.Serializable;



public class Brojevi implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer id;
   
    private boolean uredu;
  
    private Korisnici fkKorisnici;
   
    private UslugeFirmi fkUsluge;
  
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

    //Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    //Override
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

    //Override
    public String toString() {
        return "com.example.demo.model.Brojevi[ id=" + id + " ]";
    }
    
}
