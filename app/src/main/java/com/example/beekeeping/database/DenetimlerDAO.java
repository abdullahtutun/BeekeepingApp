package com.example.beekeeping.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.beekeeping.models.DenetimModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DenetimlerDAO {

    public ArrayList<DenetimModel> getDenetimler(@NonNull Database vt, String kovanNo){

        ArrayList<DenetimModel> denetimlerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from denetimler where kovan_no =" + kovanNo + " order by denetim_tarih DESC",null);

        while (c.moveToNext()){
            DenetimModel k = new DenetimModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("kovan_no"))
                    ,c.getString(c.getColumnIndex("denetim_tarih")));
            denetimlerArrayList.add(k);
        }
        db.close();
        return denetimlerArrayList;
    }

    public ArrayList<DenetimModel> getAllDenetimler(Database vt, String kovanNo){

        ArrayList<DenetimModel> denetimlerArrayListTam = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from denetimler where kovan_no =" + kovanNo + " order by denetim_tarih DESC",null);

        while (c.moveToNext()){
            DenetimModel k = new DenetimModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("kovan_no"))
                    ,c.getString(c.getColumnIndex("cerceve_sayisi")),c.getString(c.getColumnIndex("arili_cerceve"))
                    ,c.getString(c.getColumnIndex("balli_cerceve")),c.getString(c.getColumnIndex("kabarik_cerceve"))
                    ,c.getString(c.getColumnIndex("ham_cerceve")),c.getString(c.getColumnIndex("gunluk_cerceve"))
                    ,c.getString(c.getColumnIndex("larva_cerceve")),c.getString(c.getColumnIndex("kapali_cerceve"))
                    ,c.getString(c.getColumnIndex("denetim_tarih")),c.getString(c.getColumnIndex("ana_ari"))
                    ,c.getString(c.getColumnIndex("kek")),c.getString(c.getColumnIndex("surup"))
                    ,c.getString(c.getColumnIndex("diger")),c.getString(c.getColumnIndex("hastalik_belirtisi"))
                    ,c.getString(c.getColumnIndex("ilaclama")),c.getString(c.getColumnIndex("genel_gozlem")));
            denetimlerArrayListTam.add(k);
        }
        db.close();
        return denetimlerArrayListTam;
    }

    public void deleteDenetim(@NonNull Database vt, int id,View v){

        SQLiteDatabase db = vt.getWritableDatabase();
        long result = db.delete("denetimler","id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu!",Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(v,"Denetim silindi!",Snackbar.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void deleteDenetimForKovan(@NonNull Database vt, String kovan_no){

        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("denetimler","kovan_no=?",new String[]{kovan_no});
        db.close();
    }

    public void addDenetim(@NonNull Database vt, String kovan_no, String denetim_tarih, String cerceve_sayisi, String arili_cerceve, String balli_cerceve,
                           String kabarik_cerceve, String ham_cerceve, String ana_ari, String gunluk_cerceve, String larva_cerceve, String kapali_cerceve, String kek,
                           String surup, String diger, String hastalik_belirtisi, String ilaclama, String genel_gozlem, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

            cv.put("kovan_no",kovan_no);
            cv.put("denetim_tarih",denetim_tarih);
            cv.put("cerceve_sayisi",cerceve_sayisi);
            cv.put("arili_cerceve",arili_cerceve);
            cv.put("balli_cerceve",balli_cerceve);
            cv.put("kabarik_cerceve",kabarik_cerceve);
            cv.put("ham_cerceve",ham_cerceve);
            cv.put("ana_ari",ana_ari);
            cv.put("gunluk_cerceve",gunluk_cerceve);
            cv.put("larva_cerceve",larva_cerceve);
            cv.put("kapali_cerceve",kapali_cerceve);
            cv.put("kek",kek);
            cv.put("surup",surup);
            cv.put("diger",diger);
            cv.put("hastalik_belirtisi",hastalik_belirtisi);
            cv.put("ilaclama",ilaclama);
            cv.put("genel_gozlem",genel_gozlem);

            long result = db.insertOrThrow("denetimler",null,cv);

            if(result == -1){
                Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(v,"Denetim başarıyla eklendi!",Snackbar.LENGTH_LONG).show();
            }

        db.close();
    }

    public int getCountDenetim(@NonNull Database vt, String kovanNo) {

        String countQuery = "SELECT  * FROM denetimler where kovan_no ="+kovanNo ;
        SQLiteDatabase db = vt.getReadableDatabase();

        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        db.close();

        return count;
    }
}
