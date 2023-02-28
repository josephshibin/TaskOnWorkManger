

package com.example.taskonworkmanger.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.taskonworkmanger.database.VideosDatabase
import com.example.taskonworkmanger.database.asDomainModel
import com.example.taskonworkmanger.domain.DevByteVideo
import com.example.taskonworkmanger.network.DevByteNetwork
import com.example.taskonworkmanger.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class VideosRepository(private val database: VideosDatabase) {
     //Retrieve data from the database
    val videos: LiveData<List<DevByteVideo>> = Transformations.map(database.videoDao.getVideos()) {
        it.asDomainModel()
    }


    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            Timber.d("refresh videos is called");
            val playlist = DevByteNetwork.devbytes.getPlaylist()
            database.videoDao.insertAll(playlist.asDatabaseModel())
        }
    }
//    suspend fun deleteItem(item:DevByteVideo) {
//      //  withContext(Dispatchers.IO) {
//            database.videoDao.delete(item)
//        //}
//    }

}
