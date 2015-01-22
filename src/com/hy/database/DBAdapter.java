package com.hy.database;

import java.util.ArrayList;

import com.hy.objects.Brand;
import com.hy.objects.CategoryInfo;
import com.hy.objects.Color;
import com.hy.objects.Generation;
import com.hy.objects.Series;
import com.hy.objects.Shoes;

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
	
	public long insertBrand(Brand brand){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", brand.getName());
		values.put("brand_pic", brand.getBitmapBytes());
		long row = db.insert("brand", null, values);
		db.close();
		return row;
	}
	
	public long insertSeries(Series series){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", series.getBrandName());
		values.put("series_name", series.getName());
		values.put("series_pic", series.getBitmapBytes());
		values.put("series_indro", series.getIndro());
		long row = db.insert("series", null, values);
		db.close();
		return row;
	}
	
	public long insertGeneration(Generation generation){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", generation.getBrandName());
		values.put("series_name", generation.getSeriesName());
		values.put("generation", generation.getName());
		values.put("generation_pic", generation.getBitmapBytes());
		long row = db.insert("generation", null, values);
		db.close();
		return row;
	}
	
	public long insertColor(Color color){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", color.getBrandName());
		values.put("series_name", color.getSeriesName());
		values.put("generation", color.getGeneration());
		values.put("color", color.getName());
		values.put("color_pic", color.getBitmapBytes());
		long row = db.insert("color", null, values);
		db.close();
		return row;
	}
	
	public long insertShoes(Shoes shoes){
		
		db = dbHelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("brand_name", shoes.getBrand());
		values.put("series_name", shoes.getSeries());
		values.put("generation", shoes.getGeneration());
		values.put("color", shoes.getColor());
		values.put("shoes_name", shoes.getName());
		values.put("shoes_pic", shoes.getBitmapBytes());
		values.put("shoes_price", shoes.getPrice());
		values.put("shoes_season", shoes.getSeason());
		values.put("shoes_upper", shoes.getUpper());
		values.put("shoes_upperMaterial", shoes.getUpperMaterial());
		values.put("shoes_lowMaterial", shoes.getLowMaterial());
		values.put("shoes_function", shoes.getFunction());
		values.put("shoes_position", shoes.getPosition());
		values.put("shoes_sex", shoes.getSex());
		values.put("shoes_technology", shoes.getTechnology());
		values.put("shoes_indro", shoes.getIndro());
		long row = db.insert("shoes", null, values);
		db.close();
		return row;
	}
	
	public ArrayList<CategoryInfo> getBrandsList(){
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("brand", new String[]{"brand_name","brand_pic"}, null, null, null, null, null);
		String name;
		byte[] bitmapBytes;
		ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
		while(cursor.moveToNext()){
			name = cursor.getString(cursor.getColumnIndex("brand_name"));
			bitmapBytes = cursor.getBlob(cursor.getColumnIndex("brand_pic"));
			list.add(new CategoryInfo(name, bitmapBytes));
		}
		db.close();
		return list;
	}
	
	public ArrayList<CategoryInfo> getSeriesList(String brand){
		db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("series", new String[]{"series_name","series_pic"}, "brand_name=?", new String[]{brand}, null, null, null);
		String name;
		byte[] bitmapBytes;
		ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
		while(cursor.moveToNext()){
		    name = cursor.getString(cursor.getColumnIndex("series_name"));
		    bitmapBytes = cursor.getBlob(cursor.getColumnIndex("series_pic"));
		    list.add(new CategoryInfo(name, bitmapBytes));
		}
		db.close();
		return list;
	}
	
	public String getSeriesIntroduce(String brandName,String seriesName){
	    db = dbHelper.getReadableDatabase();
	    Cursor cursor = db.query("series", new String[]{"series_indro"}, "brand_name=? and series_name=?", new String[]{brandName,seriesName}, null, null, null);
	    cursor.moveToFirst();
	    String introduce = cursor.getString(cursor.getColumnIndex("series_indro"));
	    db.close();
	    return introduce;
	    
	}

}
