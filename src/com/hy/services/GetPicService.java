package com.hy.services;

import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.OnGetDrawableListener;

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
    private ImageView imageView;
    private DBAdapter dbAdapter;
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

            @Override
            public void handleMessage(Message msg) {
                Object[] infos = (Object[])msg.obj;
                imageView = (ImageView)infos[2];
                if(null!=infos[1]){
                    imageView.setImageDrawable((Drawable)infos[1]);
                    imageView.setVisibility(View.VISIBLE);
                }else{
                    imageView.setVisibility(View.GONE);
                }
            }
            
        };
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        GetPicThread getPicThread = new GetPicThread();
        getPicThread.start();
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }

    public void getPic(String tableName,int pic_id,ImageView imageView){
        Message msg = Message.obtain(); 
        msg.what = imageView.hashCode();
        Object[] infos = {tableName,pic_id,imageView};
        msg.obj = infos;
        threadHandler.sendMessage(msg);
    }
    
    private class GetPicThread extends Thread{
        Drawable drawable;
        Message message;
        @Override
        public void run() {
            Looper.prepare();
            
            threadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    
                    Object[] infos = (Object[])msg.obj;
                    drawable = dbAdapter.getDrawable((String)infos[0], (Integer)infos[1]);
                    message = Message.obtain();
                    infos[1] = drawable;
                    message.obj = infos;
                    serviceHandler.sendMessage(message);
                }
                
            };
            Looper.loop();
        }
        
    }
    
    

}
