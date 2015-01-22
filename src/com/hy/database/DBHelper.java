package com.hy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {

    static final String BRANDTABLE_CREATE =   
            "create table brand( " +
            "_id integer primary key autoincrement, " +   
            "brand_name text , " +
            "brand_pic blob);"; 
    static final String SERIESTABLE_CREATE =   
            "create table series( " +
            "_id integer primary key autoincrement, " +  
            "brand_name text, " +
            "series_name text, " +
            "series_indro text, " +
            "series_pic blob);"; 
    static final String GENERATIONTABLE_CREATE =   
            "create table generation( " +
            "_id integer primary key autoincrement, " +   
            "brand_name text, " +
            "series_name text, " +
            "generation text," +
            "generation_pic blob)"; 
    static final String COLORTABLE_CREATE =   
            "create table color( " +
            "_id integer primary key autoincrement, " +   
            "brand_name text, " +
            "series_name text, " +
            "generation text," +
            "color text," +
            "color_pic blob);"; 
    static final String SHOESTABLE_CREATE =   
            "create table shoes( " +
            "_id integer primary key autoincrement, " +   
            "brand_name text, " +
            "series_name text, " +
            "generation text," +
            "color text," +
            "shose_name text," +
            "shoes_pic blob," +
            "shoes_price integer," +
            "shoes_season text," +
            "shoes_upper text," +
            "shoes_upperMaterial text," +
            "shoes_lowMaterial text," +
            "shoes_function," +
            "shoes_position," + //Guard,Forward,Center-forward
            "shoes_sex," +
            "shoes_technology," +
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
    		path = Environment.getDataDirectory().getPath();
		}
    	
        return path;
}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(BRANDTABLE_CREATE);
		db.execSQL(SERIESTABLE_CREATE);
		db.execSQL(GENERATIONTABLE_CREATE);
		db.execSQL(COLORTABLE_CREATE);
		db.execSQL(SHOESTABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
