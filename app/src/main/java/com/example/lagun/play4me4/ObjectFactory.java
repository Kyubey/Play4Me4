package com.example.lagun.play4me4;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.google.android.gms.common.internal.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ObjectFactory {
    public static Event[] events;
    public static ArrayList<User> club=initializeClub();
    private Context context ;
    static private Resources res= Resources.getSystem();
    public static void main(){

    }

    public static ArrayList<User> initializeClub(){
        Uri uril=ResourceUtils.getDrawableUri("R.drawable.ic_calendar");
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

    static boolean saveBitmapToFile(File dir, String fileName, Bitmap bm,
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
}
