package com.example.lagun.play4me4.model;

public class Notify {
    int numberEvent;
    String contenuto;
    boolean seen;

    public Notify(int numberEvent,String contenuto){
        seen=false;
        this.numberEvent=numberEvent;
        this.contenuto=contenuto;
    }
}
