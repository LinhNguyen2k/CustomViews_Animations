package com.example.customview_canvasanimations

import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.util.TypedValue
import java.util.*
import kotlin.math.cos
import kotlin.math.sin


class ClockView : View {

    private var padding = 0
    private var fontSize = 0
    private val numeralSpacing = 0
    private var handTruncation = 0
    private  var hourHandTruncation = 0
    private var radius = 0
    private var paint: Paint? = null
    private var isInit = false
    private val numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
    private val rect: Rect = Rect()
    private var mMinute = 0
    private var mHour = 0

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun initClock() {
        padding = numeralSpacing + 50
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 13f,
            resources.displayMetrics).toInt()
        val min = height.coerceAtMost(width)
        radius = min / 2 - padding
        handTruncation = min / 20
        hourHandTruncation = min / 7
        paint = Paint()
        isInit = true
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            initClock()
        }
        drawCircle(canvas)
        drawCenter(canvas)
        drawNumeral(canvas)
        drawHands(canvas)
        postInvalidateDelayed(500)
        invalidate()
    }

    private fun drawHand(canvas: Canvas, loc: Float, isHour: Boolean) {
        val angle = Math.PI * loc / 30 - Math.PI / 2
        val handRadius =
            if (isHour) radius - handTruncation - hourHandTruncation else radius - handTruncation
        paint?.let {
            canvas.drawLine((width / 2).toFloat(), (height / 2).toFloat(),
                (width / 2 + cos(angle) * handRadius).toFloat(),
                (height / 2 + sin(angle) * handRadius).toFloat(),
                it)
        }
    }

    private fun drawHands(canvas: Canvas) {
        var c: Calendar = Calendar.getInstance()
        var hour: Float = c.get(Calendar.HOUR_OF_DAY).toFloat()
        var minute = c.get(Calendar.MINUTE).toFloat()
        minute += mMinute
        if(minute > 60) {
            minute -=60
            hour++
        }
        hour += mHour
        hour = if (hour > 12) hour - 12 else hour
        drawHand(canvas, (hour + minute / 60) *5f, isHour = true)
        drawHand(canvas, minute, isHour = false)
        drawHand(canvas, c.get(Calendar.SECOND).toFloat(), isHour = false)
    }

    private fun drawNumeral(canvas: Canvas) {
        paint!!.textSize = fontSize.toFloat()
        for (number in numbers) {
            val tmp = number.toString()
            paint!!.getTextBounds(tmp, 0, tmp.length, rect)
            val angle = Math.PI / 6 * (number - 3)
            val x = (width / 2 + cos(angle) * radius - rect.width() / 2)
            val y = (height / 2 + sin(angle) * radius + rect.height() / 2)
            canvas.drawText(tmp, x.toFloat(), y.toFloat(), paint!!)
        }
    }

    private fun drawCenter(canvas: Canvas) {
        paint!!.style = Paint.Style.FILL
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), 12f, paint!!)
    }

    private fun drawCircle(canvas: Canvas) {
        paint!!.reset()
        paint!!.color = resources.getColor(R.color.black)
        paint!!.strokeWidth = 5f
        paint!!.style = Paint.Style.STROKE
        paint!!.isAntiAlias = true
        canvas.drawCircle((width / 2).toFloat(),
            (height / 2).toFloat(), (radius + padding - 10).toFloat(), paint!!)
    }
    fun setTime(hour:Int,minute:Int){
        mHour = hour
        mMinute = minute
    }
}