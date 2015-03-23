package com.example.andres.bolitasensores;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by andres on 10/02/15.
 */
//CAMBIOOOOOOOS
public class EjemploHandler extends Activity {

    protected static final int MENSAJEID = 0x100;

    Thread threadVista = null;
    MiVista miVista=null;

    Handler vistaHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case EjemploHandler.MENSAJEID:
                    // Invalidar vista para repintado
                    miVista.invalidate();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        // crear la vista
        this.miVista = new MiVista(this);
        this.setContentView(this.miVista);

        // Thread para actualizar la vista
        Thread t = new Thread(new ActualizaVista());
        t.start();

    }

    class ActualizaVista implements Runnable {

        public void run() {
            while (! Thread.currentThread().isInterrupted()) {
                // Enviar mensaje al handler para hacer invalidate
                Message message = new Message();
                message.what = EjemploHandler.MENSAJEID;

                EjemploHandler.this.vistaHandler.sendMessage(message);


                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}