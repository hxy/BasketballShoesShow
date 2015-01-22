package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.ArrowAdapter;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class SeriesListActivity extends Activity {

    private ListView seriesListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        seriesListView = (ListView)findViewById(R.id.list);
        ArrayList<String> levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> seriesList = new DBAdapter(this).getSeriesList(levelInfo.get(0));
        if(0!=seriesList.size()){
            ArrowAdapter seriesAdapter = new ArrowAdapter(this, seriesList, levelInfo);
            seriesListView.setAdapter(seriesAdapter);
        }
        
    }

    
}
