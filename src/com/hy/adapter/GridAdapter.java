package com.hy.adapter;

import java.util.ArrayList;

import com.hy.application.BSSApplication;
import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;

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
    
    public GridAdapter(Context context, ArrayList<CategoryInfo> list){
        this.list = list;
        inflater = LayoutInflater.from(context);
//        pic_width = getPicWidth(context);
//        pic_height = getPicHeight(context);
        this.context = context;
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
        if(null==convertView){
            convertView = inflater.inflate(R.layout.gridviewitem, null);
            holder = new ViewHolder();
            holder.relativeLayout = (RelativeLayout)convertView.findViewById(R.id.griditem_layout);
            holder.imageView = (ImageView)convertView.findViewById(R.id.pic);
            holder.textView = (TextView)convertView.findViewById(R.id.pic_name);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
//            holder.imageView.getDrawable().setCallback(null);
        }
        CategoryInfo info = list.get(position);
        //holder.relativeLayout.setLayoutParams(new LayoutParams(pic_width, pic_height));
        holder.imageView.setImageDrawable(info.getDrawable(context));
        holder.textView.setText(info.getName());
        
        return convertView;
    }
    
    private class ViewHolder{
        RelativeLayout relativeLayout;
        ImageView imageView;
        TextView textView;
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
