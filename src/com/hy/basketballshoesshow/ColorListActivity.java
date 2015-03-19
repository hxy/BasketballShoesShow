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

public class ColorListActivity extends Activity {

    private StopScrollAddList colorListView;
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
                    adapter.setList(getColorList(levelInfo));
                    colorListView.setAdapter(adapter); 
                    refreshLayout.setRefreshing(false);
                    }else{
                        int beginPosition = adapter.getCount();
                        adapter.setList(getColorList(levelInfo));
                        colorListView.setAdapter(adapter); 
                        colorListView.setSelection(beginPosition);
                        colorListView.setAdding(false);
                    }
                }else if (ERROR == msg.what) {
                    Toast.makeText(ColorListActivity.this, "网络错误,请稍后再试",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }else {
                    Toast.makeText(ColorListActivity.this, "服务器已被掏空...",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
            }
            
        };
        setContentView(R.layout.activity_category);
        this.setTitle("颜色");
        colorListView = (StopScrollAddList) findViewById(R.id.list);
        
        colorListView.setService(dataService);
        colorListView.SetCategoryCache(categoryCache);
        colorListView.setScrollListener();
        
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        ArrayList<CategoryInfo> colorList = getColorList(levelInfo);
//        if (0 != colorList.size()) {
            adapter = new CategoryAdapter(this, colorList);
            colorListView.setAdapter(adapter);
            colorListView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                        int arg2, long arg3) {
                    Intent intent = new Intent(ColorListActivity.this,ShoesListActivity.class);
                    String color = ((Holder)(arg1.getTag())).getTextView().getText().toString();
                    levelInfo.add(color);
                    intent.putExtra("levelInfo", levelInfo);
                    ColorListActivity.this.startActivity(intent);
                    levelInfo.remove(levelInfo.size()-1);
                }
            });
//        }
        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            
            @Override
            public void onRefresh() {
                getColorFromServer(GetDataService.REFRESH);
            }
        });
        colorListView.setOnAddMoreListener(new OnAddMoreListener() {   
            @Override
            public void OnAddMore() {
                getColorFromServer(GetDataService.ADDMOER);
            }
        });
    }
    
    private void getColorFromServer(int model){
        int COLOR = 3;
        int startServerId = dbAdapter.getStartServerId(COLOR, levelInfo);
        dataService.getDataFromServer("color",levelInfo,startServerId,mainHandler,model);
    }

    private ArrayList<CategoryInfo> getColorList(ArrayList<String> levelInfo){
        ArrayList<CategoryInfo> colorList = null;
        colorList = dbAdapter.getColorList(levelInfo.get(0), levelInfo.get(1));
        return colorList;
    }
}
