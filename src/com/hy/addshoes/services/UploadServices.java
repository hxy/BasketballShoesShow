package com.hy.addshoes.services;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.http.HttpConnection;
import org.json.JSONException;
import org.json.JSONObject;

import com.hy.addshoes.objects.UploadShoes;
import com.hy.objects.Shoes;

import android.R.integer;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public class UploadServices extends Service {

    private Handler threadHandler;
    private Handler mainHandler;
    private final int CHECK = 0;
    private final int UPLOAD = 1;
    private String BOUNDARY = java.util.UUID.randomUUID().toString(); 
    private String PREFIX = "--", LINEND = "\r\n";    
    private String MULTIPART_FROM_DATA = "multipart/form-data";    
    private String CHARSET = "UTF-8";  
    
    @Override
    public IBinder onBind(Intent intent) {
        
        return new UploadServicesBinder();
    }
    
    public class UploadServicesBinder extends Binder{
        public UploadServices getUploadServices(){
            return UploadServices.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UploadThread uploadThread =new UploadThread();
        uploadThread.start();
    }
    
    public void checkInfo(String brandName,String seriesName,String colorName,String shoesName,Handler mainHandler){
        this.mainHandler = mainHandler;
        Message msg = Message.obtain();
        String[] infos = {brandName,seriesName,colorName,shoesName};
        msg.obj = infos;
        msg.what = CHECK;
        threadHandler.sendMessage(msg);
    }
    
    public void uploadData(UploadShoes shoes ,Handler mainHandler){
        this.mainHandler = mainHandler;
        Message msg = Message.obtain();
        msg.obj = shoes;
        msg.what = UPLOAD;
        threadHandler.sendMessage(msg);
    }
    
    private class UploadThread extends Thread{

        @Override
        public void run() {
            Looper.prepare();
            threadHandler = new Handler(){

                @Override
                public void handleMessage(Message msg) {
                    switch(msg.what){
                    case CHECK:
                        String[] infos = (String[])msg.obj;
                        String result = checkInfoFromServer(infos[0],infos[1],infos[2],infos[3]);
                        Message message = Message.obtain();
                        message.what = CHECK;
                        message.obj = result;
                        mainHandler.sendMessage(message);
                        break;
                    case UPLOAD:
                        String resultString = uploadDataToServer((UploadShoes)(msg.obj));
                        Message message2 = Message.obtain();
                        message2.what = UPLOAD;
                        message2.obj = resultString;
                        mainHandler.sendMessage(message2);
                    }
                }
                
            };
            Looper.loop();
        }
        
    }
    
    private String checkInfoFromServer(String brandName,String seriesName,String colorName,String shoesName){
        String result = null;
        String urlPath = "http://10.0.2.2:8080/BasketballShoesShow/CheckInfo.jsp?brandName="+brandName+"&seriesName="+seriesName+"&colorName="+colorName+"&shoesName="+shoesName;
        try {
            URL url = new URL(urlPath);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setConnectTimeout(5000);
            con.setRequestMethod("GET"); 
            if(200==con.getResponseCode()){
                InputStream in = con.getInputStream();
                byte[] bytes = readStream(in);
                String jsonString = new String(bytes);
                JSONObject json = new JSONObject(jsonString);
                result = json.optString("category");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    private String uploadDataToServer(UploadShoes shoes){
        String result = null;
        HttpURLConnection con = null;
        try {
            String jsonString = switchToJson(shoes);
            String urlPath = "http://10.0.2.2:8080/BasketballShoesShow/receiveUploadPage.jsp";
            URL url = new URL(urlPath);
            con = (HttpURLConnection)url.openConnection();
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);    
            con.setRequestProperty("connection", "keep-alive");
            con.setRequestProperty("Charsert", "UTF-8");
            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            DataOutputStream dataOut = new DataOutputStream(con.getOutputStream());
            String parameter = jsonString;
            dataOut.writeBytes(parameter);
            dataOut.flush();
            dataOut.close();
            if(con.getResponseCode() == 200){
                InputStream in = con.getInputStream();
                byte[] bytes = readStream(in);
                String res = new String(bytes);
                JSONObject resultJson = new JSONObject(res);
                result = resultJson.optString("resultString");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            con.disconnect();
        }
        con.disconnect();
        return result;
    }

    
    private String switchToJson(UploadShoes shoes) throws JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("brandName", shoes.getBrand());
        jsonObject.put("seriesName", shoes.getSeries());
        jsonObject.put("seriesIndro", shoes.getSeriesIndro());
        jsonObject.put("colorName", shoes.getColor());
        jsonObject.put("shoesName", shoes.getShoes());
        jsonObject.put("price", shoes.getPrice());
        jsonObject.put("season", shoes.getSeason());
        jsonObject.put("upper", shoes.getUpper());
        jsonObject.put("upperMaterial", shoes.getUpperMaterial());
        jsonObject.put("lowMaterial", shoes.getLowMaterial());
        jsonObject.put("function", shoes.getFunction());
        jsonObject.put("position", shoes.getPosition());
        jsonObject.put("technology", shoes.getTechnology());
        jsonObject.put("indro", shoes.getIndro());
        jsonObject.put("brandBitmapBytes", shoes.getBrandBitmap());
        jsonObject.put("seriesBitmapBytes", shoes.getSeriesBitmap());
        jsonObject.put("colorBitmapBytes", shoes.getColorBitmap());
        jsonObject.put("shoesBitmapBytes", shoes.getShoesBitmap());
        
        return jsonObject.toString();
    }
    
    
    private byte[] readStream(InputStream in) throws IOException{
        ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[512];
        int len = 0;
        while(-1!=(len=in.read(bytes))){
            bytesOut.write(bytes, 0, len);
        }
        return bytesOut.toByteArray();
    }
}
