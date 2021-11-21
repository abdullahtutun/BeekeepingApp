package com.example.beekeeping.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.beekeeping.Models.NotModel;

import java.util.ArrayList;

public class NotlarDAO {

    public ArrayList<NotModel> notlarıGetir(Database vt){

        ArrayList<NotModel> notlarArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from notlar",null);

        while (c.moveToNext()){
            NotModel k = new NotModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("not_tarih"))
                    ,c.getString(c.getColumnIndex("not_baslik")));
            notlarArrayList.add(k);
        }
        db.close();
        return notlarArrayList;
    }

    public ArrayList<NotModel> notlarıGetirTam(Database vt){

        ArrayList<NotModel> notlarArrayListTam = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from notlar",null);

        while (c.moveToNext()){
            NotModel k = new NotModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("not_tarih"))
                    ,c.getString(c.getColumnIndex("not_baslik")),c.getString(c.getColumnIndex("not_icerik")));
            notlarArrayListTam.add(k);
        }
        db.close();
        return notlarArrayListTam;
    }

    public void notSil(Database vt, int id){

        SQLiteDatabase db = vt.getWritableDatabase();
        db.delete("notlar","id=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void notEkle(Database vt,String not_tarih,String not_baslik,String not_icerik){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("not_tarih",not_tarih);
        cv.put("not_baslik",not_baslik);
        cv.put("not_icerik",not_icerik);

        db.insertOrThrow("notlar",null,cv);
        db.close();
    }

    public void notGuncelle(Database vt,int id,String not_tarih,String not_baslik,String not_icerik){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("not_tarih",not_tarih);
        cv.put("not_baslik",not_baslik);
        cv.put("not_icerik",not_icerik);

        db.update("notlar",cv,"id=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
