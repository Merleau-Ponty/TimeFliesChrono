package com.lmp.timeflies.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by valou on 23/01/2018.
 */

public class BDHelper extends SQLiteOpenHelper {


    public BDHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String req = "create table objectif(o_id int, o_lieu text, o_nom text, o_description text)";
        db.execSQL(req);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
