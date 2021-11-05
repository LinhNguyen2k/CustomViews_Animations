package com.example.customview_canvasanimations

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        btn_bai1.setOnClickListener {
            startActivity(Intent(applicationContext,ClockView_Activity::class.java))
        }
        btn_bai2.setOnClickListener {
            startActivity(Intent(applicationContext,LockPattern_Activity::class.java))
        }
        btn_bai3.setOnClickListener {
            startActivity(Intent(applicationContext,EditText_Activity::class.java))
        }
        btn_bai4.setOnClickListener {
            startActivity(Intent(applicationContext,Animation_Activity::class.java))
        }
        btn_bai4_1.setOnClickListener {
            startActivity(Intent(applicationContext,AnimationDrawable::class.java))
        }
        btn_bai4_2.setOnClickListener {
            startActivity(Intent(applicationContext,CanvasAnimation::class.java))
        }
    }
}