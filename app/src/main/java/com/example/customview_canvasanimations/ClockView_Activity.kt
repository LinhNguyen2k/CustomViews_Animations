package com.example.customview_canvasanimations

import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_clock_view.*
import java.util.*

class ClockView_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clock_view)
        supportActionBar?.hide()
        btn_setTime.setOnClickListener {
            var hour = 0
            var minute = 0
            val timePicker = TimePickerDialog(
                this,
                TimePickerDialog.THEME_DEVICE_DEFAULT_DARK, { _, h, m ->
                    val calendar = Calendar.getInstance()
                    hour = h - calendar.get(Calendar.HOUR_OF_DAY)
                    minute = m - calendar.get(Calendar.MINUTE)
                    view_ClockView.setTime(hour, minute)
                }, 12, 0, false
            )
            timePicker.updateTime(hour, minute)
            timePicker.show()
        }
    }
}