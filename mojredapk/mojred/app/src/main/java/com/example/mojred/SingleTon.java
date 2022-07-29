package com.example.mojred;



import com.example.mojred.Entities.Brojevi;
import com.example.mojred.Entities.Firme;
import com.example.mojred.Entities.Gradovi;
import com.example.mojred.Entities.Korisnici;
import com.example.mojred.Entities.Lokacije;
import com.example.mojred.Entities.Objekti;
import com.example.mojred.Entities.Usluge;
import com.example.mojred.Entities.UslugeFirmi;

import java.util.Calendar;
import java.util.List;


public class SingleTon {
    private Long kadCePrvi = null;
    private Integer rednibroj;
    private String namefirme;
    private String nameulice;
    private String nameusluge;
    private Integer brojuredu =0;

    public Long getKadCePrvi() {
        return kadCePrvi;
    }

    public void setKadCePrvi(Long kadCePrvi) {
        this.kadCePrvi = kadCePrvi;
    }

    public Integer getBrojuredu() {
        return brojuredu;
    }

    public void setBrojuredu(Integer brojuredu) {
        this.brojuredu = brojuredu;
    }

    public Integer getRednibroj() {
        return rednibroj;
    }

    public void setRednibroj(Integer rednibroj) {
        this.rednibroj = rednibroj;
    }

    public String getNameusluge() {
        return nameusluge;
    }

    public void setNameusluge(String nameusluge) {
        this.nameusluge = nameusluge;
    }

    public String getNameulice() {
        return nameulice;
    }

    public void setNameulice(String nameulice) {
        this.nameulice = nameulice;
    }

    public String getNamefirme() {
        return namefirme;
    }

    public void setNamefirme(String namefirme) {
        this.namefirme = namefirme;
    }

    private Integer idusluga;

    public Integer getIdusluga() {
        return idusluga;
    }

    public void setIdusluga(Integer idusluga) {
        this.idusluga = idusluga;
    }

    private Integer  idlokfirm;

    public Integer getIdlokfirm() {
        return idlokfirm;
    }

    public void setIdlokfirm(Integer idlokfirm) {
        this.idlokfirm = idlokfirm;
    }

    private Integer fkfirme;

    public Integer getFkfirme() {
        return fkfirme;
    }

    public void setFkfirme(Integer fkfirme) {
        this.fkfirme = fkfirme;
    }

    private Korisnici korisnik;
    private List<Gradovi> gradovi;
    private List<Objekti> objekti;
    private List<Firme> firme;
    private List<Lokacije> lokacije;
    private List<Usluge> usluge;
    private List<Brojevi> brojeviList;
    private UslugeFirmi uslugeFirmi;
    private Brojevi broj;
    private Calendar calendar;

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public List<Brojevi> getBrojeviList() {
        return brojeviList;
    }

    public void setBrojeviList(List<Brojevi> brojeviList) {
        this.brojeviList = brojeviList;
    }

    public Brojevi getBroj() {
        return broj;
    }

    public void setBroj(Brojevi broj) {
        this.broj = broj;
    }

    public UslugeFirmi getUslugeFirmi() {
        return uslugeFirmi;
    }

    public void setUslugeFirmi(UslugeFirmi uslugeFirmi) {
        this.uslugeFirmi = uslugeFirmi;
    }

    public List<Usluge> getUsluge() {
        return usluge;
    }

    public void setUsluge(List<Usluge> usluge) {
        this.usluge = usluge;
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

    public Korisnici getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnici korisnik) {
        this.korisnik = korisnik;
    }


    public List<Gradovi> getGradovi() {
        return gradovi;
    }

    public void setGradovi(List<Gradovi> gradovi) {
        this.gradovi = gradovi;
    }
    public List<Objekti> getObjekti() {
        return objekti;
    }

    public void setObjekti(List<Objekti> objekti) {
        this.objekti = objekti;
    }


    private static final SingleTon ourInstance = new SingleTon();
    public static SingleTon getInstance() {
        return ourInstance;
    }

    private SingleTon(){

    }


 }