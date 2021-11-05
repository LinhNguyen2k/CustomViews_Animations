package com.example.customview_canvasanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class EditText_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_text)
        supportActionBar?.hide()
    }
}