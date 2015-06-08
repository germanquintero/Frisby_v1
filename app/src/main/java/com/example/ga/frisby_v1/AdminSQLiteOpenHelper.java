package com.example.ga.frisby_v1;

/**
 * Created by ga on 30/05/2015.
 */


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public AdminSQLiteOpenHelper(Context context, String nombresede, CursorFactory factory, int version) {
        super(context, nombresede, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table frisbydb(numsede integer primary key, nombresede text, latitud real, longitud real)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnte, int versionNue) {
        db.execSQL("drop table if exists frisbydb");
        db.execSQL("create table frisbydb(numsede integer primary key, nombresede text, latitud real, longitud real)");
    }



    public Read_places readUbication(int id) {


        SQLiteDatabase db = getReadableDatabase();
        String[] register = {"numsede", "nombresede", "latitud", "longitud"};
        Cursor c = db.query("frisbydb", register, "numsede =" + id,
                null, null, null, null, null);
        if(c != null) {
            c.moveToFirst();
        }
        Read_places places = new Read_places(c.getInt(0), c.getString(1),
                c.getDouble(2), c.getDouble(3));
        db.close();
        c.close();
        return places;

    }


    public void opendatabase(){
        this.getWritableDatabase();
    }

    public void closedatabase(){
        this.close();
    }
}
