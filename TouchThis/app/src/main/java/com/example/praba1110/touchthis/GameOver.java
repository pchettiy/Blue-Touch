package com.example.praba1110.touchthis;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class GameOver extends ActionBarActivity {

    SQLhandler handler;
    EditText name;
    TextView score;
    int s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        MediaPlayer mp=MediaPlayer.create(getApplicationContext(),R.raw.buzz);
        mp.start();
        Bundle b=getIntent().getExtras();
        if(b!=null){
            s=b.getInt("score");
        }
        name= (EditText) findViewById(R.id.name);
        score= (TextView) findViewById(R.id.score);
        score.setText("Your Score: " + s);

    }

    public void share(View v){
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "I scored "+s+" in Blue touch!");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_over, menu);
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

    public void hscores(View v){
        handler=new SQLhandler(this,null,null,1);
        handler.add(name.getText().toString(),s);
        Intent intent=new Intent(this,highscores.class);
       // intent.putExtra("score", s);
       // intent.putExtra("name",name.getText().toString());
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent main=new Intent(this,MainActivity.class);
        startActivity(main);
    }
}
