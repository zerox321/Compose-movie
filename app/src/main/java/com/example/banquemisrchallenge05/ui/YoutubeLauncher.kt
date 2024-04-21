package com.example.banquemisrchallenge05.ui


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri


class YoutubeLauncher(private val context: Context) {
    fun watchYoutubeVideo(key: String) {
        try {
            context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$key")).apply {
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            })
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(Intent(
                Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=$key")
            ).apply {
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            })
        }

    }
}