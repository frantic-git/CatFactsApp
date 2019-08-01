package com.frantic.catfactsapp.presentation.catfactfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.frantic.catfactsapp.domain.DataInteractor
import com.frantic.catfactsapp.other.events.Events
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class CatFactPresenter : MvpPresenter<CatFactMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.refresh()
    }

    fun loadCatFact(id: String) {
        DataInteractor.getCatFact(id)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGetCatFactsList(event: Events.OnGetCatFactReceived) {
        event.catFact
        viewState.showCatFact(event.catFact)
    }

    fun onStart() {
        EventBus.getDefault().register(this)
    }

    fun onStop() {
        EventBus.getDefault().unregister(this)
    }

}