package com.hy.adapter;

import java.util.ArrayList;

import com.hy.application.BSSApplication;
import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;
import com.hy.objects.CategoryObject;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private ArrayList<CategoryInfo> list;
    private LayoutInflater inflater;
    private int pic_width ;
    private int pic_height;
    private Context context;
    private CategoryCache categoryCache;
    
    public GridAdapter(Context context, ArrayList<CategoryInfo> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
//        pic_width = getPicWidth(context);
//        pic_height = getPicHeight(context);
        this.context = context;
        categoryCache = ((BSSApplication)context.getApplicationContext()).getCategoryCache();
    }
    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        CategoryInfo info = list.get(position);
        if(null==convertView){
            convertView = inflater.inflate(R.layout.gridviewitem, null);
            holder = new ViewHolder();
            holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.griditem_layout);
            holder.imageView = (ImageView)convertView.findViewById(R.id.pic);
            holder.textView = (TextView)convertView.findViewById(R.id.pic_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            holder.imageView.getDrawable().setCallback(null);
        }
        CategoryObject categoryObject = categoryCache.getCategory(info.getKey());
        if(null!=categoryObject){
            holder.imageView.setImageDrawable(categoryObject.getDrawable());
            holder.textView.setText(categoryObject.getName());
        }else{
            holder.imageView.setImageDrawable(null);
            holder.textView.setText(null);
        }

        
        return convertView;
    }
    
    private class ViewHolder implements Holder{
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;
		@Override
		public ImageView getImageView() {

			return imageView;
		}
		@Override
		public TextView getTextView() {

			return textView;
		}
    }

    
    private int getPicWidth(Context context){
        int screenWith = ((BSSApplication)context.getApplicationContext()).getScreenWith();
        int column_num = screenWith/160;
        
        return screenWith/column_num;
    }
    private int getPicHeight(Context context){
        int screenHeight = ((BSSApplication)context.getApplicationContext()).getScreenHeight();
        int row_num = screenHeight/200;
        
        return screenHeight/row_num;
    }
}
