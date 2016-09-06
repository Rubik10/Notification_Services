package com.rubik.notificationsservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private Context context;
    private byte [] img_zip;
    private Long time;

    public MyReceiver() {
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(getClass().getCanonicalName(), "Me han llamado desde un service!");
        this.context = context;

        //Obtenemos los extras del intent
        this.img_zip = intent.getExtras().getByteArray("BITMAP");
        time = intent.getLongExtra("TIME",0);

        Log.d(getClass().getCanonicalName(), "Vamos a abir la notificacion:)");
        lanzarNotificacion("Bien Jugaoooo");

        /*
             //Intent de llamada a la actividad detalle
            Intent intent2 = new Intent(context,Main2Activity.class);

           // intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            //Metemos datos en el intent
            intent2.putExtra("TIME", time);
            intent2.putExtra("BITMAP", this.img_zip);

            //Lanzamos nueva actividad
            context.startActivity(intent2);
         */

    }

    private void lanzarNotificacion (String mensaje) {

        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Bien jugao")
                        .setContentText(mensaje)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_ALL);

        Intent resultIntent = new Intent(this.context, Main2Activity.class);
       // resultIntent.putExtra("mensaje", mensaje);
        resultIntent.putExtra("TIME", time);
        resultIntent.putExtra("BITMAP", this.img_zip);
        PendingIntent resultPendingIntent = PendingIntent.getActivity (context, (int) System.currentTimeMillis(), resultIntent, PendingIntent.FLAG_ONE_SHOT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(537, mBuilder.build());//537 id de la noti: único en la app!

    }
}
