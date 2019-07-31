package com.frantic.catfactsapp.other.events

import com.frantic.catfactsapp.data.db.entity.CatFactsEntity
import com.frantic.catfactsapp.presentation.catfactslistfragment.CatFactItem
import java.lang.Exception

sealed class Events {
    class OnCheckLoginPasswordReceived(val checked: Boolean) : Events()
    class OnGetCatFactsListReceived(val catFactsListReceived: MutableList<CatFactItem>) : Events()
    class OnGetCatFactReceived(val catFact: CatFactsEntity) : Events()
    class OnBtnFavoriteClickReceived(val id: String, val preference: Boolean) : Events()
    class OnUpdatePreferenceReceived(val id: String, val preference: Boolean) : Events()
    class OnDataBaseExceptionReceived(val e: Exception) : Events()
}