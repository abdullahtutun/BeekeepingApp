package com.example.beekeeping.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class GorevModel implements Parcelable {
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

    protected GorevModel(Parcel in) {
        id = in.readInt();
        tamamlanma_tarih = in.readString();
        gorev_baslik = in.readString();
        eklenme_tarih = in.readString();
        gorev = in.readString();
    }

    public static final Creator<GorevModel> CREATOR = new Creator<GorevModel>() {
        @Override
        public GorevModel createFromParcel(Parcel in) {
            return new GorevModel(in);
        }

        @Override
        public GorevModel[] newArray(int size) {
            return new GorevModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(tamamlanma_tarih);
        dest.writeString(gorev_baslik);
        dest.writeString(eklenme_tarih);
        dest.writeString(gorev);
    }
}

