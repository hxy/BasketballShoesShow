package com.hy.basketballshoesshow;

import java.util.ArrayList;

import com.hy.basketballshoesshow.R;
import com.hy.database.DBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class IntroduceActivity extends Activity {
    
    private DBAdapter db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);
        db = new DBAdapter(this);
        TextView indroduce = (TextView)findViewById(R.id.introduceText);
        indroduce.setMovementMethod(ScrollingMovementMethod.getInstance()); 
        indroduce.setText(getIntroduce(getIntent()));
    }
    
    private String getIntroduce(Intent intent){
        ArrayList<String> levelInfo = intent.getStringArrayListExtra("levelInfo");
        String introduce = null;
        switch (levelInfo.size()) {
        case 2:
            introduce = db.getSeriesIntroduce(levelInfo.get(0),levelInfo.get(1));
            break;
        }
        
        return introduce;
    }

}
