package com.example.foodbuddy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.util.Log
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(message: Int) =
    Toast.makeText(this, getString(message), Toast.LENGTH_SHORT).show()


fun startAppSettings(context: Context) {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    )
    intent.data = Uri.parse("package:" + context.packageName)
    context.startActivity(intent)
}
