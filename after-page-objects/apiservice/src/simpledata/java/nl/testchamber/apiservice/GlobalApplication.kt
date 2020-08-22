package nl.testchamber.apiservice

import android.app.Application
import android.content.Context


class GlobalApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    companion object {

        var appContext: Context? = null
            private set
    }
}