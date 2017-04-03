package com.adam.gpsstatus;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by adampc on 2016/1/30.
 */
public class GpsStatusImageView extends ImageView {
    private GpsStatusProxy proxy;
    private Drawable drawable0, drawable1, drawable2, drawable3;
    private int thr1_2 = 4;
    private int thr2_3 = 7;
    private Context context;
    public final int ZERO = 0, ONE = 1, TWO = 2, THREE = 3;
    private int signalStrength = ZERO;

    public GpsStatusImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public GpsStatusImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public GpsStatusImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        this.context = context;
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GpsStatusImageView, 0, 0);
        drawable0 = a.getDrawable(R.styleable.GpsStatusImageView_drawable0);
        drawable1 = a.getDrawable(R.styleable.GpsStatusImageView_drawable1);
        drawable2 = a.getDrawable(R.styleable.GpsStatusImageView_drawable2);
        drawable3 = a.getDrawable(R.styleable.GpsStatusImageView_drawable3);

        thr1_2 = a.getInt(R.styleable.GpsStatusImageView_thr_1_2, thr1_2);
        thr2_3 = a.getInt(R.styleable.GpsStatusImageView_thr_2_3, thr2_3);
        a.recycle();

        if (drawable0 == null)
            drawable0 = getResources().getDrawable(R.drawable.ic_gps_0_24dp);
        if (drawable1 == null)
            drawable1 = getResources().getDrawable(R.drawable.ic_gps_1_24dp);
        if (drawable2 == null)
            drawable2 = getResources().getDrawable(R.drawable.ic_gps_2_24dp);
        if (drawable3 == null)
            drawable3 = getResources().getDrawable(R.drawable.ic_gps_3_24dp);

        proxy = GpsStatusProxy.getInstance(context.getApplicationContext());
        proxy.addListener(listener);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        drawable0.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        drawable1.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        drawable2.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
        drawable3.setBounds(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        switch (signalStrength){
            case ZERO:
                drawable0.draw(canvas);
                break;
            case ONE:
                drawable1.draw(canvas);
                break;
            case TWO:
                drawable2.draw(canvas);
                break;
            case THREE:
                drawable3.draw(canvas);
                break;
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        proxy.removeListener(listener);
    }

    public int getSignalStrength(){
        return signalStrength;
    }


    private void setSignalStrength(int signalStrength){
        this.signalStrength = signalStrength;
        invalidate();
    }

    private void setSignalStrength(int inUse, int count){
        if(inUse<=thr1_2){
            setSignalStrength(ONE);
        }else if(inUse<=thr2_3){
            setSignalStrength(TWO);
        }else{
            setSignalStrength(THREE);
        }
    }

    private GpsStatusListener listener = new GpsStatusListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onStop() {
            setSignalStrength(ZERO);
        }

        @Override
        public void onFixed() {

        }

        @Override
        public void onUnFixed() {

        }

        @Override
        public void onSignalStrength(int inUse, int count) {
            setSignalStrength(inUse, count);
        }
    };
}
