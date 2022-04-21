package ru.z13.imgtags.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.z13.imgtags.R

/**
 * @author Yura F (yura-f.github.io)
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