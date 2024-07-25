package com.iot.candybar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class gameover extends AppCompatActivity {

    TextView pointtext;

    ImageView clearimage;

    Button remainbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        pointtext = findViewById(R.id.pointtext);
        int points = getIntent().getIntExtra("POINTS", 0);
        pointtext.setText("점수 : " + points);

        if(points >= 500) {
            clearimage = findViewById(R.id.gif_image);
            clearimage.setImageResource(R.drawable.clear);
            pointtext.setText("CLEAR!");
        }


        remainbutton = findViewById(R.id.remainbutton);
        remainbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gameover.this, MainActivity.class);
                intent.putExtra("POINTS", points);
                startActivity(intent);
                finish(); // 현재 액티비티를 종료하여 MainActivity로 돌아갑니다.
            }
        });


    }
}