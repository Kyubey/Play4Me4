package com.example.lagun.play4me4.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.lagun.play4me4.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class ObjectFactory{
    public static ArrayList<User> club;
    public static ArrayList<User> band;
    public static ArrayList<Event> eventi;

    private static Resources res;


   /* public findResource(){
        context.getResources().getXml(R.xml.samplexml);
    }*/
    public static void main(){

    }
    public static void initializeThis(Resources current){
        res = current;
        club=initializeClub();
        band=initializeBand();
        eventi=initializeEvents();
    }

    public static ArrayList<User> initializeClub(){
        //Uri uril=ResourceUtils.getDrawableUri("R.drawable.ic_calendar");
        ArrayList<User> array=new ArrayList<>();
        {
            User Paolo=new User("Paolo","@","p",true);
            Paolo.setDescription("Ciao");
            Paolo.setInfo("Miliardario");
            Paolo.setPhoneNumber("2345566");
            Paolo.setProPicture(res.getDrawable(R.drawable.ic_calendar));
            array.add(Paolo);
        }
        return array;
    }

    public static ArrayList<User> initializeBand(){
        //Uri uril=ResourceUtils.getDrawableUri("R.drawable.ic_calendar");
        ArrayList<User> array=new ArrayList<>();
        {
            User Marco=new User("Marco","marco@marco.it","Bistecca",false);
            Marco.setDescription("Bella");
            Marco.setInfo("Rocker");
            Marco.setPhoneNumber("63474574");
            Marco.setProPicture(res.getDrawable(R.drawable.ic_phone));
            array.add(Marco);

            User Fabio=new User("Fabietto","fabio@fabb.it","Brobro",false);
            Fabio.setDescription("Yother");
            Fabio.setInfo("Your' mon");
            Fabio.setPhoneNumber("4324429");
            Fabio.setProPicture(res.getDrawable(R.drawable.ic_menu_camera));
            array.add(Fabio);
        }
        return array;
    }

    public static ArrayList<Event> initializeEvents(){
        ArrayList<Event> array=new ArrayList<>();
        {
            Event Marcheggio=new Event("Marchiamo!", DateUtils.parseDateTime("11/11/2010 - 20:00"),club.get(0),"Un evento di Marcomanon e' Marco");
            Marcheggio.setEventPicture(res.getDrawable(R.drawable.ic_location));
            Marcheggio.setStringPlace("Via Ospedale 14 Cagliari");
            Marcheggio.accettaNuovo(band.get(0));
            Marcheggio.accettaNuovo(band.get(1));
            array.add(Marcheggio);

            Event Panini=new Event("PANINII!", DateUtils.parseDateTime("04/06/2018 - 22:00"),club.get(0),"Che buoni");
            Panini.setEventPicture(res.getDrawable(R.drawable.ic_menu_camera));
            Panini.setStringPlace("Via Cesare Serra 1 Quartucciu");
            Panini.proponiNuovo(band.get(0));
            Panini.proponiNuovo(band.get(1));
            array.add(Panini);
            //Event Marcheggio=new Event("Marchiamo!");
        }
        return array;

    }

    /*public static boolean saveBitmapToFile(File dir, String fileName, Bitmap bm,
                             Bitmap.CompressFormat format, int quality) {

        File imageFile = new File(dir,fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);

            bm.compress(format,quality,fos);

            fos.close();

            return true;
        }
        catch (IOException e) {
            Log.e("app",e.getMessage());
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return false;
    }*/

    public static ArrayList<User> getClub() {
        return club;
    }

    public static ArrayList<String> getCredentials(){
        ArrayList<String> map=new ArrayList<>();
        for(User locale:club){
            map.add(locale.getMail()+":"+locale.getPassword());
        }
        for(User locale:band){
            map.add(locale.getMail()+":"+locale.getPassword());
        }
        return map;
    }

    public static void addRegistered(User nuovo){
        if(nuovo.isClub())
            club.add(nuovo);
        else
            band.add(nuovo);
    }

    public static boolean typeUser(String email){
        for(User locale:club){
            if(locale.getMail().equals(email.toLowerCase()))
                return true;
        }
        return false;
    }

    public static void createEvent(Event nuovo){
        eventi.add(nuovo);
    }

    public static ArrayList<Event> getEventi(){
        return eventi;
    }

    public static ArrayList<User> getBand(){
        return band;
    }

    public static User getFromEmail(String email){
        for(User locale:club){
            if(locale.getMail().toLowerCase().equals(email.toLowerCase()))
                return locale;
        }
        for(User gruppo:band){
            if(gruppo.getMail().toLowerCase().equals(email.toLowerCase()))
                return gruppo;
        }
        throw new UnsupportedOperationException("Errore bizzarro");

    }

    public static User getLoggedUser(Context context) {
        return ObjectFactory.getFromEmail(PreferenceManager.getDefaultSharedPreferences(context).getString("userMail",""));
    }
}
