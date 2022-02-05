package com.example.beekeeping.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "arilik_takip.sqlite";
    private static final int DATABASE_VERSION = 1;

    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE kovanlar (id INTEGER PRIMARY KEY AUTOINCREMENT, kovan_tarih TEXT, kovan_no TEXT, kovan_kaynak TEXT, kovan_not TEXT, kovan_durum TEXT )");
        db.execSQL("CREATE TABLE denetimler (id INTEGER PRIMARY KEY AUTOINCREMENT, kovan_no TEXT, denetim_tarih TEXT, cerceve_sayisi TEXT, arili_cerceve TEXT, " +
                "balli_cerceve TEXT, kabarik_cerceve TEXT, ham_cerceve TEXT, ana_ari TEXT, gunluk_cerceve TEXT, larva_cerceve TEXT, kapali_cerceve TEXT," +
                "kek TEXT, surup TEXT, diger TEXT, hastalik_belirtisi TEXT, ilaclama TEXT, genel_gozlem TEXT )");
        db.execSQL("CREATE TABLE gorevler (id INTEGER PRIMARY KEY AUTOINCREMENT, eklenme_tarih TEXT, tamamlanma_tarih TEXT, gorev_baslik TEXT, gorev_icerik TEXT )");
        db.execSQL("CREATE TABLE notlar (id INTEGER PRIMARY KEY AUTOINCREMENT, not_tarih TEXT, not_baslik TEXT, not_icerik TEXT )");
        db.execSQL("CREATE TABLE events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT, time TEXT,date TEXT,month TEXT, year TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists DATABASE_NAME");
        onCreate(db);
    }
}
