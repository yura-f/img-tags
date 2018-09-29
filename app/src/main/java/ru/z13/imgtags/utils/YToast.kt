package ru.z13.imgtags.utils

import android.content.Context
import android.content.res.Resources
import android.support.annotation.StringRes
import android.widget.Toast
import ru.z13.imgtags.BuildConfig

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class YToast {
    companion object {
        fun showText(context: Context, text: CharSequence, duration: Int){
            if (BuildConfig.DEBUG) {
                Toast.makeText(context, text, duration).show()
            }
        }

        fun showText(context: Context, @StringRes resId: Int, duration: Int){
            if (BuildConfig.DEBUG) {
                try {
                    Toast.makeText(context, resId, duration).show()
                }catch (e: Resources.NotFoundException){
                    e.printStackTrace()
                }
            }
        }
    }
}