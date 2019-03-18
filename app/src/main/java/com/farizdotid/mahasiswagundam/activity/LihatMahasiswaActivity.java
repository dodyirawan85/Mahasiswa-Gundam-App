package com.farizdotid.mahasiswagundam.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.farizdotid.mahasiswagundam.R;
import com.farizdotid.mahasiswagundam.adapter.MahasiswaAdapter;
import com.farizdotid.mahasiswagundam.helper.DBHandler;
import com.farizdotid.mahasiswagundam.helper.RecyclerItemClickListener;
import com.farizdotid.mahasiswagundam.model.Mahasiswa;

import java.util.ArrayList;
import java.util.List;

public class LihatMahasiswaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private TextView txt_resultadapter;
    private List<Mahasiswa> mahasiswaList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_data);

        initComponents();
        initRecyclerView();
        cekDataRecyclerView();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    // FUNGSI INI UNTUK MENG-INIT RECYLERVIEW BESERTA ADAPTERNYA
    private void initRecyclerView(){
        recyclerView = findViewById(R.id.rv_mahasiswa);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DBHandler dbHandler = new DBHandler(LihatMahasiswaActivity.this);
        mahasiswaList = dbHandler.getSemuaMahasiswa();
        adapter = new MahasiswaAdapter(mahasiswaList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initComponents(){
        txt_resultadapter = findViewById(R.id.txt_resultadapter);
    }

    // FUNGSI INI UNTUK MENGECEK APAKAH ADA DATA DI DALEM RECYCLERVIEW ATAU TIDAK
    private void cekDataRecyclerView(){
        if (adapter.getItemCount() == 0){
            txt_resultadapter.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            txt_resultadapter.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            recyclerView.addOnItemTouchListener(
                    new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            // TODO Handle item click
                            Mahasiswa mhs = mahasiswaList.get(position);
                            String nama = mhs.getNama();

                            Toast.makeText(LihatMahasiswaActivity.this, "Klik di " + nama, Toast.LENGTH_SHORT).show();
                        }
                    })
            );
        }
    }
}
