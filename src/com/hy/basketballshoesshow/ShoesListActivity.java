package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.adapter.CategoryAdapter;
import com.hy.adapter.GridAdapter;
import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetPicService;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ShoesListActivity extends Activity {

    private GridView gridView;
    private DBAdapter dbAdapter;
    private ArrayList<String> levelInfo;
    private CategoryCache categoryCache;
    private GetPicService picService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        picService = ((BSSApplication)getApplication()).getService();
        categoryCache = ((BSSApplication)getApplication()).getCategoryCache();
        setContentView(R.layout.activity_shoeslist);
        gridView = (GridView)findViewById(R.id.grid_shoes);
//        gridView.setColumnWidth(getColumnWidth());
        gridView.setNumColumns(((BSSApplication)getApplicationContext()).getScreenWith()/(160+4));
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
                      if(null==categoryCache.getCategory(info.getKey())){
                        holder = (Holder)(view.getChildAt(viewIndex).getTag());
                          picService.getPic(info.getTabaleName(), info.getId(), holder);
                      }
                  }
              }
          }
          
      });
    }

//    private int getColumnWidth(){
//        int screenWith = ((BSSApplication)getApplicationContext()).getScreenWith();
//        int column_num = screenWith/(160+4);
//        
//        return screenWith/column_num;
//    }
    
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
