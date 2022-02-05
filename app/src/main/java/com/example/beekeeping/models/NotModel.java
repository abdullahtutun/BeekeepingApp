package com.example.beekeeping.models;

import android.os.Parcel;
import android.os.Parcelable;

public class NotModel implements Parcelable {

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

    protected NotModel(Parcel in) {
        id = in.readInt();
        not_tarih = in.readString();
        not_baslik = in.readString();
        not_icerik = in.readString();
    }

    public static final Creator<NotModel> CREATOR = new Creator<NotModel>() {
        @Override
        public NotModel createFromParcel(Parcel in) {
            return new NotModel(in);
        }

        @Override
        public NotModel[] newArray(int size) {
            return new NotModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(not_tarih);
        dest.writeString(not_baslik);
        dest.writeString(not_icerik);
    }
}
