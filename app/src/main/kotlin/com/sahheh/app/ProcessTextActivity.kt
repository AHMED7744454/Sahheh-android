package com.sahheh.app

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class ProcessTextActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Create a simple TextView
        val textView = TextView(this)
        textView.text = "Sahheh KeyBoard"
        textView.textSize = 20f
        textView.setTextColor(android.graphics.Color.BLACK)
        textView.layoutParams = android.view.ViewGroup.LayoutParams(
            android.view.ViewGroup.LayoutParams.MATCH_PARENT,
            android.view.ViewGroup.LayoutParams.MATCH_PARENT
        )
        
        setContentView(textView)
    }
}
