package com.example.administrator.day0620_chabaike.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.day0620_chabaike.R;

public class MoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView more_code,more_back,more_history,more_collect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        initView();
    }

    private void initView() {
        more_code = (TextView) findViewById(R.id.more_code);
        more_back = (TextView) findViewById(R.id.more_back);
        more_collect = (TextView) findViewById(R.id.more_collect);
        more_history = (TextView) findViewById(R.id.more_history);

        more_code.setOnClickListener(this);
        more_back.setOnClickListener(this);
        more_collect.setOnClickListener(this);
        more_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.more_code://版本信息
                intent.setClass(MoreActivity.this,VersionActivity.class);
                break;
            case R.id.more_back://意见反馈
                intent.setClass(MoreActivity.this,BackActivity.class);
                break;
            case R.id.more_collect://收藏夹
                intent.setClass(MoreActivity.this,CollectActivity.class);
                break;
            case R.id.more_history://浏览记录
                intent.setClass(MoreActivity.this,HistoryActivity.class);
                break;
        }
        startActivity(intent);

    }
}
