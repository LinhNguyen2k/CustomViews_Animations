package com.example.customview_canvasanimations

import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_animation_drawable.*

class AnimationDrawable : AppCompatActivity() {
    private lateinit var isAnimation:AnimationDrawable
    var isStart = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_drawable)
        supportActionBar?.hide()
        img.setImageResource(R.drawable.animation_item)

        isAnimation = img.drawable as AnimationDrawable

        btn.setOnClickListener {
            if (!isStart){
                isAnimation.start()
                btn.text = "stop"
                isStart  = true
                btn.setBackgroundColor(Color.RED)

            }
            else{
                isAnimation.stop()
                btn.text = "Start"
                isStart  = false
                btn.setBackgroundColor(resources.getColor(R.color.purple_500))

            }
        }
    }
}