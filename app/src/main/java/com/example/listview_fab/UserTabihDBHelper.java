package com.example.listview_fab;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.listview_fab.TasbihHelper.*;

import java.util.ArrayList;
import java.util.List;

public class UserTabihDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "tasbihlist.db";
    public static final int DATABASE_VERSION = 1;

    public UserTabihDBHelper(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TASBIHLIST_TABLE = "CREATE TABLE " +
                TasbihEntry.TABLE_NAME + " (" +
                TasbihEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TasbihEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                TasbihEntry.COLUMN_AMOUNT + " INTEGER NOT NULL" +
                ");";
        db.execSQL(SQL_CREATE_TASBIHLIST_TABLE);
        db.execSQL(LvItem.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TasbihEntry.TABLE_NAME);
        onCreate(db);
    }

    public ArrayList<LvItem> getAllNotes() {
        ArrayList<LvItem> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LvItem.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                LvItem note = new LvItem();
                note.setName(cursor.getString(cursor.getColumnIndex(LvItem.COLUMN_NOTE)));

                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return notes;
    }
}
