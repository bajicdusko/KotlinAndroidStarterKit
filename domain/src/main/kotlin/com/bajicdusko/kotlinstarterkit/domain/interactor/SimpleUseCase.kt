package com.bajicdusko.kotlinstarterkit.domain.interactor

import io.reactivex.Single

/**
 * Created by Dusko Bajic on 09.06.17.
 * GitHub @bajicdusko
 */
interface SimpleUseCase<M> {

    fun execute(): Single<M>
}