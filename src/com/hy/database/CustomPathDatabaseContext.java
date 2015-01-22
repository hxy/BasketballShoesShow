package com.hy.database;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class CustomPathDatabaseContext extends ContextWrapper {
	
	private String mDirPath;
    
    public CustomPathDatabaseContext(Context base, String dirPath) {
            super(base);
            this.mDirPath = dirPath + File.separator + "basketballshoesshow" +File.separator+"database";
    }
   
    @Override
    public File getDatabasePath(String name)
    {
        File result = new File(mDirPath + File.separator + name);

        if (!result.getParentFile().exists())
        {
            result.getParentFile().mkdirs();
        }

        return result;
    }
   
    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory)
    {
            return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
    }
    @SuppressLint("NewApi")
	@Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler){
           return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name).getAbsolutePath(), factory, errorHandler);
    }

}
