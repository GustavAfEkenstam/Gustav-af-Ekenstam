package com.example.labb_3_g;

import android.view.Display;

public class Controller {

    private MainActivity ma;
    private ModelGetData mgd;
    public Controller(MainActivity ma, ModelGetData mgd){
        this.ma = ma;
        this.mgd = mgd;
    }

    public void update(){
        ma.clearView();
        ma.setView();
    }
}
