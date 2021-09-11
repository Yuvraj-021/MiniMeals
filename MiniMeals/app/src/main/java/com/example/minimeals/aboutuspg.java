package com.example.minimeals;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class aboutuspg extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aboutuspg);

        VideoView v1=findViewById(R.id.videoView);
        String vpath="android.resource://" +  getPackageName() + "/"+ R.raw.video;
        Uri uri=Uri.parse(vpath);
         v1.setVideoURI(uri);
        v1.start();

    }
}
