package com.example.beekeeping.Models;

public class NotModel {
    private int id;
    private String not_tarih,not_baslik,not_icerik;

    public NotModel(int id, String not_tarih, String not_baslik) {
        this.id = id;
        this.not_tarih = not_tarih;
        this.not_baslik = not_baslik;
    }

    public NotModel(int id, String not_tarih, String not_baslik, String not_icerik) {
        this.id = id;
        this.not_tarih = not_tarih;
        this.not_baslik = not_baslik;
        this.not_icerik = not_icerik;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNot_tarih() {
        return not_tarih;
    }

    public void setNot_tarih(String not_tarih) {
        this.not_tarih = not_tarih;
    }

    public String getNot_baslik() {
        return not_baslik;
    }

    public void setNot_baslik(String not_baslik) {
        this.not_baslik = not_baslik;
    }

    public String getNot_icerik() {
        return not_icerik;
    }

    public void setNot_icerik(String not_icerik) {
        this.not_icerik = not_icerik;
    }
}
