package ru.z13.imgtags.data

import android.content.Context
import io.reactivex.Completable
import ru.z13.imgtags.data.datasource.RoomDataSource
import ru.z13.imgtags.data.entity.database.ImageData
import ru.z13.imgtags.data.entity.database.ImageTagJoinData
import ru.z13.imgtags.data.entity.database.TagData
import ru.z13.imgtags.domain.DomainEvents
import ru.z13.imgtags.domain.MutableDomainState

/**
 * @author Yura F (yura-f.github.io)
 */
class AppRepository(private val context: Context,
                    private val roomDataSource: RoomDataSource,
                    private val domainState: MutableDomainState,
                    private val domainEvents: DomainEvents): Repository {
    init {
    }

    override fun loadStartLocalData(): Completable {
        return Completable.fromAction {
            domainState.setImages(roomDataSource.getAllImageData())
        }
    }

    override fun setSelectedImagePath(path: String?): Completable {
        return Completable.fromAction {
            if(path != null) {
                domainState.setSelectedImagePath(path)
            }
        }
    }

    override fun saveImage(imageData: ImageData?): Completable {
        return Completable.fromAction {
            if(imageData != null) {
                val tagsList = getTagsList(imageData.tags)

                if(tagsList.isNotEmpty()) {
                    imageData.tags = tagsList.joinToString(limit = 7)
                    roomDataSource.insertImageData(imageData)

                    for (tag in tagsList) {
                        if (tag.isNotEmpty()) {
                            val tagData = TagData(tag)
                            roomDataSource.insertTagData(tagData)
                            roomDataSource.insertImageTagJoinData(ImageTagJoinData(imageId = imageData.path, tagId = tagData.name))
                        }
                    }

                    domainState.setImages(roomDataSource.getAllImageData())
                }
            }
        }
    }

    override fun getAllImagesByTags(tagsStr: String?): Completable {
        return Completable.fromAction {
            if (tagsStr != null) {
                val tagsList = getTagsList(tagsStr)
                if(tagsList.isNotEmpty()) {
                    val images: MutableList<ImageData> = mutableListOf()
                    for (tag in tagsList){
                        images.addAll(roomDataSource.getAllImageDataByTag(tag))
                    }

                    //remove duplicates when the image has some tags
                    domainState.setImages(images.distinct())
                }else{
                    domainState.setImages(roomDataSource.getAllImageData())
                }
            }else{
                domainState.setImages(roomDataSource.getAllImageData())
            }
        }
    }

    private fun getTagsList(str: String): List<String>{
        return str.split("\\s*,[,\\s]*".toRegex()).filter { it.isNotEmpty() }
    }

}