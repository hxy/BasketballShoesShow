package com.hy.objects;

import java.io.ByteArrayInputStream;

import android.graphics.drawable.Drawable;

public class CategoryObject {

    private String name;
    private Drawable drawable;
    
    public CategoryObject(String name, byte[] bitmapBytes){
        this.name = name;
        if(null!=bitmapBytes){
            drawable = Drawable.createFromStream(new ByteArrayInputStream(bitmapBytes), null);
        }
    }
    
    public String getName() {
        return name;
    }
    public Drawable getDrawable(){
        return drawable;
    }

}
