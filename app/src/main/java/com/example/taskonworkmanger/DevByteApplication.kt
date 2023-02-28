
package com.example.taskonworkmanger

import android.app.Application
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager

import com.example.taskonworkmanger.work.ItemWorker

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit


class DevByteApplication : AppCompatActivity() {


        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val workRequest = PeriodicWorkRequestBuilder<ItemWorker>(
                1, TimeUnit.MINUTES
            ).build()
            WorkManager.getInstance(applicationContext).enqueue(workRequest)
        }
    }
