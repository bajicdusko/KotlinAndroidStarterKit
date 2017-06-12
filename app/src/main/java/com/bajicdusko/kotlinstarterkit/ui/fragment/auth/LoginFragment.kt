package com.bajicdusko.kotlinstarterkit.ui.fragment.auth


import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.presenter.login.LoginPresenter
import com.bajicdusko.kotlinstarterkit.ui.fragment.BaseFragment
import com.bajicdusko.kotlinstarterkit.ui.rxTransaction
import com.bajicdusko.kotlinstarterkit.ui.validateNotEmpty
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment(), LoginPresenter.View {
    private val FRAGMENT_NAME = "Login"

    @Inject lateinit var loginPresenter: LoginPresenter

    @BindView(R.id.fragment_login_til_username) lateinit var tilUsername: TextInputLayout
    @BindView(R.id.fragment_login_til_password) lateinit var tilPassword: TextInputLayout
    @BindView(R.id.fragment_login_tet_username) lateinit var tetUsername: TextInputEditText
    @BindView(R.id.fragment_login_tet_password) lateinit var tetPassword: TextInputEditText
    @BindView(R.id.fragment_login_bt_login) lateinit var btLogin: Button

    companion object {
        fun newInstance() = LoginFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injector.inject(this)
        loginPresenter.view = this
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeToLoginClick()
        rxTransaction {
            getTextCountFlowable(tetPassword)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        if (it > 0) {
                            if (!tilPassword.isPasswordVisibilityToggleEnabled) {
                                tilPassword.isPasswordVisibilityToggleEnabled = true
                            }
                            if (!tetPassword.error.isNullOrEmpty()) {
                                tetPassword.error = null
                            }
                        } else {
                            tilPassword.isPasswordVisibilityToggleEnabled = false
                        }
                    }, { handleError(it) })
        }
    }

    private fun subscribeToLoginClick() {
        rxTransaction {
            RxView.clicks(btLogin)
                    .throttleFirst(2, TimeUnit.SECONDS)
                    .filter({ tilUsername.validateNotEmpty() })
                    .filter({ tilPassword.validateNotEmpty() })
                    .subscribe({ loginPresenter.onLogin() }, { throwable ->
                        handleError(throwable)
                        subscribeToLoginClick()
                    })
        }
    }

    private fun getTextCountFlowable(editText: EditText): Flowable<Int> {
        return Flowable.create({
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    it.onNext(s?.length ?: 0)
                }
            })
        }, BackpressureStrategy.DROP)
    }


    override fun restore() {

    }

    override fun getFragmentName(): String = FRAGMENT_NAME

    override fun setTitle() = fragmentChannel?.setTitle(R.string.login)

    override fun dispose() {
        super.dispose()
        loginPresenter.dispose()
    }

    override fun showHome() {
        fragmentChannel?.showHome()
    }

    override fun getLayoutId(): Int = R.layout.fragment_login
}
