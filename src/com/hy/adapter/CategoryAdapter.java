package com.hy.adapter;

import java.util.ArrayList;

import javax.security.auth.PrivateCredentialPermission;

import com.hy.basketballshoesshow.R;
import com.hy.objects.CategoryInfo;

import android.content.Context;
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
	
	public CategoryAdapter(Context context, ArrayList<CategoryInfo> list){
		this.list = list;
		layInflater = LayoutInflater.from(context);
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
			convertView = layInflater.inflate(R.layout.list_category, null);
			holder = new ViewHolder();
			holder.image = (ImageView)convertView.findViewById(R.id.img);
			holder.text = (TextView)convertView.findViewById(R.id.name);
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		CategoryInfo info = list.get(position);
		holder.image.setImageBitmap(info.getBitmap());
		holder.text.setText(info.getName());
		return convertView;
	}
	
	private class ViewHolder{
		ImageView image;
		TextView text;
	}

}
