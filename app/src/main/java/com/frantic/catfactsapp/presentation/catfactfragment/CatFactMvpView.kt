package com.frantic.catfactsapp.presentation.catfactfragment

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.frantic.catfactsapp.data.db.entity.CatFactsEntity

interface CatFactMvpView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun refresh()

    @StateStrategyType(SkipStrategy::class)
    fun showCatFact(catFact:CatFactsEntity)
}