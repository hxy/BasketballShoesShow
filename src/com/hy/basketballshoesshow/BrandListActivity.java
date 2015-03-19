package com.hy.basketballshoesshow;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.PullRefreshLayout.OnRefreshListener;
import com.hy.adapter.CategoryAdapter;
import com.hy.application.BSSApplication;
import com.hy.basketballshoesshow.R;
import com.hy.database.DBAdapter;
import com.hy.objects.Brand;
import com.hy.objects.CategoryInfo;
import com.hy.objects.CategoryObject;
import com.hy.objects.Color;
import com.hy.objects.Series;
import com.hy.objects.Shoes;
import com.hy.services.GetDataService;
import com.hy.services.GetDataService.PicBinder;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;
import com.hy.tools.OnAddMoreListener;
import com.hy.tools.StopScrollAddList;

import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.view.Menu;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class BrandListActivity extends Activity {

	private DBAdapter dbAdapter;
	private StopScrollAddList brandList;
	private CategoryAdapter adapter;
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
//        insterDataToDataBase();
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        dataService = ((BSSApplication)getApplication()).getPicService();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        mainHandler = new Handler(){
            @SuppressLint("NewApi")
            @Override
            public void handleMessage(Message msg) {
                if(OK == msg.what){
                    if(msg.arg1 == GetDataService.REFRESH){
                    adapter.setList(dbAdapter.getBrandsList());
                    brandList.setAdapter(adapter); 
                    refreshLayout.setRefreshing(false);
                    }else{
                        int beginPosition = adapter.getCount();
                        adapter.setList(dbAdapter.getBrandsList());
                        brandList.setAdapter(adapter); 
                        brandList.setSelection(beginPosition);
                        brandList.setAdding(false);
                    }
                }else if (ERROR == msg.what) {
                    Toast.makeText(BrandListActivity.this, "网络错误,请稍后再试",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    brandList.setAdding(false);
                }else {
                    Toast.makeText(BrandListActivity.this, "服务器已被掏空...",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                    brandList.setAdding(false);
                }
            }
            
        };
//        getBrandFromServer();
        setContentView(R.layout.activity_category);
        this.setTitle("品牌");
        brandList = (StopScrollAddList)findViewById(R.id.list);
        
        
        brandList.setService(dataService);
        brandList.SetCategoryCache(categoryCache);
        brandList.setScrollListener();
        
        adapter = new CategoryAdapter(this, dbAdapter.getBrandsList());
        
        brandList.setAdapter(adapter);
        brandList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                ArrayList<String> levelInfo = new ArrayList<String>();
//                String brandName = categoryCache.getCategory(((CategoryInfo)(adapter.getItem(arg2))).getKey()).getName();
                String brandName = ((Holder)(arg1.getTag())).getTextView().getText().toString();
                levelInfo.add(brandName);
                Intent intent = new Intent(BrandListActivity.this,SeriesListActivity.class);
                intent.putExtra("levelInfo", levelInfo);
                BrandListActivity.this.startActivity(intent);
            }
        });
        
        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            
            @Override
            public void onRefresh() {
                getBrandFromServer(GetDataService.REFRESH);
            }
        });
        brandList.setOnAddMoreListener(new OnAddMoreListener() {   
            @Override
            public void OnAddMore() {
                getBrandFromServer(GetDataService.ADDMOER);
            }
        });
        
    }

    private void getBrandFromServer(int model){
        int BRABD = 1;
        int startServerId = dbAdapter.getStartServerId(BRABD, null);
        dataService.getDataFromServer("brand", null,startServerId,mainHandler,model);
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
