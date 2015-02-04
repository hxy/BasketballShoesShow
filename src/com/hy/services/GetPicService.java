package com.hy.services;

import java.util.jar.Attributes.Name;

import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryObject;
import com.hy.objects.OnGetDrawableListener;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;

import android.app.Service;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class GetPicService extends Service {

    private Handler serviceHandler;
    private Handler threadHandler;
    private DBAdapter dbAdapter;
    private CategoryCache categoryCache;
    @Override
    public IBinder onBind(Intent arg0) {

        return new PicBinder();
    }
    
    public class PicBinder extends Binder{
        public GetPicService GetService(){
            return GetPicService.this;
        }
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        serviceHandler = new Handler(){
        	Holder holder;
        	String key;
        	CategoryObject object;
            @Override
            public void handleMessage(Message msg) {
                Object[] infos = (Object[])msg.obj;
                key = (String)infos[0];
                object = (CategoryObject)infos[1];
                holder = (Holder)infos[2];
                if(null!=infos[1]){
                	categoryCache.cacheCategory(key, object);
                    holder.getImageView().setImageDrawable(object.getDrawable());
                    holder.getTextView().setText(object.getName());
                }else{
//                    imageView.setVisibility(View.GONE);
                }
            }
            
        };
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        GetPicThread getPicThread = new GetPicThread();
        getPicThread.start();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }

    public void getPic(String tableName,int pic_id,Holder holder){
        Message msg = Message.obtain(); 
//        msg.what = imageView.hashCode();
        Object[] infos = {tableName,pic_id,holder};
        msg.obj = infos;
        threadHandler.sendMessage(msg);
    }
    
    private class GetPicThread extends Thread{
        CategoryObject object;
        Message message;
        String tableName;
        int id;
        String key;
        @Override
        public void run() {
            Looper.prepare();
            
            threadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    
                    Object[] infos = (Object[])msg.obj;
                    tableName = (String)infos[0];
                    id = (Integer)infos[1];
                    object = dbAdapter.getCategoryObject(tableName, id);
                    message = Message.obtain();
                    key = tableName + id;
                    infos[0] = key;
                    infos[1] = object;
                    message.obj = infos;
                    serviceHandler.sendMessage(message);
                }
                
            };
            Looper.loop();
        }
        
    }
    
    

}
