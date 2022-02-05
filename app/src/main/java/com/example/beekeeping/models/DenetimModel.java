package com.example.beekeeping.models;

import android.os.Parcel;
import android.os.Parcelable;

public class DenetimModel implements Parcelable {
    private int id;
    private String kovan_no,denetim_tarih,ana_ari,kek,surup,diger,hastalik_belirtisi,ilaclama,genel_gozlem,cerceve_sayisi,arili_cerceve,balli_cerceve,kabarik_cerceve,ham_cerceve,gunluk_cerceve,larva_cerceve,kapali_cerceve;

    public DenetimModel(int id, String kovan_no, String denetim_tarih) {
        this.id = id;
        this.kovan_no = kovan_no;
        this.denetim_tarih = denetim_tarih;
    }

    public DenetimModel(int id, String kovan_no, String cerceve_sayisi, String arili_cerceve, String balli_cerceve, String kabarik_cerceve, String ham_cerceve, String gunluk_cerceve, String larva_cerceve, String kapali_cerceve, String denetim_tarih, String ana_ari, String kek, String surup, String diger, String hastalik_belirtisi, String ilaclama, String genel_gozlem) {
        this.id = id;
        this.kovan_no = kovan_no;
        this.cerceve_sayisi = cerceve_sayisi;
        this.arili_cerceve = arili_cerceve;
        this.balli_cerceve = balli_cerceve;
        this.kabarik_cerceve = kabarik_cerceve;
        this.ham_cerceve = ham_cerceve;
        this.gunluk_cerceve = gunluk_cerceve;
        this.larva_cerceve = larva_cerceve;
        this.kapali_cerceve = kapali_cerceve;
        this.denetim_tarih = denetim_tarih;
        this.ana_ari = ana_ari;
        this.kek = kek;
        this.surup = surup;
        this.diger = diger;
        this.hastalik_belirtisi = hastalik_belirtisi;
        this.ilaclama = ilaclama;
        this.genel_gozlem = genel_gozlem;
    }

    protected DenetimModel(Parcel in) {
        id = in.readInt();
        kovan_no = in.readString();
        cerceve_sayisi = in.readString();
        arili_cerceve = in.readString();
        balli_cerceve = in.readString();
        kabarik_cerceve = in.readString();
        ham_cerceve = in.readString();
        gunluk_cerceve = in.readString();
        larva_cerceve = in.readString();
        kapali_cerceve = in.readString();
        denetim_tarih = in.readString();
        ana_ari = in.readString();
        kek = in.readString();
        surup = in.readString();
        diger = in.readString();
        hastalik_belirtisi = in.readString();
        ilaclama = in.readString();
        genel_gozlem = in.readString();
    }

    public static final Creator<DenetimModel> CREATOR = new Creator<DenetimModel>() {
        @Override
        public DenetimModel createFromParcel(Parcel in) {
            return new DenetimModel(in);
        }

        @Override
        public DenetimModel[] newArray(int size) {
            return new DenetimModel[size];
        }
    };

    public String getKek() {
        return kek;
    }

    public void setKek(String kek) {
        this.kek = kek;
    }

    public String getSurup() {
        return surup;
    }

    public void setSurup(String surup) {
        this.surup = surup;
    }

    public String getDiger() {
        return diger;
    }

    public void setDiger(String diger) {
        this.diger = diger;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKovan_no() {
        return kovan_no;
    }

    public void setKovan_no(String kovan_no) {
        this.kovan_no = kovan_no;
    }

    public String getDenetim_tarih() {
        return denetim_tarih;
    }

    public void setDenetim_tarih(String denetim_tarih) {
        this.denetim_tarih = denetim_tarih;
    }

    public String getCerceve_sayisi() {
        return cerceve_sayisi;
    }

    public void setCerceve_sayisi(String cerceve_sayisi) {
        this.cerceve_sayisi = cerceve_sayisi;
    }

    public String getArili_cerceve() {
        return arili_cerceve;
    }

    public void setArili_cerceve(String arili_cerceve) {
        this.arili_cerceve = arili_cerceve;
    }

    public String getBalli_cerceve() {
        return balli_cerceve;
    }

    public void setBalli_cerceve(String balli_cerceve) {
        this.balli_cerceve = balli_cerceve;
    }

    public String getKabarik_cerceve() {
        return kabarik_cerceve;
    }

    public void setKabarik_cerceve(String kabarik_cerceve) {
        this.kabarik_cerceve = kabarik_cerceve;
    }

    public String getHam_cerceve() {
        return ham_cerceve;
    }

    public void setHam_cerceve(String ham_cerceve) {
        this.ham_cerceve = ham_cerceve;
    }

    public String getGunluk_cerceve() {
        return gunluk_cerceve;
    }

    public void setGunluk_cerceve(String gunluk_cerceve) {
        this.gunluk_cerceve = gunluk_cerceve;
    }

    public String getLarva_cerceve() {
        return larva_cerceve;
    }

    public void setLarva_cerceve(String larva_cerceve) {
        this.larva_cerceve = larva_cerceve;
    }

    public String getKapali_cerceve() {
        return kapali_cerceve;
    }

    public void setKapali_cerceve(String kapali_cerceve) {
        this.kapali_cerceve = kapali_cerceve;
    }

    public String getAna_ari() {
        return ana_ari;
    }

    public void setAna_ari(String ana_ari) {
        this.ana_ari = ana_ari;
    }

    public String getHastalik_belirtisi() {
        return hastalik_belirtisi;
    }

    public void setHastalik_belirtisi(String hastalik_belirtisi) {
        this.hastalik_belirtisi = hastalik_belirtisi;
    }

    public String getIlaclama() {
        return ilaclama;
    }

    public void setIlaclama(String ilaclama) {
        this.ilaclama = ilaclama;
    }

    public String getGenel_gozlem() {
        return genel_gozlem;
    }

    public void setGenel_gozlem(String genel_gozlem) {
        this.genel_gozlem = genel_gozlem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(kovan_no);
        dest.writeString(cerceve_sayisi);
        dest.writeString(arili_cerceve);
        dest.writeString(balli_cerceve);
        dest.writeString(kabarik_cerceve);
        dest.writeString(ham_cerceve);
        dest.writeString(gunluk_cerceve);
        dest.writeString(larva_cerceve);
        dest.writeString(kapali_cerceve);
        dest.writeString(denetim_tarih);
        dest.writeString(ana_ari);
        dest.writeString(kek);
        dest.writeString(surup);
        dest.writeString(diger);
        dest.writeString(hastalik_belirtisi);
        dest.writeString(ilaclama);
        dest.writeString(genel_gozlem);
    }
}

