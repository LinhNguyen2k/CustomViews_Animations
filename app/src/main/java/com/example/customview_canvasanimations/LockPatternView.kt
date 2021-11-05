package com.example.customview_canvasanimations

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import kotlin.math.min
import kotlin.math.pow

class LockPatternView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {


    private var notification = arrayOf("Mật khẩu không đúng",
        "Nhập thành công",
        "Tạo mật khẩu mới",
        "Tạo thành công",
        "Yêu cầu 4 kí tự trở lên",
        "Nhập mật khẩu")
    private var pointList = ArrayList<Point>()
    private var password = ArrayList<Int>()
    private var fontSize = 0
    private var paint: Paint = Paint()
    private var mwidth = 0f
    private var startPoint: Point? = null
    private var endPoint: Point? = null
    private var isInit = false
    private var isDrawing = false
    private var isCorrect = false
    private var isCheck = false
    private var isFirstTime = true
    private var listPass = ArrayList<Int>()
    private var shared = context?.getSharedPreferences("PASSWORD", MODE_PRIVATE)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        var size = min(measuredWidth, measuredHeight)

        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas) {
        if (!isInit) {
            init()
            paint.textSize = fontSize.toFloat()
            paint.color = Color.BLACK

            paint.textAlign = Paint.Align.CENTER
            if (isFirstTime) {
                canvas.drawText(notification[2], width / 2f, (mwidth + 100)/2 , paint)
            } else {
                canvas.drawText(notification[5], width / 2f, (mwidth + 100)/2 , paint)
            }
        }
        drawPoint(canvas)
        drawPoints(canvas)
        if (isFirstTime) {
            paint.textSize = fontSize.toFloat()
            paint.textAlign = Paint.Align.CENTER
            if (isCheck) {
                if (isCorrect) {
                    canvas.drawText(notification[3], width / 2f, (mwidth + 100)/2, paint)
                    isFirstTime = false
                    shared!!.edit().putBoolean("isFirstTime", false).apply()
                } else {
                    canvas.drawText(notification[4], width / 2f, (mwidth + 100)/2, paint)
                }
            }
            isCheck = false
        } else {
            if (isCheck) {
                paint.textSize = fontSize.toFloat()
                if (isCorrect) {
                    canvas.drawText(notification[1], width / 2f, (mwidth + 100)/2, paint)
                } else {
                    canvas.drawText(notification[0], width / 2f, (mwidth + 100)/2, paint)
                }
                isCheck = false
            }
        }
    }

    private fun init() {
        mwidth = width.coerceAtMost(height) / 6f
        fontSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 20f,
            resources.displayMetrics).toInt()

        pointList.add(Point(mwidth.toInt(), mwidth.toInt() + 100))
        pointList.add(Point(width / 2, mwidth.toInt() + 100))
        pointList.add(Point(width - mwidth.toInt(), mwidth.toInt() + 100))

        pointList.add(Point(mwidth.toInt(), height / 2 + 50))
        pointList.add(Point(width / 2, height / 2 + 50))
        pointList.add(Point(width - mwidth.toInt(), height / 2 + 50))

        pointList.add(Point(mwidth.toInt(), height - mwidth.toInt()))
        pointList.add(Point(width / 2, height - mwidth.toInt()))
        pointList.add(Point(width - mwidth.toInt(), height - mwidth.toInt()))

        isFirstTime = shared!!.getBoolean("isFirstTime", true)

        val array = ArrayList<Int>()
        val size = shared!!.getInt("size", 0)

        for (i in 0 until size) {
            array.add(shared!!.getInt("key$i", 0))
        }

        listPass = array
        isInit = true
    }

    private fun drawPoints(canvas: Canvas) {
        if (isDrawing && endPoint != null) {
            paint.color = Color.BLACK
            paint.strokeWidth = 10f
            paint.style = Paint.Style.STROKE
            for (i in 0..password.size) {
                if (i < password.size - 1) {
                    canvas.drawLine(pointList[password[i]].x.toFloat(),
                        pointList[password[i]].y.toFloat(),
                        pointList[password[i + 1]].x.toFloat(),
                        pointList[password[i + 1]].y.toFloat(),
                        paint)
                }
            }
            canvas.drawLine(startPoint!!.x.toFloat(),
                startPoint!!.y.toFloat(), endPoint!!.x.toFloat(), endPoint!!.y.toFloat(), paint)
            startPoint = endPoint
            endPoint = null
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                for (i in pointList) {
                    val x = (event.x - i.x).toDouble().pow(2.0)
                    val y = (event.y - i.y).toDouble().pow(2.0)
                    if (x + y < 85.0.pow(2.0) && !password.contains(pointList.indexOf(i))) {
                        password.add(pointList.indexOf(i))
                        startPoint = i
                        isDrawing = true
                    }
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (startPoint != null) {
                    for (i in pointList) {
                        val x = (event.x - i.x).toDouble().pow(2.0)
                        val y = (event.y - i.y).toDouble().pow(2.0)
                        if (x + y < 70.0.pow(2.0) && !password.contains(pointList.indexOf(i))) {
                            endPoint = i
                            password.add(pointList.indexOf(i))
                            invalidate()
                        }
                    }
                }
            }
            MotionEvent.ACTION_UP -> {
                startPoint = null
                endPoint = null
                isDrawing = false
                isCheck = true
                if (!isFirstTime)
                    checkPassword()
                else
                    savePassword()
                invalidate()
            }
        }
        return true
    }

    private fun savePassword() {
        isCorrect = if (password.size < 4) {
            password.clear()
            false
        } else {
            listPass.clear()
            listPass.addAll(password)
            shared!!.edit().putInt("size", listPass.size).apply()
            for (i in listPass.indices) {
                shared!!.edit().putInt("key$i", listPass[i]).apply()
            }
            password.clear()
            true
        }
    }

    private fun checkPassword() {
        if (password.size < 4 || password.size != listPass.size) {
            isCorrect = false
            password.clear()
            return
        }
        for (i in listPass.indices) {
            if (password[i] != listPass[i]) {
                isCorrect = false
                password.clear()
                return
            }
        }
        password.clear()
        isCorrect = true
    }


    private fun drawPoint(canvas: Canvas) {
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL

        pointList.forEach { canvas.drawCircle(it.x.toFloat(), it.y.toFloat(), 15f, paint) }

    }
}