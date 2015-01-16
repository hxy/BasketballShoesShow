package com.hy.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {

    static final String DATABASE_CREATE =   
            "create table basketballshoes( " +
            "_id integer primary key autoincrement, " +   
            "brand_name text, " +
            "brand_pic blob," +
            "series_name text, " +
            "generation text," +
            "shose_name text," +
            "shoes_pic blob," +
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
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
