package ru.z13.imgtags.ui.fragments

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import kotlinx.android.synthetic.main.fragment_home.*
import ru.z13.imgtags.App
import ru.z13.imgtags.R
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.mvp.presenters.HomePresenter
import ru.z13.imgtags.mvp.views.HomeView
import ru.z13.imgtags.ui.adapters.ImageAdapter
import ru.z13.imgtags.ui.common.BackButtonListener
import ru.z13.imgtags.ui.widgets.AddImageBottomDialog
import javax.inject.Inject


/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class HomeFragment:BaseFragment(), HomeView, BackButtonListener {
    companion object {
        fun newInstance():HomeFragment{
            return HomeFragment()
        }
    }

    @Inject
    @InjectPresenter
    lateinit var presenter: HomePresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private var adapter: ImageAdapter? = null
    private var addImageDialog: AddImageBottomDialog? = null

    override fun onAttach(context: Context?) {
        App.appComponent.inject(this)

        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchToolbar()
        initImagesGrid()

        addButton.setOnClickListener {
            searchView.setQuery("", false)
            closeSearchToolbar()

            presenter.onClickAddBtn()
        }

        searchButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                circleReveal(searchToolbar, 1, true, true)
            }else {
                searchToolbar.visibility = View.VISIBLE
            }

            searchView.isIconified = false
        }
    }

    private fun initImagesGrid() {
        adapter = ImageAdapter(context, presenter)

        val staggeredGrid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        staggeredGrid.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        recyclerView.layoutManager = staggeredGrid
        recyclerView.adapter = adapter
        recyclerView.setOnTouchListener { view, motionEvent ->
            searchView.clearFocus()
            false
        }
    }

    private fun initSearchToolbar() {
        navigateButton.setOnClickListener {
            if(searchView.query.isNotEmpty()){
                searchView.setQuery("", false)
            }else {
                closeSearchToolbar()
            }
        }

        val searchViewFlowable: Flowable<String> = Flowable.create<String>({ emitter: FlowableEmitter<String> ->
            val listener = object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    callSearch(query)
                    searchView.clearFocus()

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    callSearch(newText)

                    return true
                }

                fun callSearch(s: String?) {
                    s?.run(emitter::onNext)
                }

            }
            emitter.setCancellable {
//                searchView.setOnQueryTextListener(null)
            }
            searchView.setOnQueryTextListener(listener)
        }, BackpressureStrategy.LATEST)

        presenter.setSearchViewFlowable(searchViewFlowable)
    }

    private fun closeSearchToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            circleReveal(searchToolbar, 1, true, false)
        } else {
            searchToolbar.visibility = View.INVISIBLE
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun circleReveal(view: View, posFromRight: Int, containsOverflow: Boolean, isShow: Boolean) {
        var width = view.width

        if (posFromRight > 0) {
            width -= posFromRight * resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) - resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_material) / 2
        }

        if (containsOverflow) {
            width -= resources.getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material)
        }

        val cx = width
        val cy = view.height / 2

        val anim: Animator
        anim = if (isShow) {
            ViewAnimationUtils.createCircularReveal(view, cx, cy, 0f, width.toFloat())
        }else {
            ViewAnimationUtils.createCircularReveal(view, cx, cy, width.toFloat(), 0f)
        }

        anim.duration = 220.toLong()

        anim.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (!isShow) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.INVISIBLE
                }
            }
        })

        if (isShow) {
            view.visibility = View.VISIBLE
        }

        anim.start()
    }

    override fun setImages(list: List<ImageData>) {
        adapter?.setList(list)
    }

    override fun setImagePath(path: String) {
        addImageDialog?.setImagePath(path)
    }

    override fun showAddDialog() {
        if (addImageDialog == null) {
            context?.let {
                addImageDialog = AddImageBottomDialog(it, object : AddImageBottomDialog.OnAddImageBottomListener {
                    override fun onOpenImagePicker() {
                        presenter.showImagePicker()
                    }

                    override fun onSaveImage(imagePath: String, tags: String){
                        presenter.saveImage(imagePath, tags)
                    }

                })

                addImageDialog?.setOnDismissListener { dialogInterface ->
                    addImageDialog?.setOnDismissListener(null)
                    addImageDialog = null
                }

                addImageDialog?.show()
            }
        }
    }


    override fun onBackPressed(): Boolean {
        presenter.onBackPressed()

        return true
    }
}