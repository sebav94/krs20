package com.example.ania.krs20.models;

import java.util.List;

/**
 * Created by Ania on 2017-05-17.
 */
public class ModelFirmy {

    private String nazwa;
    private String krs;
    private String forma;
    private String adres;
    private String nip;
    private String seria;
    private String liczba;
    private String rodzaj;
    private String id;
    private String udzialyWartoscJedn;
    private String udzialyLiczba;
    private String udzalyWartosc;

    public String getUdzialyWartoscJedn() {
        return udzialyWartoscJedn;
    }

    public void setUdzialyWartoscJedn(String udzialyWartoscJedn) {
        this.udzialyWartoscJedn = udzialyWartoscJedn;
    }

    public String getUdzialyLiczba() {
        return udzialyLiczba;
    }

    public void setUdzialyLiczba(String udzialyLiczba) {
        this.udzialyLiczba = udzialyLiczba;
    }

    public String getUdzalyWartosc() {
        return udzalyWartosc;
    }

    public void setUdzalyWartosc(String udzalyWartosc) {
        this.udzalyWartosc = udzalyWartosc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSeria() {
        return seria;
    }

    public void setSeria(String seria) {
        this.seria = seria;
    }

    public String getLiczba() {
        return liczba;
    }

    public void setLiczba(String liczba) {
        this.liczba = liczba;
    }

    public String getRodzaj() {
        return rodzaj;
    }

    public void setRodzaj(String rodzaj) {
        this.rodzaj = rodzaj;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKrs() {
        return krs;
    }

    public void setKrs(String krs) {
        this.krs = krs;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
