package com.example.testspinner;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Spinner
    private Spinner spnCategory;
    private Spinner spnCategory1;
    private Spinner spnCategory2;
    private CategoryAdapter categoryAdapter;
    private CategoryAdapter categoryAdapter1;
    private CategoryAdapter categoryAdapter2;

    //DatePicker
    EditText tvDate;
    DatePickerDialog.OnDateSetListener setListener;

    //TimePicker
    EditText tvTimer2;
    int t2Hour,t2Minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variable

        tvTimer2 = findViewById(R.id.tv_timer2);

        tvTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                String time = t2Hour + ":" + t2Minute;

                                SimpleDateFormat f24Hours = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hours.parse(time);
                                    SimpleDateFormat f12Hours = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );

                                    tvTimer2.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour,t2Minute);
                timePickerDialog.show();
            }
        });



        //DatePicker

        tvDate = findViewById(R.id.tv_date);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        ,setListener,year,day,month);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date = day+"/"+month+"/"+year;
                tvDate.setText(date);
            }
        };


        //spinner_category box 1
        spnCategory = findViewById(R.id.spn_category);

        categoryAdapter = new CategoryAdapter(this,R.layout.item_selected,getListCategory());
        categoryAdapter1 = new CategoryAdapter(this,R.layout.item_selected,getListCategory1());
        categoryAdapter2 = new CategoryAdapter(this,R.layout.item_selected,getListCategory2());

        spnCategory.setAdapter(categoryAdapter);
        //sukienclick
        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,categoryAdapter.getItem(position).getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner_category box 2
        spnCategory1 = findViewById(R.id.spn_category1);
        spnCategory1.setAdapter(categoryAdapter1);
        //sukienclick
        spnCategory1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,categoryAdapter1.getItem(position).getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner_category box 3
        spnCategory2 = findViewById(R.id.spn_category2);
        spnCategory2.setAdapter(categoryAdapter2);
        //sukienclick
        spnCategory2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this,categoryAdapter2.getItem(position).getName(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //data spinner 1
    private List<Category> getListCategory() {
        List<Category> list = new ArrayList<>();

        //add dữ liệu
        list.add(new Category("Hà Nội"));
        list.add(new Category("Kiên Giang"));
        list.add(new Category("Đà Nẵng"));
        list.add(new Category("Bắc Giang"));
        list.add(new Category("Bạc Liêu"));

        return list;
    }

    //data spinner 2
    private List<Category> getListCategory1() {
        List<Category> list = new ArrayList<>();

        //add dữ liệu
        list.add(new Category("Ba Đình"));
        list.add(new Category("Hoàn Kiếm"));
        list.add(new Category("Tây Hồ"));
        list.add(new Category("Long Biên"));
        list.add(new Category("Cầu Giấy"));

        return list;
    }

    //data spinner 3
    private List<Category> getListCategory2() {
        List<Category> list = new ArrayList<>();

        //add dữ liệu
        list.add(new Category("100 Phan Kế Bính"));
        list.add(new Category("59 Hồ Tùng Mậu"));

        return list;
    }
}