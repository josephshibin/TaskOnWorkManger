

package com.example.taskonworkmanger.domain

import com.example.taskonworkmanger.util.smartTruncate


data class DevByteVideo(val title: String,
                        val description: String,
                        val url: String,
                        val updated: String,
                        val thumbnail: String) {


    val shortDescription: String
        get() = description.smartTruncate(200)
}