package com.iot.candybar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class gameplay extends AppCompatActivity {
    public static final long DELAY_MS = 100;
    public static final int WHAT_PROGRESS = 1;

    private ProgressBar progressBar;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (progressBar.getProgress() > 0) {
                progressBar.setProgress(progressBar.getProgress() - 1);
                handler.sendEmptyMessageDelayed(WHAT_PROGRESS, DELAY_MS);
            } else {
                handler.removeCallbacksAndMessages(null);
                Intent intent = new Intent(gameplay.this, gameover.class);
                intent.putExtra("POINTS", point);
                startActivity(intent);
                finish(); // 현재 액티비티를 종료합니다.

            }
        }
    };

    ImageView image[] = new ImageView[3];
    Integer idimage[] = {R.id.image1, R.id.image2, R.id.image3};
    Button button[] = new Button[3];
    Integer[] idbutton = {R.id.buttonS, R.id.buttonB, R.id.buttonM};
    Integer drawa[] = {R.drawable.strawberry, R.drawable.blackspire, R.drawable.madarin};
    int checkturn = 0;
    TextView textView;
    Button resetbutton;

    ImageView problemimage ;
    Integer pbimage[] = {R.drawable.problem123, R.drawable.problem213, R.drawable.problem321, R.drawable.problem221, R.drawable.problem133,
                       R.drawable.problem312, R.drawable.problem332, R.drawable.problem222};
    int checkround = 0;
    int point = 0;
    int checkanswer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);
        textView = findViewById(R.id.viewpoint);
        resetbutton = findViewById(R.id.makecandy);
        progressBar = (ProgressBar)findViewById(R.id.timer);
        handler.sendEmptyMessageAtTime(WHAT_PROGRESS,DELAY_MS);
        problemimage = findViewById(R.id.answer);



        for (int i = 0 ; i <3 ; i++){
            button[i]=findViewById(idbutton[i]);
            image[i]=findViewById(idimage[i]);
        }

        problemimage.setImageResource(pbimage[0]);



        button[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextturn(0);
                answercheck(checkturn,1);
                logStatus();
            }
        });

        button[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextturn(1);
                answercheck(checkturn,2);
                logStatus();
            }
        });

        button[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextturn(2);
                answercheck(checkturn,3);
                logStatus();
            }
        });

        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               sumpoint();
               makeCandybar();
               problemimage.setImageResource(pbimage[checkround]);
               textView.setText("point = " + point);
                logStatus();
            }
        });





    }

    public void nextturn(int i) {
        image[checkturn].setImageResource(drawa[i]);
        checkturn ++;
    }

    public void makeCandybar() {
        for(int i = 0; i <3 ; i++){
            image[i].setImageResource(0);
        }
        checkturn  = 0;
        checkround++;
        checkanswer = 0;
    }

    public void answercheck(int i, int numbutton) {
        if(i == 1) {
            checkanswer = checkanswer + numbutton ;
        } else if (i == 2) {
            checkanswer = checkanswer + (numbutton*10);
        } else if (i == 3) {
            checkanswer = checkanswer + (numbutton*100);
        }
    }

    public void sumpoint() {
        // 각 라운드와 정답 조합에 따른 점수 합산
        if (checkanswer == 321 && checkround == 0) {
            point = point + 100;
        } else if (checkanswer == 312 && checkround == 1) {
            point = point + 100;
        } else if (checkanswer == 123 && checkround == 2) {
            point = point + 100;
        } else if (checkanswer == 122 && checkround == 3) {
            point = point + 100;
        } else if (checkanswer == 331 && checkround == 4) {
            point = point + 100;
        } else if (checkanswer == 213 && checkround == 5) {
            point = point + 100;
        } else if (checkanswer == 233 && checkround == 6) {
            point = point + 100;
        } else if (checkanswer == 222 && checkround == 7) {
            point = point + 100;
        }

    }

    public void logStatus() {
        Log.i("Log", "checkturn: " + checkturn);
        Log.i("Log", "checkanswer: " + checkanswer);
        Log.i("Log", "checkround: " + checkround);
        Log.i("Log", "point: " + point);
    }



}



