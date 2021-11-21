package com.example.beekeeping.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beekeeping.Models.GorevModel;

import java.util.ArrayList;

public class GorevlerDAO {

    public ArrayList<GorevModel> getGorevler(Database vt){

        ArrayList<GorevModel> gorevlerArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from gorevler",null);

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
                    c.getString(c.getColumnIndex("gorev_baslik")), c.getString(c.getColumnIndex("gorev")));
            gorevlerArrayListTam.add(a);
        }
        db.close();
        return gorevlerArrayListTam;
    }


    public void deleteGorev(Database vt, int id){

        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("gorevler","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void addGorev(Database vt,String eklenme_tarih,String tamamlanma_tarih,String gorev_baslik,String gorev){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("eklenme_tarih",eklenme_tarih);
        cv.put("tamamlanma_tarih",tamamlanma_tarih);
        cv.put("gorev_baslik",gorev_baslik);
        cv.put("gorev",gorev);

        db.insertOrThrow("gorevler",null,cv);
        db.close();
    }

    public void updateGorev(Database vt,int id,String eklenme_tarih,String tamamlanma_tarih,String gorev_baslik,String gorev){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("eklenme_tarih",eklenme_tarih);
        cv.put("tamamlanma_tarih",tamamlanma_tarih);
        cv.put("gorev_baslik",gorev_baslik);
        cv.put("gorev",gorev);

        db.update("gorevler",cv,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
