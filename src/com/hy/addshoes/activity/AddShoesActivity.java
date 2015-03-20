package com.hy.addshoes.activity;

import java.io.ByteArrayOutputStream;

import com.hy.basketballshoesshow.R;
import com.hy.objects.Brand;
import com.hy.objects.Color;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddShoesActivity extends Activity implements OnClickListener {

    private EditText brand_EditText;
    private EditText series_EditText;
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
    private Bitmap brandImg_Bitmap;
    private Bitmap seriesImg_Bitmap;
    private Bitmap colorImg_Bitmap;
    private Bitmap shoesImg_Bitmap;
    private Button submit_Button;
    private int SELECT_IMG = 1;
    private int CILP_IMG = 2;
    private final int BRAND = 1;
    private final int SERIES = 2;
    private final int COLOR = 3;
    private final int SHOES = 4;
    private int img_select_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_addshoes);
        brand_EditText = (EditText) findViewById(R.id.brandName);
        series_EditText = (EditText) findViewById(R.id.seriesName);
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
        selectColorImg_Button = (Button) findViewById(R.id.colorImg);
        selectShoesImg_Button = (Button) findViewById(R.id.shoesImg);

        submit_Button = (Button) findViewById(R.id.submit);
        submit_Button.setOnClickListener(this);
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
        case R.id.submit:submitToServer();break;
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
                  case BRAND: brandImg_Bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                              brandImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case SERIES:seriesImg_Bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                              seriesImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case COLOR:colorImg_Bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
                              colorImgOK_TextView.setVisibility(View.VISIBLE);break;
                  case SHOES:shoesImg_Bitmap = BitmapFactory.decodeByteArray(bytes, 0,bytes.length);
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
    
    private void submitToServer(){
        String brand = brand_EditText.getText().toString();
        String series = series_EditText.getText().toString();
        String color = color_EditText.getText().toString();
        String shoes = shoes_EditText.getText().toString();
        String price = price_EditText.getText().toString();
        String technology = technology_EditText.getText().toString();
        String season = season_EditText.getText().toString();
        String upper = upper_EditText.getText().toString();
        String upperMaterial = upperMaterial_EditText.getText().toString();
        String lowMaterial = lowMaterial_EditText.getText().toString();
        String function = function_EditText.getText().toString();
        String position = position_EditText.getText().toString();
        String idea = idea_EditText.getText().toString();
        if("".equals(brand)||"".equals(series)||"".equals(color)||
                "".equals(shoes)||"".equals(price)||"".equals(technology)||
                "".equals(season)||"".equals(upper)||"".equals(upperMaterial)||
                "".equals(lowMaterial)||"".equals(function)||"".equals(position)||"".equals(idea)){
            
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage(getString(R.string.info_not_complete));
            builder.setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.create().show();
        }else{
            
        }
    }
}
