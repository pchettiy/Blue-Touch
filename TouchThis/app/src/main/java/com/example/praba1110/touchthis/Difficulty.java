package com.example.praba1110.touchthis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class Difficulty extends ActionBarActivity {

    Intent game;
    int  dif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);
        game=new Intent(this,Game_activity.class);
    }

    public void easy(View v){
        dif=1;
        game.putExtra("dif",dif);
        startActivity(game);
    }
    public void medium(View V){
        dif=2;
        game.putExtra("dif",dif);
        startActivity(game);
    }
    public void difficult(View v){
        dif=3;
        game.putExtra("dif",dif);
        startActivity(game);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_difficulty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
