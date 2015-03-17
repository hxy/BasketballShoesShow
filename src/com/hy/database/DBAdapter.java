package com.hy.database;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.hy.objects.Brand;
import com.hy.objects.CategoryInfo;
import com.hy.objects.CategoryObject;
import com.hy.objects.Color;
import com.hy.objects.Generation;
import com.hy.objects.Series;
import com.hy.objects.Shoes;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

public class DBAdapter {

    private DBHelper dbHelper;
    private SQLiteDatabase writableDB;
    private SQLiteDatabase readableDB;

    public DBAdapter(Context context) {
        dbHelper = new DBHelper(context, "shoesDB", null, 1);
        writableDB = dbHelper.getWritableDatabase();
        readableDB = dbHelper.getReadableDatabase();
    }

    public long insertBrand(Brand brand) {

//        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("server_id", brand.getServerId());
        values.put("brand_name", brand.getName());
        values.put("brand_pic", brand.getBitmapBytes());
        long row = writableDB.insert("brand", null, values);
//        db.close();
        return row;
    }

    public long insertSeries(Series series) {

//        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("server_id", series.getServerId());
        values.put("brand_name", series.getBrandName());
        values.put("series_name", series.getName());
        values.put("series_pic", series.getBitmapBytes());
        values.put("series_indro", series.getIndro());
        long row = writableDB.insert("series", null, values);
//        db.close();
        return row;
    }

    public long insertGeneration(Generation generation) {

//        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("server_id", generation.getServerId());
        values.put("brand_name", generation.getBrandName());
        values.put("series_name", generation.getSeriesName());
        values.put("generation_name", generation.getName());
        values.put("generation_pic", generation.getBitmapBytes());
        long row = writableDB.insert("generation", null, values);
//        db.close();
        return row;
    }

    public long insertColor(Color color) {

//        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("server_id", color.getServerId());
        values.put("brand_name", color.getBrandName());
        values.put("series_name", color.getSeriesName());
        values.put("generation_name", color.getGeneration());
        values.put("color_name", color.getName());
        values.put("color_pic", color.getBitmapBytes());
        long row = writableDB.insert("color", null, values);
//        db.close();
        return row;
    }

    public long insertShoes(Shoes shoes) {

//        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("server_id", shoes.getServerId());
        values.put("brand_name", shoes.getBrand());
        values.put("series_name", shoes.getSeries());
        values.put("generation_name", shoes.getGeneration());
        values.put("color_name", shoes.getColor());
        values.put("shoes_name", shoes.getName());
        values.put("shoes_pic", shoes.getBitmapBytes());
        values.put("shoes_price", shoes.getPrice());
        values.put("shoes_season", shoes.getSeason());
        values.put("shoes_upper", shoes.getUpper());
        values.put("shoes_upperMaterial", shoes.getUpperMaterial());
        values.put("shoes_lowMaterial", shoes.getLowMaterial());
        values.put("shoes_function", shoes.getFunction());
        values.put("shoes_position", shoes.getPosition());
        values.put("shoes_sex", shoes.getSex());
        values.put("shoes_technology", shoes.getTechnology());
        values.put("shoes_indro", shoes.getIndro());
        long row = writableDB.insert("shoes", null, values);
//        db.close();
        return row;
    }

    public ArrayList<CategoryInfo> getBrandsList() {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("brand", new String[] { "_id", "brand_name" },
                null, null, null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("brand_name"));
            // bitmapBytes = cursor.getBlob(cursor.getColumnIndex("brand_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("brand", id));
        }
//        db.close();
        return list;
    }

    public ArrayList<CategoryInfo> getSeriesList(String brand) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("series",
                new String[] { "series_name", "_id" }, "brand_name=?",
                new String[] { brand }, null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("series_name"));
            // bitmapBytes =
            // cursor.getBlob(cursor.getColumnIndex("series_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("series", id));
        }
//        db.close();
        return list;
    }

    public String getSeriesIntroduce(String brandName, String seriesName) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("series", new String[] { "series_indro" },
                "brand_name=? and series_name=?", new String[] { brandName,
                        seriesName }, null, null, null);
        cursor.moveToFirst();
        String introduce = cursor.getString(cursor
                .getColumnIndex("series_indro"));
//        db.close();
        return introduce;

    }

    public ArrayList<CategoryInfo> getGenerationList(String brand, String series) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("generation", new String[] {
                "generation_name", "_id" }, "brand_name=? and series_name=?",
                new String[] { brand, series }, null, null, null);
        // String name;
        int id;
        // byte[] bitmapBytes = null;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("generation"));
            // bitmapBytes =
            // cursor.getBlob(cursor.getColumnIndex("generation_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("generation", id));
        }
//        db.close();
        return list;
    }

    public ArrayList<CategoryInfo> getColorList(String brand, String series,
            String generation) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("color", new String[] { "color_name", "_id" },
                "brand_name=? and series_name=? and generation_name = ?",
                new String[] { brand, series, generation }, null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("color"));
            // bitmapBytes = cursor.getBlob(cursor.getColumnIndex("color_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("color", id));
        }
//        db.close();
        return list;
    }

    public ArrayList<CategoryInfo> getColorList(String brand, String series) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("color", new String[] { "color_name", "_id" },
                "brand_name=? and series_name=?",
                new String[] { brand, series }, null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("color"));
            // bitmapBytes = cursor.getBlob(cursor.getColumnIndex("color_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("color", id));
        }
//        db.close();
        return list;
    }

    public ArrayList<CategoryInfo> getShoesList(String brand, String series,
            String generation, String color) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB
                .query("shoes",
                        new String[] { "shoes_name", "_id" },
                        "brand_name=? and series_name=? and generation_name = ? and color_name = ?",
                        new String[] { brand, series, generation, color },
                        null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("shoes_name"));
            // bitmapBytes = cursor.getBlob(cursor.getColumnIndex("shoes_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("shoes", id));
        }
