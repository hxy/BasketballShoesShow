package com.hy.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


import com.hy.adapter.ArrowAdapter;
import com.hy.adapter.GetSetList;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.Brand;
import com.hy.objects.CategoryObject;
import com.hy.objects.Color;
import com.hy.objects.Generation;
import com.hy.objects.OnGetDrawableListener;
import com.hy.objects.Series;
import com.hy.objects.Shoes;
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
import android.util.Base64;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;

public class GetDataService extends Service {

    private Handler serviceHandler;
    private Handler activityHandler;
    private Handler picThreadHandler;
    private Handler dataThreadHandler;
    private DBAdapter dbAdapter;
    private CategoryCache categoryCache;
    private int DATALIST = 0;
    private int PIC = 1;
    private int BRAND = 1;
    private int SERIES = 2;
    private int GENERATION = 3;
    private int COLOR = 4;
    private int SHOES = 5;
    private int NOMORE = 0;
    private int ERROR = -1;
    private int OK = 1;
    public static int REFRESH = 0;
    public static int ADDMOER = 1;
    private BaseAdapter adapter;
    
    @Override
    public IBinder onBind(Intent arg0) {

        return new PicBinder();
    }
    
    public class PicBinder extends Binder{
        public GetDataService GetService(){
            return GetDataService.this;
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
                        if(null!=holder.getArrowView()){
                            holder.getArrowView().setVisibility(View.VISIBLE);
                            holder.getArrowView().setOnClickListener(((ArrowAdapter)holder.getAdapter()).new ArrowClickListener(object.getName()));
                        }
//                        holder.getImageView().setVisibility(View.VISIBLE);
                    }else{
//                        imageView.setVisibility(View.GONE);
                    }
            }  
        };
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        GetPicThread getPicThread = new GetPicThread();
        getPicThread.start();
        GetDataThread getDataThread = new GetDataThread();
        getDataThread.start();
    }
    

    public void getPic(String tableName,int pic_id,Holder holder){
        Message msg = Message.obtain(); 
//        msg.what = imageView.hashCode();
        Object[] infos = {tableName,pic_id,holder};
        msg.obj = infos;
        picThreadHandler.sendMessage(msg);
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
            
            picThreadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    
                    Object[] infos = (Object[])msg.obj;
                    tableName = (String)infos[0];
                    id = (Integer)infos[1];
                    object = dbAdapter.getCategoryObject(tableName, id);
                    message = Message.obtain();
                    message.what = PIC;
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
    
    //----------------------------------------------------------------------
    
    public void getDataFromServer(String category, int startPosition,Handler activityHandler,int model){
        this.activityHandler = activityHandler;
        Message msg = Message.obtain(); 
        msg.arg1 = model;
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putInt("startPosition", startPosition);
        msg.setData(bundle);
        dataThreadHandler.sendMessage(msg);
    }
    
    private class GetDataThread extends Thread{
        String category = null;
        int startPosition = 0;
        int count =0;
        Message message;
        @Override
        public void run() {
            Looper.prepare();
            dataThreadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    category = msg.getData().getString("category");
                    startPosition = msg.getData().getInt("startPosition");
                    count = getDataListAndInsertToDB(category, startPosition);
                    if(count == NOMORE){
                        activityHandler.sendEmptyMessage(NOMORE);
                    }else if (count == ERROR) {
                        activityHandler.sendEmptyMessage(ERROR);
                    }else {
                        message = Message.obtain();
                        message.what = OK;
                        message.arg1 = msg.arg1;
                        activityHandler.sendMessage(message);
                    }
                }
                
            };
            Looper.loop();
        }

    }
    
    
    private int getDataListAndInsertToDB(String category, int startPosition){
        String urlPath = null;
        int count = 0;
        if("brand".equals(category)){
            urlPath = "http://10.0.2.2:8080/BasketballShoesShow/Brand.jsp?startposition="+startPosition;
        }else if("series".equals(category)){
            urlPath = "http://10.0.2.2:8080/BasketballShoesShow/Series.jsp?startposition="+startPosition;
        }else if("generation".equals(category)){
            urlPath = "http://10.0.2.2:8080/BasketballShoesShow/Generation.jsp?startposition="+startPosition;
        }else if("color".equals(category)){
            urlPath = "http://10.0.2.2:8080/BasketballShoesShow/Color.jsp?startposition="+startPosition;
        }else if("shoes".equals(category)){
            urlPath = "http://10.0.2.2:8080/BasketballShoesShow/Shoes.jsp?startposition="+startPosition;
        }
        
        try {
            URL url = new URL(urlPath);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            if(con.getResponseCode() == 200){
                InputStream inputStream = con.getInputStream();
                byte[] bytes = readStream(inputStream);
                String jsonString = new String(bytes);
                count = switchJsonToDataListAndInsertToDB(category,jsonString);
                
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            count = -1;
        }
        
        return count;
    }
    
    private int switchJsonToDataListAndInsertToDB(String category,String json) throws JSONException{
            int count = 0;
            JSONArray jsonArray = new JSONArray(json);
            for(int n = 0;n<jsonArray.length();n++){
                count += switchJsonToDataAndInsertToDB(category,jsonArray.getJSONObject(n));
            }
            return count;
    }
    
    private int switchJsonToDataAndInsertToDB(String category, JSONObject jsonObject) throws JSONException{
        byte[] picBytes = null;  
//        BASE64Decoder decoder = new BASE64Decoder();  
        if("brand".equals(category)){
            String brandName = jsonObject.getString("brandName");
            String brandPic = jsonObject.getString("brandPic");
            picBytes = Base64.decode(brandPic, Base64.DEFAULT);
            Brand brand = new Brand(brandName, picBytes);
            if(-1 != dbAdapter.insertBrand(brand)){
                return 1;
            }

        }else if("series".equals(category)){
            String brandName = jsonObject.getString("brandName");
            String seriesName = jsonObject.getString("seriesName");
            String seriesIndro = jsonObject.getString("seriesIndro");
            String seriesPic = jsonObject.getString("seriesPic");
            picBytes = Base64.decode(seriesPic, Base64.DEFAULT);
            Series series = new Series(brandName, seriesName, picBytes, seriesIndro);
            if(-1!=dbAdapter.insertSeries(series)){
                return 1;
            }
        }else if("generation".equals(category)){
            String brandName = jsonObject.getString("brandName");
            String seriesName = jsonObject.getString("seriesName");
            String generationName = jsonObject.getString("generationName");
            String generationPic = jsonObject.getString("generationPic");
            picBytes = Base64.decode(generationPic, Base64.DEFAULT);
            Generation generation = new Generation(brandName, seriesName, generationName,picBytes);
            if(-1!=dbAdapter.insertGeneration(generation)){
                return 1;
            }
        }else if ("color".equals(category)) {
            String brandName = jsonObject.getString("brandName");
            String seriesName = jsonObject.getString("seriesName");
            String generationName = jsonObject.getString("generationName");
            String colorName = jsonObject.getString("colorName");
            String colorPic = jsonObject.getString("colorPic");
            picBytes = Base64.decode(colorPic, Base64.DEFAULT);
            Color color = new Color(brandName, seriesName, generationName,colorName,picBytes);
            if(-1!=dbAdapter.insertColor(color)){
                return 1;
            }
            
        }else if ("shoes".equals(category)) {
            String brandName = jsonObject.getString("brandName");
            String seriesName = jsonObject.getString("seriesName");
            String generationName = jsonObject.getString("generationName");
            String colorName = jsonObject.getString("colorName");
            String shoesName = jsonObject.getString("shoesName");
            String shoesPic = jsonObject.getString("shoesPic");
            picBytes = Base64.decode(shoesPic, Base64.DEFAULT);
            int shoesPrice = jsonObject.getInt("shoesPrice");
            String shoesSeason = jsonObject.getString("shoesSeason");
            String shoesUpper = jsonObject.getString("shoesUpper");
            String shoesUpperMaterial = jsonObject.getString("shoesUpperMaterial");
            String shoesLowMaterial = jsonObject.getString("shoesLowMaterial");
            String shoesFunction = jsonObject.getString("shoesFunction");
            String shoesPosition = jsonObject.getString("shoesPosition");
            String shoesSex = jsonObject.getString("shoesSex");
            String shoesTechnology = jsonObject.getString("shoesTechnology");
            String shoesIndro = jsonObject.getString("shoesIndro");
            Shoes shoes = new Shoes(brandName, seriesName, generationName, colorName, shoesName, picBytes, shoesPrice, shoesSeason, shoesUpper, shoesUpperMaterial, shoesLowMaterial, shoesFunction, shoesPosition, shoesSex, shoesTechnology, shoesIndro);
            if(-1!=dbAdapter.insertShoes(shoes)){
                return 1;
            }
        }
        return 0;
        
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
