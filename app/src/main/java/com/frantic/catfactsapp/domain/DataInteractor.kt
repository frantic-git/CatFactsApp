package com.frantic.catfactsapp.domain

import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.data.net.retrofit.RetrofitRepository
import com.frantic.catfactsapp.data.repository.CatFactsRepository
import com.frantic.catfactsapp.other.events.Events
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactsListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.greenrobot.eventbus.EventBus

object DataInteractor {

    fun checkLoginPassword(login: String, password: String) {
        GlobalScope.launch(Dispatchers.IO) {

            val user = CatFactsRepository.getUser(login)

            if (user == null) {
                val userId = CatFactsRepository.insertUser(login, password)
                if (userId == null) {
                    EventBus.getDefault().post(Events.OnCheckLoginPasswordReceived(false))
                } else {
                    App.currentUserId = userId
                    EventBus.getDefault().post(Events.OnCheckLoginPasswordReceived(true))
                }
            } else {
                if (user.password.equals(password)) {
                    App.currentUserId = user.id
                    EventBus.getDefault().post(Events.OnCheckLoginPasswordReceived(true))
                } else {
                    EventBus.getDefault().post(Events.OnCheckLoginPasswordReceived(false))
                }
            }
        }
    }

    fun getCatFactsList() {
        GlobalScope.launch(Dispatchers.IO) {
            val catFactList = RetrofitRepository.getCatFactList()
            if (catFactList != null) {
                CatFactsRepository.updateCatFacts(catFactList)
            }

            val catFactItemList = CatFactsRepository.getAllCatFacts()
            if (catFactItemList != null) {
                EventBus.getDefault().post(Events.OnGetCatFactsListReceived(catFactItemList))
            }
        }
    }

    fun getCatFavoriteList() {
        GlobalScope.launch(Dispatchers.IO) {
            val catFavoriteList = CatFactsRepository.getFavoriteCatFacts()
            if (catFavoriteList != null) {
                EventBus.getDefault().post(Events.OnGetCatFavoriteListReceived(catFavoriteList))
            }
        }
    }

    fun getCatFact(id: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val catFact = CatFactsRepository.getCatFact(id)
            if (catFact != null) {
                EventBus.getDefault().post(Events.OnGetCatFactReceived(catFact))
            }
        }
    }

    fun updatePreference(adapterPosition: Int, id: String, preference: Boolean) {
        GlobalScope.launch(Dispatchers.IO) {
            val success = CatFactsRepository.updatePreference(id, preference)
            if (success) {
                EventBus
                    .getDefault()
                    .post(Events.OnUpdatePreferenceReceived(adapterPosition, !preference))
            }
        }
    }

}