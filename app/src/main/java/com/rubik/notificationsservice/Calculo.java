package com.rubik.notificationsservice;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Rubik on 6/9/16.
 */
public class Calculo extends AsyncTask<String,Void,Bitmap> {
    private Context contextm;
    private Bitmap bitmap;
    private long tiempoInicial;

    public long getTiempoFinal() {
        return tiempoFinal;
    }

    private long tiempoFinal;

  /*  public Calculo(Context context) {
        this.contextm = context;
    }*/

    protected Bitmap doInBackground(String... params) {
        //  Calendar calendar_actual = Calendar.getInstance();
        tiempoInicial = System.currentTimeMillis();
        URL dirImagen = null;
        HttpURLConnection httpURLConnection = null;

        try {
            dirImagen = new URL(params[0]);
            httpURLConnection = (HttpURLConnection) dirImagen.openConnection();
            InputStream input = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(input);

        } catch (Throwable t) {
            Log.d(getClass().getCanonicalName(), "Error al descargar imagen", t);
        }
        tiempoFinal = (System.currentTimeMillis() - tiempoInicial);
        Log.d(getClass().getCanonicalName(), "tiempo: " + tiempoFinal);
        //Calculamos los mb por segundo
        //TODO: No funciona
        //  Integer tamanoBitMap = bitmap.getByteCount()/1024;
        //  tiempoFinal = (tamanoBitMap.longValue() / (tiempoFinal/1000));


        return bitmap;

    }


//    @Override
//    public void onPostExecute(Bitmap bitmap) {
//        super.onPostExecute(bitmap);
//        MainActivity mainActivity = (MainActivity) contextm;
//        mainActivity.pintarResultado(bitmap,tiempoFinal);
//    }

}