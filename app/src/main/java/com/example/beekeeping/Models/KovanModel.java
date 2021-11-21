package com.example.beekeeping.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class KovanModel implements Parcelable {
    private int id;
    private String kovan_no;
    private String kovan_durum;
    private String kovan_tarih;
    private String kovan_kaynak;
    private String kovan_not;

    public KovanModel(int id, String kovan_no, String kovan_durum, String kovan_tarih) {
        this.id = id;
        this.kovan_no = kovan_no;
        this.kovan_durum = kovan_durum;
        this.kovan_tarih = kovan_tarih;
    }

    public KovanModel(int id, String kovan_no, String kovan_durum, String kovan_tarih, String kovan_kaynak , String kovan_not) {
        this.id = id;
        this.kovan_no = kovan_no;
        this.kovan_durum = kovan_durum;
        this.kovan_tarih = kovan_tarih;
        this.kovan_kaynak = kovan_kaynak;
        this.kovan_not = kovan_not;
    }

    protected KovanModel(Parcel in) {
        id = in.readInt();
        kovan_no = in.readString();
        kovan_durum = in.readString();
        kovan_tarih = in.readString();
        kovan_kaynak = in.readString();
        kovan_not = in.readString();
    }

    public static final Creator<KovanModel> CREATOR = new Creator<KovanModel>() {
        @Override
        public KovanModel createFromParcel(Parcel in) {
            return new KovanModel(in);
        }

        @Override
        public KovanModel[] newArray(int size) {
            return new KovanModel[size];
        }
    };

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getKovanNo() {
        return kovan_no;
    }

    public void setKovanNo(String kovan_no) {
        this.kovan_no = kovan_no;
    }

    public String getKovanDurum() {
        return kovan_durum;
    }

    public void setKovanDurum(String kovan_durum) {
        this.kovan_durum = kovan_durum;
    }

    public String getKovanTarih() {
        return kovan_tarih;
    }

    public void setKovanTarih(String kovan_tarih) {
        this.kovan_tarih = kovan_tarih;
    }

    public String getKovanKaynak() {
        return kovan_kaynak;
    }

    public void setKovanKaynak(String kovan_kaynak) {
        this.kovan_kaynak = kovan_kaynak;
    }

    public String getKovanNot() {
        return kovan_not;
    }

    public void setKovanNot(String kovan_not) {
        this.kovan_not = kovan_not;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(kovan_no);
        dest.writeString(kovan_durum);
        dest.writeString(kovan_tarih);
        dest.writeString(kovan_kaynak);
        dest.writeString(kovan_not);
    }
}

