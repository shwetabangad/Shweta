package com.java.towing.numberplate;

import java.util.Calendar;

public class TimeMeter {
    private long startTime;
    public TimeMeter() {
        this.startTime = Calendar.getInstance().getTimeInMillis();
    }
    public long getTime() {
        return Calendar.getInstance().getTimeInMillis() - this.startTime;
    }
}
