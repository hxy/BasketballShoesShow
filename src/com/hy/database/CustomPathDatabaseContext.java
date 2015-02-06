package com.hy.database;

import java.io.File;

import android.R;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.view.WindowManager;

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
            SQLiteDatabase db = null;
                db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), factory);
               return db;
    }
    @SuppressLint("NewApi")
	@Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, CursorFactory factory, DatabaseErrorHandler errorHandler){
        SQLiteDatabase db = null;
            db = SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name).getAbsolutePath(), factory, errorHandler); 
           return db;
    }
    
//    private void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(context); 
//        builder.setIcon(R.drawable.alert_light_frame);
//        builder.setMessage(getString(com.hy.basketballshoesshow.R.string.canNotCreateDB));
//        builder.setPositiveButton(com.hy.basketballshoesshow.R.string.OK, new OnClickListener(){
//
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//                System.exit(0);
//            }
//            
//        });
//        
//        AlertDialog dialog = builder.create();
//        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
//        dialog.show();
//    }

}
