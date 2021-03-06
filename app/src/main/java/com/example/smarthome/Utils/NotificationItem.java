package com.example.smarthome.Utils;

import java.util.Calendar;
import java.util.Date;

public class NotificationItem {
    final String LIGHT = "13";
    final String TEMP_HUMID = "7";
    final String LED = "1";
    final String FAN = "10";
    final int LIGHT_HIGH = 0;
    final int LIGHT_LOW = 1;
    final int TEMP_XLOW = 2;
    final int TEMP_LOW = 3;
    final int TEMP_MEDIUM = 4;
    final int TEMP_HIGH = 5;

    private String device;
    private int state;
    private Date time;

    public NotificationItem() {
    }

    public NotificationItem(String device, int state, Date time) {
        this.device = device;
        this.state = state;
        this.time = time;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getDevice() {
        return device;
    }

    public int getState() {
        return state;
    }

    public String getTitle() {
        if (device.equals(LIGHT) || device.equals(TEMP_HUMID)) {
            return "DATA EXCEEDS THRESHOLD";
        }
        return "DEVICE STATE CHANGES";
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getStringTime() {
        String[] months = {
                "Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug",
                "Sep", "Oct", "Nov", "Dec"};
        Calendar cal = Calendar.getInstance();
        if (time != null) cal.setTime(time);
        String hour = String.valueOf(cal.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(cal.get(Calendar.MINUTE));
        hour = hour.length() == 1?'0'+ hour:hour;
        minute = minute.length() == 1?'0' + minute:minute;
        return hour + ":"
                + minute + ", "
                + months[cal.get(Calendar.MONTH)] + " "
                + cal.get(Calendar.DAY_OF_MONTH) + ", "
                + cal.get(Calendar.YEAR);
    }

    public String getNotification() {
        switch (state) {
            case LIGHT_HIGH: switch (device) {
                case LIGHT: return "It is bright";
                case LED: return "Led is turned OFF";
            }
            case LIGHT_LOW: switch (device) {
                case LIGHT: return "It is dark";
                case LED: return "Led is turned ON";
            }
            case TEMP_XLOW: switch (device) {
                case TEMP_HUMID: return "It is cold";
                case FAN: return "Fan is turned OFF";
            }
            case TEMP_LOW: switch (device) {
                case TEMP_HUMID: return "It is cool";
                case FAN: return "Fan is changed to LOW";
            }
            case TEMP_MEDIUM: switch (device) {
                case TEMP_HUMID: return "It is warm";
                case FAN: return "Fan is changed to MEDIUM";
            }
            case TEMP_HIGH: switch (device) {
                case TEMP_HUMID: return "It is hot";
                case FAN: return "Fan is changed to HIGH";
            }
            default: return "";
        }
    }
}