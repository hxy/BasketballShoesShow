package com.hy.basketballshoesshow;

import java.io.File;
import java.io.IOException;

import com.hy.application.BSSApplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class WelcomeActivity extends Activity {

    private TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setLayoutParams(new LayoutParams(300, 200));
        textView.setText("welcome!");
        setContentView(textView);
        String path = this.getFilesDir().getPath()+File.separator+"flag.dat";
        File file = new File(path);
        if(file.exists()){
            ((BSSApplication)getApplication()).setAppFirst(false);
        }else{
            ((BSSApplication)getApplication()).setAppFirst(true);
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(){

            @Override
            public void run() {
                try {
                    this.sleep(3000);
                    WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, BrandListActivity.class));
                    WelcomeActivity.this.finish();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }.start();
    }

    
}
