package com.hy.addshoes.activity;

import java.io.ByteArrayOutputStream;

import com.hy.addshoes.objects.UploadShoes;
import com.hy.addshoes.services.UploadServices;
import com.hy.basketballshoesshow.R;
import com.hy.basketballshoesshow.R.string;
import com.hy.objects.Brand;
import com.hy.objects.Color;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddShoesActivity extends Activity implements OnClickListener {

    private EditText brand_EditText;
    private EditText series_EditText;
    private EditText seriesIndro_EditText;
    private EditText color_EditText;
    private EditText shoes_EditText;
    private EditText price_EditText;
    private EditText technology_EditText;
    private EditText season_EditText;
    private EditText upper_EditText;
    private EditText upperMaterial_EditText;
    private EditText lowMaterial_EditText;
    private EditText function_EditText;
    private EditText position_EditText;
    private EditText idea_EditText;
    private TextView brandImgOK_TextView;
    private TextView seriesImgOK_TextView;
    private TextView colorImgOK_TextView;
    private TextView shoesImgOK_TextView;
    private Button selectBrandImg_Button;
    private Button selectSeriesImg_Button;
    private Button selectColorImg_Button;
    private Button selectShoesImg_Button;
    private byte[] brandImg_bytes;
    private byte[] seriesImg_bytes;
    private byte[] colorImg_bytes;
    private byte[] shoesImg_bytes;
    private Button submit_Button;
    
    String brand;
    String series;
    String seriesIndro;
    String color;
    String shoes;
    String price;
    String technology;
    String season;
    String upper;
    String upperMaterial;
    String lowMaterial;
    String function;
    String position;
    String idea;
    
    private int SELECT_IMG = 1;
    private int CILP_IMG = 2;
    private final int BRAND = 1;
    private final int SERIES = 2;
    private final int COLOR = 3;
    private final int SHOES = 4;
    private int img_select_category;
    
    private Handler mainHandler;
    private final int CHECK = 0;
    private final int UPLOAD = 1;
    private LinearLayout progressBar_layout;
    private UploadServices uploadServices;
    private ServiceConnection connection;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
        
        
        Intent intent =new Intent(this,UploadServices.class);
        connection = new ServiceConnection() {
            
            @Override
            public void onServiceDisconnected(ComponentName name) {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                uploadServices = ((UploadServices.UploadServicesBinder)service).getUploadServices();
            }
        };
        bindService(intent, connection,BIND_AUTO_CREATE);
        
        
        
        
        
        mainHandler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == CHECK){
                    if(checkBitmap((String)msg.obj)){
                        UploadShoes uploadShoes = new UploadShoes(brand, series,seriesIndro,color, shoes, Integer.parseInt(price), season, upper, upperMaterial, lowMaterial, function, position, technology, idea, brandImg_bytes, seriesImg_bytes, colorImg_bytes, shoesImg_bytes);
                        uploadServices.uploadData(uploadShoes,mainHandler);
                    }else{
                        progressBar_layout.setVisibility(View.INVISIBLE);
                    }
                    
                }else if (msg.what == UPLOAD) {
                    progressBar_layout.setVisibility(View.INVISIBLE);
                    String resultString = (String)msg.obj;
                    if("OK".equals(resultString)){
                         brand_EditText.setText("");
                         series_EditText.setText("");
                         seriesIndro_EditText.setText("");
                         color_EditText.setText("");
                         shoes_EditText.setText("");
                         price_EditText.setText("");
                         technology_EditText.setText("");
                         season_EditText.setText("");
                         upper_EditText.setText("");
                         upperMaterial_EditText.setText("");
                         lowMaterial_EditText.setText("");
                         function_EditText.setText("");
                         position_EditText.setText("");
                         idea_EditText.setText("");
                         brandImgOK_TextView.setVisibility(View.INVISIBLE);
                         seriesImgOK_TextView.setVisibility(View.INVISIBLE);
                         colorImgOK_TextView.setVisibility(View.INVISIBLE);
                         shoesImgOK_TextView.setVisibility(View.INVISIBLE);
                        Toast.makeText(AddShoesActivity.this, getString(R.string.upload_ok), Toast.LENGTH_LONG).show();
                    }else if ("ERROR".equals(resultString) || "".equals(resultString)) {
                        Toast.makeText(AddShoesActivity.this, getString(R.string.upload_error), Toast.LENGTH_LONG).show();
                    }else if (null == resultString) {
                        Toast.makeText(AddShoesActivity.this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
                    }
                    
                }
            }
            
        };
        
        this.setContentView(R.layout.activity_addshoes);
        brand_EditText = (EditText) findViewById(R.id.brandName);
        series_EditText = (EditText) findViewById(R.id.seriesName);
        seriesIndro_EditText = (EditText)findViewById(R.id.seriesIndro);
        color_EditText = (EditText) findViewById(R.id.colorName);
        shoes_EditText = (EditText) findViewById(R.id.shoesName);
        price_EditText = (EditText) findViewById(R.id.shoesPrice);
        technology_EditText = (EditText) findViewById(R.id.shoesTechnology);
        season_EditText = (EditText) findViewById(R.id.shoesSeason);
        upper_EditText = (EditText) findViewById(R.id.shoesUpper);
        upperMaterial_EditText = (EditText) findViewById(R.id.shoesUpperMaterial);
        lowMaterial_EditText = (EditText) findViewById(R.id.shoesLowMaterial);
        function_EditText = (EditText) findViewById(R.id.shoesFunction);
        position_EditText = (EditText) findViewById(R.id.shoesPosition);
        idea_EditText = (EditText) findViewById(R.id.shoesIdea);

        brandImgOK_TextView = (TextView) findViewById(R.id.brandImg_OK);
        seriesImgOK_TextView = (TextView) findViewById(R.id.seriesImg_OK);
        colorImgOK_TextView = (TextView) findViewById(R.id.colorImg_OK);
        shoesImgOK_TextView = (TextView) findViewById(R.id.shoesImg_OK);

        selectBrandImg_Button = (Button) findViewById(R.id.brandImg);
        selectBrandImg_Button.setOnClickListener(this);
        selectSeriesImg_Button = (Button) findViewById(R.id.seriesImg);
        selectSeriesImg_Button.setOnClickListener(this);
        selectColorImg_Button = (Button) findViewById(R.id.colorImg);
        selectColorImg_Button.setOnClickListener(this);
        selectShoesImg_Button = (Button) findViewById(R.id.shoesImg);
        selectShoesImg_Button.setOnClickListener(this);

        submit_Button = (Button) findViewById(R.id.submit);
        submit_Button.setOnClickListener(this);
        
        progressBar_layout = (LinearLayout)findViewById(R.id.progressBar_layout);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.brandImg:
            img_select_category = BRAND;
            clipImage();
            break;
        case R.id.seriesImg:
            img_select_category = SERIES;
            clipImage();
            break;
        case R.id.colorImg:
            img_select_category = COLOR;
            clipImage();
            break;
        case R.id.shoesImg:
            img_select_category = SHOES;
            clipImage();
            break;
        case R.id.submit:
            submitToServer();
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            System.out.println("requestCode" + requestCode);
            if (requestCode == SELECT_IMG) {
                Uri uri = data.getData();
                System.out.println(uri.getPath());

                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bmp = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, byteOut);
                    byte[] bytes = byteOut.toByteArray();
                    Intent intent = new Intent(this, CilpImageActivity.class);
                    intent.putExtra("imgbytes", bytes);
                    startActivityForResult(intent, CILP_IMG);

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else if (requestCode == CILP_IMG) {
                byte[] bytes = data.getByteArrayExtra("imgbytes");
                switch (img_select_category) {
                  case BRAND: brandImg_bytes = bytes;
                              brandImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case SERIES:seriesImg_bytes = bytes;
                              seriesImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case COLOR:colorImg_bytes = bytes;
                              colorImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case SHOES:shoesImg_bytes = bytes;
                              shoesImgOK_TextView.setVisibility(View.VISIBLE);break;
                }
            }

        }
    }
    
    private void clipImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        // intent.putExtra("crop", "circle");
        intent.putExtra("return-data", true);
        startActivityForResult(intent, SELECT_IMG);
    }
    
    private boolean checkBitmap(String result){
        boolean canUpload = true;
        if("".equals(result)){
            if(null == brandImg_bytes || null == seriesImg_bytes || null == colorImg_bytes || null == shoesImg_bytes){
                canUpload = false;
                showDialog(getString(R.string.no_brand));
            }
        }else if ("brand".equals(result)) {
            if(null == seriesImg_bytes || null == colorImg_bytes || null == shoesImg_bytes){
                canUpload = false;
                showDialog(getString(R.string.no_series));
            }
            brandImg_bytes = null;
        }else if ("series".equals(result)) {
            if(null == colorImg_bytes || null == shoesImg_bytes){
                canUpload = false;
                showDialog(getString(R.string.no_color));
            }
            brandImg_bytes = null; seriesImg_bytes = null; seriesIndro = null;
        }else if("color".equals(result)){
            if(null == shoesImg_bytes){
                canUpload = false;
                showDialog(getString(R.string.no_shoes));
            }
            brandImg_bytes = null; seriesImg_bytes = null; seriesIndro = null; colorImg_bytes = null;
        }else if("shoes".equals(result)) {
            canUpload = false;
            showDialog(getString(R.string.have_shoes));
        }else if (null == result) {
            canUpload = false;
            showDialog(getString(R.string.network_error));
        }
        return canUpload;
    }
    
    private void submitToServer(){
         brand = brand_EditText.getText().toString();
         series = series_EditText.getText().toString();
         seriesIndro = seriesIndro_EditText.getText().toString();
         color = color_EditText.getText().toString();
         shoes = shoes_EditText.getText().toString();
         price = price_EditText.getText().toString();
         technology = technology_EditText.getText().toString();
         season = season_EditText.getText().toString();
         upper = upper_EditText.getText().toString();
         upperMaterial = upperMaterial_EditText.getText().toString();
         lowMaterial = lowMaterial_EditText.getText().toString();
         function = function_EditText.getText().toString();
         position = position_EditText.getText().toString();
         idea = idea_EditText.getText().toString();

        if("".equals(brand)||"".equals(series)||"".equals(seriesIndro)||"".equals(color)||
                "".equals(shoes)||"".equals(price)||"".equals(technology)||
                "".equals(season)||"".equals(upper)||"".equals(upperMaterial)||
                "".equals(lowMaterial)||"".equals(function)||"".equals(position)||"".equals(idea)){
            
            showDialog(getString(R.string.info_not_complete));
            
        }else{
            progressBar_layout.setVisibility(View.VISIBLE);
            uploadServices.checkInfo(brand, series, color, shoes,mainHandler);
        }
    }
    
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage(message);
        builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
            
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
    
    
    
}
