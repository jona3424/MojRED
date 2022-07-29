package com.example.mojredupravljac;



import com.example.mojredupravljac.Entities.Brojevi;
import com.example.mojredupravljac.Entities.Firme;
import com.example.mojredupravljac.Entities.Gradovi;
import com.example.mojredupravljac.Entities.Korisnici;
import com.example.mojredupravljac.Entities.Lokacije;
import com.example.mojredupravljac.Entities.Objekti;
import com.example.mojredupravljac.Entities.Usluge;
import com.example.mojredupravljac.Entities.UslugeFirmi;

import java.util.Calendar;
import java.util.List;


public class SingleTon {







    private Integer  idlokfirm;

    public Integer getIdlokfirm() {
        return idlokfirm;
    }

    public void setIdlokfirm(Integer idlokfirm) {
        this.idlokfirm = idlokfirm;
    }




    private List<Gradovi> gradovi;

    private List<Firme> firme;
    private List<Lokacije> lokacije;

    private List<Brojevi> brojeviList;






    public List<Brojevi> getBrojeviList() {
        return brojeviList;
    }

    public void setBrojeviList(List<Brojevi> brojeviList) {
        this.brojeviList = brojeviList;
    }






    public List<Lokacije> getLokacije() {
        return lokacije;
    }

    public void setLokacije(List<Lokacije> lokacije) {
        this.lokacije = lokacije;
    }

    public List<Firme> getFirme() {
        return firme;
    }

    public void setFirme(List<Firme> firme) {
        this.firme = firme;
    }




    public List<Gradovi> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<Gradovi> gradovi) {
        this.gradovi = gradovi;
    }



    private static final SingleTon ourInstance = new SingleTon();
    public static SingleTon getInstance() {
        return ourInstance;
    }

    private SingleTon(){

    }


 }