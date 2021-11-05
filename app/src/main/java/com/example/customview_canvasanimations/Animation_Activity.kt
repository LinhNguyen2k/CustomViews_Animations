package com.example.customview_canvasanimations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_animation.*

class Animation_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
        supportActionBar?.hide()
        stopAnimation.setOnClickListener {
            stopAnimation()
        }
        val animFadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_in)
        btn_FadeIn.setOnClickListener {
            drawCar.startAnimation(animFadeIn)

        }
        val animFadeOut = AnimationUtils.loadAnimation(applicationContext, R.anim.fade_out)
        btn_FadeOut.setOnClickListener {
            drawCar.startAnimation(animFadeOut)
        }
        val animBlink = AnimationUtils.loadAnimation(applicationContext, R.anim.blink)
        btn_Blink.setOnClickListener {
            drawCar.startAnimation(animBlink)
        }
        val animZoomIn = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_in)
        btn_zoom.setOnClickListener {
            drawCar.startAnimation(animZoomIn)
        }
        val animZoomOut = AnimationUtils.loadAnimation(applicationContext, R.anim.zoom_out)
        btn_zoomOut.setOnClickListener {
            drawCar.startAnimation(animZoomOut)
        }
        val animRotate = AnimationUtils.loadAnimation(applicationContext, R.anim.rotate)
        btn_animRotate.setOnClickListener {
            drawCar.startAnimation(animRotate)
        }
        val animMove = AnimationUtils.loadAnimation(applicationContext, R.anim.move)
        btn_animMove.setOnClickListener {
            drawCar.startAnimation(animMove)
        }
        val animSlideUp = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_up)
        btn_animSlideUp.setOnClickListener {
            drawCar.startAnimation(animSlideUp)
        }
        val animSlideDown = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_down)
        btn_slideDown.setOnClickListener {
            drawCar.startAnimation(animSlideDown)
        }
        val animBounce = AnimationUtils.loadAnimation(applicationContext, R.anim.bounce)
        btn_animBounce.setOnClickListener {
            drawCar.startAnimation(animBounce)
        }
        val animSequential = AnimationUtils.loadAnimation(applicationContext, R.anim.sequential)
        btn_animSequential.setOnClickListener {
            drawCar.startAnimation(animSequential)
        }
        val animTogether = AnimationUtils.loadAnimation(applicationContext, R.anim.together)
        btn_animTogether.setOnClickListener {
            drawCar.startAnimation(animTogether)
        }

    }
    private fun stopAnimation(){
        drawCar.clearAnimation()
    }
}