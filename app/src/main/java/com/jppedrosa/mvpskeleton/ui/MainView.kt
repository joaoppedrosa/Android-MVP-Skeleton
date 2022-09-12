package com.jppedrosa.mvpskeleton.ui

import com.jppedrosa.mvpskeleton.core.BaseView

/**
 * @author João Pedro Pedrosa ([joaopopedrosa@gmail.com](mailto:joaopopedrosa@gmail.com)) on 07/09/2022.
 */
interface MainView : BaseView {

    fun startInternetConnectionListener()

    fun stopInternetConnectionListener()
}