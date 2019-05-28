package com.Lenovo.phclapps.Splash;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.Lenovo.phclapps.Activity.LoginActivity;
import com.Lenovo.phclapps.R;

/**
 * Created by Vikas on 3/22/2018.
 */

public class SpScreen extends AppCompatActivity {
    final static int timeDelay = 3000;
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);

    }

    /*
     * Called when the activity is first created.
     *
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sp_screen);
        StartAnimations();

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Intent i = new Intent(SpScreen.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        },timeDelay);

    }
    private void StartAnimations() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        anim.reset();
        LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);
        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

/*
        Intent intent = new Intent(Animation_splash.this, Login.class);
        startActivity(intent);*/

    }

}