package com.example.test02;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity0 extends AppCompatActivity {

    View first,second,third,forth,fifth,sixth;
    TextView a,tagline;

    Animation topAnimation,bottomAnimation,middleAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main0);

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_ani);
        bottomAnimation = AnimationUtils.loadAnimation(this,R.anim.bottom_ani);
        middleAnimation = AnimationUtils.loadAnimation(this,R.anim.middle_ani);


        first = findViewById(R.id.first_line);
        second = findViewById(R.id.second_line);
        third = findViewById(R.id.third_line);
        forth = findViewById(R.id.fourth_line);
        fifth = findViewById(R.id.fifth_line);
        sixth = findViewById(R.id.six_line);

        a = findViewById(R.id.a);
        tagline = findViewById(R.id.tagLine);

        first.setAnimation(topAnimation);
        second.setAnimation(topAnimation);
        third.setAnimation(topAnimation);
        forth.setAnimation(topAnimation);
        fifth.setAnimation(topAnimation);
        sixth.setAnimation(topAnimation);

        a.setAnimation(middleAnimation);
        tagline.setAnimation(bottomAnimation);

        //跳转时间
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity0.this, Login2Activity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}