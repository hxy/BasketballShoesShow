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
		dbHelper = new DBHelper(context, "shoes", null, 1);
	}
	
	public long insertOneBrand(String brand_name,byte[]brand_pic,String series_name,String generation,
			String shoes_name,byte[] shoes_pic,String shoes_indro){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand_name);
		values.put("brand_pic", brand_pic);
		values.put("series_name", series_name);
		values.put("generation", generation);
		values.put("shoes_name", shoes_name);
		values.put("shoes_pic", shoes_pic);
		values.put("shoes_indro", shoes_indro);
		long row = db.insert("basketballshoes", null, values);
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
