package com.example.administrator.day0620_chabaike.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class DBUtils extends SQLiteOpenHelper{
    private static final String DB_NAME = "chabaike.db";
    private static final int VERSION = 1;

    public DBUtils(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS [collect] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] TEXT(20),[time] TEXT(20));";
        db.execSQL(sql);
        sql = "CREATE TABLE IF NOT EXISTS [history] ([_id] INTEGER PRIMARY KEY AUTOINCREMENT, [title] TEXT(20),[time] TEXT(20));";
        db.execSQL(sql);

    }
    // 插入操作(把表名作为参数传进来)
    public long insert(String name, ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        return db.insert(name, null, values);

    }
    //查询
    public Cursor queryAll(String name) {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(name, null, null, null, null, null, null);
    }
    // 删除操作
    // DELETE FROM student WHERE 字段1 = 值1 AND 字段2 = 值2
    public int delete(String name, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(name, whereClause, whereArgs);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
