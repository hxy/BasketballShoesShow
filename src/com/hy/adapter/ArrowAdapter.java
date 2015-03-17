package com.hy.adapter;

import java.util.ArrayList;

import com.hy.application.BSSApplication;
import com.hy.basketballshoesshow.IntroduceActivity;
import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;
import com.hy.objects.CategoryObject;
import com.hy.objects.Series;
import com.hy.tools.CategoryCache;
import com.hy.tools.Holder;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrowAdapter extends BaseAdapter implements GetSetList{

    private ArrayList<CategoryInfo> list;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> levelInfo;
    private CategoryCache categoryCache;
    
    public ArrowAdapter(Context context, ArrayList<CategoryInfo> list, ArrayList<String> levelInfo){
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.levelInfo = levelInfo;
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
        if(null == convertView){
            convertView = inflater.inflate(R.layout.list_arrow, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.list_arrow_img);
            holder.text = (TextView)convertView.findViewById(R.id.list_arrow_name);
            holder.arrow = (ImageView)convertView.findViewById(R.id.list_arrow_arrow);
            convertView.setTag(holder);
            ((BSSApplication)context.getApplicationContext()).getPicService().getPic(info.getTabaleName(), info.getId(), holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
            if(null!=holder.image.getDrawable()){
                holder.image.getDrawable().setCallback(null);
            }
        }
        
        CategoryObject categoryObject = categoryCache.getCategory(info.getKey());
        if(null!=categoryObject){
            holder.image.setImageDrawable(categoryObject.getDrawable());
            holder.text.setText(categoryObject.getName());
            holder.arrow.setOnClickListener(new ArrowClickListener(categoryObject.getName()));
            holder.arrow.setVisibility(View.VISIBLE);
        }else{
            holder.image.setImageDrawable(null);
            holder.text.setText(null);
            holder.arrow.setVisibility(View.GONE);
        }

        return convertView;
    }
    
    private class ViewHolder implements Holder{
        ImageView image;
        TextView text;
        ImageView arrow;
		@Override
		public ImageView getImageView() {

			return image;
		}
		@Override
		public TextView getTextView() {

			return text;
		}
        @Override
        public ImageView getArrowView() {

            return arrow;
        }
        @Override
        public BaseAdapter getAdapter() {
            
            return ArrowAdapter.this;
        }
    }
    
    public class ArrowClickListener implements View.OnClickListener{
        private String name;
        public ArrowClickListener(String name){
            this.name = name;
        }
        @Override
        public void onClick(View v) {
            levelInfo.add(name);
            Intent intent = new Intent(context,IntroduceActivity.class);
            intent.putExtra("levelInfo", levelInfo);
            context.startActivity(intent);
            levelInfo.remove(levelInfo.size()-1);
        }
        
    }

    @Override
    public ArrayList<CategoryInfo> getList() {
        
        return this.list;
    }

    @Override
    public void setList(ArrayList<CategoryInfo> list) {
       this.list = list;
    }

}
