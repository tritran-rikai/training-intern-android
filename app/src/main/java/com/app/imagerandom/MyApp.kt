package com.app.imagerandom

import android.app.Application
import android.util.Log
import com.orm.SugarContext
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    var TAG: String = "APP"

    override fun onCreate() {
        super.onCreate()
        SugarContext.init(this)
        Log.d(
            TAG,
            "----------------------------------MyApplication.onCreate----------------------------------"
        )

        instance = this
    }

    override fun onTerminate() {
        Log.d(
            TAG,
            "----------------------------------MyApplication.onTerminate----------------------------------"
        )
        super.onTerminate()
        SugarContext.terminate()
    }

    companion object {
        @get:Synchronized
        var instance: MyApp? = null

    }
}
