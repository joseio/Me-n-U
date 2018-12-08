package com.example.rayjo_000.menu;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat;
import android.widget.Button;
import android.widget.Toast;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;




public class homescreen_add2 extends Activity {

    private static final int PICK_IMAGE_REQUEST = 100;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen_add2);

        iv = (ImageView) findViewById(R.id.imgView);

        Button pic_btn = (Button)findViewById(R.id.hmscreen_pic);

    }

    public void loadImagefromGallery(View v) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case PICK_IMAGE_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        iv.setImageBitmap(bitmap);
                        Toast.makeText(getApplicationContext(),"Picture Selected", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
        }

    }

    public void sendNotification(View view) {

        int notifyid = 1;
        String CHANNEL_ID = "my_channel_01";
        CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, CHANNEL_ID);

        Intent rateintent = new Intent(this, AddMenuItem_RateIt.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,rateintent,0);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.ic_launcher_background);
        mBuilder.setContentTitle("Me 'n U");
        mBuilder.setContentText("Don't Forget to Rate the Food!!!");
        mBuilder.setChannelId(CHANNEL_ID);

        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(mChannel);

        mNotificationManager.notify(notifyid, mBuilder.build());

        startActivity(new Intent(homescreen_add2.this, MainActivity.class));
    }

}



