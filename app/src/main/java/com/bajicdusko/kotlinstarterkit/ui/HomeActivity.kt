package com.bajicdusko.kotlinstarterkit.ui

import android.os.Bundle
import android.widget.FrameLayout
import butterknife.BindView
import com.bajicdusko.kotlinstarterkit.R
import com.bajicdusko.kotlinstarterkit.presenter.HomePresenter
import com.bajicdusko.kotlinstarterkit.ui.fragment.FragmentChannel
import com.bajicdusko.kotlinstarterkit.ui.fragment.auth.LoginFragment
import com.bajicdusko.kotlinstarterkit.ui.fragment.manager.EasyFragmentManager
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 29.05.17.
 * GitHub @bajicdusko
 */
class HomeActivity : BaseActivity(), HomePresenter.View, FragmentChannel {

    @Inject lateinit var homePresenter: HomePresenter

    @BindView(R.id.activity_home_fl_fragment_container) lateinit var flContainer: FrameLayout

    val easyFragmentManager by lazy { EasyFragmentManager(supportFragmentManager, flContainer) }

    override fun inject() {
        injector.inject(this)
    }

    override fun getLayoutId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homePresenter.view = this
        homePresenter.init()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        easyFragmentManager.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        easyFragmentManager.onRestoreInstanceState(savedInstanceState)
    }

    override fun setTitle(titleId: Int) {
        supportActionBar?.title = getString(titleId)
    }

    override fun showHome() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showLogin() {
        easyFragmentManager.replaceFragment(LoginFragment.newInstance())
    }

    override fun dispose() {
        homePresenter.dispose()
    }

    override fun restore() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBackPressed() {
        if (!easyFragmentManager.onBackPressed()) {
            finish()
        }
    }
}

