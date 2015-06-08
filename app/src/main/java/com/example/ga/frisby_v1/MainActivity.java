package com.example.ga.frisby_v1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btnEditPlaces(View view){
        Intent i = new Intent(this,Manager_database.class);
        startActivity(i);
    }

    public void versedes(View view){
        Intent i = new Intent(this, Ubication_sedes.class);
        startActivity(i);
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menueditarsedes) {
            Intent i = new Intent(this,Manager_database.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.menuversdedes) {
            Intent i = new Intent(this, Ubication_sedes.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
