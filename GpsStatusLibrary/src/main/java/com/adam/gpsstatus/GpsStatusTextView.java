package com.adam.gpsstatus;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by wuzongheng on 2017/4/2.
 */

public class GpsStatusTextView extends TextView {
    private GpsStatusProxy proxy;
    private Context context;
    private int colorClosed, colorFixed, colorUnFixed;

    public GpsStatusTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GpsStatusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GpsStatusTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GpsStatusTextView, 0, 0);
        colorClosed = a.getColor(R.styleable.GpsStatusTextView_colorClosed, getResources().getColor(R.color.gps_icon_red));
        colorFixed = a.getColor(R.styleable.GpsStatusTextView_colorFixed, getResources().getColor(R.color.gps_icon_green));
        colorUnFixed = a.getColor(R.styleable.GpsStatusTextView_colorUnFixed, getResources().getColor(R.color.gps_icon_yellow));
        a.recycle();

        proxy = GpsStatusProxy.getInstance(context.getApplicationContext());
        proxy.addListener(listener);
        proxy.refreshStatus();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        proxy.removeListener(listener);
    }

    private GpsStatusListener listener = new GpsStatusListener() {
        @Override
        public void onStart() {
            setTextColor(colorUnFixed);
        }

        @Override
        public void onStop() {
            setTextColor(colorClosed);
        }

        @Override
        public void onFixed() {
            setTextColor(colorFixed);
        }

        @Override
        public void onUnFixed() {
            setTextColor(colorUnFixed);
        }

        @Override
        public void onSignalStrength(int inUse, int count) {

        }
    };
}
