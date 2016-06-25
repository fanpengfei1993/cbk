package com.example.administrator.day0620_chabaike.ui;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.day0620_chabaike.R;
import com.example.administrator.day0620_chabaike.Utils.DBUtils;
import com.example.administrator.httplib.HttpHelper;
import com.example.administrator.httplib.Request;
import com.example.administrator.httplib.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

//详情页
public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView details_title, details_keywords, details_time, details_up, details_tool_tvfx, details_tool_tvsc;
    private Toolbar details_toolabar;
    private long details_id;
    private String str_title = null, str_time = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        details_toolabar = (Toolbar) findViewById(R.id.details_toolabar);
        /*details_toolabar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });*/

        Intent intent = getIntent();
        details_id = intent.getLongExtra("id", 0);
        System.out.println("===========" + details_id);
        initView();


    }

    private void initView() {
        details_title = (TextView) findViewById(R.id.details_title);
        details_keywords = (TextView) findViewById(R.id.details_keywords);
        details_time = (TextView) findViewById(R.id.details_time);
        details_up = (TextView) findViewById(R.id.details_up);
        details_tool_tvfx = (TextView) findViewById(R.id.details_tool_tvfx);
        details_tool_tvsc = (TextView) findViewById(R.id.details_tool_tvsc);
        details_tool_tvfx.setOnClickListener(this);
        details_tool_tvsc.setOnClickListener(this);

        String url = "http://www.tngou.net/api/top/show?id=" + details_id;
        System.out.println("===========" + url);
        StringRequest sr = new StringRequest(url, Request.Method.GET, new StringRequest.Callback<String>() {
            @Override
            public void onEror(Exception e) {

            }

            @Override
            public void onResonse(String response) {
                if (response != null) {
                    try {

                        final JSONObject object = new JSONObject(response);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    details_title.setText(object.getString("title"));
                                    long time = object.getLong("time");
                                    details_time.setText(new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss").format(time));

                                    details_keywords.setText(object.getString("keywords"));
                                    details_up.setText(Html.fromHtml(object.getString("message")).toString());

                                    str_title = object.getString("title");
                                    str_time = new SimpleDateFormat("yyyy-MM-dd   hh:mm:ss").format(time);

                                    if(str_title!=null && str_time!=null){
                                        DBUtils dbUtils = new DBUtils(DetailsActivity.this);
                                        ContentValues values = new ContentValues();
                                        values.put("_id",details_id);
                                        values.put("title",str_title);
                                        values.put("time",str_time);
                                        dbUtils.insert("history", values);
                                       // Toast.makeText(DetailsActivity.this,"",Toast.LENGTH_LONG).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        HttpHelper.addRequest(sr);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.details_tool_tvfx://分享

                showShare();
                break;
            case R.id.details_tool_tvsc://收藏
                if(str_title!=null && str_time!=null){
                    DBUtils dbUtils = new DBUtils(DetailsActivity.this);
                    ContentValues values = new ContentValues();
                    values.put("_id",details_id);
                    values.put("title",str_title);
                    values.put("time",str_time);
                    dbUtils.insert("collect", values);
                    Toast.makeText(DetailsActivity.this,"收藏成功",Toast.LENGTH_LONG).show();
                }

                break;

        }
    }
    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        //oks.setTitle(getString(R.string.share));
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

// 启动分享GUI
        oks.show(this);
    }

}
