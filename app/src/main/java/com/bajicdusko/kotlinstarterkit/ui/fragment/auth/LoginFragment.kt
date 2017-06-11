package com.bajicdusko.kotlinstarterkit.ui.fragment.auth


import android.support.v4.app.Fragment
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.ui.fragment.BaseFragment


/**
 * A simple [Fragment] subclass.
 */
class LoginFragment : BaseFragment() {
    private val  FRAGMENT_NAME = "Login"

    companion object{
        fun newInstance() = LoginFragment()
    }

    override fun getFragmentName(): String = FRAGMENT_NAME

    override fun setTitle() = fragmentChannel?.setTitle(R.string.login)

    override fun dispose() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showHome(){
        fragmentChannel?.showHome()
    }

    override fun getLayoutId(): Int = R.layout.fragment_login
}