//        db.close();
        return list;
    }

    public ArrayList<CategoryInfo> getShoesList(String brand, String series,
            String color) {
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.query("shoes", new String[] { "shoes_name", "_id" },
                "brand_name=? and series_name=? and color_name = ?",
                new String[] { brand, series, color }, null, null, null);
        // String name;
        // byte[] bitmapBytes;
        int id;
        ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
        while (cursor.moveToNext()) {
            // name = cursor.getString(cursor.getColumnIndex("shoes_name"));
            // bitmapBytes = cursor.getBlob(cursor.getColumnIndex("shoes_pic"));
            id = cursor.getInt(cursor.getColumnIndex("_id"));
            list.add(new CategoryInfo("shoes", id));
        }
//        db.close();
        return list;
    }

    public Shoes getShoes(int id) {
        Shoes shoes = null;
//        db = dbHelper.getReadableDatabase();
        Cursor cursor = readableDB.rawQuery("select * from shoes where _id=?",new String[] { String.valueOf(id) });
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            int serverId = cursor.getInt(cursor.getColumnIndex("server_id"));
            String brand = cursor.getString(cursor.getColumnIndex("brand_name"));
            String series = cursor.getString(cursor.getColumnIndex("series_name"));
            // if(-1!=cursor.getColumnIndex("generation_name")){
            //
            // }
            String generation = cursor.getString(cursor.getColumnIndex("generation_name"));
            String color = cursor.getString(cursor.getColumnIndex("color_name"));
            String shoesName = cursor.getString(cursor.getColumnIndex("shoes_name"));
            byte[] pic = cursor.getBlob(cursor.getColumnIndex("shoes_pic"));
            int price = cursor.getInt(cursor.getColumnIndex("shoes_price"));
            String season = cursor.getString(cursor.getColumnIndex("shoes_season"));
            String upper = cursor.getString(cursor.getColumnIndex("shoes_upper"));
            String upperMaterial = cursor.getString(cursor.getColumnIndex("shoes_upperMaterial"));
            String lowMaterial = cursor.getString(cursor.getColumnIndex("shoes_lowMaterial"));
            String function = cursor.getString(cursor.getColumnIndex("shoes_function"));
            String position = cursor.getString(cursor.getColumnIndex("shoes_position"));
            String sex = cursor.getString(cursor.getColumnIndex("shoes_sex"));
            String technology = cursor.getString(cursor.getColumnIndex("shoes_technology"));
            String indro = cursor.getString(cursor.getColumnIndex("shoes_indro"));
            shoes = new Shoes(serverId,brand, series, generation, color, shoesName, pic,
                    price, season, upper, upperMaterial, lowMaterial, function,
                    position, sex, technology, indro);
        }
//        db.close();
        return shoes;
    }

    public CategoryObject getCategoryObject(String tableName, int _id) {
//        db = dbHelper.getReadableDatabase();
        String picColumnName = tableName + "_pic";
        String nameColumnName = tableName + "_name";
        byte[] bitmapBytes;
        String name;
        Cursor cursor = readableDB.query(tableName, new String[] { nameColumnName,
                picColumnName }, "_id=" + _id, null, null, null, null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            bitmapBytes = cursor.getBlob(cursor.getColumnIndex(picColumnName));
            name = cursor.getString(cursor.getColumnIndex(nameColumnName));
//            db.close();
            return new CategoryObject(tableName, _id, name, bitmapBytes);
        } else {
//            db.close();
            return null;
        }

    }
    
    public int getStartServerId(int category,ArrayList<String> levelInfo){
        String sql = null;
//        db = dbHelper.getReadableDatabase();
        switch(category){
        case 1: sql = "select max(server_id) from brand";break;
        case 2: sql = "select max(server_id) from series where brand_name='"+levelInfo.get(0)+"'";break;
        case 3: sql = "select max(server_id) from generation where brand_name='"+levelInfo.get(0)+"' and series_name='"+levelInfo.get(1)+"'";break;
        case 4: if(2==levelInfo.size()){
            sql = "select max(server_id) from color where brand_name='"+levelInfo.get(0)+"' and series_name='"+levelInfo.get(1)+"'";break;
                }else {
                    sql = "select max(server_id) from color where brand_name='"+levelInfo.get(0)+"' and series_name='"+levelInfo.get(1)+"' and generation_name='"+levelInfo.get(2)+"'";break;
                }
            
        case 5: sql = "select max(server_id) from shoes where brand_name='"+levelInfo.get(0)+"' and series_name='"+levelInfo.get(1)+"' and generation_name='"+levelInfo.get(2)+"' and color_name="+levelInfo.get(3)+"'";break;
        }
        Cursor cursor = readableDB.rawQuery(sql, null);
        cursor.moveToFirst();
//        db.close();
        return cursor.getInt(0);
    }
    
    public void closeDB(){
        readableDB.close();
        writableDB.close();
    }
    // private ArrayList<CategoryInfo> getCategoryList(Cursor cursor,String
    // name, String pic){
    // String name;
    // byte[] bitmapBytes;
    // ArrayList<CategoryInfo> list = new ArrayList<CategoryInfo>();
    // while(cursor.moveToNext()){
    // name = cursor.getString(cursor.getColumnIndex(name));
    // bitmapBytes = cursor.getBlob(cursor.getColumnIndex(pic));
    // list.add(new CategoryInfo(name, bitmapBytes));
    // }
    // db.close();
    // return list;
    // }

}
