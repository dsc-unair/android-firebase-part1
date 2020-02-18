package com.dscunair.rc02;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends Activity {
    private ImageButton back;
    private ImageView poster;
    private TextView judul,sinopsis;
    public  static final String EXTRA_ANIME="extra_anime";
    private modelData modelData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        back=findViewById(R.id.back);
        poster=findViewById(R.id.detail_poster);
        judul=findViewById(R.id.detail_judul);
        sinopsis=findViewById(R.id.detail_sinopsis);
        modelData=getIntent().getParcelableExtra(EXTRA_ANIME);
        judul.setText(modelData.getNama());
        sinopsis.setText(modelData.getSinopsis());
        Glide.with(this)
                .load(modelData.getPhoto())
                .apply(new RequestOptions().override(550,550))
                .into(poster);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
