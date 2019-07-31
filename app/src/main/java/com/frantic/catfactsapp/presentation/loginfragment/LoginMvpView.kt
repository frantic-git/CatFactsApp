package com.frantic.catfactsapp.presentation.loginfragment

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface LoginMvpView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun refresh()

    @StateStrategyType(SkipStrategy::class)
    fun showError(errorString: String)

    @StateStrategyType(SkipStrategy::class)
    fun showLoginError()

    @StateStrategyType(SkipStrategy::class)
    fun showPasswordError()

}