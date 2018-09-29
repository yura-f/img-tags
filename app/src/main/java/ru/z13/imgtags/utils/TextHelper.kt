package ru.z13.imgtags.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class TextHelper {
    companion object {
        fun showKeyboard(view: View?) {
            if (view == null)
                return
            try {
                val inputMethodManager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
            } catch (e: Throwable) {

            }

        }

        fun hideKeyboard(view: View?) {
            if (view == null)
                return
            try {
                val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            } catch (e: Throwable) {

            }
        }

        /**
         * IMPORTANT: Set parent container:
         * android:focusable="true"
         * android:focusableInTouchMode="true"
         *
         * @param view
         */
        fun hideFocusAndKeyboard(view: View?) {
            if (view != null) {
                view.clearFocus()
                hideKeyboard(view)
            }
        }
    }
}