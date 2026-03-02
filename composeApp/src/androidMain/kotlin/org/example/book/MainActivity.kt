package org.example.book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import org.example.book.di.appModule
import org.example.book.ui.navigation.AppNavHost
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Start Koin
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule) //dependency def
        }

        setContent { //compose UI starts
            Text("App Working")
            AppNavHost()
        }
    }
}
