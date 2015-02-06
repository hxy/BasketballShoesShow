package com.hy.tools;

import com.hy.adapter.CategoryAdapter;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetPicService;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;

public class StopScrollAddList extends ListView {

    private Context context;
    private GetPicService service;
    private CategoryCache cache;
    public StopScrollAddList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    
    public void setService(GetPicService service){
        this.service = service;
    }
    
    public void SetCategoryCache(CategoryCache cache){
        this.cache = cache;
    }
    public void setScrollListener(){
        this.setOnScrollListener(new OnScrollListener(){

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
                        if(null==cache.getCategory(info.getKey())){
                            holder = (Holder)(view.getChildAt(viewIndex).getTag());
                            service.getPic(info.getTabaleName(), info.getId(), holder);
                        }
                    }
                }
            }
            
        });
    }
    
    
}
