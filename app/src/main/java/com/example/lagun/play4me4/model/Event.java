package com.example.lagun.play4me4.model;

import android.graphics.drawable.Drawable;
import android.location.Address;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

public class Event {
    private String nome;
    public GregorianCalendar data;
    private Address place;
    private String stringPlace;
    private LatLng coordinates;
    private String description;
    private User owner;
   private Drawable eventPicture;
    private ArrayList<User> accettati=new ArrayList<User>();
    private ArrayList<User> proposti=new ArrayList<>();
public Event(String nome,GregorianCalendar data,User owner, String description){
    this.nome=nome;
    this.data=data;
    //this.place=place;
    this.owner=owner;
    //this.eventPicture=eventPicture;
    this.description=description;
}
    public Event(String nome,GregorianCalendar data,User owner, String description, Address place, LatLng coordinates){
        this.nome=nome;
        this.data=data;
        this.place=place;
        this.coordinates=coordinates;
        this.owner=owner;
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

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
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

    public Drawable getEventPicture() {
        return eventPicture;
    }

    public void setEventPicture(Drawable eventPicture) {
        this.eventPicture = eventPicture;
    }

    public void accettaNuovo(User nuovo){
        accettati.add(nuovo);
    }

    public void rimuoviAccettato(User nuovo){
        accettati.remove(nuovo);
    }

    public ArrayList<User> getAccettati() {
        return accettati;
    }

    public void setAccettati(ArrayList<User> accettati) {
        this.accettati = accettati;
    }

    public void proponiNuovo(User nuovo){
        proposti.add(nuovo);
    }

    public void rimuoviProposto(User nuovo){
        proposti.remove(nuovo);
    }

    public ArrayList<User> getProposti() {
        return proposti;
    }

    public void setProposti(ArrayList<User> proposti) {
        this.proposti = proposti;
    }

    public String getStringPlace() {
        return stringPlace;
    }

    public void setStringPlace(String stringPlace) {
        this.stringPlace = stringPlace;
    }
}
