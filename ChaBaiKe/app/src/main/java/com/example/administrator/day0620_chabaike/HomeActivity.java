package com.example.administrator.day0620_chabaike;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.day0620_chabaike.beans.TabInfo;
import com.example.administrator.day0620_chabaike.ui.ContentFragment;
import com.example.administrator.day0620_chabaike.ui.MoreActivity;

public class HomeActivity extends FragmentActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private TabLayout mTabs;
    private ViewPager viewPager;
    private TextView textView;
    private TabInfo[] tabs = new TabInfo[]{
            new TabInfo("社会热点",6),
            new TabInfo("企业要闻",1),
            new TabInfo("医疗新闻",2),
            new TabInfo("生活贴士",3),
            new TabInfo("药品新闻",4),
            new TabInfo("疾病快讯",7),
            new TabInfo("食品新闻",5)

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    //activity 非正常销毁调用此方法 写入的数据在onCreate方法中的 saveState中


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initView() {
        mTabs = (TabLayout) findViewById(R.id.home_tab);
        viewPager = (ViewPager) findViewById(R.id.home_vp);
        viewPager.setAdapter(new ContentAdapter(getSupportFragmentManager()));
        mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);//让tab滑动
        mTabs.setupWithViewPager(viewPager);
        textView = (TextView) findViewById(R.id.home_more);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MoreActivity.class);

                startActivity(intent);
            }
        });
    }




    public class ContentAdapter extends FragmentStatePagerAdapter{

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "getItem: " +position);
            ContentFragment cf = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id",tabs[position].class_id);
            cf.setArguments(bundle);
            return cf;
        }

        @Override
        public int getCount() {
            return tabs.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position].name;
        }
    }
}
