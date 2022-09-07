package com.jppedrosa.mvpskeleton.core

import android.content.Context
import androidx.annotation.NonNull

/**
 * @author João Pedro Pedrosa (<a href="mailto:joaopopedrosa@gmail.com">joaopopedrosa@gmail.com</a>) on 07/09/2022.
 */
abstract class BasePresenter<View : BaseView>
protected constructor(
    @param:NonNull protected var view: View?,
    @param:NonNull protected var mContext: Context
) {

    /**
     * Destroy view.
     */
    internal fun destroyView() {
        view = null
    }
}