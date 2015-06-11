package com.example.ga.frisby_v1;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Manager_database extends Activity {



    private EditText et1, et2, et3, et4;
    int flag=0, flag1=0;
    String repetido;

    private AdminSQLiteOpenHelper manager;
    private ListView lista;
    ArrayAdapter<String> adaptador;
    private Cursor cursor;
    private SimpleCursorAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sedes_manager);



        et1 = (EditText) findViewById(R.id.editTextNumSede);
        et2 = (EditText) findViewById(R.id.editTextNombreSede);
        et3 = (EditText) findViewById(R.id.editTextLatitud);
        et4 = (EditText) findViewById(R.id.editTextlongitud);
        lista= (ListView) findViewById(R.id.lista);



        guardar1("1", "C.C San Diego", "6.23229", "-75.5677");
        guardar1("2", "Terminal del Norte", "6.27794", "-75.572");
        guardar1("3", "Sede Laureles", "6.24276", "-75.5961");
        guardar1("4", "C.C Los Molinos", "6.23363", "-75.6044");
        guardar1("5", "Carrefour la 655", "6.2518", "-75.5839");


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void guardar1(String numsede1,String nombresede1, String latitud1, String longitud1) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede=numsede1;
        String nombresede =nombresede1;
        String latitud =latitud1;
        String longitud =longitud1;


                ContentValues registro = new ContentValues();
                registro.put("numsede ", numsede);
                registro.put("nombresede", nombresede);
                registro.put("latitud", latitud);
                registro.put("longitud", longitud);
                bd.insert("frisbydb", null, registro);
                bd.close();

        }






    public void guardar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede = et1.getText().toString();
        String nombresede = et2.getText().toString();
        String latitud = et3.getText().toString();
        String longitud = et4.getText().toString();

        if (TextUtils.isEmpty(numsede) || TextUtils.isEmpty(nombresede) || TextUtils.isEmpty(latitud) || TextUtils.isEmpty(longitud)) {
            Toast.makeText(this,  this.getString(R.string.tododatos), Toast.LENGTH_SHORT).show();
            return;
        } else {

            if (Integer.parseInt(numsede) <= 0) {
                Toast.makeText(this,this.getString(R.string.solo5), Toast.LENGTH_SHORT).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
                return;

            }
            repetido=null;
            consulta2(v);
            if (repetido==null){
                ContentValues registro = new ContentValues();
                registro.put("numsede ", numsede);
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

            } else
            {
                Toast.makeText(this,this.getString(R.string.yaexiste), Toast.LENGTH_SHORT).show();
                return;

            }
        }
        }


    public void consulta2(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();

        if (TextUtils.isEmpty(numsede) ) {
            Toast.makeText(this,  this.getString(R.string.buscarsede), Toast.LENGTH_SHORT).show();
            return;
        } else {

            Cursor fila = bd.rawQuery("select nombresede,latitud,longitud  from frisbydb where numsede =" + numsede+ "", null);
            if (fila.moveToFirst()) {
                et2.setText(fila.getString(0));
                et3.setText(fila.getString(1));
                et4.setText(fila.getString(2));
                repetido=fila.getString(0);

            }
            bd.close();

        }
    }





    public void consulta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede  = et1.getText().toString();

        if (TextUtils.isEmpty(numsede) ) {
            Toast.makeText(this,  this.getString(R.string.buscarsede), Toast.LENGTH_SHORT).show();
            return;
        } else {

        Cursor fila = bd.rawQuery("select nombresede,latitud,longitud  from frisbydb where numsede =" + numsede+ "", null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
            flag=1;
        } else {
            Toast.makeText(this,  this.getString(R.string.noexiste), Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
        }
        bd.close();

          }
    }


    public void eliminar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String numsede = et1.getText().toString();

        if (flag == 0) {
            Toast.makeText(this,  this.getString(R.string.eliminarsede), Toast.LENGTH_SHORT).show();
            return;
        } else {

            int cant = bd.delete("frisbydb", "numsede =" + numsede + "", null);
            bd.close();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            if (cant == 1) {
                Toast.makeText(this, this.getString(R.string.exitoborrar), Toast.LENGTH_LONG).show();
                flag=0;
            }

            else {
                Toast.makeText(this, this.getString(R.string.noexiste), Toast.LENGTH_LONG).show();
                et1.setText("");
                et2.setText("");
                et3.setText("");
                et4.setText("");
            }

        }
    }
    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admfrisbyDB", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        if (flag == 0) {
            Toast.makeText(this,this.getString(R.string.editarsede), Toast.LENGTH_SHORT).show();
            et1.setText("");
            et2.setText("");
            et3.setText("");
            et4.setText("");
            return;
        } else {
            flag=0;
            String numsede = et1.getText().toString();
            String nombresede = et2.getText().toString();
            String latitud = et3.getText().toString();
            String longitud = et4.getText().toString();
            ContentValues registro = new ContentValues();
            registro.put("nombresede", nombresede);
            registro.put("latitud", latitud);
            registro.put("longitud", longitud);
            int cant = bd.update("frisbydb", registro, "numsede =" + numsede, null);
            bd.close();
            if (cant == 1) {
                Toast.makeText(this, this.getString(R.string.exitomodificar), Toast.LENGTH_LONG).show();

            }

        }
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