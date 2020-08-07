package co.com.smartgeeks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.VideoView;

public class SplashScreen extends Activity {
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);



        VideoView videoview = (VideoView) findViewById(R.id.vvSplash);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        videoview.setVideoURI(uri);
        videoview.start();

        handler = new Handler();


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, menu.class);
                startActivity(intent);
                finish();

            }
        },3000);
    }
}
