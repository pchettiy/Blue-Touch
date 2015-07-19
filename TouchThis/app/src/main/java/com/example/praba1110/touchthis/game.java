package com.example.praba1110.touchthis;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by praba1110 on 16/7/15.
 */
public class game extends View {
    MediaPlayer mp=MediaPlayer.create(getContext(),R.raw.bubble);
    int speed = 1000;
    long pressTime = -1, releaseTime=0, duration;
    //Canvas canvas=new Canvas();
    int score=0,limit=1100,limit2;
    float valx, valy, valrx, valry, valox, valoy,valbx,valby;
    float[] x = {150, 400, 650, 900}, y = {150, 400, 650, 900, 1150},rc={};
    float r;
    int randx = 1, randy = 1, rx = 0, ry = 0, ox = 3, oy = 2, bx=1,by=4;
    Paint cyan = new Paint();
    Paint blue = new Paint();
    Paint bg = new Paint();
    Paint magenta = new Paint();
    Paint black= new Paint();
    Context con;
    Paint red=new Paint();
    Paint red2=new Paint();
    int dif;
    public game(Context context,int d) {
        super(context);
        con = context;
        dif=d;
        limit2=limit;
        red.setColor(Color.RED);
        red2.setColor(Color.RED);
        red2.setStyle(Paint.Style.FILL);
        blue.setColor(Color.GREEN);
        magenta.setColor(Color.MAGENTA);
        cyan.setColor(Color.CYAN);
        black.setColor(Color.YELLOW);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        r = 100;
        Rect rec = new Rect();
        rec.set(0, 0, canvas.getWidth(), canvas.getHeight());
        Rect progress=new Rect();
        progress.set(10, 10, limit, 30);
        if (rx % 2 == 0) {
            bg.setColor(Color.BLACK);
        } else {
            bg.setColor(Color.WHITE);
        }


        canvas.drawRect(rec, bg);
        canvas.drawRect(progress, red2);
        canvas.drawCircle(x[randx], y[randy], r, cyan);
        if(dif==1){ canvas.drawCircle(x[rx], y[ry], r, blue); }
        else if(dif==2){
            canvas.drawCircle(x[rx], y[ry], r, blue);
            canvas.drawCircle(x[ox], y[oy], r, magenta);
        }
        else if(dif==3){
            canvas.drawCircle(x[rx], y[ry], r, blue);
            canvas.drawCircle(x[ox],y[oy],r,magenta);
            canvas.drawCircle(x[bx],y[by],r,black);
        }
        valx = x[randx];
        valy = y[randy];
        if (randx == rx && randy == ry) {
            ry = randInt(0, 4);
        }
        else if(randx==ox && randy==oy){ ox=randInt(0,3); }
        else if(randx==bx && randy==by){ by=randInt(0,4);}
        valrx = x[rx];
        valry = y[ry];
        valox=x[ox];
        valoy=y[oy];
        valbx=x[bx];
        valby=y[by];

        red.setTextSize(250);
        canvas.drawText(String.valueOf(score), 450, 1500, red);
        if(limit>10){
            switch (dif) {
                case 1:
                    limit -= 10;
                    break;
                case 2:
                    limit -= 18;
                    break;
                case 3:
                    limit -= 25;
                    break;
            }
            invalidate();
            }

        else { Toast.makeText(con,"Keep up with time next time!",Toast.LENGTH_SHORT).show();
            gameover();}

    }

    private void gameover() {
        Intent intent = new Intent(getContext(), GameOver.class);
        intent.putExtra("score", score);
        con.startActivity(intent);

    }

    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float cx = event.getX(), cy = event.getY();
        if (pressTime != -1) {
            duration = System.currentTimeMillis() - pressTime;
        }
        pressTime = System.currentTimeMillis();
       if(duration>speed){
            Intent intent = new Intent(getContext(), GameOver.class);
            intent.putExtra("score", score);
            con.startActivity(intent);

        }

        for (float i = valx - r; i < valx + r; i++) {
            if (cx == i) {
                for (float j = valy - r; j < valy + r; j++) {
                    if (cy == j) {
                        mp.start();
                        score++;
                        randx = randInt(0, 3);
                        randy = randInt(0, 4);
                        rx = randInt(0, 3);
                        ry = randInt(0, 4);
                        ox= randInt(0,3);
                        oy=randInt(0,4);
                        bx=randInt(0,3);
                        by=randInt(0,4);
                        limit=limit2;
                    }
                    }
                    }
                }
        for (float i = valrx - r; i < valrx + r; i++) {
            if (cx == i) {
                for (float j = valry - r; j < valry + r; j++) {
                    if (cy == j) {
                        Toast.makeText(con,"Oops! Missed it!",Toast.LENGTH_SHORT).show();
                        gameover();
                    }
            }
            }
        }
        for (float i = valox - r; i < valox + r; i++) {
            if (cx == i) {
                for (float j = valoy - r; j < valoy + r; j++) {
                    if (cy == j) {
                        Toast.makeText(con,"Oops! Missed it!",Toast.LENGTH_SHORT).show();
                        gameover();
                    }
                }
            }
        }
        for (float i = valbx - r; i < valbx + r; i++) {
            if (cx == i) {
                for (float j = valby - r; j < valby + r; j++) {
                    if (cy == j) {
                        Toast.makeText(con,"Oops! Missed it!",Toast.LENGTH_SHORT).show();
                        gameover();
                    }
                }
            }
        }
            limit2-=5;
            return super.onTouchEvent(event);
        }

    }





