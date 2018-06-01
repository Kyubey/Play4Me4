package com.example.lagun.play4me4.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.media.Image;
import android.provider.ContactsContract;

public class User {
    private String name;
    private String mail;
    private String password;
    private String phoneNumber;
    private String description;
    private Address indirizzo;
    private String info;
    private Drawable proPicture;
    private boolean club;

    public User(String name, String mail, String password,boolean club){
        this.setName(name);
        this.setMail(mail);
        this.setPassword(password);
        this.setClub(club);
       /* if(club){
        ObjectFactory.addGestore(this);
            }
        else{ObjectFactory.addBand(this)}*/
    }

    public boolean validatePassword(String password){
        return password.equals(this.getPassword());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(Address indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Drawable getProPicture() {
        return proPicture;
    }

    public void setProPicture(Drawable proPicture) {
        this.proPicture = proPicture;
    }

    public boolean isClub() {
        return club;
    }

    public void setClub(boolean club) {
        this.club = club;
    }
}
