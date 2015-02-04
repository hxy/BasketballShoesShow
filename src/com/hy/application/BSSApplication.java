package com.hy.application;

import com.hy.database.DBAdapter;
import com.hy.services.GetPicService;
import com.hy.tools.CategoryCache;

import android.app.Application;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.view.Display;
import android.view.WindowManager;

public class BSSApplication extends Application {

    private Display mDisplay;
    private DBAdapter dbAdapter;
    private GetPicService picService;
    private CategoryCache categoryCache;
    @Override
    public void onCreate() {
        super.onCreate();
        mDisplay = ((WindowManager) getSystemService(this.WINDOW_SERVICE))
                .getDefaultDisplay();
        dbAdapter = new DBAdapter(this);
        categoryCache = CategoryCache.getInstance();
        
        
        Intent intent = new Intent(this,GetPicService.class);
        ServiceConnection conn = new ServiceConnection() {  
            @Override  
            public void onServiceDisconnected(ComponentName name) {  
                  
            }  
              
            @Override  
            public void onServiceConnected(ComponentName name, IBinder service) {  

                picService = ((GetPicService.PicBinder)service).GetService();   
            }  
        };  
        bindService(intent, conn, BIND_AUTO_CREATE);
    }
    
    public int getScreenWith(){
        return mDisplay.getWidth();
    }
    public int getScreenHeight(){
        return mDisplay.getHeight();
    }
    public DBAdapter getdDbAdapter(){
        return dbAdapter;
    }
    public GetPicService getService(){
        return picService;
    }
    public CategoryCache getCategoryCache(){
    	return categoryCache;
    }

}
