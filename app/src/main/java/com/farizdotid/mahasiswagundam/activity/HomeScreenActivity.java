package com.farizdotid.mahasiswagundam.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.farizdotid.mahasiswagundam.R;
import com.farizdotid.mahasiswagundam.helper.DBHandler;

public class HomeScreenActivity extends AppCompatActivity {
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        dbHandler = new DBHandler(HomeScreenActivity.this);
        initComponents();
    }

    private void initComponents(){
        Button button_tambahdata = findViewById(R.id.button_tambahdata);
        Button button_lihatdata = findViewById(R.id.button_lihatdata);
        Button button_hapusdata = findViewById(R.id.button_hapusdata);

        button_tambahdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, TambahMahasiswaActivity.class));
            }
        });

        button_lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeScreenActivity.this, LihatMahasiswaActivity.class));
            }
        });

        button_hapusdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHandler.hapusSemuaDataMahasiswa();
                Toast.makeText(HomeScreenActivity.this, "Berhasil Menghapus Semua Data Mahasiswa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
