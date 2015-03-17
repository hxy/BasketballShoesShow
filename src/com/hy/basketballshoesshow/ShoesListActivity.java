package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.baoyz.widget.PullRefreshLayout;
import com.baoyz.widget.PullRefreshLayout.OnRefreshListener;
import com.hy.adapter.CategoryAdapter;
import com.hy.adapter.GridAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetDataService;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;
import com.hy.tools.OnAddMoreListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ShoesListActivity extends Activity {

    private GridView gridView;
    private DBAdapter dbAdapter;
    private GridAdapter adapter;
    private ArrayList<String> levelInfo;
    private CategoryCache categoryCache;
    private GetDataService dataService;
    private Handler mainHandler;
    private PullRefreshLayout refreshLayout;
    private int NOMORE = 0;
    private int ERROR = -1;
    private int OK = 1;
    private LinearLayout loadMore;
    private boolean hasFooterView = false;
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
                    adapter.setList(getShoesList(levelInfo));
                    gridView.setAdapter(adapter); 
                    refreshLayout.setRefreshing(false);
                    }else{
                        int beginPosition = adapter.getCount();
                        adapter.setList(getShoesList(levelInfo));
                        gridView.setAdapter(adapter); 
                        gridView.setSelection(beginPosition);
                        loadMore.setVisibility(View.INVISIBLE);
                        hasFooterView = false;
                    }
                }else if (ERROR == msg.what) {
                    Toast.makeText(ShoesListActivity.this, "网络错误,请稍后再试",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }else {
                    Toast.makeText(ShoesListActivity.this, "服务器已被掏空...",Toast.LENGTH_SHORT).show();
                    refreshLayout.setRefreshing(false);
                }
            }
            
        };
        
        setContentView(R.layout.activity_shoeslist);
        loadMore = (LinearLayout)findViewById(R.id.loadmore);
        gridView = (GridView)findViewById(R.id.grid_shoes);
//        gridView.setColumnWidth(getColumnWidth());
        gridView.setNumColumns(((BSSApplication)getApplicationContext()).getScreenWith()/(160+4));
        levelInfo = getIntent().getStringArrayListExtra("levelInfo");
        
        ArrayList<CategoryInfo> shoesList = getShoesList(levelInfo);
        adapter = new GridAdapter(this, shoesList);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Intent intent = new Intent(ShoesListActivity.this,DetailActivity.class);
                int shoesId = (Integer)((Holder)(arg1.getTag())).getTextView().getTag();
//                levelInfo.add(Id);
//                intent.putExtra("levelInfo", levelInfo);
                intent.putExtra("shoesId", shoesId);
                ShoesListActivity.this.startActivity(intent);
//                levelInfo.remove(levelInfo.size()-1);
            }
        });
        
        gridView.setOnScrollListener(new OnScrollListener(){
            
          private int firstIndex;
          private int endIndex;
          private int viewIndex;
          private Holder holder;
          @Override
          public void onScroll(AbsListView view, int firstVisibleItem,
                  int visibleItemCount, int totalItemCount) {
              firstIndex = firstVisibleItem;
              endIndex = firstVisibleItem+visibleItemCount-1;
              
          }

          @Override
          public void onScrollStateChanged(AbsListView view, int scrollState) {
              if(scrollState == OnScrollListener.SCROLL_STATE_IDLE){
                  for(int n = firstIndex; n <= endIndex; n++){
                      viewIndex = n-firstIndex;
                      CategoryInfo info = (CategoryInfo)view.getAdapter().getItem(n);
                      String key = info.getKey();
//                      if(null==categoryCache.getCategory(info.getKey())){
                        holder = (Holder)(view.getChildAt(viewIndex).getTag());
                        if("".equals(holder.getTextView().getText().toString())){
                          holder.getTextView().setTag(info.getId());
                          dataService.getPic(info.getTabaleName(), info.getId(), holder);
                        }
//                      }
                  }
              }
              if(scrollState == OnScrollListener.SCROLL_STATE_IDLE && endIndex+1 == view.getAdapter().getCount()&&!hasFooterView){
                  
                  loadMore.setVisibility(View.VISIBLE);
                  hasFooterView = true;
                  getShoesFromServer(GetDataService.ADDMOER);
              }
          }
          
      });
        
        refreshLayout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        refreshLayout.setRefreshStyle(PullRefreshLayout.STYLE_RING);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            
            @Override
            public void onRefresh() {
                getShoesFromServer(GetDataService.REFRESH);
            }
        });
    }

    private void getShoesFromServer(int model){
        int SHOES = 5;
        int startposition = dbAdapter.getStartProsition(SHOES);
        dataService.getDataFromServer("shoes", startposition,mainHandler,model);
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
