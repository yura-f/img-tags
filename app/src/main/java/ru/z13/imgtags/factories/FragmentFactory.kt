package ru.z13.imgtags.factories

import android.support.v4.app.Fragment
import ru.z13.imgtags.enums.FragmentEnums
import ru.z13.imgtags.ui.fragments.EmptyFragment
import ru.z13.imgtags.ui.fragments.HomeFragment

/**
 * @author Yura F (yura-f.github.io)
 */
class FragmentFactory {
    companion object {
        fun getFragment(screenKey: String, data: Any?): Fragment{

            return createFragmentByTypeModel(screenKey, data)
        }

        private fun createFragmentByTypeModel(screenKey: String, data: Any?): Fragment{
            return when(screenKey){
                FragmentEnums.HOME_FRAGMENT -> HomeFragment.newInstance()
                else -> EmptyFragment.newInstance()
            }
        }
    }
}