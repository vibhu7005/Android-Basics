package com.jordiee.kotlindev.view.utils

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.util.ValueIterator
import android.net.Uri
import android.os.Build
import android.renderscript.Allocation
import android.renderscript.Element
import android.renderscript.RenderScript
import android.renderscript.ScriptIntrinsicBlur
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.jordiee.kotlindev.R
import com.jordiee.kotlindev.view.MainActivity
import com.jordiee.kotlindev.view.broadcastReceiver.JordieeBroadcastReceiver
import java.io.File
import java.io.OutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

class JordieeUtils {
    companion object {

        private const val NOTIFICATION_CHANNEL_ID = "1"
        private const val BITMAP_SCALE  = 0.4f
        private const val BITMAP_RADIUS = 20.5f
        private const val NOTIFICATION_CHANNEL_NAME = "JordieeLocalNotifications"
         fun displayNotification(context : Context) {
            val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
             val intent = Intent(context.applicationContext, MainActivity::class.java)
             val actionIntent = Intent(context.applicationContext, JordieeBroadcastReceiver::class.java)
             actionIntent.action = "NOTIFICATION_ACTION"

             val actionPendingIntent = PendingIntent.getBroadcast(context, 1003, actionIntent, PendingIntent.FLAG_IMMUTABLE)
             val pendingIntent = PendingIntent.getActivity(context, 1002, intent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                builder.setSmallIcon(R.drawable.bell)
                    .setContentTitle("Jordiee's Notification")
                    .setContentText("Here is the notification")
                    .setContentIntent(pendingIntent)
                    .addAction(R.drawable.bell,"Dismiss", actionPendingIntent)
                    .setAutoCancel(true)
                val manager: NotificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                val channel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    NOTIFICATION_CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                manager.createNotificationChannel(channel)
            } else {
                builder.setSmallIcon(R.drawable.bell)
                    .setContentTitle("Jordiee's Notification")
                    .setContentText("Here is the notification")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .priority = NotificationManager.IMPORTANCE_HIGH
            }
            val notificationManagerCompat = NotificationManagerCompat.from(context)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU && ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                (context as Activity).requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
            } else {
                notificationManagerCompat.notify(1, builder.build())
                builder.build()
            }
        }


        fun blurImage(image : Bitmap, context : Context) : Bitmap {
            val width = (image.width * BITMAP_SCALE).roundToInt()
            val height = (image.height * BITMAP_SCALE).roundToInt()
            val input = Bitmap.createScaledBitmap(image, width, height, false)
            val output = Bitmap.createBitmap(input)
            val renderScipt = RenderScript.create(context)
            val script = ScriptIntrinsicBlur.create(renderScipt, Element.U8_4(renderScipt))
            val allocation = Allocation.createFromBitmap(renderScipt, input)
            val outputAllocation = Allocation.createFromBitmap(renderScipt, output)
            script.setRadius(BITMAP_RADIUS)
            script.setInput(allocation)
            script.forEach(outputAllocation)
            outputAllocation.copyTo(output)
            return output
        }

        fun writeBitmapToFile(context : Context, bitmap : Bitmap) : Uri {
            val timestamp = SimpleDateFormat("ddMMyyyy_HHmm").format(Date())
            val imageName = "snap_$timestamp.jpg"
            val contextWrapper = ContextWrapper(context)
            var file = contextWrapper.getDir("images" , Context.MODE_PRIVATE)
            file = File(file, imageName)
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    val stream = OutputStream.nullOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                    stream.flush()
                    stream.close()
                }
            } catch (ex : Exception) {
                ex.printStackTrace()
            }
            return Uri.parse(file.absolutePath)
        }
    }
}