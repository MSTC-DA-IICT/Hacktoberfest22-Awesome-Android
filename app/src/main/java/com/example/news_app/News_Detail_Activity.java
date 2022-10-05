package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class News_Detail_Activity extends AppCompatActivity {
    String title,desc,content,imageURL,url;
    private TextView news_det_title,news_det_desc,news_det_content;
    private ImageView news_det_img;
    private Button news_det_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("desc");
        content=getIntent().getStringExtra("content");
        imageURL=getIntent().getStringExtra("image");
        url=getIntent().getStringExtra("url");
        news_det_title=findViewById(R.id.news_detail_title);
        news_det_desc=findViewById(R.id.news_detail_description);
        news_det_content=findViewById(R.id.news_detail_content);
        news_det_img=findViewById(R.id.news_detail_img);
        news_det_btn=findViewById(R.id.news_detail_button);
        news_det_title.setText(title);
        news_det_desc.setText(desc);
        news_det_content.setText(content);
        Picasso.get().load(imageURL).into(news_det_img);
        news_det_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}