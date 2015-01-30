package com.hy.basketballshoesshow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

public class Welcome extends Activity {

    private TextView textView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setLayoutParams(new LayoutParams(300, 200));
        textView.setText("welcome!");
        setContentView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(){

            @Override
            public void run() {
                try {
                    this.sleep(5000);
                    Welcome.this.startActivity(new Intent(Welcome.this, MainActivity.class));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            
        }.start();
    }

    
}
