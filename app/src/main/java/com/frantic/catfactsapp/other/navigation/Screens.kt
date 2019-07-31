package com.frantic.catfactsapp.other.navigation

import androidx.fragment.app.Fragment
import com.frantic.catfactsapp.presentation.loginfragment.LoginFragment
import com.frantic.catfactsapp.presentation.catfactfragment.CatFactFragment
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactsListFragment
import com.frantic.catfactsapp.presentation.catfavoritelistfragment.CatFavoriteListFragment

object Screens {

    const val BUNDLE_KEY = "bundleKey"

    enum class FRAGMENTS(fragmentName: String) {
        LOGIN_FRAGMENT("LOGIN_FRAGMENT"),
        CAT_FACTS_LIST_FRAGMENT("CAT_FACTS_LIST_FRAGMENT"),
        CAT_FACT_FRAGMENT("CAT_FACT_FRAGMENT"),
        CAT_FAVORITE_LIST_FRAGMENT("CAT_FAVORITE_LIST_FRAGMENT")
    }

    fun createFragment(fragment: FRAGMENTS): Fragment = when (fragment) {
        Screens.FRAGMENTS.LOGIN_FRAGMENT -> LoginFragment()
        Screens.FRAGMENTS.CAT_FACTS_LIST_FRAGMENT -> CatFactsListFragment()
        Screens.FRAGMENTS.CAT_FACT_FRAGMENT -> CatFactFragment()
        Screens.FRAGMENTS.CAT_FAVORITE_LIST_FRAGMENT -> CatFavoriteListFragment()
    }

}