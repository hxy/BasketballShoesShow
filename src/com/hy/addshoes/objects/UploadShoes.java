package com.hy.addshoes.objects;

import android.util.Base64;

public class UploadShoes {

    private String brand;
    private String series;
    private String color;
    private String shoes;
    private String seriesIndro;
    private int price;
    private String season;
    private String upper;
    private String upperMaterial;
    private String lowMaterial;
    private String function;
    private String position;
    private String technology;
    private String indro;
    private byte[] brandBitmapBytes;
    private byte[] seriesBitmapBytes;
    private byte[] colorBitmapBytes;
    private byte[] shoesBitmapBytes;
    
    public UploadShoes(String brand,String series,String seriesIndro,String color,String shoes,
             int price,String season,String upper,String upperMaterial,
            String lowMaterial,String function,String position,String technology,
            String indro,byte[] brandBitmapBytes,byte[] seriesBitmapBytes,byte[] colorBitmapBytes,byte[] shoesBitmapBytes){
        this.brand = brand;
        this.series = series;
        this.seriesIndro = seriesIndro;
        this.color = color;
        this.shoes = shoes;
        this.price = price;
        this.season = season;
        this.upper = upper;
        this.upperMaterial = upperMaterial;
        this.lowMaterial = lowMaterial;
        this.function = function;
        this.position = position;
        this.technology = technology;
        this.indro = indro;
        this.brandBitmapBytes = brandBitmapBytes;
        this.seriesBitmapBytes = seriesBitmapBytes;
        this.colorBitmapBytes = colorBitmapBytes;
        this.shoesBitmapBytes = shoesBitmapBytes;
    }

    public String getBrand() {
        return brand;
    }

    public String getSeries() {
        return series;
    }
    
    public String getSeriesIndro() {
        return seriesIndro;
    }
    
    public String getColor() {
        return color;
    }

    public String getShoes() {
        return shoes;
    }

    public int getPrice() {
        return price;
    }

    public String getSeason() {
        return season;
    }

    public String getUpper() {
        return upper;
    }

    public String getUpperMaterial() {
        return upperMaterial;
    }

    public String getLowMaterial() {
        return lowMaterial;
    }

    public String getFunction() {
        return function;
    }

    public String getPosition() {
        return position;
    }

    public String getTechnology() {
        return technology;
    }

    public String getIndro() {
        return indro;
    }
    
    public String getBrandBitmap(){
        if(null == brandBitmapBytes){
            return null;
        }else {
            return Base64.encodeToString(brandBitmapBytes, Base64.DEFAULT);
        }
    }
    
    public String getSeriesBitmap(){
        if(null == seriesBitmapBytes){
            return null;
        }else {
            return Base64.encodeToString(seriesBitmapBytes, Base64.DEFAULT);
        }
    }
    
    public String getColorBitmap(){
        if(null == colorBitmapBytes){
            return null;
        }else {
            return Base64.encodeToString(colorBitmapBytes, Base64.DEFAULT);
        }
        
    }
    
    public String getShoesBitmap(){
        if(null == shoesBitmapBytes){
            return null;
        }else {
            return Base64.encodeToString(shoesBitmapBytes, Base64.DEFAULT);
        }
        
    }
}
