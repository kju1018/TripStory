package com.example.trip.Util

import android.content.Context
import android.widget.Toast


class ToastUtil{
    companion object {
        fun toastPrint(context:Context, message:String){
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}