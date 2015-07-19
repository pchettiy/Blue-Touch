package com.example.praba1110.touchthis;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class highscores extends ActionBarActivity {

    ListView list;
    SQLhandler handler;
    List<String> name;
    List<Integer> scores,sno;
    String[] n;
    Integer[] p,s;

    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);
        list= (ListView) findViewById(R.id.listView);
        handler=new SQLhandler(this,null,null,1);
        name= new ArrayList<>();
        scores=new ArrayList<>();
        sno=new ArrayList<>();
        name=handler.getnames();
        scores=handler.getpoints();
        sno=handler.getsno();
        n=new String[name.size()];
        n=name.toArray(n);
        p=new Integer[scores.size()];
        p=scores.toArray(p);
        s=new Integer[sno.size()];
        s=sno.toArray(s);
        ListAdapter adapter = new MyAdapter(this,n,p,s);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_highscores, menu);
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent main=new Intent(this,MainActivity.class);
        startActivity(main);
    }
}
