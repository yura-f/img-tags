package ru.z13.imgtags.ui.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.arellomobile.mvp.presenter.InjectPresenter
import ru.z13.imgtags.ui.widgets.YProgressDialog
import ru.z13.imgtags.ui.common.BackButtonListener
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.z13.imgtags.App
import ru.z13.imgtags.R
import ru.z13.imgtags.mvp.presenters.MainActivityPresenter
import ru.z13.imgtags.mvp.views.MainActivityView
import ru.z13.imgtags.subnavigation.MainScreenNavigatorFactory
import ru.z13.imgtags.subnavigation.NavigatorCallback
import ru.z13.imgtags.utils.FilesUtils
import ru.z13.imgtags.utils.YToast
import javax.inject.Inject


class MainActivity : BaseActivity(), MainActivityView {
    companion object {
        private val PERMISSIONS_REQUIRED = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

        const val RC_PERMISSIONS = 101
        const val RC_SELECT_IMAGE = 103
    }

    @InjectPresenter
    lateinit var presenter: MainActivityPresenter

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private var progressDialog: YProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            presenter.onFirstStart()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(getMainScreenNavigator())
        presenter.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
        presenter.onPause()
    }

    override fun showProgress(isShow: Boolean) {
        if(isShow) {
            val isShowNow: Boolean = progressDialog?.isShowing ?: false
            if (!isShowNow) {
                progressDialog = YProgressDialog(this)
                progressDialog?.setCancelable(false)
                progressDialog?.show()
            }
        }else{
            progressDialog?.cancel()
        }
    }

    override fun showImagePicker() {
        if (!arePermissionGranted()) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_REQUIRED, RC_PERMISSIONS)
        }else{
            openGallery()
        }
    }

    private fun arePermissionGranted(): Boolean {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true
        }
        return false
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"

        startActivityForResult(galleryIntent, RC_SELECT_IMAGE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            RC_PERMISSIONS -> {
                var allGranted = false
                for (i in 0 until grantResults.size) {
                    if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                        allGranted = true
                    } else {
                        allGranted = false
                        break
                    }
                }

                if(allGranted){
                    openGallery()
                }
            }
            else -> super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            RC_SELECT_IMAGE -> {
                if (resultCode == Activity.RESULT_OK) {
                    /**
                     * The photo from GALLERY
                     */
                    if (data != null) {
                        val uri = data.data
                        val filePath = FilesUtils.getRealPathFromUri(this, uri)

                        if(filePath != null){
                            presenter.selectedImageItem(filePath)
                        }
                    }
                }
                else if(resultCode == Activity.RESULT_CANCELED) {
                    // User Cancelled the action
                }
            }
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun getMainScreenNavigator(): Navigator {
        return MainScreenNavigatorFactory.createNavigator(
                supportFragmentManager,
                R.id.main_fragment_container,
                object : NavigatorCallback {
                    override fun showSystemMessage(message: String) {
                        YToast.showText(this@MainActivity, message, Toast.LENGTH_SHORT)
                    }

                    override fun exit() {
                        finish()
                    }
                }
        )
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container)
        if (fragment != null
                && fragment is BackButtonListener
                && (fragment as BackButtonListener).onBackPressed()) {
            return
        } else {
            super.onBackPressed()
        }
    }
}
