package com.oswal.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;
    public Methods call = new Methods();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Obtener instancia de la lista

        listView= (ListView) findViewById(R.id.listView);

        fillData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Methods.HayInternet(view.getContext())){
                    Intent intent = new Intent(view.getContext(), Form.class);
                    finish();
                    startActivity(intent);
                }else {
                    downAlert(view);
                }
            }
        });


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
        if (id == R.id.action_settings) {
            fillData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void downAlert(View view){
        Snackbar.make(view, "no hay internet", Snackbar.LENGTH_LONG)
                .setAction("Cerrar", null).show();
    }

    public void fillData(){
        // Crear y setear adaptador
        if (Methods.HayInternet(this)){
            setData();
        }
        else
            downAlert(listView);
    }

    public void setData(){
        adapter = new TareaAdapter(this);
        listView.setAdapter(adapter);
    }

}
