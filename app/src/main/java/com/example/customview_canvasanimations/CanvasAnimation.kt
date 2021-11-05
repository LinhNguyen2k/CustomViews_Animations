package com.example.customview_canvasanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CanvasAnimation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_canvas_animation)
        supportActionBar?.hide()
    }
}