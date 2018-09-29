package ru.z13.imgtags.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.z13.imgtags.R

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class EmptyFragment:BaseFragment() {
    companion object {
        fun newInstance():EmptyFragment{

            return EmptyFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_empty, container, false)

        return view
    }
}