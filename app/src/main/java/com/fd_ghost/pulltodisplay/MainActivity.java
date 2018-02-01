package com.fd_ghost.pulltodisplay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    ListView listView;
    ImageView imageView;
    double init, dist, current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listview);
        listView.setAdapter(new ListViewAdapter(this));
        listView.setOnTouchListener(this);
        imageView = findViewById(R.id.hideview);
        current = 0;
    }

    public void setMargins(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        int midposition = dp2px(180);
        int botposition = dp2px(360);

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN://0
                init = motionEvent.getY();
                break;
            case MotionEvent.ACTION_UP://1
                if (current > midposition) {
                    for (double i = current; i < botposition; i++) {
                        current = i;
                        setMargins(listView, 0, (int) current, 0, 0);
                        imageView.setAlpha((float) (current / botposition));
                    }
                } else {
                    for (double i = current; i > 0; i--) {
                        current = i;
                        setMargins(listView, 0, (int) current, 0, 0);
                        imageView.setAlpha((float) (current / botposition));
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE://2
                dist = init - motionEvent.getY();
                if (current <= botposition && 0 <= current) {
                    current = current - dist;
                    current = (current > botposition) ? botposition : current;
                    current = (current < 0) ? 0 : current;
                    setMargins(listView, 0, (int) current, 0, 0);
                    imageView.setAlpha((float) (current / botposition)); 
                }
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private int dp2px(float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
