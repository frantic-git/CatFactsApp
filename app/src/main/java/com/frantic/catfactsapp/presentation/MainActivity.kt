package com.frantic.catfactsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.other.navigation.Router
import com.frantic.catfactsapp.other.navigation.Screens

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.fragmentRouter.initRouter(
            supportFragmentManager,
            R.id.container,
            ::finishActivity
        )

        if (savedInstanceState == null) {
            App.fragmentRouter.navigateTo(Screens.FRAGMENTS.LOGIN_FRAGMENT)
        }
    }

    override fun onBackPressed() {
        App.fragmentRouter.back()
    }

    private fun finishActivity() {
        finish()
    }
}
