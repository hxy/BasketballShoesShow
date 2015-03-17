package com.hy.tools;

import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public interface Holder {

	ImageView getImageView();
	TextView getTextView();
	ImageView getArrowView();
	BaseAdapter getAdapter();
}
