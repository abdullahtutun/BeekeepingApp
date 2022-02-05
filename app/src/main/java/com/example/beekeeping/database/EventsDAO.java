package com.example.beekeeping.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;

import com.example.beekeeping.EventsStructure;

public class EventsDAO {

    public void addEvents(String event, String time, String date, String month, String year, SQLiteDatabase database, View v){

        ContentValues contentValues = new ContentValues();
        contentValues.put(EventsStructure.EVENT,event);
        contentValues.put(EventsStructure.TIME,time);
        contentValues.put(EventsStructure.DATE,date);
        contentValues.put(EventsStructure.MONTH,month);
        contentValues.put(EventsStructure.YEAR,year);

        database.insert("events",null,contentValues);
    }

    public Cursor readEvents(String date, SQLiteDatabase database){

        String [] projections = {EventsStructure.EVENT,EventsStructure.TIME,EventsStructure.DATE,EventsStructure.MONTH,EventsStructure.YEAR};
        String selection = EventsStructure.DATE +"=?";
        String [] selectionArgs = {date};

        return database.query("events",projections,selection,selectionArgs,null,null,null);
    }

    public Cursor readEventsPerMonth(String month,String year, SQLiteDatabase database){

        String [] projections = {EventsStructure.EVENT,EventsStructure.TIME,EventsStructure.DATE,EventsStructure.MONTH,EventsStructure.YEAR};
        String selection = EventsStructure.MONTH +"=? and "+EventsStructure.YEAR+"=?";
        String [] selectionArgs = {month,year};

        return database.query("events",projections,selection,selectionArgs,null,null,null);
    }

    public void deleteEvent(String event,String date,String time,SQLiteDatabase database){

        String selection = EventsStructure.EVENT+"=? and "+EventsStructure.DATE+"=? and "+EventsStructure.TIME+"=?";
        String[] selectionArg = {event,date,time};
        database.delete("events",selection,selectionArg);
    }
}
