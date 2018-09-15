package com.example.hemadry.sqlinfodata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static  final  int DATABASE_VERSON=1;
    private static  final String DATABASE_NAME="login.db";
    private static final String TABLE_USER = "ap_Rec";

    private static  final String COLUMN_USER_ID = "user_id";
    private static  final String COLUMN_USER_NAME= "user_name";
    private static  final String COLUMN_USER_EMAIL= "user_email";
    private static  final String COLUMN_USER_PASSWORD= "user_password";

    private String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+"("+COLUMN_USER_ID+ " INTEGER PRIMARY KEY,"+COLUMN_USER_NAME+" text,"+COLUMN_USER_EMAIL+ " text,"+COLUMN_USER_PASSWORD+" text)";

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " +TABLE_USER;

    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSON);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DROP_USER_TABLE );
        onCreate(db);
    }
    public void addUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_NAME,user.getName());
        contentValues.put(COLUMN_USER_EMAIL,user.getEmail());
        contentValues.put(COLUMN_USER_PASSWORD,user.getPassword());
        db.insert(TABLE_USER,null,contentValues);
        db.close();
    }

    public  boolean checkUser(String email , String password)
    {
        String[] colum = {COLUMN_USER_ID};
        SQLiteDatabase db = this.getWritableDatabase();
        String Selection = COLUMN_USER_EMAIL +"=?" +"AND" +COLUMN_USER_PASSWORD +"=?" ;
        String[] SelectionAge = {email,password};
        Cursor cursor = db.query(TABLE_USER,colum,Selection,SelectionAge,null,null,null);
        int cursorCount  = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0)
        {
            return true ;
        }
        return false ;
    }
}

