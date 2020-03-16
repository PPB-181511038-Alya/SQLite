package com.example.recycler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBAdapter {
    static final String KEY_NAME = "name";
    static final String KEY_DEADLINE = "deadline";
    static final String TAG = "DBAdapter";
    static final String DATABASE_NAME = "MyDB";
    static final String DATABASE_TABLE = "contacts";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_CREATE =
            "create table contacts (name text not null, deadline text not null);";
    final Context context;
    DatabaseHelper DBHelper;
    SQLiteDatabase db;

    public DBAdapter(Context ctx){
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper {
        DatabaseHelper(Context context)        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db)        {
            try {
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS contacts");
            onCreate(db);
        }
    }
    //---opens the database--
    public DBAdapter open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }
    //---closes the database--
    public void close(){
        DBHelper.close();
    }

    //---insert a contact into the database--
    public long addTask(String name, String deadline)    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_DEADLINE, deadline);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }

    //---deletes a particular contact--
    public boolean deleteTask(String taskName)    {
        return db.delete(DATABASE_TABLE, KEY_NAME + "=" + taskName, null) > 0;
    }

    //---retrieves all the contacts--
    public Cursor getAllTask()    {
        return db.query(DATABASE_TABLE, new String[] {KEY_NAME,
                KEY_DEADLINE}, null, null, null, null, null);
    }

    //---retrieves a particular contact--
    public Cursor getTask(String taskName) throws SQLException    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                                KEY_NAME, KEY_DEADLINE},     KEY_NAME + "=" + taskName, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }        return mCursor;
    }

    //---updates a contact--
    public boolean updateTask(String name, String deadline)    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_DEADLINE, deadline);
        return db.update(DATABASE_TABLE, args, KEY_NAME + "=" + name, null) > 0;
    }
}
