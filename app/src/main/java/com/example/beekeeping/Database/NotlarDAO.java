package com.example.beekeeping.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.beekeeping.Models.NotModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NotlarDAO {

    public ArrayList<NotModel> getNotlar(Database vt){

        ArrayList<NotModel> notlarArrayList = new ArrayList<>();

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor c = db.rawQuery("select *from notlar order by not_tarih desc",null);

        while (c.moveToNext()){
            NotModel k = new NotModel(c.getInt(c.getColumnIndex("id")),c.getString(c.getColumnIndex("not_tarih"))
                    ,c.getString(c.getColumnIndex("not_baslik")));
            notlarArrayList.add(k);
        }
        db.close();
        return notlarArrayList;
    }

    public ArrayList<NotModel> getNotlarAll(Database vt){

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

    public void deleteNot(Database vt, int id,View v){

        SQLiteDatabase db = vt.getWritableDatabase();
        long result = db.delete("notlar","id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(v,"Not başarıyla silindi!",Snackbar.LENGTH_LONG).show();
        }
        db.close();
    }

    public void addNot(Database vt,String not_tarih,String not_baslik,String not_icerik, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from notlar where not_baslik =?",new String[]{not_baslik});

        if(cursor.getCount() <= 0){

            ContentValues cv = new ContentValues();

            cv.put("not_tarih",not_tarih);
            cv.put("not_baslik",not_baslik);
            cv.put("not_icerik",not_icerik);

            long result = db.insertOrThrow("notlar",null,cv);

            if(result == -1){
                Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
            }else{
                Snackbar.make(v,"Not başarıyla eklendi!",Snackbar.LENGTH_LONG).show();
            }
        }
        else{
            Snackbar.make(v,"Aynı başlığa sahip bir not var!",Snackbar.LENGTH_LONG).show();
        }



        db.close();
    }

    public void updateNot(Database vt, int id, String not_tarih, String not_baslik, String not_icerik, View v){

        SQLiteDatabase db = vt.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("not_tarih",not_tarih);
        cv.put("not_baslik",not_baslik);
        cv.put("not_icerik",not_icerik);

       long result = db.update("notlar",cv,"id=?",new String[]{String.valueOf(id)});

        if(result == -1){
            Snackbar.make(v,"Bir hata oluştu",Snackbar.LENGTH_LONG).show();
        }else{
            Snackbar.make(v,"Not başarıyla güncellendi!",Snackbar.LENGTH_LONG).show();
        }

        db.close();
    }

    public long getCountNot(Database vt) {

        SQLiteDatabase db = vt.getReadableDatabase();

        long count = DatabaseUtils.queryNumEntries(db,"notlar");

        return count;
    }

}
