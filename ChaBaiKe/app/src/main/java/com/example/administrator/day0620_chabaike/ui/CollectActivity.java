package com.example.administrator.day0620_chabaike.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.day0620_chabaike.R;
import com.example.administrator.day0620_chabaike.Utils.DBUtils;

public class CollectActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);


        DBUtils dbUtils = new DBUtils(CollectActivity.this);
        Cursor cursor = dbUtils.queryAll("collect");
        adapter = new SimpleCursorAdapter(CollectActivity.this,
                R.layout.collect_item,
                cursor,
                new String[]{"title", "time"},
                new int[]{R.id.collect_item_title, R.id.collect_item_time}, 0);
        listView = (ListView) findViewById(R.id.collect_listview);
        listView.setAdapter(adapter);

        initDelete();
    }

    private void initDelete() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // String item = (String) listItem.get(arg2).get("ItemTitle");

                //setTitle("点击第"+item);

            }
        });


        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("长按菜单-ContextMenu");

                menu.add(0, 0, 0, "删除审批单");

                menu.add(0, 1, 0, "取消删除");

            }
        });



       /* //长按菜单响应函数

        @Override

        public boolean onContextItemSelected(MenuItem item)

        {

            setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目");

            int selectedPosition = ((AdapterContextMenuInfo) item.getMenuInfo()).position;//获取点击了第几行

            return super.onContextItemSelected(item);

        }



        删除操作

        listItem.remove(position);//选择行的位置

        listItemAdapter.notifyDataSetChanged();

        list.invalidate();
*/
    }


}
