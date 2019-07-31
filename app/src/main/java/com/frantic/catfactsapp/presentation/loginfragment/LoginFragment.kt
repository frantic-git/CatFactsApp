package com.frantic.catfactsapp.presentation.loginfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.frantic.catfactsapp.R
import com.frantic.catfactsapp.presentation.MainActivity
import kotlinx.android.synthetic.main.fragment_login.*
import org.greenrobot.eventbus.EventBus

class LoginFragment : MvpAppCompatFragment(), LoginMvpView {

    @InjectPresenter
    lateinit var presenter: LoginPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        next_button.setOnClickListener { nextButtonOnClick() }
        cancel_button.setOnClickListener { cancelButtonOnClick() }
        login_edit_text.setOnClickListener { loginEditTextOnChanged() }
        password_edit_text.setOnClickListener { passwordEditTextOnChanged() }
    }

    private fun nextButtonOnClick() {
        presenter.nextBtnOnClick(
            login_edit_text.text.toString(),
            password_edit_text.text.toString()
        )
    }

    private fun cancelButtonOnClick() {
        (activity as MainActivity).finish()
    }

    private fun loginEditTextOnChanged(){
        login_text_input.error = null
    }

    private fun passwordEditTextOnChanged(){
        password_text_input.error = null
    }

    override fun refresh() {

    }

    override fun showError(errorString: String) {
        Toast.makeText(activity, errorString, Toast.LENGTH_SHORT).show()
    }

    override fun showLoginError() {
        login_text_input.error = getString(R.string.error_login)
    }

    override fun showPasswordError() {
        password_text_input.error = getString(R.string.error_password)
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