package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.PullRefreshLayout.OnRefreshListener;
import com.hy.adapter.CategoryAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetDataService;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;
import com.hy.tools.OnAddMoreListener;
import com.hy.tools.StopScrollAddList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GenerationListActivity extends Activity {

    private StopScrollAddList generationList;
    private CategoryAdapter adapter;
    private DBAdapter dbAdapter;
    private ArrayList<String> levelInfo;
    private GetDataService dataService;
	private CategoryCache categoryCache;
    private Handler mainHandler;
    private PullRefreshLayout refreshLayout;
    private int NOMORE = 0;
    private int ERROR = -1;
    private int OK = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        dataService = ((BSSApplication)getApplication()).getPicService();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        mainHandler = new Handler(){
            @SuppressLint("NewApi")
            @Override
            public void handleMessage(Message msg) {
                if(OK == msg.what){
                    if(msg.arg1 == GetDataService.REFRESH){
                    adapter.setList(dbAdapter.getGenerationList(levelInfo.get(0), levelInfo.get(1)));
                    generationList.setAdapter(adapter); 
                    refreshLayout.setRefreshing(false);
                    }else{
                        int beginPosition = adapter.getCount();
                        adapter.setList(dbAdapter.getGenerationList(levelInfo.get(0), levelInfo.get(1)));
                        generationList.setAdapter(adapter); 
                        generationList.setSelection(beginPosition);
                        generationList.setAdding(false);
                    }
                }else if (ERROR == msg.what) {
                    Toast.makeText(GenerationListActivity.this, "网络错误,请稍后再试",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }else {
                    Toast.makeText(GenerationListActivity.this, "服务器已被掏空...",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
            }
            
        };
        
        setContentView(R.layout.activity_category);
        generationList = (StopScrollAddList) findViewById(R.id.list);
        
        generationList.setService(dataService);
        generationList.SetCategoryCache(categoryCache);
        generationList.setScrollListener();
        
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> categoryList = dbAdapter.getGenerationList(levelInfo.get(0), levelInfo.get(1));
        if (0 != categoryList.size()) {
            adapter = new CategoryAdapter(this, categoryList);
            generationList.setAdapter(adapter);
            generationList.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    Intent intent = new Intent(GenerationListActivity.this,ColorListActivity.class);
                    String generation = ((Holder)(arg1.getTag())).getTextView().getText().toString();
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
        
        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            
            @Override
            public void onRefresh() {
                getGenerationFromServer(GetDataService.REFRESH);
            }
        });
        generationList.setOnAddMoreListener(new OnAddMoreListener() {   
            @Override
            public void OnAddMore() {
                getGenerationFromServer(GetDataService.ADDMOER);
            }
        });
    }
    
    private void getGenerationFromServer(int model){
        int GENERATION = 3;
        int startServerId = dbAdapter.getStartServerId(GENERATION, levelInfo);
        dataService.getDataFromServer("generation", levelInfo,startServerId,mainHandler,model);
    }
}
