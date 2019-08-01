package com.frantic.catfactsapp.other.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class Router {

    private lateinit var fragmentManager: FragmentManager
    private var containerId: Int = 0
    private lateinit var finishActivity: () -> Unit

    fun initRouter(fragmentManager: FragmentManager, containerId: Int, finishActivity: () -> Unit) {
        this.fragmentManager = fragmentManager
        this.containerId = containerId
        this.finishActivity = finishActivity
    }

    fun navigateTo(fragment: Screens.FRAGMENTS, data: Parcelable? = null) {
        fragmentManager.beginTransaction()
            .add(containerId, Screens.createFragment(fragment).addBundle(data))
            .addToBackStack(null)
            .commit()
    }

    fun replace(fragment: Screens.FRAGMENTS, data: Bundle? = null) {

        val curFragment = Screens.createFragment(fragment)
        if (data != null) curFragment.arguments = data

        fragmentManager.beginTransaction()
            .replace(containerId, curFragment)
            .addToBackStack(null)
            .commit()
    }

    fun back() {
        if (fragmentManager.backStackEntryCount == 1) {
            finishActivity.invoke()
        } else {
            fragmentManager.popBackStack()
        }
    }

    private fun Fragment.addBundle(data: Parcelable? = null): Fragment {
        data ?: return this
        val bundle = Bundle()
        bundle.putParcelable(Screens.BUNDLE_KEY, data)
        this.arguments = bundle
        return this
    }
}