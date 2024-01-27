package com.yagosouza.meucarro.core.tools

import android.content.Context
import android.widget.Toast

class ToastHelperImpl(
    private val context: Context
) : ToastHelper {

    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}