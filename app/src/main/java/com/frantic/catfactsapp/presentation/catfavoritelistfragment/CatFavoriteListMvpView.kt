package com.frantic.catfactsapp.presentation.catfavoritelistfragment

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import java.lang.Exception

interface CatFavoriteListMvpView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun refresh()

    @StateStrategyType(SkipStrategy::class)
    fun setItemList(itemsList: MutableList<CatFactItem>)

    @StateStrategyType(SkipStrategy::class)
    fun onStartLoading()

    @StateStrategyType(SkipStrategy::class)
    fun onFinishLoading()

    @StateStrategyType(SkipStrategy::class)
    fun showDataBaseException(e: Exception)

}