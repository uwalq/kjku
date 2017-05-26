
package com.labboy.zefta.gelumbang.kajianku.Kajian;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;


import com.labboy.zefta.gelumbang.kajianku.R;
import com.labboy.zefta.gelumbang.kajianku.peta.SelectKajianActivity;

import Utilities.BackgroundTaskKajian;

public class BuatKajianActivity extends AppCompatActivity {
    public Button btnSubmit, btnLokasi;
    public EditText etNamaPengisi, etNamaKajian, etAlamat;
    public DatePicker datePicker;
    public TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_kajian);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSubmit = (Button)findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insDB();
                    }
        });

        btnLokasi = (Button)findViewById(R.id.btn_lokasi_kajian);
        btnLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iMap = new Intent(BuatKajianActivity.this, SelectKajianActivity.class);
                startActivity(iMap);
            }
        });

        etNamaKajian = (EditText)findViewById(R.id.et_nama_kajian);
        etNamaPengisi = (EditText)findViewById(R.id.et_pengisi);
        etAlamat = (EditText)findViewById(R.id.tm_alamat);
        datePicker = (DatePicker)findViewById(R.id.datePicker);
        datePicker.setSpinnersShown(true);
        datePicker.setCalendarViewShown(false);

        timePicker = (TimePicker)findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);




    }

    String createStringWaktu (TimePicker timepicker){
        int hour = timePicker.getCurrentHour();
        int minute = timePicker.getCurrentMinute();
        String s_hour = Integer.toString(hour);
        String s_minute = Integer.toString(minute);
        String c_waktu =s_hour+":"+s_minute;
        return c_waktu;

    }

    String createStringTanggal (DatePicker datePicker)
    {
        String c_tanggal = null;
        int day  = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String s_day = Integer.toString(day);
        String s_month = Integer.toString(month);
        String s_year = Integer.toString(year);

        c_tanggal = s_year+"-"+s_month+"-"+s_day;
        return c_tanggal;
    }


    private void insDB() {
        String strNamakajian, strNamaPengisi, strAlamat, strTanggal, strWaktu;
        strTanggal = createStringTanggal(datePicker);
        strWaktu = createStringWaktu(timePicker);
        String method = "BuatKajian";
        strNamakajian = etNamaKajian.getText().toString();
        strNamaPengisi = etNamaPengisi.getText().toString();
        strAlamat = etAlamat.getText().toString();

        BackgroundTaskKajian backgroundTaskKajian = new BackgroundTaskKajian(this);
        backgroundTaskKajian.execute(method, strNamakajian, strNamaPengisi, strAlamat, strTanggal, strWaktu);
    }
}
