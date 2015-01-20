package com.hy.database;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {

	private DBHelper dbHelper;
	private SQLiteDatabase db;
	
	public DBAdapter(Context context) {
		dbHelper = new DBHelper(context, "shoesDB", null, 1);
	}
	
	public long insertBrand(String brand_name,byte[]brand_pic){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("brand_pic", brand_pic);
		long row = db.insert("brand", null, values);
		db.close();
		return row;
	}
	
	public long insertSeries(String brand_name,String series_name,byte[] series_pic){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("series_name", series_name);
		values.put("series_pic", series_pic);
		long row = db.insert("series", null, values);
		db.close();
		return row;
	}
	
	public long insertGeneration(String brand_name,String series_name,
			String generation,byte[] generation_pic){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("series_name", series_name);
		values.put("generation", generation);
		values.put("generation_pic", generation_pic);
		long row = db.insert("generation", null, values);
		db.close();
		return row;
	}
	
	public long insertColor(String brand_name,String series_name,
			String generation,String color,byte[] color_pic){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("series_name", series_name);
		values.put("generation", generation);
		values.put("color", color);
		values.put("color_pic", color_pic);
		long row = db.insert("color", null, values);
		db.close();
		return row;
	}
	
	public long insertShoes(String brand_name,String series_name,
			String generation,String color,String shoes_name,byte[] shoes_pic,String shoes_indro){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("series_name", series_name);
		values.put("generation", generation);
		values.put("color", color);
		values.put("shoes_name", shoes_name);
		values.put("shoes_pic", shoes_pic);
		values.put("shoes_indro", shoes_indro);
		long row = db.insert("shoes", null, values);
		db.close();
		return row;
	}
	
	public Cursor getBrandsList(){
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("basketballshoes", new String[]{"brand_name","brand_pic"}, null, null, null, null, null);
		db.close();
		return cursor;
	}
	
	public Cursor getSeriesList(String brand){
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("basketballshoes", new String[]{"series_name"}, null, null, null, null, null);
		db.close();
		
		return cursor;
	}

}
