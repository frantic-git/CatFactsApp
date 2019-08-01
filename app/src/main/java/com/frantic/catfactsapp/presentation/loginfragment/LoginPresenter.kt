package com.frantic.catfactsapp.presentation.loginfragment

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.domain.DataInteractor
import com.frantic.catfactsapp.other.events.Events
import com.frantic.catfactsapp.other.navigation.Screens
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

@InjectViewState
class LoginPresenter : MvpPresenter<LoginMvpView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.refresh()
    }

    fun nextBtnOnClick(login: String, password: String) {
        var error = false
        if (login.isEmpty()) {
            viewState.showLoginError()
            error = true
        }
        if (password.isEmpty()) {
            viewState.showPasswordError()
            error = true
        }
        if (!error) DataInteractor.checkLoginPassword(login, password)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onCheckLoginPassword(event: Events.OnCheckLoginPasswordReceived) {
        if (event.checked) {
            App.fragmentRouter.replace(Screens.FRAGMENTS.CAT_FACTS_LIST_FRAGMENT, null)
        } else {
            viewState.showPasswordError()
        }
    }

    fun onStart() {
        EventBus.getDefault().register(this)
    }

    fun onStop() {
        EventBus.getDefault().unregister(this)
    }
}