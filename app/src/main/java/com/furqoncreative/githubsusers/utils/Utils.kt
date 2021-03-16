package com.furqoncreative.githubsusers.utils

import android.content.Context
import android.view.View
import java.io.IOException
import java.nio.charset.Charset
import kotlin.text.Charsets.UTF_8

fun loadJSONFromAsset(context: Context, filenName: String): String {
    val json: String?
    try {
        val inputStream = context.assets.open(filenName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        val charset: Charset = UTF_8
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, charset)
    } catch (ex: IOException) {
        ex.printStackTrace()
        return ""
    }
    return json
}

fun View.hideSystemUi() {
    systemUiVisibility = (View.SYSTEM_UI_FLAG_LOW_PROFILE
            or View.SYSTEM_UI_FLAG_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
}