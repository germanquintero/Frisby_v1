package com.example.ga.f_restaurante;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class edit_sedes extends Fragment {

    private EditText et1, et2, et3, et4;
    private Button btguardar, btbuscar,bteliminar, btmodificar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       LinearLayout miLinearLayout= (LinearLayout) inflater.inflate(R.layout.fragment_edit_sedes, container, false);

        btguardar = (Button) miLinearLayout.findViewById(R.id.btGuardar);
        btbuscar=(Button) miLinearLayout.findViewById(R.id.btBuscar);
        bteliminar=(Button)miLinearLayout.findViewById(R.id.btEliminar);
        btmodificar=(Button)miLinearLayout.findViewById(R.id.btModificar);

        et1 = (EditText)  miLinearLayout.findViewById(R.id.editTextNombreSede);
        et2 = (EditText) miLinearLayout.findViewById(R.id.editTextNombreSede);
        et3 = (EditText) miLinearLayout.findViewById(R.id.editTextLatitud);
        et4 = (EditText) miLinearLayout.findViewById(R.id.editTextlongitud);

          btguardar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  guardar(v);
                  Toast.makeText(getActivity(), "Funciona Guardar", Toast.LENGTH_SHORT).show();
              }
          });

        btbuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscar(v);
                Toast.makeText(getActivity(), "Funciona Buscarr",Toast.LENGTH_SHORT).show();
            }
        });

        bteliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Funciona Eliminar",Toast.LENGTH_SHORT).show();
            }
        });

        btmodificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Funciona Modificar",Toast.LENGTH_SHORT).show();
            }
        });

        return miLinearLayout;


    }



    public void guardar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = et1.getText().toString();
        String nombre = et2.getText().toString();
        String colegio = et3.getText().toString();
        String nromesa = et4.getText().toString();
        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("colegio", colegio);
        registro.put("nromesa", nromesa);
        bd.insert("votantes", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(getActivity(), "Se cargaron los datos de la persona",
                Toast.LENGTH_SHORT).show();
    }

    public void buscar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(getActivity(),
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String dni = et1.getText().toString();
        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre,colegio,nromesa  from votantes where dni=" + dni, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(getActivity(), "No existe una persona con dicho dni" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }





}
