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
import android.util.Log;
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
    long pressTime = -1, duration;
    int score=0,limit=1200,limit2;
    int width,height;
    float valx, valy, valrx, valry, valox, valoy,valbx,valby;
//    float[] x = {150, 400, 650, 900}, y = {150, 400, 650, 900, 1150},rc={};
    float r=100;
    int rint=Math.round(r);
    int randx = 150, randy = 150, rx = 400, ry = 400, ox = 650, oy = 650, bx=150,by=400;
    Paint cyan = new Paint();
    Paint blue = new Paint();
    Paint bg = new Paint();
    Paint magenta = new Paint();
    Paint black= new Paint();
    Context con;
    Paint red=new Paint();
    Paint red2=new Paint();
    Rect rec = new Rect();
    Rect progress=new Rect();
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
        red.setTextSize(250);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width=canvas.getWidth();
        height=canvas.getHeight();
        rec.set(0, 0, canvas.getWidth(), canvas.getHeight());
        progress.set(10, 10, limit,30);
        if (rx % 2 == 0) {
            bg.setColor(Color.BLACK);
        } else {
            bg.setColor(Color.WHITE);
        }
        Log.d("TAG","inside onDraw");
        canvas.drawRect(rec, bg);
        canvas.drawRect(progress, red2);
        canvas.drawCircle(randx,randy,r,cyan);
        if(dif==1){
            canvas.drawCircle(rx,ry,r,blue);
            }
        else if(dif==2){
            canvas.drawCircle(rx,ry,r,blue);
            canvas.drawCircle(ox,oy,r,magenta);
        }
        else if(dif==3){

            canvas.drawCircle(rx,ry,r,blue);
            canvas.drawCircle(ox,oy,r,magenta);
            canvas.drawCircle(bx,by,r,black);
        }
        //Checking for overlap
        if(Math.sqrt(Math.pow(randx-rx,2)+Math.pow(randy-ry,2))<2*r){  ry=randInt(130,height-100);}
        if(Math.sqrt(Math.pow(randx-ox,2)+Math.pow(randy-oy,2))<2*r){  ox=randInt(100,width-100);}
        if(Math.sqrt(Math.pow(randx-bx,2)+Math.pow(randy-by,2))<2*r){  bx=randInt(100,width-100);}
        if(Math.sqrt(Math.pow(ox-rx,2)+Math.pow(oy-ry,2))<2*r){  oy=randInt(130,height-100);}
        if(Math.sqrt(Math.pow(bx-rx,2)+Math.pow(by-ry,2))<2*r){  by=randInt(130,height-100);}
        if(Math.sqrt(Math.pow(ox-bx,2)+Math.pow(oy-by,2))<2*r){  bx=randInt(100,width-100);}
        valx=(float)randx;
        valy=(float)randy;
        valrx=(float)rx;
        valry=(float)ry;
        valox=(float)ox;
        valoy=(float)oy;
        valbx=(float)bx;
        valby=(float)by;

        canvas.drawText(String.valueOf(score), 450, 1500, red);

        if(limit>10){
            switch (dif) {
                case 1:
                    limit -= 15;
                    break;
                case 2:
                    limit -= 20;
                    break;
                case 3:
                    limit -= 25;
                    break;
            }
            invalidate();
            }

        else { Toast.makeText(con,"Keep up next time!",Toast.LENGTH_SHORT).show();
            gameover();}

    }

    private void gameover() {
        Intent intent = new Intent(getContext(), GameOver.class);
        intent.putExtra("score", score);
        con.startActivity(intent);

    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public void forrand(float a,float b, float cx, float cy) {
        for (float i = a - r; i < a + r; i++) {
            if (cx == i) {
                for (float j = b - r; j < b + r; j++) {
                    if (cy == j) {
                        Toast.makeText(con, "Oops! Missed it!", Toast.LENGTH_SHORT).show();
                        gameover();
                    }
                }
            }
        }
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
                        randx = randInt(100, width-100);
                        randy = randInt(130,height-100);
                        rx = randInt(100, width-100);
                        ry = randInt(130, height-100);
                        ox= randInt(100,width-100);
                        oy=randInt(130,height-100);
                        bx=randInt(100,width-100);
                        by=randInt(130,height-100);
                        limit=limit2;
                    }
                    }
                    }
                }
        switch(dif){
            case 1:
                forrand(valrx,valry,cx,cy);
                break;
            case 2:
                forrand(valrx,valry,cx,cy);
                forrand(valox,valoy,cx,cy);
                break;
            case 3:
                forrand(valrx,valry,cx,cy);
                forrand(valox,valoy,cx,cy);
                forrand(valbx,valby,cx,cy);
                break;

        }

            return super.onTouchEvent(event);
        }

    }






