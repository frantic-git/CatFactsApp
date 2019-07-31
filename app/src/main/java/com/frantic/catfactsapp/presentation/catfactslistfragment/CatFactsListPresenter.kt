package com.frantic.catfactsapp.presentation.catfactslistfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.frantic.catfactsapp.domain.DataInteractor
import com.frantic.catfactsapp.other.events.Events
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class CatFactsListPresenter : MvpPresenter<CatFactsListMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.refresh()
    }

    fun getCatFactsList(){
        viewState.onStartLoading()
        DataInteractor.getCatFactsList()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetCatFactsList(event : Events.OnGetCatFactsListReceived){
        viewState.setItemList(event.catFactsListReceived)
        viewState.onFinishLoading()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun btnFavoriteOnClick(event : Events.OnBtnFavoriteClickReceived){
        DataInteractor.updatePreference(event.adapterPositon, event.id, event.preference)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onUpdatePreference(event: Events.OnUpdatePreferenceReceived){
        viewState.updatePreference(event.adapterPositon, event.preference)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onDataBaseExceptionReceived(event: Events.OnDataBaseExceptionReceived){
        viewState.showDataBaseException(event.e)
    }

    fun onStart(){
        EventBus.getDefault().register(this)
    }

    fun onStop(){
        EventBus.getDefault().unregister(this)
    }

}