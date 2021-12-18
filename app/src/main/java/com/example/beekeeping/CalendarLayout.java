package com.example.beekeeping;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.beekeeping.Adapters.EventAdapter;
import com.example.beekeeping.Adapters.GridAdapter;
import com.example.beekeeping.Database.Database;
import com.example.beekeeping.Database.EventsDAO;
import com.example.beekeeping.Models.EventModel;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import butterknife.BindView;

public class CalendarLayout extends LinearLayout {

    ImageButton previousBtn,nextBtn;
    TextView currentDate;
    GridView gridView;
    Calendar calendar = Calendar.getInstance();
    Context context;
    Database vt;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("tr"));
    SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM", Locale.forLanguageTag("tr"));
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy",Locale.forLanguageTag("tr"));
    SimpleDateFormat eventDateFormate = new SimpleDateFormat("dd-MM-yyyy",Locale.forLanguageTag("tr"));
    private static final int MAX_CALENDAR_DAYS = 42;
    GridAdapter adapterGrid;
    AlertDialog alertDialog;
    List<Date> dates = new ArrayList<>();
    List<EventModel> eventsList = new ArrayList<>();

    public CalendarLayout(Context context) {
        super(context);
    }

    public CalendarLayout(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        this.context = context;

        initializeLayout();
        setUpCalendar();

        clickedPreviousNext();

        clickedGridView();

        longClickedGridView();
    }

    private void clickedPreviousNext(){

        previousBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH, -1);
                setUpCalendar();
            }
        });

        nextBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.add(Calendar.MONTH,1);
                setUpCalendar();
            }
        });

    }

    private void clickedGridView(){

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View addView = LayoutInflater.from(parent.getContext()).inflate(R.layout.add_newevent_layout,null);
                final EditText eventName = addView.findViewById(R.id.event_name);
                final TextView eventTime = addView.findViewById(R.id.eventTime);
                ImageButton setTime = addView.findViewById(R.id.setTime);
                Button addEvent = addView.findViewById(R.id.addEvent);

                setTime.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Calendar calendar = Calendar.getInstance();
                        int hours = calendar.get(Calendar.HOUR_OF_DAY);
                        int minutes = calendar.get(Calendar.MINUTE);
                        TimePickerDialog timePickerDialog = new TimePickerDialog(addView.getContext(), R.style.Theme_MaterialComponents_Dialog, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                                Calendar c = Calendar.getInstance();
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.setTimeZone(TimeZone.getDefault());
                                SimpleDateFormat hformat = new SimpleDateFormat("K:mm", Locale.forLanguageTag("tr"));
                                String event_time = hformat.format(c.getTime());
                                eventTime.setText(event_time);
                            }
                        },hours,minutes,false);
                        timePickerDialog.show();
                    }
                });
                final String date = eventDateFormate.format(dates.get(position));
                final String month = monthFormat.format(dates.get(position));
                final String year = yearFormat.format(dates.get(position));

                addEvent.setOnClickListener(new OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        saveEvent(eventName.getText().toString(),eventTime.getText().toString(),date,month,year,v);
                        setUpCalendar();
                        alertDialog.dismiss();
                    }
                });
                builder.setView(addView);
                alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    private void longClickedGridView(){

        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                String date = eventDateFormate.format(dates.get(position));
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                View showView = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_events_layout,null);
                RecyclerView recyclerView = showView.findViewById(R.id.eventsRV);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(showView.getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
                EventAdapter adapterEventRcycler = new EventAdapter(showView.getContext(),CollectEventByDate(date));

                recyclerView.setAdapter(adapterEventRcycler);
                adapterEventRcycler.notifyDataSetChanged();

                builder.setView(showView);
                alertDialog = builder.create();
                alertDialog.show();
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        setUpCalendar();
                    }
                });


                return true;
            }
        });

    }

    private ArrayList<EventModel> CollectEventByDate(String date){

        ArrayList<EventModel> arrayList = new ArrayList<>();
        vt = new Database(context);
        SQLiteDatabase database = vt.getReadableDatabase();
        Cursor cursor = new EventsDAO().readEvents(date,database);

        while(cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex("event"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String Date = cursor.getString(cursor.getColumnIndex("date"));
            String month = cursor.getString(cursor.getColumnIndex("month"));
            String year = cursor.getString(cursor.getColumnIndex("year"));
            EventModel events = new EventModel(event,time,Date,month,year);
            arrayList.add(events);
        }
        cursor.close();
        vt.close();

        return arrayList;
    }

    public CalendarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
    }

    private void saveEvent(String event,String time,String date,String month,String year,View v){

        vt = new Database(context);
        SQLiteDatabase database = vt.getWritableDatabase();
        new EventsDAO().addEvents(event,time,date,month,year,database,v);
        vt.close();

    }

    private void initializeLayout(){

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.calendar_layout,this);
        nextBtn = view.findViewById(R.id.nextBtn);
        previousBtn = view.findViewById(R.id.previousBtn);
        currentDate = view.findViewById(R.id.currentDate);
        gridView = view.findViewById(R.id.gridView);
    }

    private void setUpCalendar(){

        String CurrentDate = dateFormat.format(calendar.getTime());
        currentDate.setText(CurrentDate);
        dates.clear();
        Calendar monthCalendar = (Calendar) calendar.clone();
        monthCalendar.set(Calendar.DAY_OF_MONTH,1);
        int FirstDayOfMonth = monthCalendar.get(Calendar.DAY_OF_WEEK)-1;
        monthCalendar.add(Calendar.DAY_OF_MONTH,-FirstDayOfMonth);

        CollectEventsPerMonth(monthFormat.format(calendar.getTime()),yearFormat.format(calendar.getTime()));

        while(dates.size() < MAX_CALENDAR_DAYS){
            dates.add(monthCalendar.getTime());
            monthCalendar.add(Calendar.DAY_OF_MONTH,1);
        }

        adapterGrid = new GridAdapter(context,dates,calendar,eventsList);
        gridView.setAdapter(adapterGrid);
    }

    private void CollectEventsPerMonth(String Month,String Year){

        eventsList.clear();
        vt = new Database(context);
        SQLiteDatabase database = vt.getReadableDatabase();
        Cursor cursor = new EventsDAO().readEventsPerMonth(Month,Year,database);

        while(cursor.moveToNext()){
            String event = cursor.getString(cursor.getColumnIndex("event"));
            String time = cursor.getString(cursor.getColumnIndex("time"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String month = cursor.getString(cursor.getColumnIndex("month"));
            String year = cursor.getString(cursor.getColumnIndex("year"));
            EventModel events = new EventModel(event,time,date,month,year);
            eventsList.add(events);
        }
        cursor.close();
        vt.close();
    }
}
