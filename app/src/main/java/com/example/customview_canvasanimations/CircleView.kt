package com.example.customview_canvasanimations

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.hypot

class CircleView (context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val circlePaint: Paint
    private val circleGap: Float

    private var maxRadius = 0f
    private var center = PointF(0f, 0f)
    private var initialRadius = 0f

    init {
        val attrs = context!!.obtainStyledAttributes(attrs, R.styleable.CircleView, 0, 0)
        //init paint with custom attrs
        circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = attrs.getColor(R.styleable.CircleView_waveColor, DEFAULT_FACE_COLOR)
            strokeWidth = attrs.getDimension(R.styleable.CircleView_waveStrokeWidth, 5f)
            style = Paint.Style.STROKE
        }

        circleGap = attrs.getDimension(R.styleable.CircleView_waveGap, 70f)
        attrs.recycle()
    }

    private var circleAnimator: ValueAnimator? = null
    private var circleRadiusOffset = 0f
        set(value) {
            field = value
            postInvalidateOnAnimation()
        }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        circleAnimator = ValueAnimator.ofFloat(0f, circleGap).apply {
            addUpdateListener {
                circleRadiusOffset = it.animatedValue as Float
            }
            duration = 1500L
//            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            interpolator = LinearInterpolator()
            start()
        }
    }

    override fun onDetachedFromWindow() {
        circleAnimator?.cancel()
        super.onDetachedFromWindow()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawColor(Color.RED)
        //draw circles separated by a space the size of waveGap
        var currentRadius = initialRadius + circleRadiusOffset
        while (currentRadius < maxRadius) {
            canvas.drawCircle(center.x, center.y, currentRadius, circlePaint)
            currentRadius += circleGap
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        //set the center of all circles to be center of the view
        center.set(w / 2f, h / 2f)
        maxRadius = hypot(center.x.toDouble(), center.y.toDouble()).toFloat()
        initialRadius = w / circleGap
    }

    companion object {
        private const val DEFAULT_FACE_COLOR = Color.YELLOW
    }


}