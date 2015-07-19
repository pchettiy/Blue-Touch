package com.example.praba1110.touchthis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by praba1110 on 17/7/15.
 */
public class MyAdapter extends ArrayAdapter<String> {
    public String[] n;
    public Integer[] s,num;
    Context context;
    public MyAdapter(Context context, String[] name,Integer[] points,Integer[] sno) {
        super(context,R.layout.singlerow,name);
        n=name;
        s=points;
        num=sno;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(getContext());
        View v=null;
        v=inflater.inflate(R.layout.singlerow,parent,false);
        TextView no= (TextView) v.findViewById(R.id.textView2);
        TextView name= (TextView) v.findViewById(R.id.name);
        TextView score= (TextView) v.findViewById(R.id.score);
        no.setText(String.valueOf(num[position]));
        name.setText(n[position]);
        score.setText(String.valueOf(s[position]));
        return v;
    }
}
