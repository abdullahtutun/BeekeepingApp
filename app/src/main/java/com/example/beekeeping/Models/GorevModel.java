package com.example.beekeeping.Models;

public class GorevModel {
    private int id;
    private String tamamlanma_tarih,gorev_baslik,eklenme_tarih,gorev;

    public GorevModel(int id, String eklenme_tarih, String tamamlanma_tarih, String gorev_baslik, String gorev) {
        this.id = id;
        this.tamamlanma_tarih = tamamlanma_tarih;
        this.gorev_baslik = gorev_baslik;
        this.eklenme_tarih = eklenme_tarih;
        this.gorev = gorev;
    }

    public GorevModel(int id, String tamamlanma_tarih, String gorev_baslik) {
        this.id = id;
        this.tamamlanma_tarih = tamamlanma_tarih;
        this.gorev_baslik = gorev_baslik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTamamlanma_tarih() {
        return tamamlanma_tarih;
    }

    public void setTamamlanma_tarih(String tamamlanma_tarih) {
        this.tamamlanma_tarih = tamamlanma_tarih;
    }

    public String getGorev_baslik() {
        return gorev_baslik;
    }

    public void setGorev_baslik(String gorev_baslik) {
        this.gorev_baslik = gorev_baslik;
    }

    public String getEklenme_tarih() {
        return eklenme_tarih;
    }

    public void setEklenme_tarih(String eklenme_tarih) {
        this.eklenme_tarih = eklenme_tarih;
    }

    public String getGorev() {
        return gorev;
    }

    public void setGorev(String gorev) {
        this.gorev = gorev;
    }

}

