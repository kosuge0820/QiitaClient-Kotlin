package com.example.k_satoshi.qiitaclient.extensions

import android.content.Context
import android.os.Message
import android.view.View
import android.support.annotation.IdRes
import android.widget.Toast
import javax.xml.datatype.Duration

/**
 * Created by k-satoshi on 2017/06/01.
 */

fun <T: View> View.bindView(@IdRes id: Int): Lazy<T> = lazy {
    findViewById(id) as T
}

fun Context.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}