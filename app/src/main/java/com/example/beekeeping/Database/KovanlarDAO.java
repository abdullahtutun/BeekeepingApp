package com.example.beekeeping.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.beekeeping.Models.KovanModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class KovanlarDAO {

    public ArrayList<KovanModel> getKovanlar(Database vt){

        ArrayList<KovanModel> kovanlarArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from kovanlar order by kovan_tarih desc",null);

        while (c.moveToNext()){
            KovanModel k = new KovanModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("kovan_no"))
                    ,c.getString(c.getColumnIndex("kovan_durum")),c.getString(c.getColumnIndex("kovan_tarih")));
            kovanlarArrayList.add(k);
        }
        db.close();
        return kovanlarArrayList;
    }

    public ArrayList<KovanModel> getAllKovanlar(Database vt){

        ArrayList<KovanModel> kovanlarArrayListTam = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from kovanlar",null);

        while (c.moveToNext()){
            KovanModel k = new KovanModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("kovan_no"))
                    ,c.getString(c.getColumnIndex("kovan_durum")),c.getString(c.getColumnIndex("kovan_tarih"))
                    ,c.getString(c.getColumnIndex("kovan_kaynak")), c.getString(c.getColumnIndex("kovan_not")));
            kovanlarArrayListTam.add(k);
        }
        db.close();
        return kovanlarArrayListTam;
    }

    public void deleteKovan(Database vt, int id, View v){

        SQLiteDatabase db = vt.getWritableDatabase();
        long result = db.delete("kovanlar","id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu!",Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(v,"Kovan silindi!",Snackbar.LENGTH_SHORT).show();
        }
        db.close();
    }

    public void addKovan(@NonNull Database vt, String kovanTarih, String kovanNo, String kovanKaynak, String kovanNot, String kovanDurum, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from kovanlar where kovan_no =?",new String[]{kovanNo});

        if(cursor.getCount() <= 0){

            ContentValues cv = new ContentValues();

            cv.put("kovan_tarih",kovanTarih);
            cv.put("kovan_no",kovanNo);
            cv.put("kovan_kaynak",kovanKaynak);
            cv.put("kovan_not",kovanNot);
            cv.put("kovan_durum",kovanDurum);

            long result = db.insertOrThrow("kovanlar",null,cv);

            if(result == -1){
                Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_SHORT).show();
            }else{
                Snackbar.make(v,"Kovan başarıyla eklendi!",Snackbar.LENGTH_LONG).show();
            }
        }
        else{
            Snackbar.make(v,"Aynı numara/etiket'e sahip bir kovan var!",Snackbar.LENGTH_LONG).show();
        }

        cursor.close();
        db.close();
    }

    public void updateKovan(Database vt,int id,String kovan_tarih,String kovanNo,String kovanKaynak,String kovanNot,String kovanDurum,View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("kovan_tarih",kovan_tarih);
        cv.put("kovan_no",kovanNo);
        cv.put("kovan_kaynak",kovanKaynak);
        cv.put("kovan_not",kovanNot);
        cv.put("kovan_durum",kovanDurum);

        long result = db.update("kovanlar",cv,"id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(v,"Kovan başarıyla güncellendi!",Snackbar.LENGTH_LONG).show();
        }

        db.close();
    }

    public long getCountKovan(Database vt) {

        SQLiteDatabase db = vt.getReadableDatabase();

        long countKovan = DatabaseUtils.queryNumEntries(db,"kovanlar");

        return countKovan;
    }

}
