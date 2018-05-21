package com.example.lagun.play4me4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;

import java.util.ArrayList;

public class ObjectFactory {
    public static Event[] events;
    public static ArrayList<User> club=initializeClub();
    private Context context ;
    public static void main(){

    }

    public static ArrayList<User> initializeClub(){
        //Resources res = Resources.getSystem();
        ArrayList<User> array=new ArrayList<>();
        {
            User Paolo=new User("Paolo","paolo@paolo.it","Panino",true);
            Paolo.setDescription("Ciao");
            Paolo.setInfo("Rocker");
            Paolo.setPhoneNumber("2345566");

            //Paolo.setProPicture(res.getDrawable(R.drawable.ic_calendar));
            array.add(Paolo);
        }
        return array;
    }
}
