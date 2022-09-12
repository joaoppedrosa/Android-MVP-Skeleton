package com.jppedrosa.mvpskeleton.ui

import android.content.Context
import com.jppedrosa.mvpskeleton.core.BasePresenter

/**
 * @author João Pedro Pedrosa ([joaopopedrosa@gmail.com](mailto:joaopopedrosa@gmail.com)) on 07/09/2022.
 */
class MainPresenter(
    view: MainView,
    mContext: Context
) : BasePresenter<MainView>(view, mContext) {

    fun onStart() {

    }

    fun onResume() {
        this.view?.startInternetConnectionListener()
    }

    fun onDestroy() {
        this.view?.stopInternetConnectionListener()
    }
}