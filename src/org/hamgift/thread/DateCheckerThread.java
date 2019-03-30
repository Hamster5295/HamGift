package org.hamgift.thread;

import org.hamgift.data.Data;

import java.util.Calendar;

public class DateCheckerThread extends Thread {

    private long time;
    private long before = 0;
    private Calendar calendar = Calendar.getInstance();

    public DateCheckerThread(long time){
        this.time = time;
    }

    public void setBefore(int before){
        this.before = before;
    }

    public void setTime(int time){
        this.time = time;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(Math.abs(calendar.get(Calendar.DAY_OF_YEAR) - before) >= 1){
                Data.gifts.forEach((k, gift)-> gift.upDate());
            }

            before = calendar.get(Calendar.DAY_OF_YEAR);
        }
    }
}
