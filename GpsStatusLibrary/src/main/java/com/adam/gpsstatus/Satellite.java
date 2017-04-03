package com.adam.gpsstatus;

import android.location.GpsSatellite;

/**
 * Created by adampc on 2016/1/31.
 */
public class Satellite implements Comparable {
    private int prn;
    private float snr;
    private boolean inUse;
    private GpsSatellite satellite;

    public Satellite(GpsSatellite satellite) {
        this.satellite = satellite;
        this.prn = satellite.getPrn();
        this.snr = satellite.getSnr();
        this.inUse = satellite.usedInFix();
    }

    public int getPrn(){
        return prn;
    }

    public float getSnr(){
        return snr;
    }

    public boolean isInUse(){
        return inUse;
    }

    public GpsSatellite getSatellite() {
        return satellite;
    }

    @Override
    public int compareTo(Object another) {
        // 反序
        return prn < ((Satellite)another).getPrn()? 1: (prn == ((Satellite)another).getPrn()?0:-1);
    }
}
