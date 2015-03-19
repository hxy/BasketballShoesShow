package com.hy.database;

import java.io.File;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {

    static final String BRANDTABLE_CREATE =   
            "create table brand( " +
            "_id integer primary key autoincrement, " +   
            "server_id integer," +   
            "brand_name text , " +
            "brand_pic blob);"; 
    static final String SERIESTABLE_CREATE =   
            "create table series( " +
            "_id integer primary key autoincrement, " +
            "server_id integer," + 
            "brand_name text, " +
            "series_name text, " +
            "series_indro text, " +
            "series_pic blob);"; 
    static final String COLORTABLE_CREATE =   
            "create table color( " +
            "_id integer primary key autoincrement, " +  
            "server_id integer," + 
            "brand_name text, " +
            "series_name text, " +
            "color_name text," +
            "color_pic blob);"; 
    static final String SHOESTABLE_CREATE =   
            "create table shoes( " +
            "_id integer primary key autoincrement, " +  
            "server_id integer," + 
            "brand_name text, " +
            "series_name text, " +
            "color_name text," +
            "shoes_name text," +
            "shoes_pic blob," +
            "shoes_price integer," +
            "shoes_season text," +
            "shoes_upper text," +
            "shoes_upperMaterial text," +
            "shoes_lowMaterial text," +
            "shoes_function text," +
            "shoes_position text," + //Guard,Forward,Center-forward
            "shoes_sex text," +
            "shoes_technology text," +
            "shoes_indro text);"; 
    
	public DBHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(new CustomPathDatabaseContext(context, getDirPath()), name, factory, version);
	}
	
    private static String getDirPath(){
    	String path = null;
    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
    		path = Environment.getExternalStorageDirectory().getPath();
    	}else {
    		path = Environment.getDataDirectory().getPath()+File.separator + "data"+File.separator+"com.hy.basketballshoesshow";
		}
    	
        return path;
}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(BRANDTABLE_CREATE);
		db.execSQL(SERIESTABLE_CREATE);
		db.execSQL(COLORTABLE_CREATE);
		db.execSQL(SHOESTABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
