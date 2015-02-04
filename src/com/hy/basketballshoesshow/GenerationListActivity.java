package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.CategoryAdapter;
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

public class GenerationListActivity extends Activity {

    private ListView listView;
    private CategoryAdapter adapter;
    private ArrayList<String> levelInfo;
    private GetPicService picService;
	private CategoryCache categoryCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picService = ((BSSApplication)getApplication()).getService();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        setContentView(R.layout.activity_category);
        listView = (ListView) findViewById(R.id.list);
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> categoryList = ((BSSApplication)getApplication()).getdDbAdapter().getGenerationList(levelInfo.get(0), levelInfo.get(1));
        if (0 != categoryList.size()) {
            adapter = new CategoryAdapter(this, categoryList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    Intent intent = new Intent(GenerationListActivity.this,ColorListActivity.class);
                    String generation = categoryCache.getCategory(((CategoryInfo)(adapter.getItem(arg2))).getKey()).getName();
                    levelInfo.add(generation);
                    intent.putExtra("levelInfo", levelInfo);
                    GenerationListActivity.this.startActivity(intent);
                    levelInfo.remove(levelInfo.size()-1);
                }
            });
        }else{
            Intent intent = new Intent(GenerationListActivity.this,ColorListActivity.class);
            intent.putExtra("levelInfo", levelInfo);
            GenerationListActivity.this.startActivity(intent);
            GenerationListActivity.this.finish();
        }
    }
}
