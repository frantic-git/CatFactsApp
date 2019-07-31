package com.frantic.catfactsapp.presentation.catfavoritelistfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.frantic.catfactsapp.domain.DataInteractor
import com.frantic.catfactsapp.other.events.Events
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class CatFavoriteListPresenter : MvpPresenter<CatFavoriteListMvpView>(){

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.refresh()
    }

    fun getCatFavoriteList(){
        viewState.onStartLoading()
        DataInteractor.getCatFavoriteList()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetCatFavoriteList(event : Events.OnGetCatFavoriteListReceived){
        viewState.setItemList(event.catFavoriteList)
        viewState.onFinishLoading()
    }

    fun onStart(){
        EventBus.getDefault().register(this)
    }

    fun onStop(){
        EventBus.getDefault().unregister(this)
    }

}