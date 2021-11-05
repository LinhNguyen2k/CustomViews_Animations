package com.example.customview_canvasanimations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class DrawCar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var size = 0
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawCar(canvas!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = min(measuredWidth, measuredHeight)

        setMeasuredDimension(size, size)

    }

    private fun drawCar(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 4f
        val radius = 65f
        canvas.drawLine(200f, size * 0.5f, 350f, size * 0.5f, paint)

        canvas.drawLine(350f, size * 0.5f, 400f, size * 0.4f, paint)
        canvas.drawLine(400f, size * 0.4f, 800f, size * 0.4f, paint)
        canvas.drawLine(800f, size * 0.4f, 850f, size * 0.5f, paint)
        canvas.drawLine(850f, size * 0.5f, 1000f, size * 0.5f, paint)
        canvas.drawLine(1000f, size * 0.5f, 1000f, size * 0.62f, paint)
        canvas.drawLine(1000f, size * 0.62f, 850f, size * 0.62f, paint)
        canvas.drawCircle(850f - radius, size * 0.62f, radius, paint)
        canvas.drawLine(850f - 2 * radius, size * 0.62f, 450f, size * 0.62f, paint)
        canvas.drawCircle(450f - radius, size * 0.62f, radius, paint)
        canvas.drawLine(450f - 2 * radius, size * 0.62f, 200f, size * 0.62f, paint)
        canvas.drawLine(200f, size * 0.62f, 200f, size * 0.5f, paint)

    }
}