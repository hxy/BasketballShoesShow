package com.hy.adapter;

import java.util.ArrayList;

import javax.security.auth.PrivateCredentialPermission;

import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;
import com.hy.objects.OnGetDrawableListener;
import com.hy.services.GetPicService;

import android.app.Service;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends BaseAdapter {

	private ArrayList<CategoryInfo> list;
	private LayoutInflater layInflater;
	private Context context;
	
	public CategoryAdapter(Context context, ArrayList<CategoryInfo> list){
		this.list = list;
		layInflater = LayoutInflater.from(context);
		this.context = context;
	}
	
//	public CategoryAdapter(Context context, ArrayList<CategoryInfo> list,GetPicService service){
//	     this.list = list;
//	     layInflater = LayoutInflater.from(context);
//	     this.context = context;
//	     this.picService = service;
//	 }
	
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
			convertView = layInflater.inflate(R.layout.list_category, null);
			holder = new ViewHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.img);
			holder.text = (TextView)convertView.findViewById(R.id.name);
			convertView.setTag(holder);
			//picService.getPic(info.getTabaleName(), info.getId(), holder.image);
		}else{
			holder = (ViewHolder)convertView.getTag();
			if(null!=holder.image.getDrawable()){
			    holder.image.getDrawable().setCallback(null);
			    holder.image.setImageDrawable(null);
			}
			
//			holder.image.setVisibility(View.GONE);
		}
		
		
//		if(null!=info.getDrawable(context)){
//		    holder.image.setImageDrawable(info.getDrawable(context)); 
//		    holder.image.setVisibility(View.VISIBLE);
//		}

	            //holder.image.setImageDrawable(info.getDrawable(context)); 
		//picService.getPic(info.getTabaleName(), info.getId(), holder.image);
//	            holder.image.setVisibility(View.VISIBLE);

		holder.text.setText(info.getName());
		return convertView;
	}
	
	public class ViewHolder{
		public ImageView image;
		public TextView text;
	}

}
