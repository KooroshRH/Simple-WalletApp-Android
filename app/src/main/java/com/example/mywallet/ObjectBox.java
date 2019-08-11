package com.example.mywallet;

import android.content.Context;

import io.objectbox.BoxStore;

public class ObjectBox {
    static private BoxStore boxStore;

    static public void create(Context c){
       boxStore = MyObjectBox.builder().androidContext(c.getApplicationContext()).build();
    }

    static public BoxStore getBoxStore(){
        return boxStore;
    }
}
