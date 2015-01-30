package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.CategoryAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ColorListActivity extends Activity {

    private ListView listView;
    private CategoryAdapter adapter;
    private ArrayList<String> levelInfo;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.list);
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> colorList = getColorList(levelInfo);
        if (0 != colorList.size()) {
            adapter = new CategoryAdapter(this, colorList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    Intent intent = new Intent(ColorListActivity.this,ShoesListActivity.class);
                    String color = ((CategoryInfo)(adapter.getItem(arg2))).getName();
                    levelInfo.add(color);
                    intent.putExtra("levelInfo", levelInfo);
                    ColorListActivity.this.startActivity(intent);
                    levelInfo.remove(levelInfo.size()-1);
                }
            });
        }
    }

    private ArrayList<CategoryInfo> getColorList(ArrayList<String> levelInfo){
        ArrayList<CategoryInfo> colorList = null;
        DBAdapter dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        if(levelInfo.size()==3){
            colorList = dbAdapter.getColorList(levelInfo.get(0), levelInfo.get(1),levelInfo.get(2));
        }else if (levelInfo.size()==2) {
            colorList = dbAdapter.getColorList(levelInfo.get(0), levelInfo.get(1));
        }
        return colorList;
    }
}
