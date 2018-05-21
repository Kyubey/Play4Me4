package com.example.lagun.play4me4;

import android.location.Address;
import android.media.Image;

import java.util.ArrayList;
import java.util.Date;

public class Event {
    private String nome;
    public Date data;
    private Address place;
    private String description;
    private User owner;
    private Image eventPicture;
    private ArrayList<User> accettati;
public Event(String nome,Date data,Address place, User owner, Image eventPicture, String description){
    this.nome=nome;
    this.data=data;
    this.place=place;
    this.owner=owner;
    this.eventPicture=eventPicture;
    this.description=description;
}
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Address getPlace() {
        return place;
    }

    public void setPlace(Address place) {
        this.place = place;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Image getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(Image eventPicture) {
        this.eventPicture = eventPicture;
    }

    public ArrayList<User> getAccettati() {
        return accettati;
    }

    public void setAccettati(ArrayList<User> accettati) {
        this.accettati = accettati;
    }
}
