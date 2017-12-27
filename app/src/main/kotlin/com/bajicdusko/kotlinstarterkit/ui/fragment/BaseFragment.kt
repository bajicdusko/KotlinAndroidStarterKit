package com.bajicdusko.kotlinstarterkit.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.bajicdusko.kotlinfragmentmanager.SFMFragment
import com.bajicdusko.kotlinstarterkit.data.getErrorMessage
import com.bajicdusko.kotlinstarterkit.ui.BaseActivity
import com.bajicdusko.kotlinstarterkit.ui.rxDispose
import com.bajicdusko.kotlinstarterkit.ui.toast
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by Dusko Bajic on 07.06.17.
 * GitHub @bajicdusko
 */
abstract class BaseFragment : SFMFragment<HomeFragmentChannel>() {

  protected var isNewInstance: Boolean = true
  var disposables: CompositeDisposable? = null

  protected val injector by lazy { (context as BaseActivity).injector }

  protected abstract fun getLayoutId(): Int

  @SuppressLint("MissingSuperCall")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    isNewInstance = savedInstanceState == null
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    var view = inflater.inflate(getLayoutId(), container, false)
    if (view != null) {
      ButterKnife.bind(this, view)
    }
    return view
  }

  fun handleError(throwable: Throwable?, location: String?) {
    Timber.e(throwable, location)
    toast(throwable?.getErrorMessage())
  }

  fun handleError(throwable: Throwable?) {
    handleError(throwable = throwable, location = null)
  }

  override fun dispose() {
    rxDispose()
  }
}