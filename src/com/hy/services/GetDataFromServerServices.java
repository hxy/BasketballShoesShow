package com.hy.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public class GetDataFromServerServices extends Service {

    private Handler serviceHandler;
    private Handler threadHandler;
    @Override
    public IBinder onBind(Intent intent) {
        
        return new ServerBinder();
    }
    
    public class ServerBinder extends Binder{
        public GetDataFromServerServices GetService(){
            return GetDataFromServerServices.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        serviceHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                
            }
        };
        
        GetDataThread thread = new GetDataThread();
        thread.start();
    }
    
    public void GetDataFromServer(String category, int startPosition){
        Message msg = Message.obtain(); 
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putInt("startPosition", startPosition);
        msg.setData(bundle);
        threadHandler.sendMessage(msg);
    }
    
    private class GetDataThread extends Thread{

        @Override
        public void run() {
            Looper.prepare();
            threadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    String category = msg.getData().getString("category");
                    int startPosition = msg.getData().getInt("startPosition");
                    getDataList(category, startPosition);
                    
                }
                
            };
        }

    }
    
    private ArrayList<Object> getDataList(String category, int startPosition){
        String urlPath = null;
        if("brand".equals(category)){
            urlPath = "http://localhost:8080/BasketballShoesShow/Brand.jsp?startposition="+startPosition;
        }else if("series".equals(category)){
            urlPath = "http://localhost:8080/BasketballShoesShow/Series.jsp?startposition="+startPosition;
        }else if("generation".equals(category)){
            urlPath = "http://localhost:8080/BasketballShoesShow/Generation.jsp?startposition="+startPosition;
        }else if("color".equals(category)){
            urlPath = "http://localhost:8080/BasketballShoesShow/Color.jsp?startposition="+startPosition;
        }else if("shoes".equals(category)){
            urlPath = "http://localhost:8080/BasketballShoesShow/Shoes.jsp?startposition="+startPosition;
        }
        
        try {
            URL url = new URL(urlPath);
            HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            if(con.getResponseCode() == 200){
                InputStream inputStream = con.getInputStream();
                byte[] bytes = readStream(inputStream);
                String jsonString = new String(bytes);
                System.out.println(jsonString);
                
                
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return null;
    }
    
    private ArrayList<Object> switchJsonToDataList(String category,byte[] bytes){
//        String jsonString = String.
        return null;
    }
    
    private byte[] readStream(InputStream in) throws IOException{
        
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len = in.read(bytes))!=-1){
            byteOut.write(bytes, 0 , len);
        }
        byteOut.close();
        in.close();
        
        return byteOut.toByteArray();
    }
}
