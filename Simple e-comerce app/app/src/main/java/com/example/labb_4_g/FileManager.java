package com.example.labb_4_g;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileManager {

    public ShoppingCart readFile(String fileName){
        ShoppingCart c = new ShoppingCart();

        File sdCard = new File(Environment.getExternalStorageDirectory(), "ShoppingCart");
        File directory = new File(sdCard.getAbsolutePath() + "/Items/");

        directory.mkdirs();

        if(!directory.exists()){
            Log.e("Make Directory", "Misslyckade med att skapa map");
        }

        File itemFile = new File(directory, fileName);

        try{

            FileInputStream FIS = new FileInputStream(itemFile);
            ObjectInputStream OIS = new ObjectInputStream(FIS);
            c = (ShoppingCart) OIS.readObject();
            OIS.close();
            FIS.close();
        }
        catch(IOException | ClassNotFoundException e)
        {
            Log.e("MethodReadFile", e.toString());
        }

        for(int i = 0; i < c.size(); i++){
            Log.e("FileRead", c.getProduct(i).getName());
        }
        return c;
    }
    public void writeToFile(String inFile, ShoppingCart cart) {
        //samma här
        File sdCard = new File(Environment.getExternalStorageDirectory(), "ShoppingCart");
        File directory = new File(sdCard.getAbsolutePath() + "/Items/");

        directory.mkdirs();

        if(!directory.exists()){
            Log.e("Make Directory", "Misslyckade med att skapa map");
        }

        File itemFile = new File(directory, inFile);

        try{
            FileOutputStream FOS = new FileOutputStream(itemFile);
            ObjectOutputStream OOS = new ObjectOutputStream(FOS);
            OOS.writeObject(cart);
            OOS.close();
            FOS.close();

        } catch (IOException e) {
            Log.e("File", e.toString());
        }
    }
}
