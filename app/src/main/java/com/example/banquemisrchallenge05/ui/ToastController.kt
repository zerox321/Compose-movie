package com.example.banquemisrchallenge05.ui

import android.content.Context
import android.widget.Toast

class ToastController(private val context: Context) {
    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}