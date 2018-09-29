package ru.z13.imgtags.ui.adapters

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import ru.mcgc.app.ui.glide.GlideApp
import ru.z13.imgtags.R
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.mvp.presenters.HomePresenter

/**
 * Android Studio
 *
 * @author Yura Fedorchenko (www.android.z-13.ru)
 */
class ImageAdapter(val context: Context?, val presenter: HomePresenter): RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    private var items: MutableList<ImageData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.image_view_item, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemData: ImageData = items[position]

        holder.tagsText.text = itemData.tags

        GlideApp.with(holder.itemView)
                .load(itemData.path)
                .placeholder(R.color.colorPrimaryDark)
                .into(holder.avatarImage)

        holder.itemView.setOnClickListener { presenter.onClickImage(itemData) }
    }

    fun setList(newItems: List<ImageData>) {
        val diffResult = DiffUtil.calculateDiff(ImagesDataCallback(items, newItems))

        items.clear()
        items.addAll(newItems)

        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val avatarImage: ImageView = itemView.findViewById(R.id.imageView)
        val tagsText: TextView = itemView.findViewById(R.id.tagsText)
    }

    class ImagesDataCallback(private val oldList: List<ImageData>, private val newList: List<ImageData>): DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].path == newList[newItemPosition].path
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].tags == newList[newItemPosition].tags
        }

    }
}