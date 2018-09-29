package ru.z13.imgtags.ui.widgets

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.BottomSheetDialog
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.WindowManager
import kotlinx.android.synthetic.main.dialog_add_image.*
import ru.mcgc.app.ui.glide.GlideApp
import ru.z13.imgtags.R
import ru.z13.imgtags.utils.TextHelper


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class AddImageBottomDialog(context: Context, private val listener: OnAddImageBottomListener): BottomSheetDialog(context) {
    private var imagePath: String = ""

    init {
        imagePath = ""

        val bottomSheetView = layoutInflater.inflate(R.layout.dialog_add_image, null)
        setContentView(bottomSheetView)

        initBehavior(bottomSheetView)
        initTagsInput()

        imageView.setOnClickListener { listener.onOpenImagePicker() }
        saveBtn.setOnClickListener {
            listener.onSaveImage(imagePath, tagsInputText.text.toString())

            dismiss()
        }

        checkSaveButton()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
    }

    private fun initBehavior(bottomSheetView: View) {
        val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheetView.parent as View)
        bottomSheetBehavior.isHideable = true
        (bottomSheetView.parent as View).setBackgroundColor(ContextCompat.getColor(context, android.R.color.transparent))

        bottomSheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                    TextHelper.hideFocusAndKeyboard(tagsInputText)
                    dismiss()
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun initTagsInput() {
        tagsInputText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                checkSaveButton()
            }
        })
    }

    fun setImagePath(path: String) {
        imagePath = path

        checkSaveButton()

        GlideApp.with(context)
                .load(path)
                .placeholder(R.drawable.placeholder_image)
                .into(imageView)
    }

    private fun checkSaveButton(){
        saveBtn.isEnabled = imagePath.isNotEmpty() && tagsInputText.text.isNotBlank()
    }

    interface OnAddImageBottomListener {
        fun onSaveImage(imagePath: String, tags: String)
        fun onOpenImagePicker()
    }
}