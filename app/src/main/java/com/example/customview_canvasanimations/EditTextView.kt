package com.example.customview_canvasanimations

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import yuku.ambilwarna.AmbilWarnaDialog
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener

class EditTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private var editText: EditText
    private var colorView: View
    private var defaultColor = R.color.black

    init {
        val typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.EditTextView)
        var color = typedArray.getString(R.styleable.EditTextView_editTextColor).toString()
        typedArray.recycle()
        if (color == "null") {
            color = "#000000"
        }

        val inflater =
            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_editext, this, true)
        editText = findViewById(R.id.editText)
        colorView = findViewById(R.id.textColor)

        colorView.setBackgroundColor(Color.parseColor(color))
        editText.setTextColor(Color.parseColor(color))

        colorView.setOnClickListener {
            OpenColorPickerDialog(false)
        }


    }

    private fun OpenColorPickerDialog(AlphaSupport: Boolean) {
        val ambilWarnaDialog = AmbilWarnaDialog(context,
            defaultColor,
            AlphaSupport,
            object : OnAmbilWarnaListener {
                override fun onOk(ambilWarnaDialog: AmbilWarnaDialog, color: Int) {
                    defaultColor = color
                    colorView.setBackgroundColor(color)
                    editText.setTextColor(color)
                }

                override fun onCancel(ambilWarnaDialog: AmbilWarnaDialog) {
                }
            })
        ambilWarnaDialog.show()
    }
}