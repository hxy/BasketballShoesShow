package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.GridAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ShoesListActivity extends Activity {

    private GridView gridView;
    private DBAdapter dbAdapter;
    private ArrayList<String> levelInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoeslist);
        gridView = (GridView)findViewById(R.id.grid_shoes);
        gridView.setColumnWidth(getColumnWidth());
        gridView.setNumColumns(((BSSApplication)getApplicationContext()).getScreenWith()/160);
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        ArrayList<CategoryInfo> shoesList = getShoesList(levelInfo);
        final GridAdapter gridAdapter = new GridAdapter(this, shoesList);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
//                Intent intent = new Intent(ShoesListActivity.this,ShoesShowActivity.class);
//                String color = ((CategoryInfo)(gridAdapter.getItem(arg2))).getName();
//                levelInfo.add(color);
//                intent.putExtra("levelInfo", levelInfo);
//                ShoesListActivity.this.startActivity(intent);
//                levelInfo.remove(levelInfo.size()-1);
            }
        });
    }

    private int getColumnWidth(){
        int screenWith = ((BSSApplication)getApplicationContext()).getScreenWith();
        int column_num = screenWith/(160+4);
        
        return screenWith/column_num;
    }
    
    private ArrayList<CategoryInfo> getShoesList(ArrayList<String> levelInfo){
        ArrayList<CategoryInfo> shoesList = null;
        if(levelInfo.size()==4){
            shoesList = dbAdapter.getShoesList(levelInfo.get(0), levelInfo.get(1),levelInfo.get(2),levelInfo.get(3));
        }else if (levelInfo.size()==3) {
            shoesList = dbAdapter.getShoesList(levelInfo.get(0), levelInfo.get(1),levelInfo.get(2));
        }
        return shoesList;
    }
}
