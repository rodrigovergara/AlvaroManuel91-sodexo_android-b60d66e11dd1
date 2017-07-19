package sodexo.pe.com.sodexo.presentation.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import sodexo.pe.com.sodexo.R;
import sodexo.pe.com.sodexo.presentation.activity.MainActivity;
import sodexo.pe.com.sodexo.presentation.activity.SplashActivity;

/**
 * Created by Jorge Del Aguila on 1/02/2017.
 */
public class SodexoMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Map<String, String> notificationMap = remoteMessage.getData();
        String title = notificationMap.get("title");
        String body = notificationMap.get("body");
        String dataKey = notificationMap.get("dataKey");
        int dataValue = 0;
        if(notificationMap.containsKey("dataValue")) {
            dataValue = Integer.parseInt(notificationMap.get("dataValue"));
        }
        Intent goToIntent = new Intent(getApplicationContext(),MainActivity.class);
        goToIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        goToIntent.putExtra("data_key",dataKey);
        if(dataValue!=0)
            goToIntent.putExtra("data_value",dataValue);

        sendNotification(goToIntent,body,title);
    }

    private void sendNotification(Intent intent, String messageBody, String messageTitle) {
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(messageTitle)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

}
