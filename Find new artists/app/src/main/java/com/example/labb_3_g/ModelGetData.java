package com.example.labb_3_g;

import android.database.Observable;
import android.util.Log;
import android.view.View;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class ModelGetData implements Runnable{
    private String api_key = "e5cede1e52a68228de610997878cb435";
    private String text = new String("");
    private String type = "artist.getsimilar";
    private String artistName = "Cher";
    private Controller controller;
    private URL url;
    public ModelGetData(){

    }
    
    @Override
    public void run() {
        String api_string = "http://ws.audioscrobbler.com/2.0/?"
                + "limit=5"
                + "&method="
                + type
                + "&artist="
                + artistName
                + "&api_key="
                + api_key;
        try {
            resetSearch();

            url = new URL(api_string);

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();
            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            String tagName;
            String previousTag = new String();
            while(parserEvent != XmlPullParser.END_DOCUMENT){
                if(parserEvent == XmlPullParser.START_DOCUMENT){
                    Log.e("Tagg1", "Start");
                }
                else if(parserEvent == XmlPullParser.START_TAG){
                    Log.e("Tagg2", "<" + parser.getName() + ">");
                    previousTag = parser.getName();
                } else if (parserEvent == XmlPullParser.END_TAG) {
                    Log.e("Tagg3", "<" + parser.getName() + "/>");
                } else if (parserEvent == XmlPullParser.TEXT) {
                    if(previousTag.equals("name")){
                        text +=  parser.getText();
                        Log.e("Tagg4", parser.getText());
                    }
                }
                parserEvent = parser.next();
            }
        }catch(Exception e){
            e.printStackTrace();
            Log.e("Tag name2",  e.toString());
        }
    }

    public void setArtistName(String name){
        artistName = name;
    }

    public void resetSearch(){
        text = "";
    }
    public String getText(){
        return text;
    }
}
