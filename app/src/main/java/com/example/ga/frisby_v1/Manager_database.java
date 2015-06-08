package com.example.ga.frisby_v1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Manager_database extends Activity {
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sedes_manager);

        et1 = (EditText) findViewById(R.id.editTextNumSede);
        et2 = (EditText) findViewById(R.id.editTextNombreSede);
        et3 = (EditText) findViewById(R.id.editTextLatitud);
        et4 = (EditText) findViewById(R.id.editTextlongitud);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void guardar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();
        String nombresede = et2.getText().toString();
        String latitud = et3.getText().toString();
        String longitud = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("numsede ", numsede );
        registro.put("nombresede", nombresede);
        registro.put("latitud", latitud);
        registro.put("longitud", longitud);
        bd.insert("frisbydb", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, this.getString(R.string.exitoguardar), Toast.LENGTH_LONG).show();
    }

    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombresede,latitud,longitud  from frisbydb where numsede =" + numsede
                        + "", null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una persona con dicho numsede ",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();
        int cant = bd.delete("frisbydb", "numsede =" + numsede  + "", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, this.getString(R.string.exitoborrar), Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, this.getString(R.string.noexiste), Toast.LENGTH_LONG).show();

    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();
        String nombresede = et2.getText().toString();
        String latitud = et3.getText().toString();
        String longitud = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombresede", nombresede);
        registro.put("latitud", latitud);
        registro.put("longitud", longitud);
        int cant = bd.update("frisbydb", registro, "numsede =" + numsede , null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, this.getString(R.string.exitomodificar), Toast.LENGTH_LONG).show();

        else
            Toast.makeText(this, this.getString(R.string.noexiste), Toast.LENGTH_LONG).show();

    }

    public void retornar(View v) {
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);

    }


    private void allsedes(View v) {
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this, "admfrisbyDB", null, 1);
        SQLiteDatabase bd=admin.getWritableDatabase();

        final Cursor c = bd.rawQuery("select nombresede from frisbydb", null);

        if (c.moveToFirst())
        {
            do {
                et1.setText(c.getString(0));
                et2.setText(c.getString(1));
                et3.setText(c.getString(2));
                et4.setText(c.getString(3));


            } while (c.moveToNext());
        }
        bd.close();
    }

}