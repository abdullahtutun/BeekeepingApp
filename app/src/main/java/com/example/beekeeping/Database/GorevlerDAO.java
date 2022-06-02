package com.example.beekeeping.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.beekeeping.Models.GorevModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class GorevlerDAO {

    public ArrayList<GorevModel> getGorevler(Database vt){

        ArrayList<GorevModel> gorevlerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from gorevler order by tamamlanma_tarih asc",null);

        while (c.moveToNext()){
            GorevModel k = new GorevModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("tamamlanma_tarih"))
                    ,c.getString(c.getColumnIndex("gorev_baslik")));
            gorevlerArrayList.add(k);
        }
        db.close();
        return gorevlerArrayList;
    }

    public ArrayList<GorevModel> getAllGorevler(Database vt){

        ArrayList<GorevModel> gorevlerArrayListTam = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from gorevler",null);

        while (c.moveToNext()){
            GorevModel a = new GorevModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("eklenme_tarih")),c.getString(c.getColumnIndex("tamamlanma_tarih")),
                    c.getString(c.getColumnIndex("gorev_baslik")), c.getString(c.getColumnIndex("gorev_icerik")));
            gorevlerArrayListTam.add(a);
        }
        db.close();
        return gorevlerArrayListTam;
    }


    public void deleteGorev(Database vt, int id, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        long result = db.delete("gorevler","id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu!",Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(v,"Görev başarıyla silindi!",Snackbar.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void addGorev(Database vt, String eklenme_tarih, String tamamlanma_tarih, String gorev_baslik, String gorev, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from gorevler where gorev_baslik =?",new String[]{gorev_baslik});

        if(cursor.getCount() <= 0){

            ContentValues cv = new ContentValues();

            cv.put("eklenme_tarih",eklenme_tarih);
            cv.put("tamamlanma_tarih",tamamlanma_tarih);
            cv.put("gorev_baslik",gorev_baslik);
            cv.put("gorev_icerik",gorev);

            long result = db.insertOrThrow("gorevler",null,cv);

            if(result == -1){
                Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_SHORT).show();
            }else{
                Snackbar.make(v,"Görev başarıyla eklendi!",Snackbar.LENGTH_LONG).show();
            }
        }else{
            Snackbar.make(v,"Aynı başlığa sahip bir görev var!",Snackbar.LENGTH_LONG).show();
        }

        db.close();
    }

    public void updateGorev(Database vt,int id,String eklenme_tarih,String tamamlanma_tarih,String gorev_baslik,String gorev,View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("eklenme_tarih",eklenme_tarih);
        cv.put("tamamlanma_tarih",tamamlanma_tarih);
        cv.put("gorev_baslik",gorev_baslik);
        cv.put("gorev_icerik",gorev);

        long result = db.update("gorevler",cv,"id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(v,"Görev başarıyla güncellendi!",Snackbar.LENGTH_LONG).show();
        }

        db.close();
    }

    public long getCountGorev(Database vt) {

        SQLiteDatabase db = vt.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db,"gorevler");

        return count;
    }








}
