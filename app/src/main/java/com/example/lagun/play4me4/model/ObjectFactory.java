package com.example.lagun.play4me4.model;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
            User Paolo=new User("Paolo","@","Panino",true);
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
            array.add(Marco);
        }
        return array;
    }

    public static ArrayList<Event> initializeEvents(){
        ArrayList<Event> array=new ArrayList<>();
        {
            Event Marcheggio=new Event("Marchiamo!", DateUtils.parseDateTime("10/10/2010 - 20:00"),club.get(0),"Un evento di Marcomanon e' Marco");
            Marcheggio.setEventPicture(res.getDrawable(R.drawable.ic_location));
            Marcheggio.accettaNuovo(band.get(0));
            array.add(Marcheggio);

            Event Panini=new Event("PANINII!", DateUtils.parseDateTime("10/10/2018 - 22:00"),club.get(0),"Che buoni");
            Panini.setEventPicture(res.getDrawable(R.drawable.ic_menu_camera));
            array.add(Panini);
            //Event Marcheggio=new Event("Marchiamo!");
        }
        return array;

    }

    public static boolean saveBitmapToFile(File dir, String fileName, Bitmap bm,
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
    }

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

    public static ArrayList<Event> getEventi(){
        return eventi;
    }

    public static ArrayList<User> getBand(){
        return band;
    }
}
