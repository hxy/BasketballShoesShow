package com.hy.basketballshoesshow;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hy.basketballshoesshow.R;
import com.hy.database.DBAdapter;
import com.hy.database.DBHelper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	private DBAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbAdapter = new DBAdapter(this);
        dbAdapter.insertOneBrand("Nike", getPicBytes(getResources().openRawResource(R.raw.car)), series_name, generation, shoes_name, shoes_pic, shoes_indro)
    }

    private byte[] getPicBytes(InputStream inputStream){
    	int length = -1;
    	byte[] buffer = new byte[1024];
    	ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
    	try {
			while((length=inputStream.read(buffer))!=-1){
				byteArrayInputStream.write(buffer, 0, length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return byteArrayInputStream.toByteArray();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
