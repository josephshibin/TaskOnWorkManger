//
//package com.example.taskonworkmanger
//
//import android.app.Application
//import android.os.Build
//import androidx.constraintlayout.widget.Constraints
//import androidx.work.ExistingPeriodicWorkPolicy
//import androidx.work.NetworkType
//import androidx.work.PeriodicWorkRequestBuilder
//import androidx.work.WorkManager
//
//import com.example.taskonworkmanger.work.ItemWorker
//
//import kotlinx.coroutines.CoroutineScope
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//import timber.log.Timber
//import java.util.concurrent.TimeUnit
//
//
//class DevByteApplication : Application() {
//
//    private val applicationScope = CoroutineScope(Dispatchers.Default)
//
//
//    override fun onCreate() {
//        super.onCreate()
//        delayedInit()
//    }
//
//    private fun delayedInit() {
//        applicationScope.launch {
//            Timber.plant(Timber.DebugTree())
//            setupRecurringWork()
//        }
//    }
//
//
//    private fun setupRecurringWork() {
//
//        val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.UNMETERED)
//                .setRequiresCharging(true)
//                .setRequiresBatteryNotLow(true)
//                .apply {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        setRequiresDeviceIdle(true)
//                    }
//                }
//                .build()
//
//        val repeatingRequest = PeriodicWorkRequestBuilder<ItemWorker>(1, TimeUnit.MINUTES)
//                .setConstraints(constraints)
//                .build()
//
//        Timber.d("WorkManager: Periodic Work request for sync is scheduled")
//        WorkManager.getInstance().enqueueUniquePeriodicWork(
//                ItemWorker.WORK_NAME,
//                ExistingPeriodicWorkPolicy.KEEP,
//                repeatingRequest)
//    }
//}
