package com.example.lagun.play4me4.model;

public class Notify {
    private int numberEvent;
    private String contenuto;
    private boolean seen;

    public Notify(int numberEvent,String contenuto){
        setSeen(false);
        this.setNumberEvent(numberEvent);
        this.setContenuto(contenuto);
    }

    public int getNumberEvent() {
        return numberEvent;
    }

    public void setNumberEvent(int numberEvent) {
        this.numberEvent = numberEvent;
    }

    public String getContenuto() {
        return contenuto;
    }

    public void setContenuto(String contenuto) {
        this.contenuto = contenuto;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
