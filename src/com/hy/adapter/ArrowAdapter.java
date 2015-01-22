package com.hy.adapter;

import java.util.ArrayList;

import com.hy.basketballshoesshow.IntroduceActivity;
import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;
import com.hy.objects.Series;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ArrowAdapter extends BaseAdapter {

    private ArrayList<CategoryInfo> list;
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<String> levelInfo;
    
    public ArrowAdapter(Context context, ArrayList<CategoryInfo> list, ArrayList<String> levelInfo){
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.levelInfo = levelInfo;
        this.levelInfo.add(list.get(0).getName());
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
        if(null == convertView){
            convertView = inflater.inflate(R.layout.list_arrow, null);
            holder = new ViewHolder();
            holder.image = (ImageView)convertView.findViewById(R.id.list_arrow_img);
            holder.text = (TextView)convertView.findViewById(R.id.list_arrow_name);
            holder.arrow = (ImageView)convertView.findViewById(R.id.list_arrow_arrow);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        CategoryInfo info = list.get(position);
        holder.image.setImageBitmap(info.getBitmap());
        holder.text.setText(info.getName());
        holder.arrow.setOnClickListener(new ArrowClickListener());
        return convertView;
    }
    
    private class ViewHolder{
        ImageView image;
        TextView text;
        ImageView arrow;
    }
    
    private class ArrowClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context,IntroduceActivity.class);
            intent.putExtra("levelInfo", levelInfo);
            context.startActivity(intent);
        }
        
    }

}
