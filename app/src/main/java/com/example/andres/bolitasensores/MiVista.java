package com.example.andres.bolitasensores;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.View;

/**
 * Created by andres on 10/02/15.
 */
public class MiVista extends View {

    float posx, posy, deltax, deltay, posz, deltaz, radius;
    SensorManager sensorManager;

    Paint fondo = new Paint();
    Paint bola = new Paint();

    public MiVista(Context context) {
        super(context);
        posx = 10;
        posy = 10;

        deltax = 50;
        deltay = 50;

        radius = 10;
        fondo.setColor(Color.GRAY);
        bola.setColor(Color.BLUE);

        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(accelerometerEventListener, accelerometer, SensorManager.SENSOR_DELAY_FASTEST);


    }

    float xAxis, yAxis, zAxis;
    public final SensorEventListener accelerometerEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                xAxis = event.values[0];
                yAxis = event.values[1];
                zAxis = event.values[2];

            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0, 0, canvas.getWidth(),
                canvas.getHeight(), fondo);

        posx = posx - xAxis;

        if (posx > this.getWidth() || posx < 0) {

        posx = posx + xAxis;

            if(radius>10 ) {
                radius--;
                radius--;
                radius--;
                radius--;
            }
        }

        posy = posy + yAxis;

        if (posy > this.getHeight() || posy < 0) {
            posy = posy - yAxis;

            radius++;
            radius++;
            radius++;
            radius++;


        }



        canvas.drawCircle(posx, posy, radius, bola);

    }

}