package com.bajicdusko.kotlinstarterkit.ui

import android.os.Bundle
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.presenter.HomePresenter
import com.bajicdusko.kotlinstarterkit.ui.fragment.HomeFragmentChannel
import com.bajicdusko.kotlinstarterkit.ui.fragment.auth.LoginFragment
import com.bajicdusko.kotlinstarterkit.ui.questions.QuestionsFragment
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 29.05.17.
 * GitHub @bajicdusko
 */
class HomeActivity : BaseActivity(), HomePresenter.View, HomeFragmentChannel {

    @Inject lateinit var homePresenter: HomePresenter

    override fun getFrameLayoutContainerId(): Int = R.id.activity_home_fl_fragment_container

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun inject() {
        injector.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePresenter.view = this
        homePresenter.init()
    }

    override fun setToolbarTitle(titleId: Int) {
        supportActionBar?.title = getString(titleId)
    }

    override fun showHome() {
        simpleFragmentManager.popUp()
        simpleFragmentManager.replaceFragment(QuestionsFragment.newInstance())
    }

    override fun showLogin() {
        simpleFragmentManager.replaceFragment(LoginFragment.newInstance())
    }

    override fun dispose() {
        homePresenter.dispose()
    }

    override fun restore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

