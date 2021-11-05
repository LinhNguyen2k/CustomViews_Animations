package com.example.customview_canvasanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LockPattern_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_pattern)
        supportActionBar?.hide()
    }
}