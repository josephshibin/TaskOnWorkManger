

package com.example.taskonworkmanger.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.taskonworkmanger.domain.DevByteVideo



@Entity
data class DatabaseVideo(

        val url: String,
        val updated: String,
        val title: String,
        val description: String,
        val thumbnail: String){

        @PrimaryKey(autoGenerate = true)
        var id: Int = 0

}


fun List<DatabaseVideo>.asDomainModel(): List<DevByteVideo> {
        return map {
                DevByteVideo(
                        url = it.url,
                        title = it.title,
                        description = it.description,
                        updated = it.updated,
                        thumbnail = it.thumbnail)
        }
}
