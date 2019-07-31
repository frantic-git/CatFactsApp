package com.frantic.catfactsapp.data.repository

import com.frantic.catfactsapp.App
import com.frantic.catfactsapp.data.db.entity.CatFactsEntity
import com.frantic.catfactsapp.data.db.entity.PreferencesEntity
import com.frantic.catfactsapp.data.db.entity.UsersEntity
import com.frantic.catfactsapp.data.net.retrofit.CatFact
import com.frantic.catfactsapp.other.events.Events
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import org.greenrobot.eventbus.EventBus
import java.lang.Exception

object CatFactsRepository {

    fun getUser(login: String): UsersEntity? {
        val usersList = App.catFactsDataBase?.catFactsDao()?.getUser(login)
        usersList ?: return null
        if (usersList.isNotEmpty()) return usersList[usersList.size - 1]
        return null
    }

    fun insertUser(login: String, password: String): Long? {
        return App.catFactsDataBase?.catFactsDao()?.insertUser(UsersEntity(null, login, password))
    }

    fun updateCatFacts(catFactList: List<CatFact>) {
        for (catFact in catFactList) {
            try {
                App.catFactsDataBase
                    ?.catFactsDao()
                    ?.insertCatFact(
                        CatFactsEntity(
                            catFact._id,
                            catFact.used,
                            catFact.source,
                            catFact.type,
                            catFact.deleted,
                            catFact.__v,
                            catFact.text,
                            catFact.updatedAt,
                            catFact.createdAt
                        )
                    )
            } catch (e: Exception) {
                EventBus.getDefault().post(Events.OnDataBaseExceptionReceived(e))
            }
        }
    }

    fun getAllCatFacts(): MutableList<CatFactItem>? {
        val catFactItems = mutableListOf<CatFactItem>()
        if (App.currentUserId != null) {
            val catFactList = App.catFactsDataBase
                ?.catFactsDao()
                ?.getAllCatFacts(App.currentUserId!!)
            if (catFactList != null) {
                for (catFact in catFactList) {
                    catFactItems.add(catFact)
                }
            }
        }
        return catFactItems
    }

    fun getFavoriteCatFacts(): MutableList<CatFactItem>? {
        val catFactItems = mutableListOf<CatFactItem>()

        if (App.currentUserId != null) {
            val catFavoriteList = App.catFactsDataBase
                ?.catFactsDao()
                ?.getFavoriteCatFacts(App.currentUserId!!)
            if (catFavoriteList != null) return catFavoriteList
        }
        return catFactItems
    }

    fun getCatFact(id: String): CatFactsEntity? {
        val catFactsList = App.catFactsDataBase?.catFactsDao()?.getCatFact(id)
        catFactsList ?: return null
        if (catFactsList.isNotEmpty()) return catFactsList[catFactsList.size - 1]
        return null
    }

    fun updatePreference(id: String, preference: Boolean): Boolean {
        try {
            if (preference) {
                App.catFactsDataBase
                    ?.catFactsDao()
                    ?.deletePreference(App.currentUserId!!, id)
            } else {
                App.catFactsDataBase
                    ?.catFactsDao()
                    ?.insertPreference(PreferencesEntity(App.currentUserId!!, id))
            }
            return true
        } catch (e: Exception) {
            EventBus.getDefault().post(Events.OnDataBaseExceptionReceived(e))
        }
        return false
    }
}