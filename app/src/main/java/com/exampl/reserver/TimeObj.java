package com.exampl.reserver;

public class TimeObj {
    private int id;

    private String nameTime;

    public TimeObj(int id, String nameTime) {
        this.id = id;
        this.nameTime = nameTime;
    }

    public int getId() {
        return id;
    }

    public String getNameTime() {
        return nameTime;
    }
}
