package com.hy.application;

import com.hy.database.DBAdapter;
import com.hy.services.GetDataService;
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
    private GetDataService picService;
    private CategoryCache categoryCache;
    private boolean isAppFirst = true;
    private boolean isBrandFirst = true;
    private boolean isSeriesFirst = true;
    private boolean isColorFirst = true;
    private boolean isShoesFirst = true;

    @Override
    public void onCreate() {
        super.onCreate();
        mDisplay = ((WindowManager) getSystemService(this.WINDOW_SERVICE))
                .getDefaultDisplay();
        dbAdapter = new DBAdapter(this);
        categoryCache = CategoryCache.getInstance();
        
        
        Intent intent = new Intent(this,GetDataService.class);
        ServiceConnection conn = new ServiceConnection() {  
            @Override  
            public void onServiceDisconnected(ComponentName name) {  
                  
            }  
              
            @Override  
            public void onServiceConnected(ComponentName name, IBinder service) {  
                String nameString = name.getShortClassName();
                    picService = ((GetDataService.PicBinder)service).GetService();

                   
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
    public GetDataService getPicService(){
        return picService;
    }
    public CategoryCache getCategoryCache(){
    	return categoryCache;
    }

    public boolean isAppFirst() {
        return isAppFirst;
    }
    public void setAppFirst(boolean isFirst) {
        this.isAppFirst = isFirst;
    }
    
    public boolean isBrandFirst() {
        return isBrandFirst;
    }

    public void setBrandFirst(boolean isBrandFirst) {
        this.isBrandFirst = isBrandFirst;
    }

    public boolean isSeriesFirst() {
        return isSeriesFirst;
    }

    public void setSeriesFirst(boolean isSeriesFirst) {
        this.isSeriesFirst = isSeriesFirst;
    }

    public boolean isColorFirst() {
        return isColorFirst;
    }

    public void setColorFirst(boolean isColorFirst) {
        this.isColorFirst = isColorFirst;
    }

    public boolean isShoesFirst() {
        return isShoesFirst;
    }

    public void setShoesFirst(boolean isShoesFirst) {
        this.isShoesFirst = isShoesFirst;
    }
}
