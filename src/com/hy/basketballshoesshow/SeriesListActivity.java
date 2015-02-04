package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.ArrowAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetPicService;
import com.hy.tools.CategoryCache;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SeriesListActivity extends Activity {

    private ListView seriesListView;
    private ArrowAdapter seriesAdapter;
    private ArrayList<String> levelInfo;
    private GetPicService picService;
	private CategoryCache categoryCache;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picService = ((BSSApplication)getApplication()).getService();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        setContentView(R.layout.activity_category);
        seriesListView = (ListView)findViewById(R.id.list);
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> seriesList = ((BSSApplication)getApplication()).getdDbAdapter().getSeriesList(levelInfo.get(0));
        if(0!=seriesList.size()){
            seriesAdapter = new ArrowAdapter(this, seriesList, levelInfo);
            seriesListView.setAdapter(seriesAdapter);
            seriesListView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    String seriesName = categoryCache.getCategory(((CategoryInfo)(seriesAdapter.getItem(arg2))).getKey()).getName();
                    levelInfo.add(seriesName);
                    Intent intent = new Intent(SeriesListActivity.this,GenerationListActivity.class);
                    intent.putExtra("levelInfo", levelInfo);
                    SeriesListActivity.this.startActivity(intent);
                    levelInfo.remove(levelInfo.size()-1);
                }
            });
        }
        
    }

    
}
