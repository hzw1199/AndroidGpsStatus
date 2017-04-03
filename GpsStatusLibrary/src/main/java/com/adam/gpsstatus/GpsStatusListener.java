package com.adam.gpsstatus;

/**
 * Created by wuzongheng on 2017/4/2.
 */

public interface GpsStatusListener {
    void onStart();
    void onStop();
    void onFixed();
    void onUnFixed();
    void onSignalStrength(int inUse, int count);
}
