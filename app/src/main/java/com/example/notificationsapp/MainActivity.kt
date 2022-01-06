package com.example.notificationsapp

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var EditT :EditText
    lateinit var notBU: Button
    lateinit var builder: Notification.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        EditT = findViewById(R.id.EditT)
        notBU = findViewById(R.id.notBU)


        notBU.setOnClickListener{
            val channelId = "myapp.notifications"
            val description = "Notification App Example"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val intent = Intent(this, NotificationActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                var notificationChannel = NotificationChannel(channelId,description,NotificationManager.IMPORTANCE_HIGH)
                notificationManager.createNotificationChannel(notificationChannel)
                builder = Notification.Builder(this, channelId)
                    .setSmallIcon(R.drawable.notifications_icon)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(EditT.text.toString())
            } else {
                builder = Notification.Builder(this)
                    .setSmallIcon(R.drawable.notifications_icon)
                    .setContentIntent(pendingIntent)
                    .setContentTitle("My Notification")
                    .setContentText(("Hello"))
            }
            notificationManager.notify(1234, builder.build())
        }
        }


}