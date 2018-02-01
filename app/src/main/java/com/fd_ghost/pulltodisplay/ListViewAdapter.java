package com.fd_ghost.pulltodisplay;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by FD-GHOST on 2018/1/31.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> list;
    //private LayoutInflater inflater;

    public ListViewAdapter(Context context) {
        this.context = context;
        //this.inflater = LayoutInflater.from(context);
        //Sample list
        list = new ArrayList<String>();
        list.add("Test Sample 1");
        list.add("Test Sample 2");
        list.add("Test Sample 3");
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(context);
        textView.setText(list.get(position));
        return textView;
    }
}
