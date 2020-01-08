package com.daivers.ujicobavifast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation applogoanim, techanim;
    ImageView applogo, apptech;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //load animation
        applogoanim = AnimationUtils.loadAnimation(this, R.anim.applogoanim);
        techanim = AnimationUtils.loadAnimation(this, R.anim.techanim);

        //load element
        applogo = findViewById(R.id.appLogo);
        apptech = findViewById(R.id.appTech);

        //run animation
        applogo.startAnimation(applogoanim);
        apptech.startAnimation(techanim);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent tohome = new Intent(MainActivity.this, LoginVillagersAct.class);
                startActivity(tohome);
                finish();
            }
        }, 2000);







    }
}
