package com.bajicdusko.kotlinstarterkit.presenter.questions

import android.os.Bundle
import com.bajicdusko.kotlinstarterkit.domain.model.SOQuestion
import com.bajicdusko.kotlinstarterkit.presenter.Presenter
import io.reactivex.disposables.CompositeDisposable
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Dusko Bajic on 11.06.17.
 * GitHub @bajicdusko
 */
class QuestionsAdapterPresenter @Inject constructor() : Presenter {

    private var questions: List<SOQuestion?> = ArrayList()
    private var view: View? = null

    override var disposables: CompositeDisposable = CompositeDisposable()

    override fun onSaveInstanceState(outState: Bundle?) {

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {}

    override fun restore() {
        view?.notifyAdapter()
    }

    fun onDataChange(list: List<SOQuestion?>?) {
        if (list != null) {
            questions = list
        }

        view?.notifyAdapter()
    }

    interface View : Presenter.View {
        fun notifyAdapter()
    }

    fun getCount(): Int = questions.size
    fun getQuestion(position: Int) = questions[position]
}