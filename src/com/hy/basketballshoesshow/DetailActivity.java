package com.hy.basketballshoesshow;


import com.hy.application.BSSApplication;
import com.hy.database.DBAdapter;
import com.hy.objects.Shoes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends Activity {

    private DBAdapter dbAdapter;
    private Shoes shoes;
    private ImageView imageView;
    private TextView price;
    private TextView code;
    private TextView brand;
    private TextView series;
    private TextView color;
    private TextView season;
    private TextView upper;
    private TextView upperMaterial;
    private TextView lowMaterial;
    private TextView function;
    private TextView position;
    private TextView technology;
    private TextView idea;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageView = (ImageView)findViewById(R.id.shoes_pic);
        price = (TextView)findViewById(R.id.price);
        code = (TextView)findViewById(R.id.code);
        brand = (TextView)findViewById(R.id.brand);
        series = (TextView)findViewById(R.id.series);
        color = (TextView)findViewById(R.id.color);
        season = (TextView)findViewById(R.id.season);
        upper = (TextView)findViewById(R.id.upper);
        upperMaterial = (TextView)findViewById(R.id.upperMaterial);
        lowMaterial = (TextView)findViewById(R.id.lowMaterial);
        function = (TextView)findViewById(R.id.function);
        position = (TextView)findViewById(R.id.position);
        technology = (TextView)findViewById(R.id.technology);
        idea = (TextView)findViewById(R.id.idea);
        
        Intent intent = getIntent();
        int id = intent.getIntExtra("shoesId", -1);
        dbAdapter = ((BSSApplication)getApplication()).getdDbAdapter();
        shoes = dbAdapter.getShoes(id);
        
        imageView.setImageDrawable(shoes.getDrawable());
        price.setText(String.valueOf(shoes.getPrice()));
        code.setText(shoes.getName());
        brand.setText(shoes.getBrand());
        series.setText(shoes.getSeries());
        color.setText(shoes.getColor());
        season.setText(shoes.getSeason());
        upper.setText(shoes.getUpper());
        upperMaterial.setText(shoes.getUpperMaterial());
        lowMaterial.setText(shoes.getLowMaterial());
        function.setText(shoes.getFunction());
        position.setText(shoes.getPosition());
        technology.setText(shoes.getTechnology());
        idea.setText(shoes.getIndro());
    }
}
