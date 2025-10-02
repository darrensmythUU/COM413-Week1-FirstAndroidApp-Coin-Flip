package com.example.firstandroidappcoin_flip;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final Random random = new Random();
    private ImageView coin;
    private Button btnFlip;
    private Button btnHeads;
    private Button btnTails;
    public TextView txtScore;
    public int scoreNum = 0;
    private boolean result = true;
    private boolean predict = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coin = (ImageView) findViewById(R.id.coinImg);
        btnFlip = (Button) findViewById(R.id.btnFlip);
        txtScore = (TextView) findViewById(R.id.scoreTxt);
        txtScore.setText("Correct Score: 0");

        btnHeads = (Button) findViewById(R.id.headsBtn);
        btnHeads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                predict = true;
            }
        });

        btnTails = (Button) findViewById(R.id.tailsBtn);
        btnTails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                predict = false;
            }
        });

        btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flipCoin();
            }
        });
    }

    private void flipCoin() {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (random.nextFloat() > 0.5f){
                    coin.setImageResource(R.drawable.pound_tails);
                    result = false;
                }
                else{
                    coin.setImageResource(R.drawable.pound_heads);
                    result = true;
                }

                if (predict == result){
                    scoreNum++;
                }

                Animation fadeIn = new AlphaAnimation(0,1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(3000);
                fadeIn.setFillAfter(true);

                coin.startAnimation(fadeIn);
                txtScore.setText("Correct score: " + Integer.toString(scoreNum));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        coin.startAnimation(fadeOut);
    }
}