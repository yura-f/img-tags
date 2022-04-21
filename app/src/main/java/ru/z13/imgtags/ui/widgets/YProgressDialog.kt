package ru.z13.imgtags.ui.widgets

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import ru.z13.imgtags.R


/**
 * @author Yura F (yura-f.github.io)
 */
class YProgressDialog(context: Context) : Dialog(context) {

    init {
        initUI()
    }

    private fun initUI(){
        val view = LayoutInflater.from(context).inflate(R.layout.yprogress_dialog, null)
        setContentView(view)

        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }
}