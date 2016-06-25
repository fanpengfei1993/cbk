package com.example.administrator.day0620_chabaike.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.administrator.day0620_chabaike.R;
import com.example.administrator.day0620_chabaike.Utils.DBUtils;

public class HistoryActivity extends AppCompatActivity {

    private ListView listView;
    private SimpleCursorAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        //testQueryAll();

        DBUtils dbUtils = new DBUtils(HistoryActivity.this);
        Cursor cursor = dbUtils.queryAll("history");
        adapter = new SimpleCursorAdapter(HistoryActivity.this,
                R.layout.history_item,
                cursor,
                new String[]{"title","time"},
                new int[]{R.id.history_item_title,R.id.history_item_time},0);
        listView = (ListView) findViewById(R.id.history_listview);
        listView.setAdapter(adapter);
        initDelete();
    }

    private void initDelete() {
        findViewById(R.id.history_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getApplicationContext();
                DBUtils dbUtils = new DBUtils(context);
                dbUtils.delete("history",null,null);
                Toast.makeText(context,"清除成功",Toast.LENGTH_LONG).show();
            }
        });

    }


    // 测试查询所有记录
    // SELECT * FROM student
    //private Cursor cursor ;
    public void testQueryAll() {
        Context context = getApplicationContext();
        DBUtils dbUtils = new DBUtils(context);
        Cursor cursor = dbUtils.queryAll("history"); // Cursor称为游标
        // 通过Cursor遍历数据库中的一个个记录(通过第一次的moveToNext()才能移动到第一个记录上)
        while(cursor.moveToNext()) {
            int index = cursor.getColumnIndex("title"); // 获得name字段所在的索引号
            String title = cursor.getString(index); // 获得索引对应的值(字符串)
            index = cursor.getColumnIndex("time");// 获得age字段所在的索引号
            long time = cursor.getLong(index);//所有索引对应的值(整型)
            System.out.println(title+"<title的值   "+"time de 值  >"+time);
        }
    }
   /* class hisAdapter extends SimpleCursorAdapter{

        public hisAdapter(Context context, int layout, Cursor c, String[] from, int[] to, int flags) {
            super(context, layout, c, from, to, flags);
        }
    }*/
}
