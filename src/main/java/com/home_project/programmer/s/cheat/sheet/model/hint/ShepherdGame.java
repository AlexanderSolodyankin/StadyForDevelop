package com.home_project.programmer.s.cheat.sheet.model.hint;


public class ShepherdGame extends Hints {
    private int step;
    private int shorePoint;
    private String[] shore1;
    private String[] shore2;
    private String[] shoreActive;
    private String[] boat;
    private boolean status;
    private boolean failure;
    private String action;


    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getShorePoint() {
        return shorePoint;
    }

    public void setShorePoint(int shorePoint) {
        this.shorePoint = shorePoint;
    }

    public String[] getShore1() {
        return shore1;
    }

    public void setShore1(String[] shore1) {
        this.shore1 = shore1;
    }

    public String[] getShore2() {
        return shore2;
    }

    public void setShore2(String[] shore2) {
        this.shore2 = shore2;
    }

    public String[] getShoreActive() {
        return shoreActive;
    }

    public void setShoreActive(String[] shoreActive) {
        this.shoreActive = shoreActive;
    }

    public String[] getBoat() {
        return boat;
    }

    public void setBoat(String[] boat) {
        this.boat = boat;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isFailure() {
        return failure;
    }

    public void setFailure(boolean failure) {
        this.failure = failure;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
