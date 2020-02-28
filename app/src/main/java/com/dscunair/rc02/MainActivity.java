package com.dscunair.rc02;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<modelData>list=new ArrayList<>();
    private RecyclerView recyclerView;
    int[] dataposter;

    private ImageButton signOut;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.rv_anime);
        recyclerView.hasFixedSize();
        list.addAll(getData());
        tampilkanRecyclerView();

        signOut = findViewById(R.id.sign_out);
        mAuth = FirebaseAuth.getInstance();

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void tampilkanRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdapterAnime adapterAnime=new AdapterAnime(this,list);
        recyclerView.setAdapter(adapterAnime);
    }

    public ArrayList<modelData>getData(){
       ArrayList<modelData>listAnime=new ArrayList<>();
       String[] datajudul=getResources().getStringArray(R.array.data_nama);
       String[] datasinopsis=getResources().getStringArray(R.array.data_sinopsis);
       dataposter=getResources().getIntArray(R.array.data_poster);
       String[] dataphoto=getResources().getStringArray(R.array.data_photo);

       for(int i=0;i<datajudul.length;i++){
           modelData modelData=new modelData();
           modelData.setNama(datajudul[i]);
           modelData.setSinopsis(datasinopsis[i]);
           modelData.setPhoto(dataphoto[i]);
           listAnime.add(modelData);

       }
        return  listAnime;
   }

}
