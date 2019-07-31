package com.frantic.catfactsapp.presentation.catfactfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.data.db.entity.CatFactsEntity
import kotlinx.android.synthetic.main.fragment_catfact.*

class CatFactFragment : MvpAppCompatFragment(), CatFactMvpView {

    @InjectPresenter
    lateinit var presenter: CatFactPresenter

    private lateinit var id: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_catfact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments!!.getString("catFactId")!!
        presenter.loadCatFact(id)
    }

    override fun refresh() {
    }

    override fun showCatFact(catFact: CatFactsEntity) {
        tvId.text = id
        tvUsed.text = catFact.used.toString()
        tvSource.text = catFact.source
        tvType.text = catFact.type
        tvDeleted.text = catFact.deleted.toString()
        tvV.text = catFact.v.toString()
        tvText.text = catFact.text
        tvCreatedAt.text = catFact.createDate
        tvUpdatedAt.text = catFact.updateDate
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}