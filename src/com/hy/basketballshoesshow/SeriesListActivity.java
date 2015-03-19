package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.PullRefreshLayout.OnRefreshListener;
import com.hy.adapter.ArrowAdapter;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class SeriesListActivity extends Activity {

    private StopScrollAddList seriesListView;
    private ArrowAdapter adapter;
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
                    adapter.setList(dbAdapter.getSeriesList(levelInfo.get(0)));
                    seriesListView.setAdapter(adapter); 
                    refreshLayout.setRefreshing(false);
                    }else{
                        int beginPosition = adapter.getCount();
                        adapter.setList(dbAdapter.getSeriesList(levelInfo.get(0)));
                        seriesListView.setAdapter(adapter); 
                        seriesListView.setSelection(beginPosition);
                        seriesListView.setAdding(false);
                    }
                }else if (ERROR == msg.what) {
                    Toast.makeText(SeriesListActivity.this, "网络错误,请稍后再试",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    seriesListView.setAdding(false);
                }else {
                    Toast.makeText(SeriesListActivity.this, "服务器已被掏空...",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    seriesListView.setAdding(false);
                }
            }
            
        };
        
        setContentView(R.layout.activity_category);
        this.setTitle("系列");
        seriesListView = (StopScrollAddList)findViewById(R.id.list);
        
        seriesListView.setService(dataService);
        seriesListView.SetCategoryCache(categoryCache);
        seriesListView.setScrollListener();
        
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> seriesList = dbAdapter.getSeriesList(levelInfo.get(0));
//        if(0!=seriesList.size()){
            adapter = new ArrowAdapter(this, seriesList, levelInfo);
            seriesListView.setAdapter(adapter);
            seriesListView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    String seriesName = ((Holder)(arg1.getTag())).getTextView().getText().toString();
                    levelInfo.add(seriesName);
                    Intent intent = new Intent(SeriesListActivity.this,ColorListActivity.class);
                    intent.putExtra("levelInfo", levelInfo);
                    SeriesListActivity.this.startActivity(intent);
                    levelInfo.remove(levelInfo.size()-1);
                }
            });
            
//        }
     
        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            
            @Override
            public void onRefresh() {
                getSeriesFromServer(GetDataService.REFRESH);
            }
        });
        seriesListView.setOnAddMoreListener(new OnAddMoreListener() {   
            @Override
            public void OnAddMore() {
                getSeriesFromServer(GetDataService.ADDMOER);
            }
        });
    }

    private void getSeriesFromServer(int model){
        int SERIES = 2;
        int startServerId = dbAdapter.getStartServerId(SERIES, levelInfo);
        dataService.getDataFromServer("series",levelInfo ,startServerId,mainHandler,model);
    }
}
