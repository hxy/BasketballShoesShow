package com.hy.objects;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CategoryInfo {

	private String name;
	private Bitmap bitmap;
	
	public CategoryInfo(String name, byte[] bitmapBytes){
		this.name = name;
		this.bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
	}
	
	public String getName() {
		return name;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}
	
}
