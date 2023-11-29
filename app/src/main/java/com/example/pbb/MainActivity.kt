package com.example.pbb

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.pbb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val channelId = "TEST NOTIF"
    private  val notifId = 10

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notifManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        binding.btnNotif.setOnClickListener{
            val flag = if (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.M){
                PendingIntent.FLAG_MUTABLE
            }
            else {
                0
            }
//
//            val intent = Intent(this,
//            NotifReceiver::class.java)
//                .putExtra("MESSAGE", "baca selengkapnya...")
            val intent = Intent(this, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                this, 0,
                intent, flag
            )
            val builder = NotificationCompat.Builder(this,
                channelId).setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Notifku")
                .setContentText("Hello world!")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
//                .addAction(0, "Baca Notif", pendingIntent)

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O ){
                val notifChannel = NotificationChannel(channelId, "Notifku",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                with(notifManager){
                    createNotificationChannel(notifChannel)
                    notify(0, builder.build())
                }
            }
            else{
                notifManager.notify(0, builder.build())
            }
        }

        binding.btnUpdate.setOnClickListener{
            val notifImage = BitmapFactory.decodeResource(
                resources, R.drawable.notif_picture
            )

            val builder = NotificationCompat.Builder(this,
                channelId)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle("Updated Notif")
                .setContentText("Ini update notifikasinya")
                .setStyle(
                    NotificationCompat.BigPictureStyle()
                        .bigPicture(notifImage)
                )
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                notifManager.notify(notifId, builder.build())
        }
    }
}