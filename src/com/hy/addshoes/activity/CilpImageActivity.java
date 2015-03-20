package com.hy.addshoes.activity;

import java.io.ByteArrayOutputStream;

import com.hy.basketballshoesshow.R;
import com.zhy.view.ClipImageLayout;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CilpImageActivity extends Activity implements OnClickListener{

    private ClipImageLayout clipImageLayout;
    private Button clipButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipimage);
        setTitle("图片裁剪");
        clipButton = (Button)findViewById(R.id.clip_button);
        clipButton.setOnClickListener(this);
        clipImageLayout = (ClipImageLayout)findViewById(R.id.clipImageLayout);
        byte imgbytes[] = getIntent().getByteArrayExtra("imgbytes");
        Bitmap bitmap = BitmapFactory.decodeByteArray(imgbytes, 0, imgbytes.length);
        clipImageLayout.setImage(new BitmapDrawable(bitmap));
    }

    @Override
    public void onClick(View v) {
       
        Bitmap bitmap = clipImageLayout.clip();
        Bitmap targetBitmap = Bitmap.createScaledBitmap(bitmap, 150, 150, true);
        ByteArrayOutputStream bytesout = new ByteArrayOutputStream();
        targetBitmap.compress(CompressFormat.PNG, 100, bytesout);
        Intent intent = new Intent();
        byte[] bytes = bytesout.toByteArray();
        intent.putExtra("imgbytes", bytes);
        setResult(RESULT_OK, intent);
        finish();
    }
    
    
}
