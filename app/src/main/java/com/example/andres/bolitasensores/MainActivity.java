package com.example.andres.bolitasensores;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;


public class MainActivity extends ActionBarActivity {

    TextView txArriba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txArriba = (TextView) findViewById(R.id.txArriba);
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onEmpezarClick( View v ){
        txArriba.setText("cosa");

    }

    private static class MyHandler extends Handler {
        WeakReference<MainActivity> miActividad;


        MyHandler(MainActivity miActividad){
            this.miActividad = new WeakReference<MainActivity>(miActividad);
        }

        public void handleMessage( Message msg ){
            MainActivity miac = miActividad.get();
            if( miac == null ) return; //se destruy�
            switch( msg.what){
                case 1:
                    miac.txArriba.setText("opcion 1");
                    break;
                case 2:
                    miac.txArriba.setText("opcion 2");
                    break;
            }

            super.handleMessage(msg);
        }

    }
}
