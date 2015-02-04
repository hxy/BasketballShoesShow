package com.hy.objects;

import java.io.ByteArrayInputStream;

import android.graphics.drawable.Drawable;

public class CategoryObject {

	private String tableName;
	private int _id;
    private String name;
    private Drawable drawable;
    
    public CategoryObject(String tableName,int _id,String name, byte[] bitmapBytes){
        this.tableName = tableName;
        this._id = _id;
    	this.name = name;
        if(null!=bitmapBytes){
            drawable = Drawable.createFromStream(new ByteArrayInputStream(bitmapBytes), null);
        }
    }
    
	public String getTabaleName(){
	    return tableName;
	}
	
	public int getId(){
	    return _id;
	}
    public String getName() {
        return name;
    }
    public Drawable getDrawable(){
        return drawable;
    }

}
