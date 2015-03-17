package com.hy.tools;

import com.hy.adapter.CategoryAdapter;
import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;
import com.hy.services.GetDataService;

import android.R.bool;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug.FlagToString;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Toast;

public class StopScrollAddList extends ListView {

    private Context context;
    private GetDataService service;
    private CategoryCache cache;
    private View footerView;
    private OnAddMoreListener addMoreListener;
    private boolean hasFooterView = false;

    public StopScrollAddList(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        footerView = View.inflate(context, R.layout.listview_footer_layout, null);
        StopScrollAddList.this.addFooterView(footerView, null, false);
        footerView.setVisibility(View.INVISIBLE);
    }

    public void setService(GetDataService service) {
        this.service = service;
    }

    public void SetCategoryCache(CategoryCache cache) {
        this.cache = cache;
    }
    
    public void setOnAddMoreListener(OnAddMoreListener listener){
        this.addMoreListener = listener;
    }
    
    public void setAdding(boolean b){
        if(!b){
            footerView.setVisibility(View.INVISIBLE);
            hasFooterView = false;
        }
    }

    public void setScrollListener() {
        this.setOnScrollListener(new OnScrollListener() {

            private int firstIndex;
            private int endIndex;
            private int viewIndex;
            private Holder holder;
            

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                    int visibleItemCount, int totalItemCount) {
//                firstIndex = firstVisibleItem;
//                endIndex = firstVisibleItem + visibleItemCount - 1;
                
            }

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                firstIndex = view.getFirstVisiblePosition();
                endIndex = view.getLastVisiblePosition();
                //因为加了footerview,所以在最后一屏的时候view.getLastVisiblePosition()得到的值会比adapter中list的最后一个item多一
                if(endIndex == view.getAdapter().getCount()){
                    endIndex -=1; 
                }
                if (scrollState == OnScrollListener.SCROLL_STATE_IDLE) {
                    
                    for (int n = firstIndex; n <= endIndex; n++) {
                        viewIndex = n - firstIndex;
                        CategoryInfo info = (CategoryInfo) view.getAdapter().getItem(n);
                        holder = (Holder) (view.getChildAt(viewIndex).getTag());
                        if(null == holder){break;}//防止加上footer后出来null
                        if ("".equals(holder.getTextView().getText().toString())) {                           
                            service.getPic(info.getTabaleName(), info.getId(),holder);
                        }

                    }
                }
                if(scrollState == OnScrollListener.SCROLL_STATE_IDLE && endIndex+1 == view.getAdapter().getCount()&&!hasFooterView){
                                          
                    footerView.setVisibility(View.VISIBLE);
                    hasFooterView = true;
                    addMoreListener.OnAddMore();
                }
            }

        });
    }

}
