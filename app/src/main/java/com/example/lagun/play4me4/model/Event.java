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

    public void accettaNuovo(User accettato,int event){
        accettati.add(accettato);
        accettato.addNotify(new Notify(event,"Sei stato ingaggiato per l'evento "+this.getNome()));
    }

    public void rimuoviAccettato(User rimosso,int event){
        accettati.remove(rimosso);
        rimosso.addNotify(new Notify(event, "Sei stato rimosso per l'evento " +this.getNome()));
    }

    public void rimuoviDaAccettati(User rimosso,int event){
        accettati.remove(rimosso);
        owner.addNotify(new Notify(event,"La band "+rimosso.getName()+" si è ritirata dall'evento "+getNome()));
    }

    public ArrayList<User> getAccettati() {
        return accettati;
    }

    public void setAccettati(ArrayList<User> accettati) {
        this.accettati = accettati;
    }

    public void proponiNuovo(User proposto,int event){
        proposti.add(proposto);
        for(Notify notifica:owner.getNotifiche())
        if(notifica.seen==false && notifica.contenuto.equals("Hai nuove proposte per l'evento "+this.getNome()))
            return;
        this.owner.addNotify(new Notify(event,"Hai nuove proposte per l'evento "+this.getNome()));
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
