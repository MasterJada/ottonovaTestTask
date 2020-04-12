package dev.jetlaunch.ottonova.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.util.AttributeSet

class RoundImageView: androidx.appcompat.widget.AppCompatImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private val path = Path()

    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            canvas.clipPath(path)
        }
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val radius = h / 2
        path.addCircle(radius.toFloat(), radius.toFloat(), radius.toFloat(), Path.Direction.CW)
    }
}